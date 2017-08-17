package com.xiaofan.car.util.lang;

import org.apache.commons.lang3.StringUtils;

/**
 * TODO: 请添加描述
 *
 * @author gongdaoshun
 * @date 2017/8/17
 * @since 1.0.0
 */
public class SmartString {
    /**
     * Smart String
     */
    private StringBuilder smtString;

    /**
     * Smart String some property:
     * 1. tail: <结尾符>, length == 1
     */
    private String tail;
    private boolean isDoTail = false;
    private boolean isKeepNull = false;        //是否保留Null或" ", 默认为false不保留。

    public SmartString(String tail) {
        super();
        this.tail = tail;
        this.isDoTail = false;
        if (this.smtString == null)
            this.smtString = new StringBuilder();
    }

    public SmartString(String tail, boolean isKeepNull) {
        super();
        this.tail = tail;
        this.isDoTail = false;
        this.isKeepNull = isKeepNull;
        if (this.smtString == null)
            this.smtString = new StringBuilder();
    }


    /**
     * 1. 处理尾巴
     */
    private void doTail() {
        if (smtString.length() == 0) {
            this.isDoTail = false;
        } else {
            if (!this.isDoTail) {
                this.smtString.append(tail);
                this.isDoTail = true;
            }
        }
    }

    /**
     * 2.1 处理尾巴以后，再附加后面的String对象
     */
    public void append(String str) {
        if (!StringTools.isTrimNull(str)) {
            doTail();
            this.smtString.append(str);
            this.isDoTail = false;
        } else {
            if (isKeepNull) {//保留Null
                doTail();
                this.smtString.append(" ");
                this.isDoTail = false;
            }
        }
    }

    /**
     * 2.2 处理尾巴以后，再附加后面的对象
     */
    public void append(Object obj) {
        if (obj != null) {
            if (obj instanceof String) {
                append("" + obj);
            } else {
                doTail();
                this.smtString.append(obj);
                this.isDoTail = false;
            }
        } else {
            if (isKeepNull) {//保留Null
                doTail();
                this.smtString.append(" ");
                this.isDoTail = false;
            }
        }
    }


    /**
     * 3. 附加多个字符串
     */
    public void append(String... str) {
        if (str != null) {
            for (int i = 0; i < str.length; i++) {
                append(str[i]);
            }
        }
    }

    /**
     * 4. 附加多个对象
     */
    public void append(Object... obj) {
        if (obj != null) {
            for (int i = 0; i < obj.length; i++) {
                append(obj[i]);
            }
        }
    }

    /**
     * 5. 特殊情况下：在最前面插入1个符号。如：( 、[ 、等
     */
    public void preAppend(String symbol) {
        if (!StringTools.isTrimNull(symbol)) {
            this.smtString.insert(0, symbol + tail);
        }
    }

    /**
     * 6. 特殊情况下：在最前面插入1个特殊的值。如：0 、1 、等
     */
    public void preAppend(Object value) {
        if (value != null) {
            if (value instanceof String) {
                preAppend(value.toString());
            } else {
                this.smtString.insert(0, value + tail);
            }
        }
    }


    /**
     * 重置
     */
    public void reset() {
        this.smtString.delete(0, this.smtString.length());
    }

    public int length() {
        return this.smtString.length();
    }

    public String replaceAll(String regex) {
        return StringUtils.replace(this.smtString.toString(), regex, tail);
    }

    public String replaceAll(String... regex) {
        String str = this.smtString.toString();
        for (int i = 0; i < regex.length; i++) {
            str = StringUtils.replace(str, regex[i], tail);
        }
        return str;
    }


    public String toString() {
        if (smtString != null) {
            return smtString.toString();
        } else {
            return "";
        }
    }


    public StringBuilder getSmtString() {
        return smtString;
    }

    public void setSmtString(StringBuilder smtString) {
        this.smtString = smtString;
    }

    public String getTail() {
        return tail;
    }

    public void setTail(String tail) {
        this.tail = tail;
        this.isDoTail = false;//结尾符变更后，重置处理
    }

    public boolean isDoTail() {
        return isDoTail;
    }

    /**
     * 设置结尾符处理：可跳过1次结尾符处理
     */
    public void setDoTail(boolean isTailed) {
        this.isDoTail = isTailed;
    }

    public boolean setDoTail() {
        //char c = smtString.charAt(smtString.length() - 1);
        //this.isDoTail = this.tail.equals(c);
        this.isDoTail = smtString.toString().endsWith(this.tail);
        return this.isDoTail;
    }

    public boolean isKeepNull() {
        return isKeepNull;
    }

    public void setKeepNull(boolean isKeepNull) {
        this.isKeepNull = isKeepNull;
    }
}
