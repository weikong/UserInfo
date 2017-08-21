package com.king.flyme.commons;

import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by xinzhendi-031 on 2017/8/21.
 */
public class StringUtil {

    /**
     * 验证输入的邮箱格式是否符合
     *
     * @param email
     * @return 是否合法
     */
    public static boolean emailFormat(String email) {
        final String pattern1 = "^(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w+)+)$";
        final Pattern pattern = Pattern.compile(pattern1);
        final Matcher mat = pattern.matcher(email);
        return mat.matches();
    }

    //3.还是正则表达式
    public static boolean isNumeric(String str) {
        if (StringUtils.isEmpty(str))
            return false;
        if (str.matches("//d*")){
            return true;
        } else {
            return false;
        }
    }
}
