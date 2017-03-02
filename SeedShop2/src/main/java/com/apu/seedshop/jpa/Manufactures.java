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
@Table(name = "manufactures")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Manufactures.findAll", query = "SELECT m FROM Manufactures m")
    , @NamedQuery(name = "Manufactures.findByManufactId", query = "SELECT m FROM Manufactures m WHERE m.manufactId = :manufactId")
    , @NamedQuery(name = "Manufactures.findByName", query = "SELECT m FROM Manufactures m WHERE m.name = :name")
    , @NamedQuery(name = "Manufactures.findByAddress", query = "SELECT m FROM Manufactures m WHERE m.address = :address")})
public class Manufactures implements Serializable {

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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "manufactId")
    private Collection<Products> productsCollection;

    public Manufactures() {
    }

    public Manufactures(Integer manufactId) {
        this.manufactId = manufactId;
    }

    public Manufactures(Integer manufactId, String name, String address) {
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

    @XmlTransient
    public Collection<Products> getProductsCollection() {
        return productsCollection;
    }

    public void setProductsCollection(Collection<Products> productsCollection) {
        this.productsCollection = productsCollection;
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
        if (!(object instanceof Manufactures)) {
            return false;
        }
        Manufactures other = (Manufactures) object;
        if ((this.manufactId == null && other.manufactId != null) || (this.manufactId != null && !this.manufactId.equals(other.manufactId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.apu.seedshop.jpa.Manufactures[ manufactId=" + manufactId + " ]";
    }
    
}
