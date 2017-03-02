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
@Table(name = "product_locations")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductLocations.findAll", query = "SELECT p FROM ProductLocations p")
    , @NamedQuery(name = "ProductLocations.findByLocationId", query = "SELECT p FROM ProductLocations p WHERE p.locationId = :locationId")
    , @NamedQuery(name = "ProductLocations.findByName", query = "SELECT p FROM ProductLocations p WHERE p.name = :name")})
public class ProductLocations implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "location_id")
    private Integer locationId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "name")
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "locationId")
    private Collection<Availability> availabilityCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sourceId")
    private Collection<Invoices> invoicesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "destinationId")
    private Collection<Invoices> invoicesCollection1;
    @OneToMany(mappedBy = "currentLocId")
    private Collection<Invoices> invoicesCollection2;

    public ProductLocations() {
    }

    public ProductLocations(Integer locationId) {
        this.locationId = locationId;
    }

    public ProductLocations(Integer locationId, String name) {
        this.locationId = locationId;
        this.name = name;
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

    @XmlTransient
    public Collection<Availability> getAvailabilityCollection() {
        return availabilityCollection;
    }

    public void setAvailabilityCollection(Collection<Availability> availabilityCollection) {
        this.availabilityCollection = availabilityCollection;
    }

    @XmlTransient
    public Collection<Invoices> getInvoicesCollection() {
        return invoicesCollection;
    }

    public void setInvoicesCollection(Collection<Invoices> invoicesCollection) {
        this.invoicesCollection = invoicesCollection;
    }

    @XmlTransient
    public Collection<Invoices> getInvoicesCollection1() {
        return invoicesCollection1;
    }

    public void setInvoicesCollection1(Collection<Invoices> invoicesCollection1) {
        this.invoicesCollection1 = invoicesCollection1;
    }

    @XmlTransient
    public Collection<Invoices> getInvoicesCollection2() {
        return invoicesCollection2;
    }

    public void setInvoicesCollection2(Collection<Invoices> invoicesCollection2) {
        this.invoicesCollection2 = invoicesCollection2;
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
        if (!(object instanceof ProductLocations)) {
            return false;
        }
        ProductLocations other = (ProductLocations) object;
        if ((this.locationId == null && other.locationId != null) || (this.locationId != null && !this.locationId.equals(other.locationId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.apu.seedshop.jpa.ProductLocations[ locationId=" + locationId + " ]";
    }
    
}
