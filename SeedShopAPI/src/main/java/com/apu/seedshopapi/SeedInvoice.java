/*
 * 
 * 
 */
package com.apu.seedshopapi;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SeedInvoice {
    @XmlElement(required=true)
    public Long orderId;
    @XmlElement(required=true)
    public Long userId;
    @XmlElement(required=true)
    public String orderDate;
    @XmlElement(required=true)
    public String paidDate;
    @XmlElement(required=true)
    public String sentDate;
    @XmlElement(required=true)
    public String discount;
    @XmlElement(required=true)
    public String pay;
    @XmlElement(required=true)
    public String secName;
    @XmlElement(required=true)
    public String firstName;
    @XmlElement(required=true)
    public String thirdName;
    @XmlElement(required=true)
    public String phone;
    @XmlElement(required=true)
    public String declaration; 
    @XmlElement(required=true)
    public String country;
    @XmlElement(required=true)
    public String region;
    @XmlElement(required=true)
    public String area;
    @XmlElement(required=true)
    public String city;
    @XmlElement(required=true)
    public Integer delivery;
    @XmlElement(required=true)
    public Integer deliveryOffice;
    @XmlElement(required=true)
    public String prepayment;
    @XmlElement(required=true)
    public Integer status;
    @XmlElement(required=true)
    public Integer sourceL;
    @XmlElement(required=true)
    public Integer destL;
    @XmlElement(required=true)
    public Integer currL; 
    @XmlElement(required=true)
    public Long backorderId;
    @XmlElement(required=true)
    public String addInfoU;
    @XmlElement(required=true)
    public String addInfoM;
    
}
