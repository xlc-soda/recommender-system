package service.service;

import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import util.logger.LoggerUtil;
import util.redis.RedisUtil;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

@Service
public class PopularityRecommenderService {

    private static final Logger logger = Logger.getLogger(PopularityRecommenderService.class);

    @Autowired
    private UserService userService;

    /**
     * 获取推荐结果
     * @param sessionId
     * @return
     */
    public synchronized String getRecommendResult(String sessionId) {
        ArrayList<Node> arrayList = new ArrayList<>();
        Jedis jedis = RedisUtil.getConnection();
        Set<String> set = jedis.keys("*");
        String value;
        for (String key : set) {
            // key：物品名
            // value：热度值
            value = jedis.get(key);
            System.out.println("key: " + key + " value: " + value);
            if (value.matches("[0-9]*")) {
                // 将键值存入ArrayList
                arrayList.add(new Node(key, Integer.valueOf(value)));
            }
        }
        Collections.sort(arrayList);
        ArrayList<String> hotList = new ArrayList<>();
        for (int i = 0, size = Math.min(arrayList.size(), 10); i < size; ++i) {
            hotList.add(arrayList.get(i).name);
        }
        String result = JSON.toJSONString(hotList);
        logger.info(LoggerUtil.info(sessionId, result));
        return result;
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
