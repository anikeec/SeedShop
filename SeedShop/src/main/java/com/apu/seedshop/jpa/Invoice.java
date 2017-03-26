/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apu.seedshop.jpa;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Ksusha
 */
@Entity
@Table(name = "invoice")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Invoice.findAll", query = "SELECT i FROM Invoice i")
    , @NamedQuery(name = "Invoice.findByOrderId", query = "SELECT i FROM Invoice i WHERE i.orderId = :orderId")
    , @NamedQuery(name = "Invoice.findByUserId", query = "SELECT i FROM Invoice i WHERE i.userId = :userId")
    , @NamedQuery(name = "Invoice.findByOrderDate", query = "SELECT i FROM Invoice i WHERE i.orderDate = :orderDate")
    , @NamedQuery(name = "Invoice.findByPaidDate", query = "SELECT i FROM Invoice i WHERE i.paidDate = :paidDate")
    , @NamedQuery(name = "Invoice.findBySentDate", query = "SELECT i FROM Invoice i WHERE i.sentDate = :sentDate")
    , @NamedQuery(name = "Invoice.findByDiscount", query = "SELECT i FROM Invoice i WHERE i.discount = :discount")
    , @NamedQuery(name = "Invoice.findByPay", query = "SELECT i FROM Invoice i WHERE i.pay = :pay")
    , @NamedQuery(name = "Invoice.findBySecName", query = "SELECT i FROM Invoice i WHERE i.secName = :secName")
    , @NamedQuery(name = "Invoice.findByFirstName", query = "SELECT i FROM Invoice i WHERE i.firstName = :firstName")
    , @NamedQuery(name = "Invoice.findByThirdName", query = "SELECT i FROM Invoice i WHERE i.thirdName = :thirdName")
    , @NamedQuery(name = "Invoice.findByPhone", query = "SELECT i FROM Invoice i WHERE i.phone = :phone")
    , @NamedQuery(name = "Invoice.findByCountry", query = "SELECT i FROM Invoice i WHERE i.country = :country")
    , @NamedQuery(name = "Invoice.findByRegion", query = "SELECT i FROM Invoice i WHERE i.region = :region")
    , @NamedQuery(name = "Invoice.findByArea", query = "SELECT i FROM Invoice i WHERE i.area = :area")
    , @NamedQuery(name = "Invoice.findByCity", query = "SELECT i FROM Invoice i WHERE i.city = :city")
    , @NamedQuery(name = "Invoice.findByDeliveryOffice", query = "SELECT i FROM Invoice i WHERE i.deliveryOffice = :deliveryOffice")
    , @NamedQuery(name = "Invoice.findByPrepayment", query = "SELECT i FROM Invoice i WHERE i.prepayment = :prepayment")
    , @NamedQuery(name = "Invoice.findByDeclaration", query = "SELECT i FROM Invoice i WHERE i.declaration = :declaration")})
public class Invoice implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "order_id")
    private Long orderId;
    @Column(name = "order_date")
    @Temporal(TemporalType.DATE)
    private Date orderDate;
    @Column(name = "paid_date")
    @Temporal(TemporalType.DATE)
    private Date paidDate;
    @Column(name = "sent_date")
    @Temporal(TemporalType.DATE)
    private Date sentDate;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "discount")
    private BigDecimal discount;
    @Column(name = "pay")
    private BigDecimal pay;
    @Size(max = 30)
    @Column(name = "sec_name")
    private String secName;
    @Size(max = 20)
    @Column(name = "first_name")
    private String firstName;
    @Size(max = 20)
    @Column(name = "third_name")
    private String thirdName;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 40)
    @Column(name = "phone")
    private String phone;
    @Size(max = 20)
    @Column(name = "country")
    private String country;
    @Size(max = 30)
    @Column(name = "region")
    private String region;
    @Size(max = 30)
    @Column(name = "area")
    private String area;
    @Size(max = 30)
    @Column(name = "city")
    private String city;
    @Column(name = "delivery_office")
    private Integer deliveryOffice;
    @Column(name = "prepayment")
    private Boolean prepayment;
    @Size(max = 30)
    @Column(name = "declaration")
    private String declaration;
    @Lob
    @Size(max = 65535)
    @Column(name = "add_info_u")
    private String addInfoU;
    @Lob
    @Size(max = 65535)
    @Column(name = "add_info_m")
    private String addInfoM;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orderId")
    private Collection<AnOrder> anOrderCollection;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private Appuser userId;
    @JoinColumn(name = "delivery_id", referencedColumnName = "delivery_id")
    @ManyToOne
    private DeliveryService deliveryId;
    @JoinColumn(name = "status_id", referencedColumnName = "status_id")
    @ManyToOne(optional = false)
    private DeliveryStatus statusId;
    @JoinColumn(name = "source_id", referencedColumnName = "location_id")
    @ManyToOne
    private ProductLocation sourceId;
    @JoinColumn(name = "destination_id", referencedColumnName = "location_id")
    @ManyToOne
    private ProductLocation destinationId;
    @JoinColumn(name = "current_loc_id", referencedColumnName = "location_id")
    @ManyToOne
    private ProductLocation currentLocId;
    @OneToMany(mappedBy = "backorderId")
    private Collection<Invoice> invoiceCollection;
    @JoinColumn(name = "backorder_id", referencedColumnName = "order_id")
    @ManyToOne
    private Invoice backorderId;

    public Invoice() {
    }

    public Invoice(Long orderId) {
        this.orderId = orderId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getPaidDate() {
        return paidDate;
    }

    public void setPaidDate(Date paidDate) {
        this.paidDate = paidDate;
    }

    public Date getSentDate() {
        return sentDate;
    }

    public void setSentDate(Date sentDate) {
        this.sentDate = sentDate;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getPay() {
        return pay;
    }

    public void setPay(BigDecimal pay) {
        this.pay = pay;
    }

    public String getSecName() {
        return secName;
    }

    public void setSecName(String secName) {
        this.secName = secName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getThirdName() {
        return thirdName;
    }

    public void setThirdName(String thirdName) {
        this.thirdName = thirdName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getDeliveryOffice() {
        return deliveryOffice;
    }

    public void setDeliveryOffice(Integer deliveryOffice) {
        this.deliveryOffice = deliveryOffice;
    }

    public Boolean getPrepayment() {
        return prepayment;
    }

    public void setPrepayment(Boolean prepayment) {
        this.prepayment = prepayment;
    }

    public String getDeclaration() {
        return declaration;
    }

    public void setDeclaration(String declaration) {
        this.declaration = declaration;
    }

    public String getAddInfoU() {
        return addInfoU;
    }

    public void setAddInfoU(String addInfoU) {
        this.addInfoU = addInfoU;
    }

    public String getAddInfoM() {
        return addInfoM;
    }

    public void setAddInfoM(String addInfoM) {
        this.addInfoM = addInfoM;
    }

    @XmlTransient
    public Collection<AnOrder> getAnOrderCollection() {
        return anOrderCollection;
    }

    public void setAnOrderCollection(Collection<AnOrder> anOrderCollection) {
        this.anOrderCollection = anOrderCollection;
    }

    public Appuser getUserId() {
        return userId;
    }

    public void setUserId(Appuser userId) {
        this.userId = userId;
    }

    public DeliveryService getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(DeliveryService deliveryId) {
        this.deliveryId = deliveryId;
    }

    public DeliveryStatus getStatusId() {
        return statusId;
    }

    public void setStatusId(DeliveryStatus statusId) {
        this.statusId = statusId;
    }

    public ProductLocation getSourceId() {
        return sourceId;
    }

    public void setSourceId(ProductLocation sourceId) {
        this.sourceId = sourceId;
    }

    public ProductLocation getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(ProductLocation destinationId) {
        this.destinationId = destinationId;
    }

    public ProductLocation getCurrentLocId() {
        return currentLocId;
    }

    public void setCurrentLocId(ProductLocation currentLocId) {
        this.currentLocId = currentLocId;
    }

    @XmlTransient
    public Collection<Invoice> getInvoiceCollection() {
        return invoiceCollection;
    }

    public void setInvoiceCollection(Collection<Invoice> invoiceCollection) {
        this.invoiceCollection = invoiceCollection;
    }

    public Invoice getBackorderId() {
        return backorderId;
    }

    public void setBackorderId(Invoice backorderId) {
        this.backorderId = backorderId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderId != null ? orderId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Invoice)) {
            return false;
        }
        Invoice other = (Invoice) object;
        if ((this.orderId == null && other.orderId != null) || (this.orderId != null && !this.orderId.equals(other.orderId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.apu.seedshop.jpa.Invoice[ orderId=" + orderId + " ]";
    }
    
}
