package com.example.demo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author wangxc
 */
public class RegexDemo {
    static String str = "　　hhhhhh,　　呵呵,UAPPROJECT_ID='402894cb4833decf014833e04fd70002 ; \n\r */' select ";

    /**
     * 包含回车换行符的处理
     */
    public static void testa() {
        Pattern wp = Pattern.compile("'.*?'", Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
        Matcher m = wp.matcher(str);
        String result = m.replaceAll("");
        System.out.println("===============result====================:" + result);
    }

    /**
     * 包含回车换行符的处理
     */
    public static void testb() {
        String result = str.replaceAll("^[0-9]*$", "");
        System.out.println("===============result====================:" + result);
    }

    public static String getStringNoBlank(String str) {
        if (str != null && !"".equals(str)) {
//            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Pattern p = Pattern.compile("[\\s*|\t|\r|\n_\\u3000]");
//            Pattern p = Pattern.compile("[0-9]");
            Matcher m = p.matcher(str);
            String strNoBlank = m.replaceAll("");
            System.out.println("++++++++：" + strNoBlank);
            String s = str.replaceAll("[a-zA-Z0-9_\\u4e00-\\u9fa5]", "");
            System.out.println("=========:" + s);

            return strNoBlank;
        } else {
            System.out.println("__________:" + str);
            return str;
        }
    }
}

