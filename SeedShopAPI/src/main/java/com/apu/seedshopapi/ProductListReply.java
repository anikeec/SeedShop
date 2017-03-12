package com.apu.seedshopapi;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ProductListReply extends GenericReply{
    @XmlElement(required=true)
    public List<SeedProduct> products = new ArrayList<>();
}
