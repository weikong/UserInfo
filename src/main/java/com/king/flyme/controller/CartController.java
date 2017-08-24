package com.king.flyme.controller;

import com.king.flyme.bean.Carts;
import com.king.flyme.service.AccountService;
import com.king.flyme.service.CartService;
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
@RequestMapping("/cart")
public class CartController extends AbsController {
    private static final Logger log = LoggerFactory.getLogger(CartController.class);

    @Autowired
    private CartService cartService;

    @PostMapping("/join")
    @ResponseBody
    public Object joinCartItem(@RequestParam Map param) {
        try {
            cartService.joinCartItem(param);
            return ajax();
        } catch (RuntimeException e) {
            log.error(e.getMessage());
            return ajax(e);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ajax(e);
        }
    }

    @PostMapping("/update")
    @ResponseBody
    public Object updateCartItem(@RequestParam Map param) {
        try {
            cartService.updateCartItem(param);
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
    public Object selectCartAll(@RequestParam Map param) {
        try {
            List<Carts> list = cartService.selectMyCart(param);
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
    public Object delCartItems(@RequestParam Map param) {
        try {
            cartService.delCartItems(param);
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
