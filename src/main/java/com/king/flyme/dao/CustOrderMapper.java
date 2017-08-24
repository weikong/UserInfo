package com.king.flyme.dao;

import com.king.flyme.bean.OrderItem;

import java.util.List;
import java.util.Map;

public interface CustOrderMapper {

    List<Map> selectCustomerOrder(Map map);

    List<Map> loadOrderDetail(Map map);

    int insertOrder(List<OrderItem> list);
}