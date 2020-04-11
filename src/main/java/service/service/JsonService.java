package service.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class JsonService {
    public String getJsonResult(int errno, String errmsg) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("errno", errno);
        jsonObject.put("errmsg", errmsg);
        return jsonObject.toJSONString();
    }

    public String getJsonResult(int errno, String errmsg, JSONObject data) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("errno", errno);
        jsonObject.put("data", data);
        jsonObject.put("errmsg", errmsg);
        return jsonObject.toJSONString();
    }
}
