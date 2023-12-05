package com.limeng.rabbitma.reliability.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderPo {
    //订单id
    private String orderId;
    //订单价格
    private BigDecimal price;
    //订单数量
    private Integer count;
}
