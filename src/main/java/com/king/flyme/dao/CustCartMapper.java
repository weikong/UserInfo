package com.king.flyme.dao;

import com.king.flyme.bean.Carts;

import java.util.List;
import java.util.Map;

/**
 * 购物车接口
 */
public interface CustCartMapper {

    List<Carts> selectCartItems(int accountId);

    void deleteCartItem(Map map);
}
