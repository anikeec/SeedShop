/*
 * 
 * 
 */
package com.apu.seedshopapi;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AddBasketRequest {
    @XmlElement(required=true)
    public String sessionId;
    @XmlElement(required=true)
    public List<AnProductItem> products = new ArrayList<>();
}
