package com.tsilva.newspapertask.controller.rest.contract.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by Telmo Silva on 05.10.2020.
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "epaperRequest")
public class EpaperRequest implements Serializable
{
    private static final long serialVersionUID = 48220055L;

    @XmlElement(name = "deviceInfo", type = DeviceInfo.class)
    public DeviceInfo deviceInfo;

    @XmlElement(name = "getPages", type = GetPages.class)
    public GetPages getPages;

    public EpaperRequest() {}

    public EpaperRequest(DeviceInfo deviceInfo, GetPages getPages)
    {
        this.deviceInfo = deviceInfo;
        this.getPages = getPages;
    }

    public DeviceInfo getDeviceInfo()
    {
        return deviceInfo;
    }

    public GetPages getGetPages()
    {
        return getPages;
    }

    public EpaperRequest setDeviceInfo(DeviceInfo deviceInfo)
    {
        this.deviceInfo = deviceInfo;
        return this;
    }

    public EpaperRequest setGetPages(GetPages getPages)
    {
        this.getPages = getPages;
        return this;
    }
}
