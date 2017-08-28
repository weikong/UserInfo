package com.king.flyme.controller;

import com.king.flyme.bean.Carts;
import com.king.flyme.bean.CustOrders;
import com.king.flyme.bean.OrderItem;
import com.king.flyme.service.CartService;
import com.king.flyme.service.OrdersService;
import org.apache.commons.collections4.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by xinzhendi-031 on 2017/8/21.
 * 用户相关操作
 */
@Controller
@RequestMapping("/order")
public class OrdersController extends AbsController {
    private static final Logger log = LoggerFactory.getLogger(OrdersController.class);

    @Autowired
    private OrdersService ordersService;

    @GetMapping("/join")
    @ResponseBody
    public Object joinOrderItem(@RequestParam Map param) {
        try {
            Map data = ordersService.joinOrders(param);
            return ajax(data);
        } catch (RuntimeException e) {
            log.error(e.getMessage());
            return ajax(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
            return ajax(e);
        }
    }

    @GetMapping("/select/my_state")
    @ResponseBody
    public Object selectMyStateOrder(@RequestParam Map param) {
        try {
            List<CustOrders> list = ordersService.selectMyStatusOrders(param);
            return ajax(list);
        } catch (RuntimeException e) {
            log.error(e.getMessage());
            return ajax(e);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ajax(e);
        }
    }

    @GetMapping("/delete")
    @ResponseBody
    public Object delOrderItems(@RequestParam Map param) {
        try {
            ordersService.delOrders(param);
            return ajax();
        } catch (RuntimeException e) {
            log.error(e.getMessage());
            return ajax(e);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ajax(e);
        }
    }

    @GetMapping("/confirm")
    @ResponseBody
    public Object confirmOrderItems(@RequestParam Map param) {
        try {
            ordersService.confirmOrder(param);
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
