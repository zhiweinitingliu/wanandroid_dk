package com.wdk.component_base.data.bean;

import java.io.Serializable;

/**
 * Description :
 *
 * @Author : wdk
 * @CreateTiem : 2020/10/14 10:23 AM
 * @LaseModify(最终修改人): wdk
 * @LastModityTime(最终修改时间): 2020/10/14 10:23 AM
 * @LastCheckBy: wdk
 */
public class ResultData<T> implements Serializable {

    private int errorCode;

    private T data;

    private String errorMsg = "";

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
