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
    public String userId;
    @XmlElement(required=false)
    public String login;
    @XmlElement(required=false)
    public String role;
    @XmlElement(required=true)
    public String secName;
    @XmlElement(required=true)
    public String firstName;
    @XmlElement(required=true)
    public String thirdName;
    @XmlElement(required=true)
    public Integer genderId; 
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
    @XmlElement(required=true)
    public String temp;
    @XmlElement(required=true)
    public String used; 
}
