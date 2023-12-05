package com.limeng.rabbitmq.idempotent.receive.utils;

import org.springframework.util.StringUtils;

public class HashUtils {
    //定义范围
    private Integer size;

    //定义一个质数 2,3,5,7,11,13,17,19,23,29,31,37,41,43,47
    private Integer seed;

    public HashUtils(Integer size, Integer seed) {
        this.size = size;
        this.seed = seed;
    }


    private int hashCode(String str) {
        int h = 0;
        if (!StringUtils.hasText(str)) {
            return h;
        }
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            h = this.seed * h + chars[i];
        }
        return h;
    }

    public int getPosition(String str) {
        int hash = hashCode(str);
        return hash & (this.size - 1);
    }



}
