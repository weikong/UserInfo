package com.king.flyme.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.king.flyme.bean.*;
import com.king.flyme.commons.AppUtil;
import com.king.flyme.commons.StringUtil;
import com.king.flyme.dao.AccountMapper;
import com.king.flyme.dao.CartsMapper;
import com.king.flyme.dao.CustCartMapper;
import org.apache.commons.collections4.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by xinzhendi-031 on 2017/8/21.
 */

@Service
@Transactional
public class CartService {
    private static final Logger log = LoggerFactory.getLogger(CartService.class);

    @Autowired
    private CartsMapper cartsMapper;
    @Autowired
    private CustCartMapper custCartMapper;

    /**
     * 加入购物车
     * */
    public void joinCartItem(Map param){
        int account_id = MapUtils.getInteger(param, "account_id", -1);
        if (account_id == -1)
            throw new RuntimeException("该账号不存在");
        String product_id = MapUtils.getString(param, "product_id");
        if (org.thymeleaf.util.StringUtils.isEmpty(product_id))
            throw new RuntimeException("商品不存在");
        int count = MapUtils.getInteger(param, "count", -1);
        String name = MapUtils.getString(param, "name","");
        String desc = MapUtils.getString(param, "desc","");
        float price = MapUtils.getFloat(param, "price",0f);
        String address = MapUtils.getString(param, "address","");
        CartsExample example = new CartsExample();
        example.createCriteria().andProductIdEqualTo(product_id).andAccountIdEqualTo(account_id);
        List<Carts> list = cartsMapper.selectByExample(example);
        if (list != null && list.size() > 0){
            //购物车中存在
            Carts carts = list.get(0);
            int count2 = carts.getCount();
            carts.setCount(count2+count);
            int update = cartsMapper.updateByPrimaryKey(carts);
            if (update != 1)
                throw new RuntimeException("加入购物车失败");
        } else {
            //购物车中不存在
            Carts carts = new Carts();
            carts.setAccountId(account_id);
            carts.setProductId(product_id);
            carts.setCount(count);
            carts.setAddress(address);
            carts.setProductDesc(desc);
            carts.setProductName(name);
            carts.setPrice(price);
            int insert = cartsMapper.insert(carts);
            if (insert != 1)
                throw new RuntimeException("加入购物车失败");
        }
    }

    /**
     * 更新购物车
     * */
    public void updateCartItem(Map param){
        int account_id = MapUtils.getInteger(param, "account_id", -1);
        if (account_id == -1)
            throw new RuntimeException("该账号不存在");
        String product_id = MapUtils.getString(param, "product_id");
        if (org.thymeleaf.util.StringUtils.isEmpty(product_id))
            throw new RuntimeException("商品不存在");
        int count = MapUtils.getInteger(param, "count", 0);
        String name = MapUtils.getString(param, "name","");
        String desc = MapUtils.getString(param, "desc","");
        float price = MapUtils.getFloat(param, "price",0f);
        String address = MapUtils.getString(param, "address","");
        CartsExample example = new CartsExample();
        example.createCriteria().andProductIdEqualTo(product_id).andAccountIdEqualTo(account_id);
        List<Carts> list = cartsMapper.selectByExample(example);
        if (list != null && list.size() > 0){
            Carts carts = list.get(0);
            int count2 = carts.getCount();
            carts.setCount(count2+count);
            int update = cartsMapper.updateByPrimaryKey(carts);
            if (update != 1)
                throw new RuntimeException("更新失败");
        } else {
            throw new RuntimeException("商品出现问题");
        }
    }

    /**
     * 删除购物车中商品
     * */
    public void delCartItems(Map param){
        int account_id = MapUtils.getInteger(param, "account_id", -1);
        if (account_id == -1)
            throw new RuntimeException("该账号不存在");
        List<Integer> carts = (List<Integer>) MapUtils.getObject(param,"carts");
        if (carts != null && carts.size() > 0){
            for (int cart_id : carts){
                int del = cartsMapper.deleteByPrimaryKey(cart_id);
                if (del != 1)
                    throw new RuntimeException("删除失败");
            }
        }

    }

    /**
     * 查询购物车中商品
     * */
    public List<Carts> selectMyCart(Map param){
        int account_id = MapUtils.getInteger(param, "account_id", -1);
        if (account_id == -1)
            throw new RuntimeException("该账号不存在");
        List<Carts> list = custCartMapper.selectCartItems(account_id);
        return list == null ? new ArrayList<Carts>() : list;
    }
}
