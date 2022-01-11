package com.jalivv.redis;

import com.alibaba.fastjson.JSONObject;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

/**
 * @Description 事务
 * @Created: with IntelliJ IDEA.
 * @Author jalivv
 * @createTime 2022/1/9 14:52
 */
public class TestTX {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost", 6379);

        JSONObject obj = new JSONObject();

        obj.put("username", "jalivv");
        obj.put("age", 18);
        obj.put("description", "a java boy");

        String val = obj.toJSONString();

        // 开启事务
        Transaction tx = jedis.multi();
        try {
            tx.set("userid-1", val);
            //int i = 10 / 0;
            tx.exec();
        } catch (Exception e) {
            tx.discard();       // 出现异常 回滚
        }
        finally {
            jedis.close();
        }
    }
}
