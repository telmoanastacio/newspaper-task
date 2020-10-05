package com.tsilva.newspapertask.controller.rest.contract.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by Telmo Silva on 05.10.2020.
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "osInfo")
public class OsInfo implements Serializable
{
    private static final long serialVersionUID = 48220059L;

    @XmlAttribute(name = "name")
    public String name;

    @XmlAttribute(name = "version")
    public String version;

    public OsInfo() {}

    public OsInfo(String name, String version)
    {
        this.name = name;
        this.version = version;
    }

    public String getName()
    {
        return name;
    }

    public String getVersion()
    {
        return version;
    }

    public OsInfo setName(String name)
    {
        this.name = name;
        return this;
    }

    public OsInfo setVersion(String version)
    {
        this.version = version;
        return this;
    }
}
