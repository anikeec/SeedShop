package com.apu.seedshopapi;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UserListReply extends GenericReply{
    @XmlElement(required=true)
    public List<SeedsUser> users = new ArrayList<>();
}
