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
    public String id;
    @XmlElement(required=true)
    public String orderId;
    @XmlElement(required=true)
    public String barcode;
    @XmlElement(required=true)
    public String price;
    @XmlElement(required=true)
    public Integer amount;
         
}
