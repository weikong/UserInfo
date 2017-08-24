package com.king.flyme.controller;

import com.king.flyme.service.AccountService;
import com.king.flyme.service.MainPageService;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
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
@RequestMapping("")
public class MainPageController extends AbsController {
    private static final Logger log = LoggerFactory.getLogger(MainPageController.class);

    @Autowired
    private MainPageService mainPageService;

    @GetMapping("/mine/info")
    @ResponseBody
    public Object selectMineInfo(@RequestParam Map param) {
        try {
            mainPageService.selectMineInfo(param);
            return ajax(param);
        } catch (RuntimeException e) {
            log.error(e.getMessage());
            return ajax(e);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ajax(e);
        }
    }

    @GetMapping("/cart_count")
    @ResponseBody
    public Object selectCartCount(@RequestParam Map param) {
        try {
            mainPageService.selectCartCount(param);
            return ajax(param);
        } catch (RuntimeException e) {
            log.error(e.getMessage());
            return ajax(e);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ajax(e);
        }
    }
}
