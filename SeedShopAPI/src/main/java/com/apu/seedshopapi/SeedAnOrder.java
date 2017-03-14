/*
 * 
 * 
 */
package com.apu.seedshopapi;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SeedAnOrder {
    @XmlElement(required=false)
    public Integer id;
    @XmlElement(required=true)
    public Long orderId;
    @XmlElement(required=true)
    public String barcode;
    @XmlElement(required=true)
    public String price;
    @XmlElement(required=true)
    public Integer amount;
         
}
