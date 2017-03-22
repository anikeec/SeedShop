/*
 * 
 * 
 */
package com.apu.seedshopapi;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SeedProductLocation {
    @XmlElement(required=true)
    public Integer locationId;
    @XmlElement(required=true)
    public String name;
    @XmlElement(required=true)
    public String used;     
}
