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
@Table(name = "pack")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pack.findAll", query = "SELECT p FROM Pack p")
    , @NamedQuery(name = "Pack.findByPackId", query = "SELECT p FROM Pack p WHERE p.packId = :packId")
    , @NamedQuery(name = "Pack.findByName", query = "SELECT p FROM Pack p WHERE p.name = :name")})
public class Pack implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "pack_id")
    private Integer packId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "name")
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "packId")
    private Collection<Packing> packingCollection;

    public Pack() {
    }

    public Pack(Integer packId) {
        this.packId = packId;
    }

    public Pack(Integer packId, String name) {
        this.packId = packId;
        this.name = name;
    }

    public Integer getPackId() {
        return packId;
    }

    public void setPackId(Integer packId) {
        this.packId = packId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public Collection<Packing> getPackingCollection() {
        return packingCollection;
    }

    public void setPackingCollection(Collection<Packing> packingCollection) {
        this.packingCollection = packingCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (packId != null ? packId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pack)) {
            return false;
        }
        Pack other = (Pack) object;
        if ((this.packId == null && other.packId != null) || (this.packId != null && !this.packId.equals(other.packId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.apu.seedshop.jpa.Pack[ packId=" + packId + " ]";
    }
    
}
