/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apu.seedshop.jpa;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Ksusha
 */
@Entity
@Table(name = "packing")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Packing.findAll", query = "SELECT p FROM Packing p")
    , @NamedQuery(name = "Packing.findByPackingId", query = "SELECT p FROM Packing p WHERE p.packingId = :packingId")
    , @NamedQuery(name = "Packing.findByWeight", query = "SELECT p FROM Packing p WHERE p.weight = :weight")
    , @NamedQuery(name = "Packing.findByAmount", query = "SELECT p FROM Packing p WHERE p.amount = :amount")
    , @NamedQuery(name = "Packing.findByUsed", query = "SELECT p FROM Packing p WHERE p.used = :used")})
public class Packing implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "packing_id")
    private Integer packingId;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "weight")
    private BigDecimal weight;
    @Column(name = "amount")
    private Integer amount;
    @Column(name = "used")
    private Boolean used;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "packingId")
    private Collection<Product> productCollection;
    @JoinColumn(name = "pack_id", referencedColumnName = "pack_id")
    @ManyToOne(optional = false)
    private Pack packId;

    public Packing() {
    }

    public Packing(Integer packingId) {
        this.packingId = packingId;
    }

    public Integer getPackingId() {
        return packingId;
    }

    public void setPackingId(Integer packingId) {
        this.packingId = packingId;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
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

    public Pack getPackId() {
        return packId;
    }

    public void setPackId(Pack packId) {
        this.packId = packId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (packingId != null ? packingId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Packing)) {
            return false;
        }
        Packing other = (Packing) object;
        if ((this.packingId == null && other.packingId != null) || (this.packingId != null && !this.packingId.equals(other.packingId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.apu.seedshop.jpa.Packing[ packingId=" + packingId + " ]";
    }
    
}
