package com.apu.seedshopapi;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SeedAvailabilityReply extends SeedGenericReply{
    @XmlElement(required=true)
    public SeedAvailability availability;
}
