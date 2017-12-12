package com.dumai.xianjindai.commons;

/**
 * @author hrg
 *         2017年11月17日18:38:13
 */
public class ToUIEvent {

    public static final int CREDIT_CARD_EVENT = 1;        //信用卡列表

    private int what;
    private Object obj;

    public ToUIEvent(int what) {
        this.what = what;
    }

    public ToUIEvent(int what, Object obj) {
        this.what = what;
        this.obj = obj;
    }

    public int getWhat() {
        return what;
    }

    public void setWhat(int what) {
        this.what = what;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

}
