/*
 * 
 * 
 */
package com.apu.seedshopapi;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SeedPack {
    @XmlElement(required=true)
    public Integer packId;
    @XmlElement(required=true)
    public String name;
    @XmlElement(required=true)
    public String used;     
}
