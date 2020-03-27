package service.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import util.redis.RedisUtil;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

@RestController
public class PopularityRecommender implements Recommender {
    @Override
    @RequestMapping(name = "/check", method = {RequestMethod.GET, RequestMethod.POST})
    public String getRecommendResult() {
        ArrayList<Node> arrayList = new ArrayList<>();
        Jedis jedis = RedisUtil.getConnection();
        Set<String> set = jedis.keys("*");
        String value;
        for (String key : set) {
            value = jedis.get(key);
            System.out.println("key: " + key + " value: " + value);
            if (value.matches("[0-9]*")) {
                arrayList.add(new Node(key, Integer.valueOf(value)));
            }
        }
        Collections.sort(arrayList);
        ArrayList<String> hotList = new ArrayList<>();
        for (int i = 0, size = Math.min(arrayList.size(), 10); i < size; ++i) {
            hotList.add(arrayList.get(i).name);
        }
        return JSON.toJSONString(hotList);
    }

    private static class Node implements Comparable<Node> {
        String name;
        int hot;

        Node(String name, int hot) {
            this.name = name;
            this.hot = hot;
        }

        @Override
        public int compareTo(@NotNull Node o) {
            int value = o.hot - this.hot;
            if (0 == value) {
                value = o.name.compareTo(this.name);
            }
            return value;
        }
    }
}
