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
 * @author Ksusha
 */
@Entity
@Table(name = "product_location")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductLocation.findAll", query = "SELECT p FROM ProductLocation p")
    , @NamedQuery(name = "ProductLocation.findByLocationId", query = "SELECT p FROM ProductLocation p WHERE p.locationId = :locationId")
    , @NamedQuery(name = "ProductLocation.findByName", query = "SELECT p FROM ProductLocation p WHERE p.name = :name")
    , @NamedQuery(name = "ProductLocation.findByUsed", query = "SELECT p FROM ProductLocation p WHERE p.used = :used")})
public class ProductLocation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "location_id")
    private Integer locationId;
    @Size(max = 15)
    @Column(name = "name")
    private String name;
    @Column(name = "used")
    private Boolean used;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "locationId")
    private Collection<Availability> availabilityCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sourceId")
    private Collection<Invoice> invoiceCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "destinationId")
    private Collection<Invoice> invoiceCollection1;
    @OneToMany(mappedBy = "currentLocId")
    private Collection<Invoice> invoiceCollection2;

    public ProductLocation() {
    }

    public ProductLocation(Integer locationId) {
        this.locationId = locationId;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getUsed() {
        return used;
    }

    public void setUsed(Boolean used) {
        this.used = used;
    }

    @XmlTransient
    public Collection<Availability> getAvailabilityCollection() {
        return availabilityCollection;
    }

    public void setAvailabilityCollection(Collection<Availability> availabilityCollection) {
        this.availabilityCollection = availabilityCollection;
    }

    @XmlTransient
    public Collection<Invoice> getInvoiceCollection() {
        return invoiceCollection;
    }

    public void setInvoiceCollection(Collection<Invoice> invoiceCollection) {
        this.invoiceCollection = invoiceCollection;
    }

    @XmlTransient
    public Collection<Invoice> getInvoiceCollection1() {
        return invoiceCollection1;
    }

    public void setInvoiceCollection1(Collection<Invoice> invoiceCollection1) {
        this.invoiceCollection1 = invoiceCollection1;
    }

    @XmlTransient
    public Collection<Invoice> getInvoiceCollection2() {
        return invoiceCollection2;
    }

    public void setInvoiceCollection2(Collection<Invoice> invoiceCollection2) {
        this.invoiceCollection2 = invoiceCollection2;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (locationId != null ? locationId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductLocation)) {
            return false;
        }
        ProductLocation other = (ProductLocation) object;
        if ((this.locationId == null && other.locationId != null) || (this.locationId != null && !this.locationId.equals(other.locationId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.apu.seedshop.jpa.ProductLocation[ locationId=" + locationId + " ]";
    }
    
}
