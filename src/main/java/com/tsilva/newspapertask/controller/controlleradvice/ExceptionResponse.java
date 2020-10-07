package com.tsilva.newspapertask.controller.controlleradvice;

import java.io.Serializable;

/**
 * Created by Telmo Silva on 07.10.2020.
 */

public class ExceptionResponse implements Serializable
{
    private static final long serialVersionUID = 48220500L;

    private long timeStamp;
    private int statusCode;
    private String reason;

    public ExceptionResponse(long timeStamp, int statusCode, String reason)
    {
        this.timeStamp = timeStamp;
        this.statusCode = statusCode;
        this.reason = reason;
    }

    public Long getTimeStamp()
    {
        return timeStamp;
    }

    public int getStatusCode()
    {
        return statusCode;
    }

    public String getReason()
    {
        return reason;
    }

    public ExceptionResponse setTimeStamp(Long timeStamp)
    {
        this.timeStamp = timeStamp;
        return this;
    }

    public ExceptionResponse setStatusCode(int statusCode)
    {
        this.statusCode = statusCode;
        return this;
    }

    public ExceptionResponse setReason(String reason)
    {
        this.reason = reason;
        return this;
    }
}
