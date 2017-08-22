package com.king.flyme.controller;

import com.alibaba.fastjson.JSONObject;
import com.king.flyme.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by xinzhendi-031 on 2017/8/21.
 * 用户相关操作
 */
@Controller
@RequestMapping("")
public class AccountController extends AbsController {
    private static final Logger log = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private AccountService accountService;

    @GetMapping("index")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login(@RequestParam Map param) {
        try {
            return "login";
        } catch (RuntimeException e) {
            log.error(e.getMessage());
            return "RuntimeException";
        } catch (Exception e) {
            log.error(e.getMessage());
            return "Exception";
        }
    }

    @PostMapping
    @ResponseBody
    public Object addAccount(@RequestBody String body) {
        try {
            JSONObject object = JSONObject.parseObject(body);
            accountService.addAccount(object);
            return ajax();
        } catch (RuntimeException e) {
            log.error(e.getMessage());
            return ajax(e);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ajax(e);
        }
    }

    @GetMapping("add/account")
    @ResponseBody
    public Object addAccount(@RequestParam Map param) {
        try {
            accountService.addAccount(param);
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
