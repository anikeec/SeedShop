/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apu.seedshop.jpa;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "an_order")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AnOrder.findAll", query = "SELECT a FROM AnOrder a")
    , @NamedQuery(name = "AnOrder.findById", query = "SELECT a FROM AnOrder a WHERE a.id = :id")
    , @NamedQuery(name = "AnOrder.findByPrice", query = "SELECT a FROM AnOrder a WHERE a.price = :price")
    , @NamedQuery(name = "AnOrder.findByAmount", query = "SELECT a FROM AnOrder a WHERE a.amount = :amount")})
public class AnOrder implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "price")
    private BigDecimal price;
    @Basic(optional = false)
    @NotNull
    @Column(name = "amount")
    private int amount;
    @JoinColumn(name = "barcode", referencedColumnName = "barcode")
    @ManyToOne(optional = false)
    private Products barcode;
    @JoinColumn(name = "order_id", referencedColumnName = "order_id")
    @ManyToOne(optional = false)
    private Invoices orderId;

    public AnOrder() {
    }

    public AnOrder(Integer id) {
        this.id = id;
    }

    public AnOrder(Integer id, BigDecimal price, int amount) {
        this.id = id;
        this.price = price;
        this.amount = amount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Products getBarcode() {
        return barcode;
    }

    public void setBarcode(Products barcode) {
        this.barcode = barcode;
    }

    public Invoices getOrderId() {
        return orderId;
    }

    public void setOrderId(Invoices orderId) {
        this.orderId = orderId;
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
        if (!(object instanceof AnOrder)) {
            return false;
        }
        AnOrder other = (AnOrder) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.apu.seedshop.jpa.AnOrder[ id=" + id + " ]";
    }
    
}
