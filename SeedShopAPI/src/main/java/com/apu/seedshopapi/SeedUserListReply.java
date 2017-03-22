package com.apu.seedshopapi;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SeedUserListReply extends SeedGenericReply{
    @XmlElement(required=true)
    public List<SeedUser> users = new ArrayList<>();
}
