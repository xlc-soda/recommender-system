package service.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IndexService {

    @Autowired
    private JsonService jsonService;

    // TODO: implement method index
    public String index(String sessionId, JSONObject jsonObject) {
        return "";
    }
}
