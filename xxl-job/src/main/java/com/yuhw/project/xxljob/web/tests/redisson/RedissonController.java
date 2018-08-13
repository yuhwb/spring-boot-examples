package com.yuhw.project.xxljob.web.tests.redisson;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
public class RedissonController {
    @Autowired
    private RedissonClient redissonClient;
    @RequestMapping(value = "/redisson" , method = {RequestMethod.GET})
    public User testRedisson(){
        User user = new User("1","yuhaiwei" ,25);
        RBucket<User> rBucket = redissonClient.getBucket("name");
        rBucket.set(user , 20 , TimeUnit.MINUTES);

        User user1 = rBucket.get();
        return user1;
    }
}
