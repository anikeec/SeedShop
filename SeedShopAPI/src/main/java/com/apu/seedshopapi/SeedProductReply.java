package com.apu.seedshopapi;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SeedProductReply extends SeedGenericReply{
    @XmlElement(required=true)
    public SeedProduct product;
}
