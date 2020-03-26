package util.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import util.Configs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HbaseUtil {

    private static Admin admin;

    private HbaseUtil() {}

    public static void main(String[] args) {
        try {
            createTable("items", new String[]{"counts"});
            insertData("items", "counts", "test", "item1", "1");
            System.out.println("--------------------获取原始数据-----------------------");
            getNoDealData("items");
            System.out.println("--------------------根据rowKey查询---------------------");
            String result = getDataByRowKey("items", "item1");
            System.out.println(result);
            System.out.println("--------------------获取指定单条数据-------------------");
            String user_phone = getCellData("items", "item1", "counts", "test");
            System.out.println(user_phone);
            List<String> list2 = getAllData("items");
            System.out.println("--------------------插入测试数据后---------------------");
            for (String string : list2) {
                System.out.println(string);
            }
            deleteByRowKey("items", "item1");
            List<String> list3 = getAllData("items");
            System.out.println("--------------------删除测试数据后---------------------");
            for (String string : list3) {
                System.out.println(string);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //连接集群
    public static Connection initHbase() throws IOException {
        Configuration configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.property.clientPort", "9000");
        configuration.set("hbase.zookeeper.quorum", Configs.HBASE_QUORUM);
        //集群配置↓
        configuration.set("util.hbase.master", Configs.HBASE_MASTER);
        Connection connection = ConnectionFactory.createConnection(configuration);
        return connection;
    }

    //创建表
    public static void createTable(String name, String[] families) throws IOException {
        TableName tableName = TableName.valueOf(name);
        admin = initHbase().getAdmin();
        if (admin.tableExists(tableName)) {
            System.out.println("表已存在！");
        } else {
            System.out.println("即将创建表 " + name);
            /*
            HTableDescriptor hTableDescriptor = new HTableDescriptor(tableName);
            for(String col: families) {
                HColumnDescriptor hColumnDescriptor = new HColumnDescriptor(col);
                hTableDescriptor.addFamily(hColumnDescriptor);
            }
            admin.createTable(hTableDescriptor);
            */

            TableDescriptorBuilder tableDescriptorBuilder = TableDescriptorBuilder.newBuilder(tableName);
            ArrayList<ColumnFamilyDescriptor> arrayList = new ArrayList<>();
            for (String col : families) {
                ColumnFamilyDescriptorBuilder columnFamilyDescriptorBuilder =
                        ColumnFamilyDescriptorBuilder.newBuilder(Bytes.toBytes(col));
                arrayList.add(columnFamilyDescriptorBuilder.build());
            }
            tableDescriptorBuilder = tableDescriptorBuilder.setColumnFamilies(arrayList);
            TableDescriptor tableDescriptor = tableDescriptorBuilder.build();
            admin.createTable(tableDescriptor);

            admin.close();
        }
    }

    //插入数据
    public static void insertData(String tableName, String family, String qualifier, String rowKey, String value) throws IOException {
        TableName tablename = TableName.valueOf(tableName);
        Put put = new Put(rowKey.getBytes());
        //参数：1.列族名  2.列名  3.值
        put.addColumn(family.getBytes(), qualifier.getBytes(), value.getBytes());
        Table table = initHbase().getTable(tablename);
        table.put(put);
    }

    //获取原始数据
    public static void getNoDealData(String tableName) {
        try {
            Table table = initHbase().getTable(TableName.valueOf(tableName));
            Scan scan = new Scan();
            ResultScanner resutScanner = table.getScanner(scan);
            for (Result result : resutScanner) {
                System.out.println("scan:  " + result);
            }
            table.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //根据rowKey进行查询
    public static String getDataByRowKey(String tableName, String rowKey) throws IOException {
        Table table = initHbase().getTable(TableName.valueOf(tableName));
        Get get = new Get(rowKey.getBytes());
        StringBuilder stringBuilder = new StringBuilder();
        //先判断是否有此条数据
        if (!get.isCheckExistenceOnly()) {
            Result result = table.get(get);
            for (Cell cell : result.rawCells()) {
                String colName = Bytes.toString(cell.getQualifierArray(), cell.getQualifierOffset(), cell.getQualifierLength());
                String value = Bytes.toString(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength());
                stringBuilder.append("column: " + colName + " value: " + value + "\r\n");
            }
        }
        table.close();
        return stringBuilder.toString();
    }

    //查询指定单cell内容
    public static String getCellData(String tableName, String rowKey, String family, String qualifier) {
        try {
            Table table = initHbase().getTable(TableName.valueOf(tableName));
            String result = "";
            Get get = new Get(rowKey.getBytes());
            if (!get.isCheckExistenceOnly()) {
                get.addColumn(Bytes.toBytes(family), Bytes.toBytes(qualifier));
                Result res = table.get(get);
                byte[] resByte = res.getValue(Bytes.toBytes(family), Bytes.toBytes(qualifier));
                result = Bytes.toString(resByte);
            } else {
                result = "查询结果不存在";
            }
            table.close();
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "出现异常";
    }

    //查询指定表名中所有的数据
    public static List<String> getAllData(String tableName) {
        Table table = null;
        List<String> list = new ArrayList<String>();
        try {
            table = initHbase().getTable(TableName.valueOf(tableName));
            ResultScanner results = table.getScanner(new Scan());
            StringBuilder stringBuilder = null;
            for (Result result : results) {
                String id = new String(result.getRow());
                System.out.println("用户名:" + new String(result.getRow()));
                stringBuilder = new StringBuilder();
                for (Cell cell : result.rawCells()) {
                    String row = Bytes.toString(cell.getRowArray(), cell.getRowOffset(), cell.getRowLength());
                    //String family =  Bytes.toString(cell.getFamilyArray(),cell.getFamilyOffset(),cell.getFamilyLength());
                    String colName = Bytes.toString(cell.getQualifierArray(), cell.getQualifierOffset(), cell.getQualifierLength());
                    String value = Bytes.toString(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength());
                    stringBuilder.append("row: " + row + " column: " + colName + " value: " + value + "\r\n");
                }
                list.add(stringBuilder.toString());
            }
            table.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    //删除指定cell数据
    public static void deleteByRowKey(String tableName, String rowKey) throws IOException {
        Table table = initHbase().getTable(TableName.valueOf(tableName));
        Delete delete = new Delete(Bytes.toBytes(rowKey));
        //删除指定列
        //delete.addColumns(Bytes.toBytes("contact"), Bytes.toBytes("email"));
        table.delete(delete);
        table.close();
    }

    //删除表
    public static void deleteTable(String tableName) {
        try {
            TableName tablename = TableName.valueOf(tableName);
            admin = initHbase().getAdmin();
            admin.disableTable(tablename);
            admin.deleteTable(tablename);
            admin.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
