package com.wdk.baselibrary.network.error;

/**
 * Description :
 *
 * @Author : wdk
 * @CreateTiem : 2020/10/13 8:51 PM
 * @LaseModify(最终修改人): wdk
 * @LastModityTime(最终修改时间): 2020/10/13 8:51 PM
 * @LastCheckBy: wdk
 */
public class NetWorkException extends RuntimeException {
    private int errorCode;

    private String errorMsg;

    public int getErrorCode() {
        return errorCode;
    }

    public NetWorkException setErrorCode(int errorCode) {
        this.errorCode = errorCode;
        return this;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public NetWorkException setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
        return this;
    }
}
