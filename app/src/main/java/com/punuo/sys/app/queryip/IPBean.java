package com.punuo.sys.app.queryip;

import org.w3c.dom.ProcessingInstruction;

import java.util.List;

/**
 * Created by asus on 2017/11/20.
 */

public class IPBean {
    private String ret;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    private String ip;
    private List<String> data;

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }


    public String getRet() {
        return ret;
    }

    public void setRet(String ret) {
        this.ret = ret;
    }





}
