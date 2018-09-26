package com.example.demo.util;


import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by yongjin on 2016-11-20.
 * 数据库数据处理工具
 */
public class DataUtils {

    //分隔符定义
    public enum Sepatator {
        SEP_COMMA(",","逗号"),SEP_HYPHEN("-","横杠"),SEP_UNDERSTRIKE("_","下横杠"),SEP_VERTICAL("|","垂直");


        private String value;
        private String desc;
        Sepatator(String value,String desc) {
            this.value = value;
            this.desc = desc;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }

    /**
     * 返回去除指定分隔符后的Set集合数据
     * @param origin 源传入数据
     * @param separator 分隔符
     * @return 集合结果
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public static <T> Set<T> getNoSeparatorData(String origin, Sepatator separator,Class<T> clz) {
        String[] strings = origin.split(separator.value);
        Set<T> set = new HashSet<>();

        for (String str: strings) {
            if (clz.getGenericSuperclass() == Number.class) {
                if(!"".equals(str)) {
                    try {
                        Method method = null;
                        Constructor constructor = null;
                        if (clz == Integer.class) {
                            constructor = clz.getDeclaredConstructor(int.class);
                            method = clz.getDeclaredMethod("parseInt",String.class);
                        }else if (clz == Long.class) {
                            constructor = clz.getDeclaredConstructor(long.class);
                            method = clz.getDeclaredMethod("parseLong",String.class);
                        }else if (clz == Float.class) {
                            constructor = clz.getDeclaredConstructor(float.class);
                            method = clz.getDeclaredMethod("parseFloat",String.class);
                        }else if (clz == Double.class) {
                            constructor = clz.getDeclaredConstructor(double.class);
                            method = clz.getDeclaredMethod("parseDouble",String.class);
                        }
                        constructor.setAccessible(true);
                        T t = (T) constructor.newInstance(0);
                        Object obj = method.invoke(t,str);
                        set.add((T) obj);
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }else if (clz == String.class) {
                set.add((T) str);
            }
        }
        return set;
    }

    /**
     * 返回以指定分隔符分隔的字符串
     * @param origin Number类型的Set集合
     * @param sepatator 分隔符
     * @return 字符串
     */
    public static <T> String getSeparatorData(Set<T> origin, Sepatator sepatator) {
        StringBuffer buffer = new StringBuffer();
        for (Object obj:origin) {

            buffer.append(obj).append(sepatator.value);
        }
        String str = buffer.toString();
        if (str.length() > 0 && str.lastIndexOf(sepatator.value) == (str.length()-1)) {
            return str.substring(0,str.length()-1);
        }else {
            return str;
        }
    }

    public static void main(String[] args) {
        Set<String> set = new HashSet<String>();
        set.add("1");
        set.add("2");
        set.add("3");
        String s = getSeparatorData(set,Sepatator.SEP_COMMA);
        Set<String> strings = getNoSeparatorData(s,Sepatator.SEP_COMMA,String.class);
        System.out.println(strings);
    }
}
