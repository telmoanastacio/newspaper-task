package com.tsilva.newspapertask.persistence.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Telmo Silva on 05.10.2020.
 */

@Entity
@Table(name = "device_info")
public class DeviceInfo implements Serializable
{
    private static final long serialVersionUID = 48220100L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "screen_width")
    private Integer screenWidth;

    @Column(name = "screen_height")
    private Integer screenHeight;

    @Column(name = "screen_dpi")
    private Integer screenDpi;

    @Column(name = "os_name")
    private String osName;

    @Column(name = "os_version")
    private String osVersion;

    @Column(name = "app_name")
    private String appName;

    @Column(name = "app_version")
    private String appVersion;

    @Column(name = "name")
    private String name;

    @Column(name = "id_str")
    private String idStr;

    @Column(name = "update_ts")
    private Long updateTs;

    @Column(name = "file_name")
    private String fileName;

    @OneToOne(mappedBy = "deviceInfo",
            orphanRemoval = true,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private GetPages getPages;

    public DeviceInfo() {}

    public DeviceInfo(
            Integer screenWidth,
            Integer screenHeight,
            Integer screenDpi,
            String osName,
            String osVersion,
            String appName,
            String appVersion,
            String name,
            String idStr)
    {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.screenDpi = screenDpi;
        this.osName = osName;
        this.osVersion = osVersion;
        this.appName = appName;
        this.appVersion = appVersion;
        this.name = name;
        this.idStr = idStr;
        this.updateTs = new Date().getTime();
        this.fileName = appName + appVersion + updateTs;
    }

    public DeviceInfo(
            Long id,
            Integer screenWidth,
            Integer screenHeight,
            Integer screenDpi,
            String osName,
            String osVersion,
            String appName,
            String appVersion,
            String name,
            String idStr,
            Long updateTs,
            String fileName)
    {
        this.id = id;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.screenDpi = screenDpi;
        this.osName = osName;
        this.osVersion = osVersion;
        this.appName = appName;
        this.appVersion = appVersion;
        this.name = name;
        this.idStr = idStr;
        this.updateTs = updateTs;
        this.fileName = fileName;
    }

    public Long getId()
    {
        return id;
    }

    public Integer getScreenWidth()
    {
        return screenWidth;
    }

    public Integer getScreenHeight()
    {
        return screenHeight;
    }

    public Integer getScreenDpi()
    {
        return screenDpi;
    }

    public String getOsName()
    {
        return osName;
    }

    public String getOsVersion()
    {
        return osVersion;
    }

    public String getAppName()
    {
        return appName;
    }

    public String getAppVersion()
    {
        return appVersion;
    }

    public String getName()
    {
        return name;
    }

    public String getIdStr()
    {
        return idStr;
    }

    public Long getUpdateTs()
    {
        return updateTs;
    }

    public String getFileName()
    {
        return fileName;
    }

    public GetPages getGetPages()
    {
        return getPages;
    }

    public DeviceInfo setGetPages(GetPages getPages)
    {
        this.getPages = getPages;
        return this;
    }
}
