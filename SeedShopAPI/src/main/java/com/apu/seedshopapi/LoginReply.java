/*
 * 
 * 
 */
package com.apu.seedshopapi;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author al
 */
    @XmlRootElement
public class LoginReply {
    @XmlElement
    public String token="";
    @XmlElement
    public SeedUser user;
}
