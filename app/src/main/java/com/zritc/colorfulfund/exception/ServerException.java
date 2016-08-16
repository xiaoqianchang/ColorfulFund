package com.zritc.colorfulfund.exception;

/**
 * $desc$
 * <p>
 * Created by Chang.Xiao on 2016/8/16.
 *
 * @version 1.0
 */
public class ServerException extends RuntimeException {

    public ServerException() {
    }

    public ServerException(String detailMessage) {
        super(detailMessage);
    }

    public ServerException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public ServerException(Throwable throwable) {
        super(throwable);
    }
}
