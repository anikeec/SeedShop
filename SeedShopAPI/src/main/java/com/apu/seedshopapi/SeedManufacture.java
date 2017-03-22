/*
 * 
 * 
 */
package com.apu.seedshopapi;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SeedManufacture {
    @XmlElement(required=true)
    public Integer manufactureId;
    @XmlElement(required=true)
    public String name;
    @XmlElement(required=true)
    public String adress;
    @XmlElement(required=true)
    public String used;
}
