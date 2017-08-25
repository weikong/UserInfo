package com.king.flyme.controller;

import com.alibaba.fastjson.JSONObject;
import com.king.flyme.service.ProductService;
import com.king.flyme.service.RecordService;
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
@RequestMapping("/product")
public class ProductController extends AbsController {
    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @GetMapping("/select_detail")
    @ResponseBody
    public Object selectProductDetail(@RequestParam Map param) {
        try {
            JSONObject object = productService.selectProductDetail(param);
            return ajax(object);
        } catch (RuntimeException e) {
            log.error(e.getMessage());
            return ajax(e);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ajax(e);
        }
    }
}
