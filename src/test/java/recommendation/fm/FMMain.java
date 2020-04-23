package recommendation.fm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class FMMain {
    public static void main(String[] args) throws IOException {
        // filename
        String filename = "data.txt";
        // 导入样本特征和标签
        double [][] feature = LoadData.Loadfeature(filename);
        double [] Label = LoadData.LoadLabel(filename);
        // 参数设置
        int samNum = feature.length;
        int paraNum = feature[0].length;
        double rate = 0.01;
        int maxCycle = 10000;
        int k = 3;
        //FM模型训练
        FMtrainstocGradAscent FM = new FMtrainstocGradAscent(paraNum,rate,samNum,feature,Label,maxCycle,k);
        Map<String,double[][]> m = FM.Updata(rate, feature, Label, maxCycle, k);
        //将Map中的数据导入到ArrayList中
        ArrayList<double [][]> valuesList = new ArrayList<double [][]>(m.values());
        double [][] V = valuesList.get(0);
        double [][] WTmp = valuesList.get(1);
        double [][] w0 = valuesList.get(2);
        //将参数导出成原始形式
        double b = w0[0][0];
        double [] W = WTmp[0];
    }
}
