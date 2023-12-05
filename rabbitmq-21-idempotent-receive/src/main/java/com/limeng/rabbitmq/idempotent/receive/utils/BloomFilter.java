package com.limeng.rabbitmq.idempotent.receive.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.convert.StringToRedisClientInfoConverter;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class BloomFilter {

    @Autowired
    private StringRedisTemplate redisTemplate;

    private Integer size=2<<30;
    private Integer[] seeds={11,13,17,19,23,29};

    private HashUtils[] hashUtils=new HashUtils[this.seeds.length];

    public BloomFilter() {
        for (int i = 0; i < hashUtils.length; i++) {
            hashUtils[i]=new HashUtils(this.size,this.seeds[i]);
        }
    }


    public void add(String str){
        for (HashUtils hashUtil : hashUtils) {
            int position = hashUtil.getPosition(str);
            redisTemplate.opsForValue().setBit("bloom-filter",position,true);

        }
    }


    public boolean exists(String str){
        for (HashUtils hashUtil : hashUtils) {
            int position = hashUtil.getPosition(str);
            Boolean check = redisTemplate.opsForValue().getBit("bloom-filter", position);
            if (!check){
                return false;
            }
        }
        return true;
    }


}
