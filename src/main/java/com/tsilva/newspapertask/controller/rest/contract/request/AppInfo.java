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
@XmlRootElement(name = "appInfo")
public class AppInfo implements Serializable
{
    private static final long serialVersionUID = 48220060L;

    @XmlElement(name = "newspaperName", type = String.class)
    private String newspaperName;

    @XmlElement(name = "version", type = String.class)
    private String version;

    public AppInfo() {}

    public AppInfo(String newspaperName, String version)
    {
        this.newspaperName = newspaperName;
        this.version = version;
    }

    public String getNewspaperName()
    {
        return newspaperName;
    }

    public String getVersion()
    {
        return version;
    }

    public AppInfo setNewspaperName(String newspaperName)
    {
        this.newspaperName = newspaperName;
        return this;
    }

    public AppInfo setVersion(String version)
    {
        this.version = version;
        return this;
    }
}
