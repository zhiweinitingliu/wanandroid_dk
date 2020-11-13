package com.wdk.component_base.utils;

/*
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license agreements. See the NOTICE
 * file distributed with this work for additional information regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 Unless required by
 * applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */

import android.text.TextUtils;

import java.util.List;

/**
 * <p>
 * Common <code>String</code> manipulation routines.
 * </p>
 * <p/>
 * <p>
 * Originally from <a href="http://jakarta.apache.org/turbine/">Turbine</a>, the GenerationJavaCore library and
 * Velocity. Later a lots methods from commons-lang StringUtils got added too. Gradually smaller additions and fixes
 * have been made over the time by various ASF committers.
 * </p>
 */
public class StringUtils {

    /**
     * 拼接传递过来的字符串 生成新的字符串
     *
     * @param str
     * @return
     */
    public static String appendStrSplicer(String splicer, String... str) {
        StringBuffer sb = new StringBuffer();
        if (!TextUtils.isEmpty(splicer)) {
            for (int i = 0; i < str.length; i++) {
                if (!TextUtils.isEmpty(str[i])) {
                    if (sb.length() > 0) {
                        sb.append(splicer).append(str[i]);
                    } else {
                        sb.append(str[i]);
                    }
                }
            }

        } else {
            for (int i = 0; i < str.length; i++) {
                if (!TextUtils.isEmpty(str[i])) {
                    sb.append(str[i]);
                }
            }
        }
        return sb.toString();
    }

    public static String appendStr(String... str) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < str.length; i++) {
            if (!TextUtils.isEmpty(str[i])) {
                sb.append(str[i]);
            }
        }
        return sb.toString();
    }

    /**
     * 拼接传递过来的字符串 生成新的字符串
     *
     * @param strArray 拼接的字符串
     * @param splitStr 分隔符
     * @return 拼接后的字符串
     */
    public static String appendStr(String[] strArray, String splitStr) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < strArray.length; i++) {
            if (!TextUtils.isEmpty(strArray[i])) {
                if (sb.length() > 0) {
                    sb.append(splitStr).append(strArray[i]);
                } else {
                    sb.append(strArray[i]);
                }
            }
        }
        return sb.toString();
    }

    public static String appendStr(List<String> stringList, String splitStr) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < stringList.size(); i++) {
            if (!TextUtils.isEmpty(stringList.get(i))) {
                if (sb.length() > 0) {
                    sb.append(splitStr).append(stringList.get(i));
                } else {
                    sb.append(stringList.get(i));
                }
            }
        }
        return sb.toString();
    }

}
