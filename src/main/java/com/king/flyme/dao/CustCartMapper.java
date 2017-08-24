package com.king.flyme.dao;

import java.util.List;
import java.util.Map;

/**
 * 购物车接口
 */
public interface CustCartMapper {

    List<Map<String, Object>> selectCartItems(long accountId);

    void deleteCartItem(Map map);
}
