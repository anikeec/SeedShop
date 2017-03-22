package com.apu.seedshopapi;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SeedDeliveryServiceReply extends SeedGenericReply{
    @XmlElement(required=true)
    public SeedDeliveryService deliveryService;
}
