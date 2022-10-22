package com.example.mall.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumUtil {

    // 判断字符串是否符合int规范
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

}
