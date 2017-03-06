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
@Table(name = "delivery_statuses")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DeliveryStatuses.findAll", query = "SELECT d FROM DeliveryStatuses d")
    , @NamedQuery(name = "DeliveryStatuses.findByStatusId", query = "SELECT d FROM DeliveryStatuses d WHERE d.statusId = :statusId")
    , @NamedQuery(name = "DeliveryStatuses.findByStatus", query = "SELECT d FROM DeliveryStatuses d WHERE d.status = :status")})
public class DeliveryStatuses implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "status_id")
    private Integer statusId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "status")
    private String status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "statusId")
    private Collection<Invoices> invoicesCollection;

    public DeliveryStatuses() {
    }

    public DeliveryStatuses(Integer statusId) {
        this.statusId = statusId;
    }

    public DeliveryStatuses(Integer statusId, String status) {
        this.statusId = statusId;
        this.status = status;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
        hash += (statusId != null ? statusId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DeliveryStatuses)) {
            return false;
        }
        DeliveryStatuses other = (DeliveryStatuses) object;
        if ((this.statusId == null && other.statusId != null) || (this.statusId != null && !this.statusId.equals(other.statusId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.apu.seedshop.jpa.DeliveryStatuses[ statusId=" + statusId + " ]";
    }
    
}
