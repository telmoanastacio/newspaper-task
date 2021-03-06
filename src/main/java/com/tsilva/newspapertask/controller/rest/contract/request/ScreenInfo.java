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
@XmlRootElement(name = "screenInfo")
public class ScreenInfo implements Serializable
{
    private static final long serialVersionUID = 48220058L;

    @XmlAttribute(name = "width")
    private Integer width;

    @XmlAttribute(name = "height")
    private Integer height;

    @XmlAttribute(name = "dpi")
    private Integer dpi;

    public ScreenInfo() {}

    public ScreenInfo(Integer width, Integer height, Integer dpi)
    {
        this.width = width;
        this.height = height;
        this.dpi = dpi;
    }

    public Integer getWidth()
    {
        return width;
    }

    public Integer getHeight()
    {
        return height;
    }

    public Integer getDpi()
    {
        return dpi;
    }

    public ScreenInfo setWidth(Integer width)
    {
        this.width = width;
        return this;
    }

    public ScreenInfo setHeight(Integer height)
    {
        this.height = height;
        return this;
    }

    public ScreenInfo setDpi(Integer dpi)
    {
        this.dpi = dpi;
        return this;
    }
}
