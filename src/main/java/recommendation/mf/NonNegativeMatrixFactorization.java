package recommendation.mf;

import util.calculation.Matrix;

import java.io.*;

public class NonNegativeMatrixFactorization {

    private Matrix V;

    private Matrix W, H;

    private int row, column;

    private int base;

    private int round = 100;

    public NonNegativeMatrixFactorization(Matrix v) {
        this.V = v;
        this.row = v.getRow();
        this.column = v.getColumn();
    }

    public void loadData() throws IOException {
        this.row = 20;
        this.column = 10;
        V = new Matrix(row, column);
        try {
            File file = new File("D:\\study\\大四上\\毕设\\文件\\csv\\filmtrust\\ratings.txt");
            InputStreamReader inputStream = new InputStreamReader(new FileInputStream(file));
            BufferedReader reader = new BufferedReader(inputStream);
            String line;
            int count = 0;
            int maxuid = 1;
            int maxiid = 1;
            double maxrating = 0;
            while((line = reader.readLine()) != null) {
                String[] nums = line.split(" ");
                ++count;
//                System.out.println(count +  " user id: " + nums[0] + " item id: " + nums[1] + " rating: " + nums[2]);
                int uid = Integer.parseInt(nums[0]);
                int iid = Integer.parseInt(nums[1]);
                double rate = Double.parseDouble(nums[2]);
                maxuid = Math.max(maxuid, uid);
                maxiid = Math.max(maxiid, iid);
                maxrating = Math.max(maxrating, rate);
                if(uid > column) {
                    break;
                }
                if(iid <= row) {
                    V.num[iid - 1][uid - 1] = rate;
                }
            }
//            System.out.println("maxuid: " + maxuid + " maxiid: " + maxiid + " maxrating: " + maxrating);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("V:");
        V.print();
    }

    public void train() {
        base = Math.max((row * column) / (row + column), 1);
        W = new Matrix(row, base, true);
        H = new Matrix(base, column, true);
        double eps = 1e-3;
        double norm = 0;
        for(int count = 1; count <= round; ++count) {
            Matrix WT = W.transpose();
            Matrix a = WT.multiply(V);
            Matrix b = WT.multiply(W).multiply(H);
            Matrix HT = H.transpose();
            Matrix c = V.multiply(HT);
            Matrix d = W.multiply(H).multiply(HT);
            for(int i = 0; i < base; ++i) {
                for(int j = 0; j < column; ++j) {
                    H.num[i][j] *= a.num[i][j] / b.num[i][j];
                }
            }
            for(int i = 0; i < row; ++i) {
                for(int j = 0; j < base; ++j) {
                    W.num[i][j] *= c.num[i][j] / d.num[i][j];
                }
            }
            Matrix pre = W.multiply(H);
            Matrix E = V.substract(pre);
            if((norm = E.norm2()) < eps) {
                break;
            }
            System.out.println("round " + count + " norm2: " + norm);
        }
        System.out.println("W:");
        W.print();
        System.out.println("H:");
        H.print();
    }
}
