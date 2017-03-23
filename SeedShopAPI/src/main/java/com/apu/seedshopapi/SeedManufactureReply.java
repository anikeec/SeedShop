package com.apu.seedshopapi;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SeedManufactureReply extends SeedGenericReply{
    @XmlElement(required=true)
    public SeedManufacture manufacture;
}
