package com.king.flyme.service;

import com.king.flyme.bean.GoodsRecord;
import com.king.flyme.bean.GoodsRecordExample;
import com.king.flyme.dao.GoodsRecordMapper;
import org.apache.commons.collections4.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by xinzhendi-031 on 2017/8/21.
 */

@Service
@Transactional
public class RecordService {
    private static final Logger log = LoggerFactory.getLogger(RecordService.class);

    @Autowired
    private GoodsRecordMapper recordMapper;

    /**
     * 加入足迹
     * */
    public void joinRecordItem(Map param){
        try {
            int account_id = MapUtils.getInteger(param, "account_id", -1);
            String product_id = MapUtils.getString(param, "product_id");
            GoodsRecord goodsRecord = null;
            GoodsRecordExample example = new GoodsRecordExample();
            example.createCriteria().andAccountIdEqualTo(account_id).andProductIdEqualTo(product_id);
            List<GoodsRecord> list = recordMapper.selectByExample(example);
            if (list != null && list.size() > 0){
                goodsRecord = list.get(0);
                goodsRecord.setTime(new Date());
                recordMapper.updateByPrimaryKey(goodsRecord);
            } else {
                String name = MapUtils.getString(param, "name","");
                String desc = MapUtils.getString(param, "desc","");
                float price = MapUtils.getFloat(param, "price",0f);
                String address = MapUtils.getString(param, "address","");
                goodsRecord = new GoodsRecord();
                goodsRecord.setAccountId(account_id);
                goodsRecord.setProductId(product_id);
                goodsRecord.setProductName(name);
                goodsRecord.setProductDesc(desc);
                goodsRecord.setPrice(price);
                goodsRecord.setAddress(address);
                recordMapper.insertSelective(goodsRecord);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除足迹中商品
     * */
    public void delRecordItems(Map param){

    }

    /**
     * 查询足迹中商品
     * */
    public List<GoodsRecord> selectMyRecord(Map param){
        int account_id = MapUtils.getInteger(param, "account_id", -1);
        if (account_id == -1)
            throw new RuntimeException("该账号不存在");
        GoodsRecordExample example = new GoodsRecordExample();
        example.createCriteria().andAccountIdEqualTo(account_id);
        List<GoodsRecord> list = recordMapper.selectByExample(example);
        return list;
    }

    /**
     * 清空足迹
     * */
    public void clearRecords(Map param){
        int account_id = MapUtils.getInteger(param, "account_id", -1);
        if (account_id == -1)
            throw new RuntimeException("该账号不存在");
        GoodsRecordExample example = new GoodsRecordExample();
        example.createCriteria().andAccountIdEqualTo(account_id);
        int del = recordMapper.deleteByExample(example);
        log.error("del = "+del);
    }
}
