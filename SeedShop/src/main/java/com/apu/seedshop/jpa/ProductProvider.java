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
 * @author apu
 */
@Entity
@Table(name = "product_provider")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductProvider.findAll", query = "SELECT p FROM ProductProvider p")
    , @NamedQuery(name = "ProductProvider.findById", query = "SELECT p FROM ProductProvider p WHERE p.id = :id")})
public class ProductProvider implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    @ManyToOne(optional = false)
    private AProduct productId;
    @JoinColumn(name = "provider_id", referencedColumnName = "provider_id")
    @ManyToOne(optional = false)
    private Provider providerId;

    public ProductProvider() {
    }

    public ProductProvider(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AProduct getProductId() {
        return productId;
    }

    public void setProductId(AProduct productId) {
        this.productId = productId;
    }

    public Provider getProviderId() {
        return providerId;
    }

    public void setProviderId(Provider providerId) {
        this.providerId = providerId;
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
        if (!(object instanceof ProductProvider)) {
            return false;
        }
        ProductProvider other = (ProductProvider) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.apu.seedshop.jpa.ProductProvider[ id=" + id + " ]";
    }
    
}
