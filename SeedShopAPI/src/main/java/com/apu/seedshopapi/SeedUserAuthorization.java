/*
 * 
 * 
 */
package com.apu.seedshopapi;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SeedUserAuthorization {
    @XmlElement(required=false)
    public Integer authId;
    @XmlElement(required=false)
    public String login;
    @XmlElement(required=true)
    public String passwdHash;
    @XmlElement(required=true)
    public String role;
    @XmlElement(required=true)
    public String used; 
}
