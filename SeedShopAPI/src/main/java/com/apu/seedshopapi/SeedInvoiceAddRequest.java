/*
 * 
 * 
 */
package com.apu.seedshopapi;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SeedInvoiceAddRequest {
    @XmlElement(required=true)
    public String sessionId;
    @XmlElement(required=true)
    public SeedInvoice invoice;      
}
