package com.king.flyme.service;

import com.king.flyme.bean.Carts;
import com.king.flyme.bean.CartsExample;
import com.king.flyme.dao.CartsMapper;
import com.king.flyme.dao.CustCartMapper;
import org.apache.commons.collections4.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by xinzhendi-031 on 2017/8/21.
 */

@Service
@Transactional
public class CollectService {
    private static final Logger log = LoggerFactory.getLogger(CollectService.class);

    /**
     * 加入收藏
     * */
    public void joinCollectItem(Map param){
    }

    /**
     * 删除收藏中商品
     * */
    public void delCollectItems(Map param){

    }

    /**
     * 查询收藏中商品
     * */
    public List<Map> selectMyCollect(Map param){
        return null;
    }
}
