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
@Table(name = "manufacture")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Manufacture.findAll", query = "SELECT m FROM Manufacture m")
    , @NamedQuery(name = "Manufacture.findByManufactId", query = "SELECT m FROM Manufacture m WHERE m.manufactId = :manufactId")
    , @NamedQuery(name = "Manufacture.findByName", query = "SELECT m FROM Manufacture m WHERE m.name = :name")
    , @NamedQuery(name = "Manufacture.findByAddress", query = "SELECT m FROM Manufacture m WHERE m.address = :address")
    , @NamedQuery(name = "Manufacture.findByUsed", query = "SELECT m FROM Manufacture m WHERE m.used = :used")})
public class Manufacture implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "manufact_id")
    private Integer manufactId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "address")
    private String address;
    @Column(name = "used")
    private Boolean used;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "manufactId")
    private Collection<Product> productCollection;

    public Manufacture() {
    }

    public Manufacture(Integer manufactId) {
        this.manufactId = manufactId;
    }

    public Manufacture(Integer manufactId, String name, String address) {
        this.manufactId = manufactId;
        this.name = name;
        this.address = address;
    }

    public Integer getManufactId() {
        return manufactId;
    }

    public void setManufactId(Integer manufactId) {
        this.manufactId = manufactId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getUsed() {
        return used;
    }

    public void setUsed(Boolean used) {
        this.used = used;
    }

    @XmlTransient
    public Collection<Product> getProductCollection() {
        return productCollection;
    }

    public void setProductCollection(Collection<Product> productCollection) {
        this.productCollection = productCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (manufactId != null ? manufactId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Manufacture)) {
            return false;
        }
        Manufacture other = (Manufacture) object;
        if ((this.manufactId == null && other.manufactId != null) || (this.manufactId != null && !this.manufactId.equals(other.manufactId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.apu.seedshop.jpa.Manufacture[ manufactId=" + manufactId + " ]";
    }
    
}
