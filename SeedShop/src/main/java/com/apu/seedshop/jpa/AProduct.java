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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "a_product")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AProduct.findAll", query = "SELECT a FROM AProduct a")
    , @NamedQuery(name = "AProduct.findByProductId", query = "SELECT a FROM AProduct a WHERE a.productId = :productId")
    , @NamedQuery(name = "AProduct.findByName", query = "SELECT a FROM AProduct a WHERE a.name = :name")
    , @NamedQuery(name = "AProduct.findByUsed", query = "SELECT a FROM AProduct a WHERE a.used = :used")})
public class AProduct implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "product_id")
    private Integer productId;
    @Size(max = 30)
    @Column(name = "name")
    private String name;
    @Column(name = "used")
    private Boolean used;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productId")
    private Collection<Product> productCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parentId")
    private Collection<AProduct> aProductCollection;
    @JoinColumn(name = "parent_id", referencedColumnName = "product_id")
    @ManyToOne
    private AProduct parentId;

    public AProduct() {
    }

    public AProduct(Integer productId) {
        this.productId = productId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
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
    public Collection<Product> getProductCollection() {
        return productCollection;
    }

    public void setProductCollection(Collection<Product> productCollection) {
        this.productCollection = productCollection;
    }

    @XmlTransient
    public Collection<AProduct> getAProductCollection() {
        return aProductCollection;
    }

    public void setAProductCollection(Collection<AProduct> aProductCollection) {
        this.aProductCollection = aProductCollection;
    }

    public AProduct getParentId() {
        return parentId;
    }

    public void setParentId(AProduct parentId) {
        this.parentId = parentId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productId != null ? productId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AProduct)) {
            return false;
        }
        AProduct other = (AProduct) object;
        if ((this.productId == null && other.productId != null) || (this.productId != null && !this.productId.equals(other.productId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.apu.seedshop.jpa.AProduct[ productId=" + productId + " ]";
    }
    
}
