package com.jalivv.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisDataException;

/**
 * @Description 限流操作
 * @Created: with IntelliJ IDEA.
 * @Author jalivv
 * @createTime 2022/1/11 10:48
 */
public class Service {

    // 控制单元
    public void service(String id) {
        Jedis jedis = new Jedis("service.jalivv.com", 6379);
        jedis.auth("jalivvRedis");
        // 获取 值
        String val = jedis.get("compid:" + id);
        // 判断该值是否存在
        try {
            if (val == null || "".equals(val)) {
                // 不存在 创建 值
                jedis.setex("compid:" + id, 20, Long.MAX_VALUE - 10 + "");
            } else {
                //自增
                jedis.incr("compid:" + id);
                business();
            }
        } catch (JedisDataException e) {
            System.out.println("使用已到达上限");
        } finally {
            jedis.close();
        }
    }


    // 业务操作
    public void business() {
        System.out.println("正在执行业务.....");
    }
}


class MyThread extends Thread {
    Service service = new Service();
    @Override
    public void run() {
        while (true) {
            service.service("初级用户");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}

class Main{
    public static void main(String[] args) {
        new MyThread().start();
    }
}
