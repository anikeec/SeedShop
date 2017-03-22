/*
 * 
 * 
 */
package com.apu.seedshopapi;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SeedUserGender {
    @XmlElement(required=true)
    public Integer genderId;
    @XmlElement(required=true)
    public String name;
    @XmlElement(required=true)
    public String used;
}
