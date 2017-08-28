package com.king.flyme.controller;

import com.king.flyme.bean.OrderItem;
import com.king.flyme.bean.OrderItemExample;
import com.king.flyme.bean.Orders;
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

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/pay")
public class PayController extends AbsController {
    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

    /**
     * 初始化：0；
     * 待付款：1；
     * 待发货：2；
     * 待收货：3；
     * 取消订单：-1；
     * 支付失敗：7；
     * 已完成：9
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
            //orders id
            int id = MapUtils.getInteger(param, "id", -1);
            if (id == -1)
                throw new RuntimeException("订单不存在");
            float price = MapUtils.getFloat(param, "price", 0f);
            Orders orders = ordersMapper.selectByPrimaryKey(id);
            if (orders != null) {
                OrderItemExample example = new OrderItemExample();
                example.createCriteria().andAccountIdEqualTo(account_id).andOrderIdEqualTo(orders.getOrderId());
                List<OrderItem> list = orderItemMapper.selectByExample(example);
                float price2 = 0f;
                for (OrderItem orderItem : list){
                    price2 += orderItem.getPrice()*orderItem.getCount();
                }
                if (price != price2)
                    throw new RuntimeException("请确认订单金额是否出错");
                // TODO: 2017/8/28 订单支付
                orders.setStatus(2);
                int update = ordersMapper.updateByPrimaryKeySelective(orders);
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
