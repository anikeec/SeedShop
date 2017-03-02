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
@Table(name = "delivery_services")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DeliveryServices.findAll", query = "SELECT d FROM DeliveryServices d")
    , @NamedQuery(name = "DeliveryServices.findByDeliveryId", query = "SELECT d FROM DeliveryServices d WHERE d.deliveryId = :deliveryId")
    , @NamedQuery(name = "DeliveryServices.findByName", query = "SELECT d FROM DeliveryServices d WHERE d.name = :name")
    , @NamedQuery(name = "DeliveryServices.findByCollectAvail", query = "SELECT d FROM DeliveryServices d WHERE d.collectAvail = :collectAvail")})
public class DeliveryServices implements Serializable {

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
    private int collectAvail;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "deliveryId")
    private Collection<Invoices> invoicesCollection;

    public DeliveryServices() {
    }

    public DeliveryServices(Integer deliveryId) {
        this.deliveryId = deliveryId;
    }

    public DeliveryServices(Integer deliveryId, String name, int collectAvail) {
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

    public int getCollectAvail() {
        return collectAvail;
    }

    public void setCollectAvail(int collectAvail) {
        this.collectAvail = collectAvail;
    }

    @XmlTransient
    public Collection<Invoices> getInvoicesCollection() {
        return invoicesCollection;
    }

    public void setInvoicesCollection(Collection<Invoices> invoicesCollection) {
        this.invoicesCollection = invoicesCollection;
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
        if (!(object instanceof DeliveryServices)) {
            return false;
        }
        DeliveryServices other = (DeliveryServices) object;
        if ((this.deliveryId == null && other.deliveryId != null) || (this.deliveryId != null && !this.deliveryId.equals(other.deliveryId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.apu.seedshop.jpa.DeliveryServices[ deliveryId=" + deliveryId + " ]";
    }
    
}
