package com.king.flyme.service;

import com.alibaba.fastjson.JSONObject;
import com.king.flyme.bean.Account;
import com.king.flyme.bean.AccountExample;
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
//    @Autowired
//    private PasswordEncoder passwordEncoder;

    public Account findAccountByEmail(String email) {
        if (StringUtils.isEmpty(email))
            return null;
        AccountExample example = new AccountExample();
        example.createCriteria().andEmailEqualTo(email);
        List<Account> list = accountMapper.selectByExample(example);
        if (list != null && list.size() > 0)
            return list.get(0);
        return null;
    }

    /**
     * 添加账号
     */
    public void addAccount(JSONObject object) {
        String email = object.getString("email");
        String phone = object.getString("phone");
        String name = "";
        if (!StringUtils.isEmpty(email) && StringUtil.emailFormat(email)) {
            name = email.split("@")[0];
        } else if (StringUtils.isEmpty(phone)) {
            name = phone;
        } else {
            throw new RuntimeException();
        }
        String password = object.getString("password");
        //设置默认密码
        if (StringUtils.isEmpty(password)) {
            password = "123456";
        }
        Account account = new Account();
        account.setEmail(email);
        account.setName(name);
        account.setPhone(phone);
        account.setPassword(password);
        int insert = accountMapper.insert(account);
        if (insert != 1)
            throw new RuntimeException();
        String[] datas = new String[2];
        datas[4] = "hello !";
        insert = 4;
        log.error("insert = " + insert);
    }

    /**
     * 添加账号
     */
    public void addAccount(Map param) {
        String email = MapUtils.getString(param, "email");
        String phone = MapUtils.getString(param, "phone");
        String name = "";
        if (!StringUtils.isEmpty(email) && StringUtil.emailFormat(email)) {
            name = email.split("@")[0];
        } else if (StringUtil.isNumeric(phone)) {
            name = phone.trim();
        } else {
            throw new RuntimeException("账号为空");
        }
        String password = MapUtils.getString(param, "password");
        //设置默认密码
        if (StringUtils.isEmpty(password)) {
            password = "123456";
        }
        Account account = new Account();
        account.setEmail(email);
        account.setName(name);
        account.setPhone(phone);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        account.setPassword(passwordEncoder.encode(password));
//        account.setPassword(password);
        int insert = accountMapper.insert(account);
        if (insert != 1)
            throw new RuntimeException("账号插入失败");
    }

    public String login(Map param) {
        String loginName = MapUtils.getString(param, "name");
        String password = MapUtils.getString(param, "pwd");
        Account account = findAccountByEmail(loginName);
        String pwd = account.getPassword();
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        boolean isLogin = passwordEncoder.matches(password, pwd);
        return "OK : " + password + " == " + pwd;
    }
}
