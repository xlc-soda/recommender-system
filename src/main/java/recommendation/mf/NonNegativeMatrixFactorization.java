package recommendation.mf;

import util.calculation.Matrix;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class NonNegativeMatrixFactorization {

    private Matrix V;

    private Matrix W, H;

    private int row, column;

    private double alpha = 1.0, beta = 1.0;

    private double eps = 1e-3;

    private int base;

    private int round = 100;

    private static class Node {
        public Integer iid;
        public Double rate;
        public Node(int iid, double rate) {
            this.iid = iid;
            this.rate = rate;
        }
    }

    public NonNegativeMatrixFactorization() {}

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

    public void trainIncr(double[] vK) {
        trainIncr(vK, this.eps);
    }

    // TODO: finish this method incr

    /**
     * train with new data
     * @param vK new column of matrix V (actually is V_(k+1) )
     * @param eps limit to stop iteration
     */
    public void trainIncr(double[] vK, double eps) {
        double[] hK = new double[H.getRow()];
        for(int i = 0; i < hK.length; ++i) {
            hK[i] = H.num[i][H.getColumn() - 1];
        }
        Matrix HT = H.transpose();
        double temp = 0;
        for(int count = 1; count <= round; ++count) {
            Matrix a = new Matrix(vK.length, hK.length);
            for (int i = 0; i < vK.length; ++i) {
                for (int j = 0; j < hK.length; ++j) {
                    a.num[i][j] = vK[i] * hK[j];
                }
            }
            a = a.add(V.multiply(HT)); // a: V.row * H.row(which is base)
            Matrix b = new Matrix(hK.length, hK.length);
            for (int i = 0; i < hK.length; ++i) {
                for (int j = 0; j < hK.length; ++j) {
                    b.num[i][j] = hK[i] * hK[j];
                }
            }
            b = W.multiply(b).add(W.multiply(H).multiply(HT));
            Matrix WT = W.transpose();
            double[] c = WT.multiply(vK);
            double[] d = WT.multiply(W).multiply(hK);
            for(int i = 0; i < W.getRow(); ++i) {
                for(int j = 0; j < W.getColumn(); ++j) {
                    W.num[i][j] *= a.num[i][j] / (b.num[i][j] + alpha);
                }
            }
            for(int i = 0; i < hK.length; ++i) {
                hK[i] *= c[i] / (d[i] + beta);
            }
            // balabala
            if((temp = lossIncr(vK, hK)) < eps) {
                break;
            }
            System.out.println("train(incr) round: " + count + " loss: " + temp);
        }
        H = H.addColumn(hK);
        V = V.addColumn(vK);
//        System.out.println("W:");
//        W.print();
//        System.out.println("H:");
//        H.print();
    }

    public void train() {
        train(this.eps);
    }

    public void train(double eps) {
        base = Math.max((row * column) / (row + column), 1);
        W = new Matrix(row, base, true);
        H = new Matrix(base, column, true);
        double temp = 0;
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
            if((temp = loss()) < eps) {
                break;
            }
            System.out.println("train round: " + count + " loss: " + temp);
        }
//        System.out.println("W:");
//        W.print();
//        System.out.println("H:");
//        H.print();
    }

    private double loss() {
        return V.substract(W.multiply(H)).norm2Pow() * 0.5 + alpha * W.norm1() + beta * H.norm1();
    }

    private double lossIncr(double[] vK, double[] hK) {
        double answer = 0;
        double[] temp = W.multiply(hK);
        for(int i = 0; i < vK.length; ++i) {
            answer += 0.5 * Math.pow(vK[i] - temp[i], 2);
        }
        for(int i = 0; i < hK.length; ++i) {
            answer += beta * hK[i];
        }
        return answer + loss();
    }

    public List<Integer> recommend(int uid) {
        Matrix result = W.multiply(H);
        ArrayList<Node> recommend = new ArrayList<>();
        for(int i = 0; i < V.getRow(); ++i) {
            if(0 == V.num[i][uid - 1]) {
                recommend.add(new Node(i + 1, result.num[i][uid - 1]));
            }
        }
        Collections.sort(recommend, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                int answer = o1.rate.compareTo(o2.rate);
                if(0 == answer) {
                    return o1.iid.compareTo(o2.iid);
                } else {
                    return answer;
                }
            }
        });
        ArrayList<Integer> iidList = new ArrayList<>();
        for(Node node: recommend) {
            iidList.add(node.iid);
        }
        return iidList;
    }

}
