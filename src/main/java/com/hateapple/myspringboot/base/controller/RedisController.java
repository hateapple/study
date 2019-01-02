package com.hateapple.myspringboot.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/redis")
public class RedisController {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @RequestMapping("put")
    public void put(){
        redisTemplate.opsForValue().set("name", String.valueOf(new Date()));
    }

    @RequestMapping("get")
    @ResponseBody
    public String get(){
        return (String)redisTemplate.opsForValue().get("name");
    }

    @RequestMapping("test")
    public void test(){
        opsForListTest();
    }


    private void opsForListTest(){
        String keyName = "personInfo";
        redisTemplate.opsForList().rightPush(keyName, "delete");
        redisTemplate.opsForList().rightPush(keyName, "jack");
        redisTemplate.opsForList().rightPush(keyName, "China");
        redisTemplate.opsForList().rightPush(keyName, "man");
        //指定范围内的数据,下标从0开始
        System.out.println(redisTemplate.opsForList().range(keyName, 0, -1));
        System.out.println(redisTemplate.opsForList().range(keyName, 1, 2));
        //删除第一个元素
        redisTemplate.opsForList().trim(keyName, 1, -1);
        System.out.println(redisTemplate.opsForList().range(keyName, 0, -1));


    }

    private void opsForValueTest(){
        redisTemplate.delete(Arrays.asList("name", "addres"));
        //set Value 10s后过期
        redisTemplate.opsForValue().set("name", "hello world", 10,
                TimeUnit.SECONDS);
        System.out.println(redisTemplate.opsForValue().get("name"));

        //验证过期
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(redisTemplate.opsForValue().get("name"));

        //不存在则set,true:set成功/false：set失败(已存在)
        System.out.println(redisTemplate.opsForValue().setIfAbsent("name", "hello jack"));
        System.out.println(redisTemplate.opsForValue().setIfAbsent("name", "hello jack"));

        //同时设置多个key-value
        Map<String, String> map = new HashMap<>();
        map.put("name", "jack");
        map.put("addres", "China");
        redisTemplate.opsForValue().multiSet(map);
        List<String> list = Arrays.asList("name", "addres");
        System.out.println(redisTemplate.opsForValue().multiGet(list));

        //同时设置多个key-value，如果已存在则false
        //有一个主键相同即false
        Map<String, String> map2 = new HashMap<>();
        map2.put("name1", "jack");
        map2.put("addres1", "China");
        System.out.println(redisTemplate.opsForValue().multiSetIfAbsent(map2));

        //获取old的value,并设置新的值
        System.out.println(redisTemplate.opsForValue().getAndSet("name1", "tom"));
        System.out.println(redisTemplate.opsForValue().getAndSet("name", "tom"));

        //增加
        System.out.println(redisTemplate.opsForValue().increment("increment", 1));
        System.out.println(redisTemplate.opsForValue().increment("increment", 2.2));

        //追加
        redisTemplate.opsForValue().set("say", "hello ");
        System.out.println(redisTemplate.opsForValue().get("say"));
        redisTemplate.opsForValue().append("say", " world ");
        System.out.println(redisTemplate.opsForValue().get("say"));

        //截取
        System.out.println(redisTemplate.opsForValue().get("say", 0, 4));
        System.out.println(redisTemplate.opsForValue().get("say", 0, -1));
        System.out.println(redisTemplate.opsForValue().get("say", -3, -1));

        //value长度
        System.out.println(redisTemplate.opsForValue().size("say"));


        //通过修改ascii改变value
        //3二进制：11
        redisTemplate.opsForValue().set("number", "a");
         //改成5，：101
        redisTemplate.opsForValue().setBit("number", 6, true);
        redisTemplate.opsForValue().setBit("number", 7, false);
        System.out.println(redisTemplate.opsForValue().get("number"));

    }



}
