package com.spir.ggcycling.bean;

/**
 * created by spir
 * Date2019/4/10 Time 20:18
 */
public class oResult {
    int code;
    String msgname;

    public oResult(int code, String msgname) {
        this.code = code;
        this.msgname = msgname;
    }

    @Override
    public String toString() {
        return "oResult{" +
                "code=" + code +
                ", msgname='" + msgname + '\'' +
                '}';
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsgname() {
        return msgname;
    }

    public void setMsgname(String msgname) {
        this.msgname = msgname;
    }
}
