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
    public String name;
    @XmlElement(required=true)
    public String weight;
    @XmlElement(required=true)
    public String amount;
    @XmlElement(required=true)
    public String manufact; 
    @XmlElement(required=true)
    public String pack;
    @XmlElement(required=true)
    public String price; 
    @XmlElement(required=true)
    public String used;     
}
