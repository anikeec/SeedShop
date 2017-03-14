/*
 * 
 * 
 */
package com.apu.seedshopapi;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SeedUser {
    @XmlElement(required=false)
    public Long userId;
    @XmlElement(required=false)
    public String login;
    @XmlElement(required=true)
    public String secName;
    @XmlElement(required=true)
    public String firstName;
    @XmlElement(required=true)
    public String thirdName;
    @XmlElement(required=true)
    public String gender; 
    @XmlElement(required=true)
    public String email;
    @XmlElement(required=true)
    public String phones;
    @XmlElement(required=true)
    public String discount;
    @XmlElement(required=true)
    public String birthday;
    @XmlElement(required=true)
    public String country;
    @XmlElement(required=true)
    public String region;
    @XmlElement(required=true)
    public String area;
    @XmlElement(required=true)
    public String city;      
}
