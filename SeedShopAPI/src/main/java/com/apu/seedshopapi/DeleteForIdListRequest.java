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
public class DeleteForIdListRequest {
    @XmlElement(required=true)
    public List<Long> itemsId = new ArrayList<>();
}
