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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author apu
 */
@Entity
@Table(name = "products")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Products.findAll", query = "SELECT p FROM Products p")
    , @NamedQuery(name = "Products.findByBarcode", query = "SELECT p FROM Products p WHERE p.barcode = :barcode")
    , @NamedQuery(name = "Products.findByPrice", query = "SELECT p FROM Products p WHERE p.price = :price")})
public class Products implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "barcode")
    private String barcode;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "price")
    private BigDecimal price;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "barcode")
    private Collection<Availability> availabilityCollection;
    @JoinColumn(name = "manufact_id", referencedColumnName = "manufact_id")
    @ManyToOne(optional = false)
    private Manufactures manufactId;
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    @ManyToOne(optional = false)
    private AProduct productId;
    @JoinColumn(name = "packing_id", referencedColumnName = "packing_id")
    @ManyToOne(optional = false)
    private Packings packingId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "barcode")
    private Collection<AnOrder> anOrderCollection;

    public Products() {
    }

    public Products(String barcode) {
        this.barcode = barcode;
    }

    public Products(String barcode, BigDecimal price) {
        this.barcode = barcode;
        this.price = price;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @XmlTransient
    public Collection<Availability> getAvailabilityCollection() {
        return availabilityCollection;
    }

    public void setAvailabilityCollection(Collection<Availability> availabilityCollection) {
        this.availabilityCollection = availabilityCollection;
    }

    public Manufactures getManufactId() {
        return manufactId;
    }

    public void setManufactId(Manufactures manufactId) {
        this.manufactId = manufactId;
    }

    public AProduct getProductId() {
        return productId;
    }

    public void setProductId(AProduct productId) {
        this.productId = productId;
    }

    public Packings getPackingId() {
        return packingId;
    }

    public void setPackingId(Packings packingId) {
        this.packingId = packingId;
    }

    @XmlTransient
    public Collection<AnOrder> getAnOrderCollection() {
        return anOrderCollection;
    }

    public void setAnOrderCollection(Collection<AnOrder> anOrderCollection) {
        this.anOrderCollection = anOrderCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (barcode != null ? barcode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Products)) {
            return false;
        }
        Products other = (Products) object;
        if ((this.barcode == null && other.barcode != null) || (this.barcode != null && !this.barcode.equals(other.barcode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.apu.seedshop.jpa.Products[ barcode=" + barcode + " ]";
    }
    
}
