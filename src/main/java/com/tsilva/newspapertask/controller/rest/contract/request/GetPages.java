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
@XmlRootElement(name = "getPages")
public class GetPages implements Serializable
{
    private static final long serialVersionUID = 48220057L;

    @XmlAttribute(name = "editionDefId")
    public Integer editionDefId;

    @XmlAttribute(name = "publicationDate")
    public String publicationDate;

    public Integer getEditionDefId()
    {
        return editionDefId;
    }

    public String getPublicationDate()
    {
        return publicationDate;
    }

    public GetPages setEditionDefId(Integer editionDefId)
    {
        this.editionDefId = editionDefId;
        return this;
    }

    public GetPages setPublicationDate(String publicationDate)
    {
        this.publicationDate = publicationDate;
        return this;
    }
}
