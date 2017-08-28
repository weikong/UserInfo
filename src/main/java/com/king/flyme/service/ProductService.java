package com.king.flyme.service;

import com.alibaba.fastjson.JSONObject;
import com.king.flyme.bean.GoodsRecord;
import com.king.flyme.bean.GoodsRecordExample;
import com.king.flyme.bean.Product;
import com.king.flyme.bean.ProductExample;
import com.king.flyme.commons.StringUtil;
import com.king.flyme.dao.GoodsRecordMapper;
import com.king.flyme.dao.ProductMapper;
import org.apache.commons.collections4.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

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
    private ProductMapper productMapper;
    @Autowired
    private RecordService recordService;

    /**
     * 查询商品详情
     * 1、商品信息
     * 2、默认地址信息
     * 3、优惠券
     * 4、选择种类
     */
    public Map selectProductDetail(Map param) {
        String product_id = MapUtils.getString(param, "product_id", "");
        if (StringUtils.isEmpty(product_id))
            throw new RuntimeException("商品不存在");
        ProductExample productExample = new ProductExample();
        productExample.createCriteria().andProductIdEqualTo(product_id);
        List<Product> productList = productMapper.selectByExample(productExample);
        if (productList != null && productList.size() > 0) {
            Product product = productList.get(0);
            param.putAll(StringUtil.objectToMap(product));
        }
        recordService.joinRecordItem(param);
        return param;
    }
}
