/*
 * 
 * 
 */
package com.apu.seedshopapi;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SeedProduct {
    @XmlElement(required=true)
    public String barcode;
    @XmlElement(required=true)
    public Integer aProductId;
    @XmlElement(required=true)
    public Integer packingId;
    @XmlElement(required=true)
    public Integer manufactId; 
    @XmlElement(required=true)
    public String price; 
    @XmlElement(required=true)
    public String used;     
}
