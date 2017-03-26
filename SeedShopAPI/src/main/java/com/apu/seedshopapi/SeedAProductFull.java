/*
 * 
 * 
 */
package com.apu.seedshopapi;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SeedAProduct {
    @XmlElement(required=true)
    public Integer productId;
    @XmlElement(required=true)
    public Integer parentId;
    @XmlElement(required=true)
    public String name;    
    @XmlElement(required=true)
    public String used;
         
}
