package com.dumai.xianjindai.jparser;

/**
 * 名称：JsonLoginObject
 * 描述：登录解析类
 *
 * @author haoruigang
 * @version v1.0
 * @date：2017 2017/11/29 9:41
 */
public class JsonLoginObject {


    /**
     * value : {"msg":"success","code":"1","data":{}}
     */

    private ValueBean value;

    public ValueBean getValue() {
        return value;
    }

    public void setValue(ValueBean value) {
        this.value = value;
    }

    public static class ValueBean {
        /**
         * msg : success
         * code : 1
         * data : {}
         */

        private String msg;
        private String code;
        private DataBean data;

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public DataBean getData() {
            return data;
        }

        public void setData(DataBean data) {
            this.data = data;
        }

        public static class DataBean {
        }
    }
}
