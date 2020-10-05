package com.tsilva.newspapertask.persistence.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Telmo Silva on 05.10.2020.
 */

@Entity
@Table(name = "get_pages")
public class GetPages implements Serializable
{
    private static final long serialVersionUID = 48220101L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "edition_def_id")
    private Long editionDefId;

    @Column(name = "publication_date")
    private String publicationDate;

    @OneToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "device_info_id")
    private DeviceInfo deviceInfo;

    public GetPages() {}

    public GetPages(Long editionDefId, String publicationDate, DeviceInfo deviceInfo)
    {
        this.editionDefId = editionDefId;
        this.publicationDate = publicationDate;
        this.deviceInfo = deviceInfo;
    }

    public Long getId()
    {
        return id;
    }

    public Long getEditionDefId()
    {
        return editionDefId;
    }

    public String getPublicationDate()
    {
        return publicationDate;
    }

    public DeviceInfo getDeviceInfo()
    {
        return deviceInfo;
    }

    public GetPages setId(Long id)
    {
        this.id = id;
        return this;
    }

    public GetPages setEditionDefId(Long editionDefId)
    {
        this.editionDefId = editionDefId;
        return this;
    }

    public GetPages setPublicationDate(String publicationDate)
    {
        this.publicationDate = publicationDate;
        return this;
    }

    public GetPages setDeviceInfo(DeviceInfo deviceInfo)
    {
        this.deviceInfo = deviceInfo;
        return this;
    }
}
