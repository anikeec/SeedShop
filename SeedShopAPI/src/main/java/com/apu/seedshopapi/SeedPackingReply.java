package com.apu.seedshopapi;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SeedPackingReply extends SeedGenericReply{
    @XmlElement(required=true)
    public SeedPacking packing;
}
