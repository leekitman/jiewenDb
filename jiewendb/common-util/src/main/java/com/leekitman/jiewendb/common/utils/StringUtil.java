package com.leekitman.jiewendb.common.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * @author LeeKITMAN
 */
public class StringUtil {

    public static boolean isEmpty(String string){
        // isEmpty只判定字符串不为null和长度不为0
        return StringUtils.isEmpty(string);
    }

    public static boolean isBlank(String string){
        // 对于制表符(\t)、换行符(\n)、换页符(\f)和回车符(\r)StringUtils.isBlank()均识为空白符。空白符判定为空
        // "\b"为单词边界符，判定为非空
        return StringUtils.isBlank(string);
    }
}
