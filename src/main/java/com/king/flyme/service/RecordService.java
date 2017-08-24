package com.king.flyme.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by xinzhendi-031 on 2017/8/21.
 */

@Service
@Transactional
public class RecordService {
    private static final Logger log = LoggerFactory.getLogger(RecordService.class);

    /**
     * 加入足迹
     * */
    public void joinRecordItem(Map param){
    }

    /**
     * 删除足迹中商品
     * */
    public void delRecordItems(Map param){

    }

    /**
     * 查询足迹中商品
     * */
    public List<Map> selectMyRecord(Map param){
        return null;
    }
}
