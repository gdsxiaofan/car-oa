package com.xiaofan.car.util.lang;

import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Pattern;

/**
 * TODO: 请添加描述
 *
 * @author gongdaoshun
 * @date 2017/8/17
 * @since 1.0.0
 */
public class StringTools {
    private StringTools() {
    }

    /**
     * 用Java自带的函数，判断字符串是否为数字值
     */
    public static boolean isNum1(String str) {
        for (int i = str.length(); --i >= 0; ) {
            if (str.charAt(i) == '-' || str.charAt(i) == '+'
                    || str.charAt(i) == '.')
                continue;
            if (!Character.isDigit(str.charAt(i)))
                return false;
        }
        return true;
    }

    /**
     * 用ASCII码，判断字符串是否为数字值
     */
    public static boolean isNum2(String str) {
        for (int i = str.length(); --i >= 0; ) {
            int chr = str.charAt(i);
            if (chr == 45)
                continue;// -
            if (chr == 46)
                continue;// .
            if (chr == 43)
                continue;// +
            if (chr < 48 || chr > 57) {
                return false;
            }
        }
        return true;
    }

    /**
     * 使用Common包，判断字符串是否为数字值
     *
     * @不支持：-、+、. 符号
     */
    public static boolean isNum3(String str) {
        return StringUtils.isNumeric(str);
    }

    /**
     * 用正则表达式，判断字符串是否为数字值
     */
    public static boolean isNum4(String str) {
        // [0-9]*
        // \\d+\\.{0,1}\\d*
        // ^(-)?\\d+(\\.\\d+)?$
        // -?[0-9]+.?[0-9]+
        // ^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$
        return Pattern.compile("^[-+]?\\d+(\\.\\d+)?$").matcher(str).matches();
    }

    /**
     * 用正则表达式，判断字符串是否为字母值
     */
    public static boolean isLetter(String str) {
        return Pattern.compile("^[a-zA-Z]+$").matcher(str).matches();
    }

    /**
     * 获取字符串中的数字，并转换为long类型
     *
     * @param stepId : TC001 (-002_003)
     */
    public static long getNum(String stepId) {
        // stepId = Pattern.compile("[^0-9]").matcher(stepId).replaceAll("");
        if (stepId == null || "".equals(stepId.trim()))
            return 0;
        stepId = Pattern.compile("[^\\d]").matcher(stepId).replaceAll("");
        stepId = StringUtils.trim(stepId);
        return Converter.parseLong(stepId);
    }

    /**
     * 字符串CN中文编码处理
     */
    public static String cnCoding(String cnStr) {
        try {
            if (cnStr != null && cnStr.length() > 0)
                return new String(cnStr.getBytes("GBK"), "iso-8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return cnStr;
    }

    // 除空格
    public static String trimStr(String str) {
        if (str != null) {
            return StringUtils.trim(str);
        }
        return null;
    }

    /**
     * 清除search字符
     */
    public static String clear(String src, String search) {
        if (src == null)
            return null;
        return StringUtils.replace(src, search, "");
    }

    /**
     * 清除空格" "
     */
    public static String clearBlank(String src) {
        if (src == null)
            return null;
        return clear(src, " ");
    }

    /**
     * 清除  字符集
     */
    public static String clear(String src, String... searchs) {
        if (src == null)
            return null;
        if (searchs == null || searchs.length <= 0)
            return src;
        for (String s : searchs) {
            src = clear(src, s);
        }
        return src;
    }

    /**
     * 删除  字符集
     */
    public static String remove(String src, String... searchs) {
        if (src == null)
            return null;
        if (searchs == null || searchs.length <= 0)
            return src;
        for (String s : searchs) {
            src = StringUtils.remove(src, s);
        }
        return src;
    }

    /**
     * 先Trim后，再清除search字符
     */
    public static String trimClear(String src, String search) {
        if (src == null)
            return null;
        src = StringUtils.trim(src);
        return clear(src, search);
    }

    /**
     * 替换search字符
     */
    public static String replace(String src, String search, String rep) {
        if (rep == null)
            rep = "";
        if (src == null || search == null)
            return src;
        return StringUtils.replace(src, search, rep);
    }

    /**
     * 忽视大小写，替换search字符
     */
    public static String replaceIgnoreCase(String src, String search,
                                           String rep) {
        if (rep == null)
            rep = "";
        if (src == null || search == null)
            return src;
        if (StringUtils.containsIgnoreCase(src, search)) {
            int start = StringUtils.indexOfIgnoreCase(src, search);// >= 0
            String temp = StringUtils.substring(src, start,
                    (start + search.length()));
            src = StringUtils.replace(src, temp, rep);
            temp = null;
            return src;
        }
        return src;
    }

    /**
     * 先Trim后，再替换search字符
     */
    public static String trimReplace(String src, String search, String rep) {
        if (src == null)
            return null;
        src = StringUtils.trim(src);
        return replace(src, search, rep);
    }

    // Byte[]转换为String
    public static String byteToStr(byte[] str) {
        String result = null;
        if (str != null && str.length != 0) {
            result = new String(str);
        }
        return result;
    }

    /**
     * Trim()后, 判断是否是Null || ""
     */
    public static boolean isTrimNull(String str) {
        boolean flag = false;
        if (str == null || str.trim().length() == 0) {// str[i].trim()
            flag = true;
        }
        return flag;
    }

    /**
     * Trim()后, 判断是否是Null || ""
     */
    public static boolean isTrimNull(String... str) {
        boolean flag = false;
        if (str != null && str.length > 0) {
            for (int i = 0; i < str.length; i++) {
                flag = isTrimNull(str[i]);
                if (flag)
                    break;
            }
        }
        return flag;
    }

    /**
     * 要求：不等于null<且>大于0
     */
    public static boolean isLong(Long id) {
        return (id != null && id.longValue() > 0);
    }

    /**
     * 要求：不等于null<且>大于等于min最小值
     */
    public static boolean isByte(Byte status, byte min) {
        return (status != null && status.byteValue() >= min);
    }

    /**
     * 要求：不等于null<且>大于等于min最小值
     */
    public static boolean isInteger(Integer status, int min) {
        return (status != null && status.intValue() >= min);
    }

    /**
     * 要求：不等于null<且>不等0
     */
    public static boolean isLong(Long... ids) {
        boolean flag = true;
        if (ids != null && ids.length > 0) {
            for (int i = 0; i < ids.length; i++) {
                flag = isLong(ids[i]);
                if (!flag)
                    break;
            }
        }
        return flag;
    }

    /**
     * 判断是否是Null || ""
     */
    public static boolean isNull(String str) {
        boolean flag = false;
        if (str == null || str.length() == 0) {
            flag = true;
        }
        return flag;
    }

    /**
     * 判断是否是Null || ""
     */
    public static boolean isNull(String... str) {
        boolean flag = false;
        if (str != null && str.length > 0) {
            for (int i = 0; i < str.length; i++) {
                flag = isNull(str[i]);
                if (flag)
                    break;
            }
        }
        return flag;
    }

    /**
     * 计算"src"字符串中"regex"字符的个数
     */
    public static int count(String src, String regex) {
        int sum = 0;
        if (!isTrimNull(src, regex)) {
            if (src.contains(regex)) {
                // src.replace(regex, "").length()
                // int len = StringUtils.replace(src, regex, "").length();
                int len = StringUtils.remove(src, regex).length();
                sum = (src.length() - len) / regex.length();
            }
        }
        return sum;
    }

    /**
     * 计算"str"字符串中"sub"子字符串的个数
     */
    public static int cntMatches(String str, String sub) {
        return StringUtils.countMatches(str, sub);
    }

    /**
     * 检索第n个参数占位的索引位置
     */
    public static int indexOf(String str, int n, String searchStr) {
        int index = 0;
        for (int i = 0; i < n; i++) {
            int idx = StringUtils.indexOf(str, searchStr) + 1;
            index += idx;
            str = StringUtils.substring(str, idx);
        }
        return index;
    }

    ////////////////////////////////////////////////////////////////////////////////////

    /**
     * 比较两者的内容是否相同, Null 和 ""的情况均返回false
     */
    public static boolean equals(String self, String other) {
        boolean flag = false;
        if (!isTrimNull(self, other)) {
            flag = StringUtils.equalsIgnoreCase(self, other);
        }
        return flag;
    }

    /**
     * 比较内容<包含其一>是否相同, Null 和 ""的情况均返回false
     */
    public static boolean equalsOnce(String self, String... other) {
        boolean flag = false;
        if (self != null && other != null) {
            for (String o : other) {
                flag = StringUtils.equalsIgnoreCase(self, o);
                if (flag)
                    break;
            }
        } else
            flag = false;

        return flag;
    }

    /**
     * 是否同时包含1个非空字符的判断
     */
    public static boolean contains(String src, String search) {
        boolean flag = true;
        if (!isTrimNull(src, search)) {
            flag = StringUtils.containsIgnoreCase(src, search);
        } else
            flag = false;
        return flag;
    }

    /**
     * 是否<同时>包含多个非空字符的判断
     */
    public static boolean contains(String src, String... search) {
        boolean flag = true;
        if (src != null && search != null) {
            for (int i = 0; i < search.length; i++) {
                flag &= StringUtils.containsIgnoreCase(src, search[i]);// TODO =
                // &&
                if (!flag)
                    break;
            }
        } else
            flag = false;
        return flag;
    }

    /**
     * 是否包含<其中之一>的1个非空字符的判断
     */
    public static boolean containsOnce(String src, String... search) {
        boolean flag = false;
        if (src != null && search != null) {
            for (String s : search) {
                flag = StringUtils.containsIgnoreCase(src, s);
                if (flag)
                    break;
            }
        } else
            flag = false;
        return flag;
    }

    /**
     * 是否同时包含1个非空字符的判断
     */
    public static boolean startsWith(String src, String start) {
        boolean flag = true;
        if (!isTrimNull(src, start)) {
            flag = StringUtils.startsWithIgnoreCase(src, start);
        } else
            flag = false;
        return flag;
    }

    /**
     * 是否以<其中之一>非空字符为开始字符的判断
     */
    public static boolean startsWithOnce(String src, String... starts) {
        boolean flag = false;
        if (src != null && starts != null) {
            for (int i = 0; i < starts.length; i++) {
                flag = StringUtils.startsWithIgnoreCase(src, starts[i]);
                if (flag)
                    break;
            }
        } else
            flag = false;
        return flag;
    }

    //////////////////////////////////////////////////////////////////////////////////////

    /**
     * 获取最后一个regex在src中的索引
     */
    public static int lastIndexOf(String src, String regex) {
        int inx = 0;
        if (!isTrimNull(src, regex)) {
            inx = StringUtils.lastIndexOf(src, regex);
        }
        return inx;
    }

    /**
     * 获取"src"字符串中第sn个"regex"的索引
     *
     * @param src:   源字符串
     * @param regex: 包涵字符
     * @param sn:    序号
     */
    public static int indexOf(String src, String regex, int sn) {
        int inx = 0;
        if (!isTrimNull(src, regex)) {
            int len = src.length();
            int sum = count(src, regex);
            if (sn < 0 || sn > sum)
                sn = sum;
            for (int i = 1; i <= sn; i++) {
                int start = StringUtils.indexOf(src, regex) + 1;
                // src = src.substring(src.indexOf(regex) + 1);
                src = StringUtils.substring(src, start);
            }
            inx = len - src.length();
        }
        return inx;
    }

    /**
     * 向指定位置插入指定的字符串
     */
    public static String insertString(int index, String src, String insert) {
        if (!isTrimNull(src, insert) && index > 0)
            return src.substring(0, index) + insert + src.substring(index + 1);
        return src;
    }

    /**
     * 向指定位置插入指定的字符串
     */
    public static String insertString(String src, int index, String insert) {
        if (!isTrimNull(src, insert) && index > 0) {
            StringBuffer sb = new StringBuffer(src);
            sb.insert(index, insert);
            src = sb.toString();
            sb = null;
        }
        return src;
    }

    /**
     * 获取子字符串
     *
     * @param start 开始位置
     * @param end   结束位置
     */
    public static String subString(String str, int start, int end) {
        String sub = null;
        if (str != null) {
            int size = str.length();
            if (start >= 0 && start < end) {
                if (end <= size) {
                    // sub = str.substring(start, end);
                    sub = StringUtils.substring(str, start, end);
                }
            } else {
                if (end <= size) {
                    // sub = str.substring(0, end);
                    sub = StringUtils.substring(str, 0, end);
                }
            }
        }
        return sub;
    }

    /**
     * 获取从searchStr开始往后的子字符串
     */
    public static String subEndString(String str, String searchStr) {
        if (str == null)
            return null;
        return StringUtils.substringAfter(str, searchStr);
    }

    /**
     * 获取从searchStr开始往后的子字符串
     */
    public static String subBetweenString(String str, String start,
                                          String end) {
        if (str == null)
            return null;
        return StringUtils.substringBetween(str, start, end);
    }

    /**
     * 获取从searchStr开始往后的子字符串
     */
    public static String subBetweenTrim(String str, String start, String end) {
        if (str == null)
            return null;
        return StringUtils.substringBetween(str, start, end).trim();
    }

    /**
     * 获取从searchStr开始往后的子字符串
     */
    public static String subStartString(String str, String searchStr) {
        if (str == null)
            return null;
        return StringUtils.substringBefore(str, searchStr);
    }

    /**
     * 根据分隔符，将字符串解析为字段Set集
     */
    public static Set<String> toSet(String src, String split) {
        Set<String> set = new TreeSet<String>();
        if (!isTrimNull(src, split)) {
            if (src.contains(split)) {
                String[] fields = StringUtils.split(src, split); // src.split(
                // split );
                for (int i = 0; i < fields.length; i++) {
                    if (!isTrimNull(fields[i]))
                        set.add(fields[i]);
                }
            } else {
                set.add(src);
            }
        }
        return set;
    }

    /**
     * 是否Exist不匹配的域
     * <注意> 类域的索引、写Excel的列索引、错误列的索引 <注意>
     */
    public static boolean containIndex(List<Integer> idxList, int index) {
        boolean flag = false;
        if (idxList == null || idxList.size() == 0) {
            return false;
        } else {
            for (int i = 0; i < idxList.size(); i++) {
                if (index == idxList.get(i)) {
                    flag = true;
                    break;
                }
            }
        }
        return flag;
    }

    /**
     * 首字母大写
     */
    public static String firstToUpper(String str) {
        if (!isTrimNull(str)) {
            String fst = StringUtils.substring(str, 0, 1).toUpperCase();
            return fst + StringUtils.substring(str, 1);
        }
        return str;
    }

    /**
     * 首字母小写
     */
    public static String firstToLower(String str) {
        if (!isTrimNull(str)) {
            String fst = StringUtils.substring(str, 0, 1).toLowerCase();
            return fst + StringUtils.substring(str, 1);
        }
        return str;
    }

    /**
     * 清除空格" "
     */
    public static String clearNull(String src) {
        if (src == null)
            return null;
        return clearNull(src, " ");
    }

    /**
     * 清除search字符
     */
    public static String clearNull(String src, String search) {
        if (src == null)
            return null;
        return StringUtils.replace(src, search, "");
    }

    /**
     * 先Trim后，再清除search字符
     */
    public static String trimClearNull(String src, String search) {
        if (src == null)
            return null;
        src = StringUtils.trim(src);
        return StringUtils.replace(src, search, "");
    }

    /**
     * 清除search字符集
     */
    public static String replace(String src, String... searchs) {
        if (src == null)
            return null;
        if (searchs != null && searchs.length > 0) {
            for (int i = 0; i < searchs.length; i++) {
                src = StringUtils.replace(src, searchs[i], "");
            }
        }
        return src;
    }

    /**
     * 比对两个String类型的字段数值是否相同
     */
    public static boolean isSame(String thisFld, String otherFld) {
        if ("".equals(thisFld))
            thisFld = null;
        if ("".equals(otherFld))
            otherFld = null;

        if (thisFld == otherFld) {
            return true;
        } else if (thisFld == null || otherFld == null) {
            return false;
        } else {
            return thisFld.toString().trim().equals(otherFld.toString().trim());
        }
    }

    /**
     * 数组List，转换为String其中元素以","分隔。
     */
    public static String list2String(List<Long> list) {
        if (list != null && !list.isEmpty()) {
            String s = list.toString();
            s = StringUtils.replace(s, "[", "");
            s = StringUtils.replace(s, "]", "");
            return s;
        }
        return null;
    }

    /**
     * 字符串比较
     */
    public static int compare(String s1, String s2) {
        if (s1 == null) {
            return -1;
        } else if (s2 == null) {
            return 1;
        } else if (s1.compareTo(s2) < 0) {
            return -1;
        } else if (s1.compareTo(s2) > 0) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * 字符编码转换
     *
     * @param str
     * @param srcCharset
     * @param toCharset
     * @return
     */
    public static String toCharset(String str, String srcCharset,
                                   String toCharset) {
        if (str != null) {
            try {
                if (!StringTools.isTrimNull(str)) {
                    if (!StringTools.isTrimNull(srcCharset))
                        return new String(str.getBytes(srcCharset), toCharset);
                    else
                        return new String(str.getBytes("ISO-8859-1"),
                                toCharset);
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return str;
    }
}
