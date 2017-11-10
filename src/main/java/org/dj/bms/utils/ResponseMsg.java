package org.dj.bms.utils;

/**
 * @author Created by YANGJINLONG on 2017-11-10.
 */
public class ResponseMsg {
    /**
     * 状态
     */
    private boolean status;
    /**
     * 消息
     */
    private String msg;
    /**
     * 数据
     */
    private Object data;

    public ResponseMsg(boolean status) {
        this.status = status;
    }

    public ResponseMsg(boolean status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public ResponseMsg(String msg, boolean status) {
        this.msg = msg;
        this.status = status;
    }

    public ResponseMsg() {}

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
