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
        int state = -1;
        Orders orders = new Orders();
        orders.setId(null);
        orders.setAccountId(account_id);
        Date date = new Date();
        String order_id = account_id+" "+date.toLocaleString();
        orders.setOrderId(order_id);
        orders.setTime(date);
        int insert1 = ordersMapper.insert(orders);
        if (insert1 != 1)
            throw new RuntimeException("加入订单失败");
        OrderItem orderItem = new OrderItem();
        orderItem.setId(null);
        orderItem.setTime(date);
        orderItem.setOrderId(order_id);
        orderItem.setProductId(product_id);
        orderItem.setAccountId(account_id);
        orderItem.setCount(count);
        orderItem.setProductName(name);
        orderItem.setProductDesc(desc);
        orderItem.setAddress(address);
        orderItem.setPrice(price);
        orderItem.setState(state);
        int insert2 = orderItemMapper.insertSelective(orderItem);
        if (insert2 != 1)
            throw new RuntimeException("加入订单失败");
    }

    /**
     * 删除订单
     */
    public void delOrders(Map param) {
        int account_id = MapUtils.getInteger(param, "account_id", -1);
        if (account_id == -1)
            throw new RuntimeException("该账号不存在");
        String order_id = MapUtils.getString(param,"order_id");
        OrdersExample ordersExample = new OrdersExample();
        ordersExample.createCriteria().andOrderIdEqualTo(order_id).andAccountIdEqualTo(account_id);
        List<Orders> list1 = ordersMapper.selectByExample(ordersExample);
        if (list1 == null || list1.size() == 0)
            throw new RuntimeException("该订单已不存在");
        for (Orders orders:list1){
            int del = ordersMapper.deleteByPrimaryKey(orders.getId());
            if (del != 1)
                throw new RuntimeException("订单删除失败");
        }
        OrderItemExample orderItemExample = new OrderItemExample();
        orderItemExample.createCriteria().andOrderIdEqualTo(order_id).andAccountIdEqualTo(account_id);
        List<OrderItem> list2 = orderItemMapper.selectByExample(orderItemExample);
        if (list2 == null || list2.size() == 0)
            throw new RuntimeException("该订单已不存在");
        for (OrderItem orderItem:list2){
            int del = orderItemMapper.deleteByPrimaryKey(orderItem.getId());
            if (del != 1)
                throw new RuntimeException("订单删除失败");
        }
    }

    /**
     * 查询订单 - state
     */
    public List<OrderItem> selectMyStateOrders(Map param) {
        int account_id = MapUtils.getInteger(param, "account_id", -1);
        if (account_id == -1)
            throw new RuntimeException("该账号不存在");
        Integer state = MapUtils.getInteger(param, "state", null);
        OrderItemExample example = new OrderItemExample();
        OrderItemExample.Criteria criteria = example.createCriteria();
        criteria.andAccountIdEqualTo(account_id);
        if (state == null)
            criteria.andStateIsNotNull();
        else
            criteria.andStateEqualTo(state);
        example.setOrderByClause("time desc");
        List<OrderItem> list = orderItemMapper.selectByExample(example);
        return list;
    }

    public void confirmOrder(Map param) {
        int account_id = MapUtils.getInteger(param, "account_id", -1);
        if (account_id == -1)
            throw new RuntimeException("该账号不存在");
        String order_id = MapUtils.getString(param, "order_id");
        String verification_code = MapUtils.getString(param, "verification_code");

    }

}
