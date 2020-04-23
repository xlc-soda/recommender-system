package recommendation.mf;

import util.calculation.Matrix;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class MatrixFactorization {

    private Matrix V;

    private Matrix W, H;

    private int row, column;

    private int base;

    private int round = 100;

    // TODO: rewrite this method
    public void loadData(String filePath) throws IOException {
        File f = new File(filePath);
        FileInputStream fileInputStream = new FileInputStream(f);
        // 构建FileInputStream对象
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
        // 构建InputStreamReader对象
        StringBuffer stringBuffer = new StringBuffer();
        while(inputStreamReader.ready()) {
            stringBuffer.append((char) inputStreamReader.read());
        }
        inputStreamReader.close();
        fileInputStream.close();
        // 将读入的数据流转换为字符串
        String sb1 = stringBuffer.toString();
        // 按行将字符串分割,计算二维数组行数
        String [] a = sb1.split("\n");
        row = a.length;
        System.out.println("二维数组行数为:" + row);
        // 计算二维数组列数
        String [] a0 = a[0].split("\t");
        column = a0.length;
        System.out.println("二维数组列数为:" + column);

        V = new Matrix(row, column);

        for (int i = 0; i < row; i ++) {
            String [] tmp = a[i].split("\t");
            for(int j = 0; j < column - 1; j ++) {
                V.num[i][j] = Double.parseDouble(tmp[j]);
            }
        }
    }

    public void train() {
        base = Math.max((row * column) / (row + column), 1);
        W = new Matrix(row, base, true);
        H = new Matrix(base, column, true);
        double eps = 1e-3;
        double norm = 0;
        for(int count = 0; count < round; ++count) {
            Matrix pre = W.multiply(H);
            Matrix E = V.substract(pre);
            if((norm = E.norm2()) < eps) {
                break;
            }
            System.out.println("round " + round + " norm2: " + norm);
            Matrix a = W.transpose().multiply(V);
            Matrix b = W.multiply(W).multiply(H);
            for(int i = 0; i < base; ++i) {
                for(int j = 0; j < row; ++j) {
                    H.num[i][j] = H.num[i][j] * a.num[i][j] / b.num[i][j];
                }
            }
            Matrix c = V.multiply(H.transpose());
            Matrix d = W.transpose().multiply(W).multiply(H);
            for(int i = 0; i < column; ++i) {
                for(int j = 0; j < base; ++j) {
                    W.num[i][j] = W.num[i][j] * c.num[i][j] / d.num[i][j];
                }
            }
        }
    }

}
