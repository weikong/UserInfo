package com.king.flyme.service;

import com.king.flyme.bean.*;
import com.king.flyme.commons.StringUtil;
import com.king.flyme.dao.CartsMapper;
import com.king.flyme.dao.CustOrderMapper;
import com.king.flyme.dao.OrderItemMapper;
import com.king.flyme.dao.OrdersMapper;
import org.apache.commons.collections4.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by xinzhendi-031 on 2017/8/21.
 */

@Service
@Transactional
public class OrdersService {
    private static final Logger log = LoggerFactory.getLogger(OrdersService.class);

    @Autowired
    private OrdersMapper ordersMapper;
    @Autowired
    private OrderItemMapper orderItemMapper;
    @Autowired
    private CustOrderMapper custOrderMapper;


    /**
     * 加入订单
     * 
     */
    public void joinOrders(Map param) {
        // TODO: 2017/8/23 现在每次只能添加一个商品
        int account_id = MapUtils.getInteger(param, "account_id", -1);
        if (account_id == -1)
            throw new RuntimeException("该账号不存在");
        String product_id = MapUtils.getString(param, "product_id");
        if (StringUtils.isEmpty(product_id))
            throw new RuntimeException("商品不存在");
        int count = MapUtils.getInteger(param, "count", -1);
        String name = MapUtils.getString(param, "name","");
        String desc = MapUtils.getString(param, "desc","");
        float price = MapUtils.getFloat(param, "price",0f);
        String address = MapUtils.getString(param, "address","");
        int state = 0;
        Orders orders = new Orders();
        orders.setAccountId(account_id);
        Date date = new Date();
        String order_id = date.toString();
        orders.setOrderId(order_id);
        int insert1 = ordersMapper.insert(orders);
        if (insert1 != 1)
            throw new RuntimeException("加入订单失败");
        OrderItem orderItem = new OrderItem();
        orderItem.setOrderId(order_id);
        orderItem.setProductId(product_id);
        orderItem.setAccountId(account_id);
        orderItem.setCount(count);
        orderItem.setName(name);
        orderItem.setDesc(desc);
        orderItem.setAddress(address);
        orderItem.setPrice(price);
        orderItem.setState(0);
        int insert2 = orderItemMapper.insert(orderItem);
        if (insert2 != 1)
            throw new RuntimeException("加入订单失败");
    }

    /**
     * 删除订单
     */
    public void delOrders(Map param) {

    }

    /**
     * 查询订单 - ALL
     */
    public List<Map> selectMyOrders(Map param) {
        int account_id = MapUtils.getInteger(param, "account_id", -1);
        if (account_id == -1)
            throw new RuntimeException("该账号不存在");
        List<Map> list = custOrderMapper.selectCustomerOrder(param);
        for (Map item : list){
            List<Map> orderItems = custOrderMapper.loadOrderDetail(item);
            item.put("order_items",orderItems);
        }
        return list;
    }
}
