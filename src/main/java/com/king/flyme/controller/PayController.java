package com.king.flyme.controller;

import com.king.flyme.bean.OrderItem;
import com.king.flyme.dao.OrderItemMapper;
import com.king.flyme.dao.OrdersMapper;
import org.apache.commons.collections4.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/pay")
public class PayController extends AbsController {
    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

    /**
     * 加入订单：1
     * 取消订单：2
     * 取消支付：4
     * 支付成功：8
     * 支付异常：9
     */

    @Autowired
    private OrderItemMapper orderItemMapper;
    @Autowired
    private OrdersMapper ordersMapper;

    /**
     * 完成付款
     */
    @GetMapping("/order_pay")
    @ResponseBody
    public Object orderPay(@RequestParam Map param) {
        try {
            int account_id = MapUtils.getInteger(param, "account_id", -1);
            if (account_id == -1)
                throw new RuntimeException("该账号不存在");
            //order_item id
            int id = MapUtils.getInteger(param, "id", -1);
            if (id == -1)
                throw new RuntimeException("订单不存在");
            OrderItem orderItem = orderItemMapper.selectByPrimaryKey(id);
            if (orderItem != null) {
                orderItem.setState(1);
                int update = orderItemMapper.updateByPrimaryKeySelective(orderItem);
                if (update != 1)
                    throw new RuntimeException("订单操作失败");
            }
            return ajax();
        } catch (RuntimeException e) {
            log.error(e.getMessage());
            return ajax(e);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ajax(e);
        }
    }


    /**
     * 取消付款
     */
    @GetMapping("/cancle_pay")
    @ResponseBody
    public Object canclePay(@RequestParam Map param) {
        try {
            int account_id = MapUtils.getInteger(param, "account_id", -1);
            if (account_id == -1)
                throw new RuntimeException("该账号不存在");
            //order_item id
            int id = MapUtils.getInteger(param, "id", -1);
            if (id == -1)
                throw new RuntimeException("订单不存在");
            OrderItem orderItem = orderItemMapper.selectByPrimaryKey(id);
            if (orderItem != null) {
                orderItem.setState(-1);
                int update = orderItemMapper.updateByPrimaryKeySelective(orderItem);
                if (update != 1)
                    throw new RuntimeException("订单操作失败");
            }
            return ajax();
        } catch (RuntimeException e) {
            log.error(e.getMessage());
            return ajax(e);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ajax(e);
        }
    }
}
