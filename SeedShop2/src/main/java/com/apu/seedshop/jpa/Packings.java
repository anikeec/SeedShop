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
 * @author apu
 */
@Entity
@Table(name = "packings")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Packings.findAll", query = "SELECT p FROM Packings p")
    , @NamedQuery(name = "Packings.findByPackingId", query = "SELECT p FROM Packings p WHERE p.packingId = :packingId")
    , @NamedQuery(name = "Packings.findByWeight", query = "SELECT p FROM Packings p WHERE p.weight = :weight")
    , @NamedQuery(name = "Packings.findByAmount", query = "SELECT p FROM Packings p WHERE p.amount = :amount")})
public class Packings implements Serializable {

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
    @JoinColumn(name = "package_id", referencedColumnName = "package_id")
    @ManyToOne(optional = false)
    private Packages packageId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "packingId")
    private Collection<Products> productsCollection;

    public Packings() {
    }

    public Packings(Integer packingId) {
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

    public Packages getPackageId() {
        return packageId;
    }

    public void setPackageId(Packages packageId) {
        this.packageId = packageId;
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
        hash += (packingId != null ? packingId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Packings)) {
            return false;
        }
        Packings other = (Packings) object;
        if ((this.packingId == null && other.packingId != null) || (this.packingId != null && !this.packingId.equals(other.packingId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.apu.seedshop.jpa.Packings[ packingId=" + packingId + " ]";
    }
    
}
