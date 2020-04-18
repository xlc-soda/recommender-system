package recommendation.collaborate;

public class CollaborativeFilter {
    private static int[][] rate_set = {{5, 5, 0, 0}, {5, -1, -1, 0},
            {-1, 4, 0, -1}, {0, 0, 5, 4}, {0, 0, 5, -1}};// 5*4的评分矩阵，5个movie，4个user
    private static int m = 5;// 电影的数目
    private static int u = 4;// 用户的数目
    private static int n = 2;// 特征的数目
    private static double[][] x = new double[m][n];// 电影-特征 矩阵
    private static double[][] x_partial = new double[m][n];// 电影-特征 矩阵每个参数的偏导数
    private static double[][] theta = new double[u][n];// 用户-特征 矩阵
    private static double[][] theta_partial = new double[u][n];// 用户-特征 矩阵每个参数的偏导数
    private static double alpha = 0.05;// 偏导数学习步长
    private static double lambda = 1.0;// 正则化参数

    public static void main(String[] args) {
        init();
        int i, j, k, times = 0;
        // 学习的次数
        while (times < 100) {
            show(times++);

            // 对x矩阵的参数x[i][k]求偏导数
            for (i = 0; i < m; i++) {
                for (k = 0; k < n; k++) {
                    x_partial[i][k] = 0.0;
                    for (j = 0; j < u; j++) {
                        if (rate_set[i][j] != -1) {
                            x_partial[i][k] += (getPredict(i, j) - rate_set[i][j]) * theta[j][k];
                        }
                    }
                    x_partial[i][k] += lambda * x[i][k];
                }
            }

            // 对theta矩阵的参数theta[j][k]求偏导数
            for (j = 0; j < u; j++) {
                for (k = 0; k < n; k++) {
                    theta_partial[j][k] = 0.0;
                    for (i = 0; i < m; i++) {
                        if (rate_set[i][j] != -1) {
                            theta_partial[j][k] += (getPredict(i, j) - rate_set[i][j])
                                    * x[i][k];
                        }
                    }
                    theta_partial[j][k] += lambda * theta[j][k];
                }
            }

            // 更新 电影-特征 矩阵x
            for (i = 0; i < m; i++) {
                for (k = 0; k < n; k++) {
                    x[i][k] -= alpha * x_partial[i][k];
                }
            }
            // 更新  用户-特征 矩阵theta
            for (j = 0; j < u; j++) {
                for (k = 0; k < n; k++) {
                    theta[j][k] -= alpha * theta_partial[j][k];
                }
            }
        }
    }

    private static void show(int times) {
        int i, j, k;
        double min = 0.0;
        System.out.println("第" + times + "次学习后， 电影-特征 矩阵x 和 用户-特征 矩阵theta 为：");
        for (i = 0; i < m; i++) {
            for (k = 0; k < n; k++) {
                if (k == 0) {
                    System.out.print(x[i][k]);
                } else {
                    System.out.println("," + x[i][k]);
                }
            }
        }
        System.out.println();
        for (j = 0; j < u; j++) {
            for (k = 0; k < n; k++) {
                if (k == 0) {
                    System.out.print(theta[j][k]);
                } else {
                    System.out.println("," + theta[j][k]);
                }
            }
        }

        min = getMin();
        System.out.println("时，代价函数的值为" + min);
        System.out.println();
    }

    // 求得代价函数的值
    private static double getMin() {
        double min = 0.0, cost = 0.0, x_regul = 0.0, theta_regul = 0.0;
        double pred;
        int i, j;
        for (i = 0; i < m; i++) {
            for (j = 0; j < u; j++) {
                if (rate_set[i][j] != -1) {
                    pred = getPredict(i, j);
                    cost += Math.pow(pred - rate_set[i][j], 2);
                }
            }
        }

        for (i = 0; i < m; i++) {
            for (j = 0; j < n; j++) {
                x_regul += Math.pow(x[i][j], 2);
            }
        }

        for (i = 0; i < u; i++) {
            for (j = 0; j < n; j++) {
                theta_regul += Math.pow(theta[i][j], 2);
            }
        }

        min = cost + x_regul + theta_regul;
        return min;
    }

    // 求得两个向量的内积
    private static double getPredict(int i, int j) {
        double pre = 0.0;
        for (int k = 0; k < n; k++) {
            pre += x[i][k] * theta[j][k];
        }
        return pre;
    }

    private static void init() {
        int i = 0, j = 0;
        for (i = 0; i < m; ++i) {
            for (j = 0; j < n; ++j) {
                // 这里是为了验证代码时方便而采用的数据，是随便取的
                x[i][j] = 1.0 * (i % 3 + j % 2 - 1);
            }
        }
        //  x[i][j]=Math.random();
        for (i = 0; i < u; ++i) {
            for (j = 0; j < n; ++j) {
                theta[i][j] = 1.0 * (-i % 3 + j % 2 + 1);
            }
        }
        //  theta[i][j]=Math.random();
    }
}
