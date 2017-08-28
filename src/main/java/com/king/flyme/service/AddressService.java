package com.king.flyme.service;

import com.king.flyme.bean.Account;
import com.king.flyme.bean.Address;
import com.king.flyme.bean.AddressExample;
import com.king.flyme.dao.AddressMapper;
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
public class AddressService {
    private static final Logger log = LoggerFactory.getLogger(AddressService.class);

    @Autowired
    private AddressMapper addressMapper;
    @Autowired
    private AccountService accountService;

    /**
     * 添加地址
     */
    public void addAddress(Map param) {
        int account_id = MapUtils.getInteger(param, "account_id", -1);
        if (account_id == -1)
            throw new RuntimeException("该账号不存在");
        Address address = new Address();
        String name = MapUtils.getString(param, "name");
        String phone = MapUtils.getString(param, "phone");
        String area = MapUtils.getString(param, "area");
        String detail_address = MapUtils.getString(param, "detail_address");
        int state = -1;
        if (StringUtils.isEmpty(name)
                || StringUtils.isEmpty(phone)
                || StringUtils.isEmpty(area)
                || StringUtils.isEmpty(detail_address))
            return;
        AddressExample example = new AddressExample();
        example.createCriteria().andAccountIdEqualTo(account_id);
        List<Address> list = addressMapper.selectByExample(example);
        if (list == null || list.size() == 0)
            state = 1;
        address.setAccountId(account_id);
        Account account = accountService.findUserById(account_id);
        address.setAccountName(account != null ? account.getAccount() : "");
        address.setName(name);
        address.setPhone(phone);
        address.setArea(area);
        address.setAddress(detail_address);
        address.setState(state);
        int insert = addressMapper.insertSelective(address);
        if (insert != 1)
            throw new RuntimeException("添加地址失败");
    }

    /**
     * 更新地址
     */
    public void updateAddress(Map param) {
        int account_id = MapUtils.getInteger(param, "account_id", -1);
        if (account_id == -1)
            throw new RuntimeException("该账号不存在");
        int id = MapUtils.getInteger(param, "id");
        Address address = new Address();
        String name = MapUtils.getString(param, "name");
        String phone = MapUtils.getString(param, "phone");
        String area = MapUtils.getString(param, "area");
        String detail_address = MapUtils.getString(param, "detail_address");
        int state = MapUtils.getInteger(param, "state", -1);
        if (StringUtils.isEmpty(name)
                || StringUtils.isEmpty(phone)
                || StringUtils.isEmpty(area)
                || StringUtils.isEmpty(detail_address))
            return;
        address.setId(id);
        address.setAccountId(account_id);
        Account account = accountService.findUserById(account_id);
        address.setAccountName(account != null ? account.getAccount() : "");
        address.setName(name);
        address.setPhone(phone);
        address.setArea(area);
        address.setAddress(detail_address);
        address.setUpdateTime(new Date());
        if (state == 1) {
            address.setState(state);
            AddressExample example = new AddressExample();
            example.createCriteria().andAccountIdEqualTo(account_id);
            List<Address> list = addressMapper.selectByExample(example);
            for (Address item : list) {
                if (item.getId() != id) {
                    item.setState(-1);
                    addressMapper.updateByPrimaryKeySelective(item);
                }
            }
        }
        int update = addressMapper.updateByPrimaryKeySelective(address);
        if (update != 1)
            throw new RuntimeException("更新地址失败");
    }

    /**
     * 删除地址
     */
    public void delAddress(Map param) {
        int account_id = MapUtils.getInteger(param, "account_id", -1);
        if (account_id == -1)
            throw new RuntimeException("该账号不存在");
        String strAddresses = MapUtils.getString(param, "addresses", "");
        if (StringUtils.isEmpty(strAddresses))
            throw new RuntimeException("地址不存在");
        String[] addresses = strAddresses.split(",");
        for (String address_id : addresses) {
            int id = Integer.parseInt(address_id);
            int del = addressMapper.deleteByPrimaryKey(id);
            if (del != 1) {
                throw new RuntimeException("地址删除失败");
            }
        }
    }

    /**
     * 查询地址
     */
    public List<Address> selectAddress(Map param) {
        int account_id = MapUtils.getInteger(param, "account_id", -1);
        if (account_id == -1)
            throw new RuntimeException("该账号不存在");
        AddressExample example = new AddressExample();
        example.createCriteria().andAccountIdEqualTo(account_id);
        List<Address> list = addressMapper.selectByExample(example);
        return list;
    }

}
