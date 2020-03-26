package util.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.net.URI;

public class HDFSDemo {
    private HDFSDemo() {}

    public static void main(String[] args) throws Exception {
        FileSystem fileSystem = FileSystem.get(new URI("util.hdfs://localhost:9000"), new Configuration(), "root");
        Path path = new Path("/test");
        Boolean flag = fileSystem.mkdirs(path);
        System.out.println(flag);
        flag = fileSystem.delete(path, true);
        System.out.println(flag);
    }
}
