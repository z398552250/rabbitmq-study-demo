package com.limeng.rabbitmq.idempotent.receive.utils;

public class Bitmap {
    //容器
    private byte[] bytes;

    //最大值,用户输入的值
    private int maxValue;

    //容量,就是bytes的数据大小
    private int capacity;


    public Bitmap(int maxValue) {
        this.maxValue = maxValue;
        //一个byte=1bit,1bite=8位,通过用户设定的值除以8计算byte数组的大小,考虑从0开始,所以要加1
        this.capacity = (maxValue / 8) + 1;
        //通过计算出的数据大小,创建byte数组
        bytes = new byte[capacity];
    }

    public void add(int num) {
        //通过用户输入的值,计算该值应该存放在哪个byte上,
        int index = num / 8;
        //通过取余计算具体的存储位置
        int position = num % 8;
        bytes[index] = (byte) (bytes[index] | 1 << position);
    }

    public void delete(int num) {
        int index = num / 8;
        int position = num % 8;
        bytes[index] = (byte) (bytes[index] & ~(1 << position));
    }

    public boolean contains(int num) {
        int index = num / 8;
        int position = num % 8;
        return (bytes[index] & 1 << position) != 0;
    }

}
