package com.king.flyme.service;

import com.alibaba.fastjson.JSONObject;
import com.king.flyme.bean.GoodsRecord;
import com.king.flyme.bean.GoodsRecordExample;
import com.king.flyme.dao.GoodsRecordMapper;
import org.apache.commons.collections4.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by xinzhendi-031 on 2017/8/21.
 */

@Service
@Transactional
public class ProductService {
    private static final Logger log = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    private GoodsRecordMapper recordMapper;
    @Autowired
    private RecordService recordService;

    /**
     * 查询商品详情
     * 1、商品信息
     * 2、默认地址信息
     * 3、优惠券
     * 4、选择种类
     * */
    public JSONObject selectProductDetail(Map param){
        int account_id = MapUtils.getInteger(param, "account_id", -1);
        if (account_id == -1)
            throw new RuntimeException("该账号不存在");
        String product_id = MapUtils.getString(param, "product_id", "");
        // TODO: 2017/8/25 根据 product_id 查询商品详情
        GoodsRecordExample example = new GoodsRecordExample();
        example.createCriteria().andProductIdEqualTo(product_id);
        List<GoodsRecord> list = recordMapper.selectByExample(example);
        if (list != null && list.size() > 0){
            GoodsRecord goodsRecord = list.get(0);
        }
        // TODO: 2017/8/25 加入历史记录
        recordService.joinRecordItem(param);
        return null;
    }
}
