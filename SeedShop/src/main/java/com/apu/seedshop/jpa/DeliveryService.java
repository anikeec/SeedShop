/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apu.seedshop.jpa;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author apu
 */
@Entity
@Table(name = "delivery_service")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DeliveryService.findAll", query = "SELECT d FROM DeliveryService d")
    , @NamedQuery(name = "DeliveryService.findByDeliveryId", query = "SELECT d FROM DeliveryService d WHERE d.deliveryId = :deliveryId")
    , @NamedQuery(name = "DeliveryService.findByName", query = "SELECT d FROM DeliveryService d WHERE d.name = :name")
    , @NamedQuery(name = "DeliveryService.findByCollectAvail", query = "SELECT d FROM DeliveryService d WHERE d.collectAvail = :collectAvail")
    , @NamedQuery(name = "DeliveryService.findByUsed", query = "SELECT d FROM DeliveryService d WHERE d.used = :used")})
public class DeliveryService implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "delivery_id")
    private Integer deliveryId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Column(name = "collect_avail")
    private Boolean collectAvail;
    @Column(name = "used")
    private Boolean used;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "deliveryId")
    private Collection<Invoice> invoiceCollection;

    public DeliveryService() {
    }

    public DeliveryService(Integer deliveryId) {
        this.deliveryId = deliveryId;
    }

    public DeliveryService(Integer deliveryId, String name, Boolean collectAvail) {
        this.deliveryId = deliveryId;
        this.name = name;
        this.collectAvail = collectAvail;
    }

    public Integer getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(Integer deliveryId) {
        this.deliveryId = deliveryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getCollectAvail() {
        return collectAvail;
    }

    public void setCollectAvail(Boolean collectAvail) {
        this.collectAvail = collectAvail;
    }

    public Boolean getUsed() {
        return used;
    }

    public void setUsed(Boolean used) {
        this.used = used;
    }

    @XmlTransient
    public Collection<Invoice> getInvoiceCollection() {
        return invoiceCollection;
    }

    public void setInvoiceCollection(Collection<Invoice> invoiceCollection) {
        this.invoiceCollection = invoiceCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (deliveryId != null ? deliveryId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DeliveryService)) {
            return false;
        }
        DeliveryService other = (DeliveryService) object;
        if ((this.deliveryId == null && other.deliveryId != null) || (this.deliveryId != null && !this.deliveryId.equals(other.deliveryId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.apu.seedshop.jpa.DeliveryService[ deliveryId=" + deliveryId + " ]";
    }
    
}
