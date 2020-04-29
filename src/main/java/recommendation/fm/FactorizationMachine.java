package recommendation.fm;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class FactorizationMachine {
    private static int paraNum; //权重参数的个数
    private static double rate = 0.01; //学习率
    private static int samNum; //样本个数
    private static double[][] feature; //样本特征矩阵
    private static double[] Label;//样本标签
    private static int maxCycle = 10000; //最大迭代次数
    private static int k = 3; //FM算法中特征相关矩阵的列数(FM算法的度)
    private static double[] W;
    private static double[][] V;
    private static double b;

    //构造器
    public FactorizationMachine(double[][] feature, double[] Label) {
        this.feature = feature;
        this.Label = Label;
        this.paraNum = feature[0].length;
        this.samNum = feature.length;
    }

    // 权值矩阵初始化
    private double[] weightsInitialize(int paraNum) {
        double[] W = new double[paraNum];
        for (int i = 0; i < paraNum; i++) {
            W[i] = 1.0;
        }
        return W;
    }

    //系数矩阵初始化
    private double[][] coefficientInitialize(int paraNum, int k) {
        double[][] V = new double[paraNum][k];
        Random random = new Random();
        for (int i = 0; i < paraNum; i++) {
            for (int j = 0; j < k; j++) {
                V[i][j] = random.nextGaussian();
            }
        }
        return V;
    }

    //计算非线性项
    private double nonLinerTerm(double[] x, double[][] V) {
        //参数设置
        int k = V[0].length;
        int paraNum = V.length;
        //完整的非线性项
        double tmp = 0;
        for (int i = 0; i < k; i++) {
            //非线性项的两个子项
            double tmp1 = 0;
            double tmp2 = 0;
            for (int j = 0; j < paraNum; j++) {
                tmp1 += V[j][i] * x[j];
                tmp2 += Math.pow(V[j][i], 2) * Math.pow(x[j], 2);
            }
            tmp += (Math.pow(tmp1, 2) - tmp2);
        }
        return tmp * 0.5;
    }

    //计算系数矩阵中元素的梯度系数的累加项
    private double sumTerm(double[] x, double[][] V, int k1) {
        //参数设置
        int paraNum = V.length;
        double sumterm = 0;
        for (int i = 0; i < paraNum; i++) {
            sumterm += V[i][k1] * x[i];
        }
        return sumterm;
    }

    //预测样本结果
    public double[] predict() {
        int n = feature.length;
        int m = feature[0].length;
        double[] results = new double[n];
        for (int i = 0; i < n; i++) {
            //非线性项
            double inter = nonLinerTerm(feature[i], V);
            //线性项
            double linerterm = 0;
            for (int j = 0; j < m; j++) {
                linerterm += feature[i][j] * W[j];
            }
            // 样本到分离超平面的几何距离
            double d1 = b + inter + linerterm;
            double pre = Sigmoid.sigmoid(d1);
            results[i] = pre;
        }
        return results;
    }

    //计算损失函数值
    private double getCost(double[] results, double[] Label) {
        int n = results.length;
        double cost = 0;
        for (int i = 0; i < n; i++) {
            cost -= Math.log(Sigmoid.sigmoid(results[i] * Label[i]));
        }
        return cost;
    }

    //利用随机梯度训练进行FM模型训练
    public Map<String, double[][]> train() {
        //初始化偏置
        b = 0;
        //初始化权重矩阵
        W = weightsInitialize(paraNum);
        //初始化系数矩阵
        V = coefficientInitialize(paraNum, k);
        // 循环迭代优化权重矩阵(随机梯度下降)
        for (int iter = 0; iter < maxCycle; iter++) {
            for (int i = 0; i < samNum; i++) {
                //非线性项
                double interaction = nonLinerTerm(feature[i], V);
                //线性项
                double LinerTerm = 0;
                for (int j = 0; j < paraNum; j++) {
                    LinerTerm += feature[i][j] * W[j];
                }
                // 样本到分离超平面的几何距离
                double d = b + LinerTerm + interaction;
                //预测结果与样本标签的差
                double loss = Sigmoid.sigmoid(d * Label[i]) - 1;
                //计算每个参数的梯度方向
                double temp = rate * loss * Label[i];
                b = b - temp;
                //计算权重矩阵与系数矩阵中每个元素的梯度方向
                for (int n = 0; n < paraNum; n++) {
                    if (feature[i][n] != 0) {
                        W[n] = W[n] - temp * feature[i][n];
                        for (int m = 0; m < k; m++) {
                            double sumterm = sumTerm(feature[i], V, m);
                            V[n][m] = V[n][m] - temp * feature[i][n] * (sumterm - V[n][m] * feature[i][n]);
                        }
                    }
                }
            }
            if (iter % 100 == 0) {
                double[] results = predict();
                double cost = getCost(results, Label);
                System.out.println("第" + iter + "迭代损失函数的值为:" + cost);
            }
        }
        int l = 1;
        //将偏置也封装为二维数值
        double[][] w0 = new double[l][l];
        for (int i = 0; i < l; i++) {
            for (int j = 0; j < l; j++) {
                w0[i][j] = b;
            }
        }
        double[][] WTmp = new double[l][paraNum];
        for (int i = 0; i < l; i++) {
            for (int j = 0; j < paraNum; j++) {
                WTmp[i][j] = W[j];
            }
        }

        //使用Map返回多值
        Map<String, double[][]> m = new HashMap<>();
        m.put("w0", w0);
        m.put("WTmp", WTmp);
        m.put("V", V);
        return m;
    }
}