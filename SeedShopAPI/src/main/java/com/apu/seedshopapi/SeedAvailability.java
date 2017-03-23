/*
 * 
 * 
 */
package com.apu.seedshopapi;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SeedAvailability {
    @XmlElement(required=true)
    public Integer id;
    @XmlElement(required=true)
    public String barcode;
    @XmlElement(required=true)
    public Integer locationId;
    @XmlElement(required=true)
    public Integer available;
    @XmlElement(required=true)
    public Integer reserv;
}
