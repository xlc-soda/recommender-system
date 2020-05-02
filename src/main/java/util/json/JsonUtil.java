package util.json;

import com.alibaba.fastjson.JSONObject;

public class JsonUtil {
    public static String getJsonResult(int errno, String errmsg) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("errno", errno);
        jsonObject.put("errmsg", errmsg);
        return jsonObject.toJSONString();
    }

    public static String getJsonResult(int errno, String errmsg, JSONObject data) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("errno", errno);
        jsonObject.put("data", data);
        jsonObject.put("errmsg", errmsg);
        return jsonObject.toJSONString();
    }
}
