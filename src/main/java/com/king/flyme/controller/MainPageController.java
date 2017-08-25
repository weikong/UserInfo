package com.king.flyme.controller;

import com.king.flyme.commons.Const;
import com.king.flyme.commons.FileUtil;
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

    @GetMapping("/upload_file")
    public String uploadfile(@RequestParam Map param) {
        try {
            return "file";
        } catch (RuntimeException e) {
            log.error(e.getMessage());
            return "RuntimeException";
        } catch (Exception e) {
            log.error(e.getMessage());
            return "Exception";
        }
    }

    @GetMapping("/main_data")
    @ResponseBody
    public Object selectMainData(@RequestParam Map param) {
        try {
            mainPageService.selectMainData(param);
            return ajax(param);
        } catch (RuntimeException e) {
            log.error(e.getMessage());
            return ajax(e);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ajax(e);
        }
    }

    @GetMapping("/recommend_data")
    @ResponseBody
    public Object selectRecommendData(@RequestParam Map param) {
        try {
            mainPageService.selectRecommendData(param);
            return ajax(param);
        } catch (RuntimeException e) {
            log.error(e.getMessage());
            return ajax(e);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ajax(e);
        }
    }

    @GetMapping("/kuaidi_data")
    @ResponseBody
    public Object selectKuaiDiData(@RequestParam Map param) {
        try {
            String kdType = MapUtils.getString(param,"type",Const.ZhongTong);
            String kdId = MapUtils.getString(param,"id",Const.KuaiDiOrderId);
            String strKuaidi = String.format(Const.KuaiDi,kdType,kdId);
            String info = FileUtil.getNetInfo(strKuaidi);
            return ajax(info);
        } catch (RuntimeException e) {
            log.error(e.getMessage());
            return ajax(e);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ajax(e);
        }
    }



    @GetMapping("/main/mine_info")
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
