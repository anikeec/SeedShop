/*
 * 
 * 
 */
package com.apu.seedshopapi;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class BasketItem {
    @XmlElement(required=true)
    public Long orderId;     
    @XmlElement(required=true)
    public Integer count;
    @XmlElement(required=true)
    public String price;
    @XmlElement(required=true)
    public SeedProduct product;
}
