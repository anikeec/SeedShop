/*
 * 
 * 
 */
package com.apu.seedshopapi;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AnProductItem {
    @XmlElement(required=true)
    public String barcode; 
    @XmlElement(required=true)
    public Integer amount;
}
