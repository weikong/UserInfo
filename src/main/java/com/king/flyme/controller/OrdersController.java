package com.king.flyme.controller;

import com.king.flyme.bean.Carts;
import com.king.flyme.service.CartService;
import com.king.flyme.service.OrdersService;
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

    @PostMapping("/join")
    @ResponseBody
    public Object joinOrderItem(@RequestParam Map param) {
        try {
            ordersService.joinOrders(param);
            return ajax();
        } catch (RuntimeException e) {
            log.error(e.getMessage());
            return ajax(e);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ajax(e);
        }
    }

    @GetMapping("/select/all")
    @ResponseBody
    public Object selectOrderAll(@RequestParam Map param) {
        try {
            List<Map> list = ordersService.selectMyOrders(param);
            return ajax(list);
        } catch (RuntimeException e) {
            log.error(e.getMessage());
            return ajax(e);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ajax(e);
        }
    }

    @PostMapping("/delete")
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
}
