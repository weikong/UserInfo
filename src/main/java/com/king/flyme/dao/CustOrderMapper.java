package com.king.flyme.dao;

import java.util.List;
import java.util.Map;

public interface CustOrderMapper {

    List<Map> selectCustomerOrder(Map map);

    List<Map> loadOrderDetail(Map map);
}