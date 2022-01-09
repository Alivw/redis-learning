package com.jalivv.redis;

import com.alibaba.fastjson.JSONObject;
import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * @Description 测试 jedis api
 * @Created: with IntelliJ IDEA.
 * @Author jalivv
 * @createTime 2022/1/9 14:19
 */
public class RedisApplication {

    public static void main(String[] args) {


        Jedis jedis = new Jedis("localhost", 6379);

        JSONObject obj = new JSONObject();

        obj.put("username", "jalivv");
        obj.put("age", 18);
        obj.put("description", "a java boy");


        System.out.println(jedis.ping());
        System.out.println("清空数据：" + jedis.flushDB());
        System.out.println("判断某个键是否存在" + jedis.exists("username"));
        System.out.println("新增<'username','awei'>的键值对：" + jedis.set("username", "awei"));
        System.out.println("新增<'password','123'>的键值对：" + jedis.set("password", "123"));
        System.out.println("系统中所有的键如下：");
        Set<String> keys = jedis.keys("*");
        System.out.println(keys);
        System.out.println("删除键password" + jedis.del("password"));
        System.out.println("判断password是否存在" + jedis.exists("password"));
        System.out.println("查看键username所存储的值的类型：" + jedis.type("username"));
        System.out.println("随机返回key 空间的一个：" + jedis.randomKey());
        System.out.println("重命名key：" + jedis.rename("username", "name"));
        System.out.println("去除改后的name：" + jedis.get("name"));
        System.out.println("按索引查询：" + jedis.select(0));
        System.out.println("返回当前库中key的数目： " + jedis.dbSize());

        jedis.append("user", obj.toJSONString());
    }
}
