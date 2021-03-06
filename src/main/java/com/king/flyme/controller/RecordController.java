package com.king.flyme.controller;

import com.king.flyme.bean.GoodsRecord;
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
@RequestMapping("/record")
public class RecordController extends AbsController {
    private static final Logger log = LoggerFactory.getLogger(RecordController.class);

    @Autowired
    private RecordService recordService;

    @PostMapping("/join")
    @ResponseBody
    public Object joinRecordItem(@RequestParam Map param) {
        try {
            recordService.joinRecordItem(param);
            return ajax();
        } catch (RuntimeException e) {
            log.error(e.getMessage());
            return ajax(e);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ajax(e);
        }
    }

    @GetMapping("/select_my")
    @ResponseBody
    public Object selectMyRecord(@RequestParam Map param) {
        try {
            List<GoodsRecord> list = recordService.selectMyRecord(param);
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
    public Object delRecordItems(@RequestParam Map param) {
        try {
            recordService.delRecordItems(param);
            return ajax();
        } catch (RuntimeException e) {
            log.error(e.getMessage());
            return ajax(e);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ajax(e);
        }
    }

    @PostMapping("/clear")
    @ResponseBody
    public Object clearRecords(@RequestParam Map param) {
        try {
            recordService.clearRecords(param);
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
