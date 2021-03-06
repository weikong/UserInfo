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
    private OrdersMapper ordersMapper;

    /**
     * 查询首页数据
     * 1、Banner数据
     * 2、热销水果数据
     * 3、功效水果数据
     * 4、新鲜预售数据
     */
    public Map selectMainData(Map param) {
        return param;
    }

    /**
     * 1、用户地址
     * 2、用户长查询水果
     * 种类（李子）、酸甜、产地、大小、好坏、热带、价格、功效、稀有
     * */
    public Map selectRecommendData(Map param) {
        // TODO: 2017/8/25  种类（李子）、酸甜、产地、大小、好坏、热带、价格、功效、稀有
        int account_id = MapUtils.getInteger(param, "account_id", -1);
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
        OrdersExample example1 = new OrdersExample();
        example1.createCriteria().andAccountIdEqualTo(account_id).andStatusEqualTo(1);
        long paymentCount = ordersMapper.countByExample(example1);
        param.put("order_payment_count",paymentCount);
        //待发货订单数量
        OrdersExample example2 = new OrdersExample();
        example2.createCriteria().andAccountIdEqualTo(account_id).andStatusEqualTo(2);
        long orderSendCount = ordersMapper.countByExample(example2);
        param.put("order_send_count",orderSendCount);
        //待收货订单数量
        OrdersExample example3 = new OrdersExample();
        example3.createCriteria().andAccountIdEqualTo(account_id).andStatusEqualTo(3);
        long orderReceiveCount = ordersMapper.countByExample(example3);
        param.put("order_receive_count",orderReceiveCount);
        //全部订单数量
        OrdersExample example4 = new OrdersExample();
        example4.createCriteria().andAccountIdEqualTo(account_id).andStatusGreaterThan(0);
        long orderAllCount = ordersMapper.countByExample(example4);
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
