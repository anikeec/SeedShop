/*
 * 
 * 
 */
package com.apu.seedshopapi;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SeedDeliveryService {
    @XmlElement(required=true)
    public Integer deliveryId;
    @XmlElement(required=true)
    public String name;
    @XmlElement(required=true)
    public String collectAvail;
    @XmlElement(required=true)
    public String used;
}
