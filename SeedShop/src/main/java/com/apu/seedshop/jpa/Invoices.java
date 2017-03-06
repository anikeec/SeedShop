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
 * @author apu
 */
@Entity
@Table(name = "invoices")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Invoices.findAll", query = "SELECT i FROM Invoices i")
    , @NamedQuery(name = "Invoices.findByOrderId", query = "SELECT i FROM Invoices i WHERE i.orderId = :orderId")
    , @NamedQuery(name = "Invoices.findByOrderDate", query = "SELECT i FROM Invoices i WHERE i.orderDate = :orderDate")
    , @NamedQuery(name = "Invoices.findByPaidDate", query = "SELECT i FROM Invoices i WHERE i.paidDate = :paidDate")
    , @NamedQuery(name = "Invoices.findBySentDate", query = "SELECT i FROM Invoices i WHERE i.sentDate = :sentDate")
    , @NamedQuery(name = "Invoices.findByDiscount", query = "SELECT i FROM Invoices i WHERE i.discount = :discount")
    , @NamedQuery(name = "Invoices.findByPay", query = "SELECT i FROM Invoices i WHERE i.pay = :pay")
    , @NamedQuery(name = "Invoices.findBySecName", query = "SELECT i FROM Invoices i WHERE i.secName = :secName")
    , @NamedQuery(name = "Invoices.findByFirstName", query = "SELECT i FROM Invoices i WHERE i.firstName = :firstName")
    , @NamedQuery(name = "Invoices.findByThirdName", query = "SELECT i FROM Invoices i WHERE i.thirdName = :thirdName")
    , @NamedQuery(name = "Invoices.findByPhone", query = "SELECT i FROM Invoices i WHERE i.phone = :phone")
    , @NamedQuery(name = "Invoices.findByDeliveryOffice", query = "SELECT i FROM Invoices i WHERE i.deliveryOffice = :deliveryOffice")
    , @NamedQuery(name = "Invoices.findByPrepayment", query = "SELECT i FROM Invoices i WHERE i.prepayment = :prepayment")
    , @NamedQuery(name = "Invoices.findByDeclaration", query = "SELECT i FROM Invoices i WHERE i.declaration = :declaration")})
public class Invoices implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "order_id")
    private Integer orderId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "order_date")
    @Temporal(TemporalType.DATE)
    private Date orderDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "paid_date")
    @Temporal(TemporalType.DATE)
    private Date paidDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "sent_date")
    @Temporal(TemporalType.DATE)
    private Date sentDate;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "discount")
    private BigDecimal discount;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pay")
    private BigDecimal pay;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "sec_name")
    private String secName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "first_name")
    private String firstName;
    @Size(max = 20)
    @Column(name = "third_name")
    private String thirdName;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "phone")
    private String phone;
    @Basic(optional = false)
    @NotNull
    @Column(name = "delivery_office")
    private int deliveryOffice;
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
    private Users userId;
    @JoinColumn(name = "delivery_id", referencedColumnName = "delivery_id")
    @ManyToOne(optional = false)
    private DeliveryServices deliveryId;
    @JoinColumn(name = "status_id", referencedColumnName = "status_id")
    @ManyToOne(optional = false)
    private DeliveryStatuses statusId;
    @JoinColumn(name = "source_id", referencedColumnName = "location_id")
    @ManyToOne(optional = false)
    private ProductLocations sourceId;
    @JoinColumn(name = "destination_id", referencedColumnName = "location_id")
    @ManyToOne(optional = false)
    private ProductLocations destinationId;
    @JoinColumn(name = "current_loc_id", referencedColumnName = "location_id")
    @ManyToOne
    private ProductLocations currentLocId;

    public Invoices() {
    }

    public Invoices(Integer orderId) {
        this.orderId = orderId;
    }

    public Invoices(Integer orderId, Date orderDate, Date paidDate, Date sentDate, BigDecimal pay, String secName, String firstName, String phone, int deliveryOffice) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.paidDate = paidDate;
        this.sentDate = sentDate;
        this.pay = pay;
        this.secName = secName;
        this.firstName = firstName;
        this.phone = phone;
        this.deliveryOffice = deliveryOffice;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
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

    public int getDeliveryOffice() {
        return deliveryOffice;
    }

    public void setDeliveryOffice(int deliveryOffice) {
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

    public Users getUserId() {
        return userId;
    }

    public void setUserId(Users userId) {
        this.userId = userId;
    }

    public DeliveryServices getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(DeliveryServices deliveryId) {
        this.deliveryId = deliveryId;
    }

    public DeliveryStatuses getStatusId() {
        return statusId;
    }

    public void setStatusId(DeliveryStatuses statusId) {
        this.statusId = statusId;
    }

    public ProductLocations getSourceId() {
        return sourceId;
    }

    public void setSourceId(ProductLocations sourceId) {
        this.sourceId = sourceId;
    }

    public ProductLocations getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(ProductLocations destinationId) {
        this.destinationId = destinationId;
    }

    public ProductLocations getCurrentLocId() {
        return currentLocId;
    }

    public void setCurrentLocId(ProductLocations currentLocId) {
        this.currentLocId = currentLocId;
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
        if (!(object instanceof Invoices)) {
            return false;
        }
        Invoices other = (Invoices) object;
        if ((this.orderId == null && other.orderId != null) || (this.orderId != null && !this.orderId.equals(other.orderId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.apu.seedshop.jpa.Invoices[ orderId=" + orderId + " ]";
    }
    
}
