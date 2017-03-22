/*
 * 
 * 
 */
package com.apu.seedshopapi;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SeedPacking {
    @XmlElement(required=true)
    public Integer packingId;
    @XmlElement(required=true)
    public String weight;
    @XmlElement(required=true)
    public Integer amount;
    @XmlElement(required=true)
    public Integer packId;
    @XmlElement(required=true)
    public String used;     
}
