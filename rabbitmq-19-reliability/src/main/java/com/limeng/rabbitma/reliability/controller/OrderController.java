package com.limeng.rabbitma.reliability.controller;

import com.limeng.rabbitma.reliability.po.OrderPo;
import com.limeng.rabbitma.reliability.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     *  添加一条订单信息
     * @return
     */
    @PostMapping("/add")
    public String add(@RequestBody OrderPo orderPo){
        orderService.add(orderPo);
        return "ok";
    }
}
