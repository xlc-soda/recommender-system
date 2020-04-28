package recommendation.fm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class LoadData {

    private static double[][] feature;

    private static double[] Label;

    //导入样本特征
    public static void Loadfeature() {
        int row = 20;
        int column = 10;
        ArrayList<double[]> f = new ArrayList<>();
        ArrayList<Double> l = new ArrayList<>();
        try {
            File file = new File("D:\\study\\大四上\\毕设\\文件\\csv\\filmtrust\\ratings.txt");
            InputStreamReader inputStream = new InputStreamReader(new FileInputStream(file));
            BufferedReader reader = new BufferedReader(inputStream);
            String line;
            int count = 0;
            int maxuid = 1;
            int maxiid = 1;
            double maxrating = 0;
            while ((line = reader.readLine()) != null) {
                String[] nums = line.split(" ");
                ++count;
//                System.out.println(count +  " user id: " + nums[0] + " item id: " + nums[1] + " rating: " + nums[2]);
                int uid = Integer.parseInt(nums[0]);
                int iid = Integer.parseInt(nums[1]);
                double rate = Double.parseDouble(nums[2]);
                maxuid = Math.max(maxuid, uid);
                maxiid = Math.max(maxiid, iid);
                maxrating = Math.max(maxrating, rate);
                if (uid > column) {
                    break;
                }
                if (iid <= row) {
                    double[] tempf = new double[row + column];
                    tempf[uid - 1] = 1;
                    tempf[uid + iid - 1] = 1;
                    f.add(tempf);
                    l.add(rate < 3.0 ? 0 : 1.0);
                }
            }
//            System.out.println("maxuid: " + maxuid + " maxiid: " + maxiid + " maxrating: " + maxrating);
        } catch (Exception e) {
            e.printStackTrace();
        }

        feature = new double[f.size()][row + column];
        Label = new double[f.size()];
        for (int i = 0; i < Label.length; i++) {
            Label[i] = l.get(i);
            double[] tempf = f.get(i);
            for (int j = 0; j < row + column; j++) {
                feature[i][j] = tempf[j];
            }
        }
    }

    public static double[][] getFeature() {
        return feature;
    }

    public static double[] getLabel() {
        return Label;
    }
}