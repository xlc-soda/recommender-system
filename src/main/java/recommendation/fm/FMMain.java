package recommendation.fm;

import java.util.ArrayList;
import java.util.Map;

public class FMMain {
    public static void main(String[] args) {
        // 导入样本特征和标签
        LoadData.loadFeature();
        double[][] feature = LoadData.getFeature();
        double[] label = LoadData.getLabel();
        //FM模型训练
        FactorizationMachine factorizationMachine = new FactorizationMachine(feature, label);
        Map<String, double[][]> m = factorizationMachine.train();
        //将Map中的数据导入到ArrayList中
        ArrayList<double[][]> valuesList = new ArrayList<double[][]>(m.values());
        double[][] V = valuesList.get(0);
        System.out.println("V: ");
        for (int i = 0; i < V.length; ++i) {
            for (int j = 0; j < V[0].length; ++j) {
                System.out.print(V[i][j] + " ");
            }
            System.out.println();
        }
        double[][] WTmp = valuesList.get(1);
        System.out.println("WTmp: ");
        for (int i = 0; i < WTmp.length; ++i) {
            for (int j = 0; j < WTmp[0].length; ++j) {
                System.out.print(WTmp[i][j] + " ");
            }
            System.out.println();
        }
        double[][] w0 = valuesList.get(2);
        System.out.println("w0: ");
        for (int i = 0; i < w0.length; ++i) {
            for (int j = 0; j < w0[0].length; ++j) {
                System.out.print(w0[i][j] + " ");
            }
            System.out.println();
        }
        //将参数导出成原始形式
        double b = w0[0][0];
        System.out.println("b: " + b);
        double[] W = WTmp[0];
        System.out.println("W: ");
        for (int i = 0; i < W.length; ++i) {
            System.out.print(W[i] + " ");
        }
        System.out.println();
        double[] predict = factorizationMachine.predict();
        System.out.println("predict: ");
        for(int i = 0; i < predict.length; ++i) {
            System.out.println(i + " label: " + label[i] + " predict: " + predict[i]);
        }
        System.out.println();
    }
}
