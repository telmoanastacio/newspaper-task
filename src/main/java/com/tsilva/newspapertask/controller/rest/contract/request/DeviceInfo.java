package com.tsilva.newspapertask.controller.rest.contract.request;

import javax.xml.bind.annotation.*;
import java.io.Serializable;

/**
 * Created by Telmo Silva on 05.10.2020.
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "deviceInfo")
public class DeviceInfo implements Serializable
{
    private static final long serialVersionUID = 48220056L;

    @XmlElement(name = "screenInfo")
    public ScreenInfo screenInfo;

    @XmlElement(name = "osInfo")
    public OsInfo osInfo;

    @XmlElement(name = "appInfo")
    public AppInfo appInfo;

    @XmlAttribute(name = "name")
    public String name;

    @XmlAttribute(name = "id")
    public String id;

    public ScreenInfo getScreenInfo()
    {
        return screenInfo;
    }

    public OsInfo getOsInfo()
    {
        return osInfo;
    }

    public AppInfo getAppInfo()
    {
        return appInfo;
    }

    public String getName()
    {
        return name;
    }

    public String getId()
    {
        return id;
    }

    public DeviceInfo setScreenInfo(ScreenInfo screenInfo)
    {
        this.screenInfo = screenInfo;
        return this;
    }

    public DeviceInfo setOsInfo(OsInfo osInfo)
    {
        this.osInfo = osInfo;
        return this;
    }

    public DeviceInfo setAppInfo(AppInfo appInfo)
    {
        this.appInfo = appInfo;
        return this;
    }

    public DeviceInfo setName(String name)
    {
        this.name = name;
        return this;
    }

    public DeviceInfo setId(String id)
    {
        this.id = id;
        return this;
    }
}
