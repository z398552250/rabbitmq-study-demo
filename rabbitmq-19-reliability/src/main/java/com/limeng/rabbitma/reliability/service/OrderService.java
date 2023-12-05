package com.limeng.rabbitma.reliability.service;

import com.limeng.rabbitma.reliability.po.OrderPo;

public interface OrderService {

    /**
     * 添加一条订单信息
     * @param orderPo
     * @return
     */
    Integer add(OrderPo orderPo);
}
