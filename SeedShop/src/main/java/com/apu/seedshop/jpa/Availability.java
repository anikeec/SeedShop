/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apu.seedshop.jpa;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ksusha
 */
@Entity
@Table(name = "availability")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Availability.findAll", query = "SELECT a FROM Availability a")
    , @NamedQuery(name = "Availability.findById", query = "SELECT a FROM Availability a WHERE a.id = :id")
    , @NamedQuery(name = "Availability.findByLocationId", query = "SELECT a FROM Availability a WHERE a.locationId = :locationId")
    , @NamedQuery(name = "Availability.findByAvailable", query = "SELECT a FROM Availability a WHERE a.available = :available")
    , @NamedQuery(name = "Availability.findByReserv", query = "SELECT a FROM Availability a WHERE a.reserv = :reserv")})
public class Availability implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Column(name = "available")
    private Integer available;
    @Column(name = "reserv")
    private Integer reserv;
    @JoinColumn(name = "location_id", referencedColumnName = "location_id")
    @ManyToOne
    private ProductLocation locationId;
    @JoinColumn(name = "barcode", referencedColumnName = "barcode")
    @ManyToOne
    private Product barcode;

    public Availability() {
    }

    public Availability(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }

    public Integer getReserv() {
        return reserv;
    }

    public void setReserv(Integer reserv) {
        this.reserv = reserv;
    }

    public ProductLocation getLocationId() {
        return locationId;
    }

    public void setLocationId(ProductLocation locationId) {
        this.locationId = locationId;
    }

    public Product getBarcode() {
        return barcode;
    }

    public void setBarcode(Product barcode) {
        this.barcode = barcode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Availability)) {
            return false;
        }
        Availability other = (Availability) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.apu.seedshop.jpa.Availability[ id=" + id + " ]";
    }
    
}
