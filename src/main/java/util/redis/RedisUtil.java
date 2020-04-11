package util.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import util.Configs;

public class RedisUtil {
    private static ThreadLocal<Jedis> threadLocal = new ThreadLocal<>();
    private static JedisPool jedisPool;


    static {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(100);
        jedisPoolConfig.setMaxIdle(50);
        jedisPoolConfig.setMaxWaitMillis(1500);
        jedisPoolConfig.setTestOnBorrow(true);
        jedisPoolConfig.setTestOnReturn(true);
        jedisPool = new JedisPool(jedisPoolConfig, Configs.MASTER_HOST);
    }

    private RedisUtil() {}

    public static void main(String[] args) {
        testConnection();
    }

    public static void testConnection() {
        Jedis jedis = getConnection();
        System.out.println("The redis server says " + jedis.ping());
//        jedis.set("testKey", "testVal");
        System.out.println("key: testKey  value: " + jedis.get("testKey"));
        closeConnection();
        closePool();
    }

    public static Jedis getConnection() {
//        if(false) {
        Jedis jedis = threadLocal.get();
        if (null == jedis) {
            jedis = jedisPool.getResource();
            threadLocal.set(jedis);
        }
        return jedis;
//        } else {
//            return new Jedis("master");
//        }
    }

    public static void closeConnection() {
        Jedis jedis = threadLocal.get();
        if (null != jedis) {
            jedis.close();
        }
        threadLocal.set(null);
    }

    public static void closePool() {
        if (null != jedisPool) {
            jedisPool.close();
        }
    }
}
