package com.apu.seedshopapi;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SeedAProductReply extends SeedGenericReply{
    @XmlElement(required=true)
    public SeedAProduct aProduct;
}
