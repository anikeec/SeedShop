/*
 * 
 * 
 */
package com.apu.seedshopapi;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SeedDeliveryStatus {
    @XmlElement(required=true)
    public Integer statusId;
    @XmlElement(required=true)
    public String status;
    @XmlElement(required=true)
    public String used;
}
