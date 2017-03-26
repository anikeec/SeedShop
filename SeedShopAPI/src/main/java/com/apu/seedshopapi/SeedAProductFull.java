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
public class SeedAProductFull {
    @XmlElement(required=true)
    public List<SeedAProduct> parent = new ArrayList<>();
    @XmlElement(required=true)
    public SeedAProduct aProduct;
         
}
