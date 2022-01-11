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

    private String id;

    private Integer times;

    public Service(String str, Integer times) {
        this.id = str;
        this.times = times;
    }

    // 控制单元
    public void service() {
        Jedis jedis = new Jedis("service.jalivv.com", 6379);
        jedis.auth("jalivvRedis");
        // 获取 值
        String val = jedis.get("compid:" + id);
        // 判断该值是否存在
        try {
            if (val == null || "".equals(val)) {
                // 不存在 创建 值
                jedis.setex("compid:" + id, 20, Long.MAX_VALUE - times + "");
            } else {
                //自增
                Long incr = 10 - (Long.MAX_VALUE - jedis.incr("compid:" + id));
                business(id, incr);
            }
        } catch (JedisDataException e) {
            System.out.println(this.id + "使用已达上限");
        } finally {
            jedis.close();
        }
    }


    // 业务操作
    public void business(String id, Long incr) {
        System.out.println("user：" + id + "is invoking service....." + "for times:" + incr);
    }
}


class MyThread extends Thread {
    public MyThread(String str, Integer times) {
        this.service = new Service(str, times);
    }

    Service service;

    @Override
    public void run() {
        while (true) {
            service.service();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}

class Main {
    public static void main(String[] args) {
        new MyThread("初级用户", 5).start();
        new MyThread("高级用户", 10).start();

    }
}
