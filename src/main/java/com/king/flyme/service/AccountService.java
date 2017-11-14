package com.king.flyme.service;

import com.alibaba.fastjson.JSONObject;
import com.king.flyme.bean.Account;
import com.king.flyme.bean.AccountExample;
import com.king.flyme.commons.Const;
import com.king.flyme.commons.StringUtil;
import com.king.flyme.dao.AccountMapper;
import org.apache.commons.collections4.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by xinzhendi-031 on 2017/8/21.
 */

@Service
@Transactional
public class AccountService {
    private static final Logger log = LoggerFactory.getLogger(AccountService.class);

    @Autowired
    private AccountMapper accountMapper;

    /**
     * 通过邮箱或电话查询用户信息
     * */
    public Account findUserByAccount(String account) {
        if (StringUtils.isEmpty(account))
            return null;
        AccountExample example = new AccountExample();
        example.createCriteria().andAccountEqualTo(account);
        List<Account> list = accountMapper.selectByExample(example);
        if (list != null && list.size() > 0)
            return list.get(0);
        return null;
    }

    /**
     * 通过ID查询用户信息
     * */
    public Account findUserById(int id) {
        if (id <= 0)
            throw new RuntimeException("该账号不存在");
        Account account = accountMapper.selectByPrimaryKey(id);
        return account;
    }

    /**
     * 添加账号
     */
    public void addAccount(Map param) {
        String strAccount = MapUtils.getString(param, "account");
        String name = "";
        int type = 1;
        if (!StringUtils.isEmpty(strAccount) && StringUtil.emailFormat(strAccount)) {
            strAccount = strAccount.trim();
            name = strAccount.trim().split("@")[0];
            type = 1;
        } else if (StringUtil.isMobile(strAccount) || StringUtil.isPhone(strAccount)) {
            strAccount = strAccount.trim();
            name = strAccount.trim();
            type = 2;
        } else {
            throw new RuntimeException("账号不正确");
        }
        String password = MapUtils.getString(param, "password");
        //设置默认密码
        if (StringUtils.isEmpty(password)) {
            throw new RuntimeException("请输入密码");
        }
        Account account = new Account();
        account.setAccount(strAccount);
        account.setName(name);
        account.setType(type);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        account.setPassword(passwordEncoder.encode(password));
        int insert = accountMapper.insert(account);
        if (insert != 1)
            throw new RuntimeException("账号插入失败");
    }

    public String login(Map param) {
        String loginName = MapUtils.getString(param, "name");
        String password = MapUtils.getString(param, "pwd");
        Account account = findUserByAccount(loginName);
        String pwd = account.getPassword();
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        boolean isLogin = passwordEncoder.matches(password, pwd);
        return "OK : " + password + " == " + pwd;
    }
}
