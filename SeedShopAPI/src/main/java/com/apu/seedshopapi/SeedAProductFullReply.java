package com.apu.seedshopapi;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SeedAProductFullReply extends SeedGenericReply{
    @XmlElement(required=true)
    public SeedAProductFull aProductF;

    public SeedAProductFullReply() {
        this.aProductF = new SeedAProductFull();
    }   
    
}
