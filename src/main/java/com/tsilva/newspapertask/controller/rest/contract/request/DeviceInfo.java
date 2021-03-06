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
    private ScreenInfo screenInfo;

    @XmlElement(name = "osInfo")
    private OsInfo osInfo;

    @XmlElement(name = "appInfo")
    private AppInfo appInfo;

    @XmlAttribute(name = "name")
    private String name;

    @XmlAttribute(name = "id")
    private String id;

    public DeviceInfo() {}

    public DeviceInfo(ScreenInfo screenInfo, OsInfo osInfo, AppInfo appInfo, String name, String id)
    {
        this.screenInfo = screenInfo;
        this.osInfo = osInfo;
        this.appInfo = appInfo;
        this.name = name;
        this.id = id;
    }

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
