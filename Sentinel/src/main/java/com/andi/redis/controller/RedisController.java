package com.andi.redis.controller;

import com.andi.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Percy on 2020-04-15.
 */
@RestController
@RequestMapping(value = "redis")
public class RedisController {
    @Autowired
    private RedisService redisService;

    @ResponseBody
    @RequestMapping(value = "test")
    public String getTest() {
        try {
            return redisService.RedisTest();
        } catch (Exception e) {
            return e.getLocalizedMessage();
        }
    }
}
