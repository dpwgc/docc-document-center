package com.dpwgc.document.center.infrastructure.exception;

public class InnerException extends RuntimeException {
    private String msgInfo;

    private InnerExceptionLevel liveExceptionLevel;

    public InnerException() {
        super();
    }

    public InnerException(String msgInfo) {
        super(msgInfo);
        this.msgInfo = msgInfo;
        this.liveExceptionLevel = InnerExceptionLevel.ERROR;
    }

    public InnerException(String msgInfo, InnerExceptionLevel exceptionLevel) {
        super(msgInfo);
        this.msgInfo = msgInfo;
        this.liveExceptionLevel = exceptionLevel;
    }

    public InnerException(String msgInfo, Throwable cause) {
        super(cause);
        this.msgInfo = msgInfo;
    }

    public String getMsgInfo() {
        return msgInfo;
    }

    public void setMsgInfo(String msgInfo) {
        this.msgInfo = msgInfo;
    }

    public InnerExceptionLevel getLiveExceptionLevel() {
        return liveExceptionLevel;
    }

    public void setLiveExceptionLevel(InnerExceptionLevel liveExceptionLevel) {
        this.liveExceptionLevel = liveExceptionLevel;
    }
}
