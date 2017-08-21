package com.king.flyme.controller;

import com.alibaba.fastjson.JSONObject;
import com.king.flyme.service.AccountService;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
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
@RestController
@RequestMapping("")
public class TestController extends AbsController {
    private static final Logger log = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private AccountService accountService;

    @GetMapping("/hello")
    public String hello(@RequestParam Map param) {
        try {
            String name = MapUtils.getString(param, "name");
            return StringUtils.isNotEmpty(name) ? name : "Hello Null";
        } catch (RuntimeException e) {
            log.error(e.getMessage());
            return "RuntimeException";
        } catch (Exception e) {
            log.error(e.getMessage());
            return "Exception";
        }
    }

    @GetMapping("/errorpage")
    public String errorpage(@RequestParam Map param) {
        try {
            return "Error Page";
        } catch (RuntimeException e) {
            log.error(e.getMessage());
            return "RuntimeException";
        } catch (Exception e) {
            log.error(e.getMessage());
            return "Exception";
        }
    }

    @GetMapping("/home")
    public String hemo(@RequestParam Map param) {
        try {
            return "Hemo";
        } catch (RuntimeException e) {
            log.error(e.getMessage());
            return "RuntimeException";
        } catch (Exception e) {
            log.error(e.getMessage());
            return "Exception";
        }
    }

    @GetMapping("/log_in")
    public String login(@RequestParam Map param) {
        try {
            return accountService.login(param);
        } catch (RuntimeException e) {
            log.error(e.getMessage());
            return "RuntimeException";
        } catch (Exception e) {
            log.error(e.getMessage());
            return "Exception";
        }
    }
}
