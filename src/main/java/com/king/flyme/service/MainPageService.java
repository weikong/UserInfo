package com.king.flyme.service;

import com.king.flyme.bean.*;
import com.king.flyme.commons.StringUtil;
import com.king.flyme.dao.*;
import org.apache.commons.collections4.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xinzhendi-031 on 2017/8/21.
 */

@Service
@Transactional
public class MainPageService {
    private static final Logger log = LoggerFactory.getLogger(MainPageService.class);

    @Autowired
    private GoodsCollectMapper collectMapper;
    @Autowired
    private GoodsRecordMapper recordMapper;
    @Autowired
    private CartsMapper cartsMapper;
    @Autowired
    private OrderItemMapper orderItemMapper;

    /**
     * 查询首页数据
     */
    public Map selectMainData(Map param) {
        return param;
    }

    /**
     * 查询首页我的信息
     */
    public Map selectMineInfo(Map param) {
        int account_id = MapUtils.getInteger(param, "account_id", -1);
        if (account_id == -1)
            throw new RuntimeException("该账号不存在");
        if (param == null)
            param = new HashMap();
        //收藏商品数量
        GoodsCollectExample collectExample = new GoodsCollectExample();
        collectExample.createCriteria().andAccountIdEqualTo(account_id);
        long collectCount = collectMapper.countByExample(collectExample);
        param.put("collect_count",collectCount);
        //足迹商品数量
        GoodsRecordExample recordExample = new GoodsRecordExample();
        recordExample.createCriteria().andAccountIdEqualTo(account_id);
        long recordCount = recordMapper.countByExample(recordExample);
        param.put("record_count",recordCount);
        //待付款订单数量
        OrderItemExample example1 = new OrderItemExample();
        example1.createCriteria().andAccountIdEqualTo(account_id).andStateEqualTo(-1);
        long paymentCount = orderItemMapper.countByExample(example1);
        param.put("order_payment_count",paymentCount);
        //待发货订单数量
        OrderItemExample example2 = new OrderItemExample();
        example2.createCriteria().andAccountIdEqualTo(account_id).andStateEqualTo(1);
        long orderSendCount = orderItemMapper.countByExample(example2);
        param.put("order_send_count",orderSendCount);
        //待收货订单数量
        OrderItemExample example3 = new OrderItemExample();
        example3.createCriteria().andAccountIdEqualTo(account_id).andStateEqualTo(2);
        long orderReceiveCount = orderItemMapper.countByExample(example3);
        param.put("order_receive_count",orderReceiveCount);
        //全部订单数量
        OrderItemExample example4 = new OrderItemExample();
        example4.createCriteria().andAccountIdEqualTo(account_id).andStateIsNotNull();
        long orderAllCount = orderItemMapper.countByExample(example4);
        param.put("order_all_count",orderAllCount);
        return param;
    }

    /**
     * 查询购物车中商品数量
     */
    public Map selectCartCount(Map param) {
        int account_id = MapUtils.getInteger(param, "account_id", -1);
        if (account_id == -1)
            throw new RuntimeException("该账号不存在");
        if (param == null)
            param = new HashMap();
        //收藏商品数量
        CartsExample cartsExample = new CartsExample();
        cartsExample.createCriteria().andAccountIdEqualTo(account_id);
        long cartCount = cartsMapper.countByExample(cartsExample);
        param.put("cart_count",cartCount);
        return param;
    }
}
