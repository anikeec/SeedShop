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
@Table(name = "appuser")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Appuser.findAll", query = "SELECT a FROM Appuser a")
    , @NamedQuery(name = "Appuser.findByUserId", query = "SELECT a FROM Appuser a WHERE a.userId = :userId")
    , @NamedQuery(name = "Appuser.findBySecName", query = "SELECT a FROM Appuser a WHERE a.secName = :secName")
    , @NamedQuery(name = "Appuser.findByFirstName", query = "SELECT u FROM Appuser u WHERE LOWER(u.firstName) LIKE LOWER(CONCAT('%',:firstName,'%'))")
    , @NamedQuery(name = "Appuser.findByThirdName", query = "SELECT a FROM Appuser a WHERE a.thirdName = :thirdName")
    , @NamedQuery(name = "Appuser.findByEmail", query = "SELECT a FROM Appuser a WHERE a.email = :email")
    , @NamedQuery(name = "Appuser.findByPhones", query = "SELECT a FROM Appuser a WHERE a.phones = :phones")
    , @NamedQuery(name = "Appuser.findByDiscount", query = "SELECT a FROM Appuser a WHERE a.discount = :discount")
    , @NamedQuery(name = "Appuser.findByBirthday", query = "SELECT a FROM Appuser a WHERE a.birthday = :birthday")
    , @NamedQuery(name = "Appuser.findByCountry", query = "SELECT a FROM Appuser a WHERE a.country = :country")
    , @NamedQuery(name = "Appuser.findByRegion", query = "SELECT a FROM Appuser a WHERE a.region = :region")
    , @NamedQuery(name = "Appuser.findByArea", query = "SELECT a FROM Appuser a WHERE a.area = :area")
    , @NamedQuery(name = "Appuser.findByCity", query = "SELECT a FROM Appuser a WHERE a.city = :city")
    , @NamedQuery(name = "Appuser.findByLogin", query = "SELECT a FROM Appuser a WHERE a.login = :login")
    , @NamedQuery(name = "Appuser.findByPasswdHash", query = "SELECT a FROM Appuser a WHERE a.passwdHash = :passwdHash")
    , @NamedQuery(name = "Appuser.findByRole", query = "SELECT a FROM Appuser a WHERE a.role = :role")
    , @NamedQuery(name = "Appuser.findBySessId", query = "SELECT a FROM Appuser a WHERE a.sessId = :sessId")
    , @NamedQuery(name = "Appuser.findByTemp", query = "SELECT a FROM Appuser a WHERE a.temp = :temp")
    , @NamedQuery(name = "Appuser.findByUsed", query = "SELECT a FROM Appuser a WHERE a.used = :used")})
public class Appuser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "user_id")
    private Long userId;
    @Size(max = 30)
    @Column(name = "sec_name")
    private String secName;
    @Size(max = 20)
    @Column(name = "first_name")
    private String firstName;
    @Size(max = 20)
    @Column(name = "third_name")
    private String thirdName;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 30)
    @Column(name = "email")
    private String email;
    @Size(max = 40)
    @Column(name = "phones")
    private String phones;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "discount")
    private BigDecimal discount;
    @Column(name = "birthday")
    @Temporal(TemporalType.DATE)
    private Date birthday;
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
    @Size(max = 25)
    @Column(name = "login")
    private String login;
    @Size(max = 35)
    @Column(name = "passwd_hash")
    private String passwdHash;
    @Size(max = 20)
    @Column(name = "role")
    private String role;
    @Size(max = 32)
    @Column(name = "sess_id")
    private String sessId;
    @Column(name = "temp")
    private Boolean temp;
    @Column(name = "used")
    private Boolean used;
    @JoinColumn(name = "gender_id", referencedColumnName = "gender_id")
    @ManyToOne
    private UserGender genderId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Collection<Invoice> invoiceCollection;

    public Appuser() {
    }

    public Appuser(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhones() {
        return phones;
    }

    public void setPhones(String phones) {
        this.phones = phones;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPasswdHash() {
        return passwdHash;
    }

    public void setPasswdHash(String passwdHash) {
        this.passwdHash = passwdHash;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getSessId() {
        return sessId;
    }

    public void setSessId(String sessId) {
        this.sessId = sessId;
    }

    public Boolean getTemp() {
        return temp;
    }

    public void setTemp(Boolean temp) {
        this.temp = temp;
    }

    public Boolean getUsed() {
        return used;
    }

    public void setUsed(Boolean used) {
        this.used = used;
    }

    public UserGender getGenderId() {
        return genderId;
    }

    public void setGenderId(UserGender genderId) {
        this.genderId = genderId;
    }

    @XmlTransient
    public Collection<Invoice> getInvoiceCollection() {
        return invoiceCollection;
    }

    public void setInvoiceCollection(Collection<Invoice> invoiceCollection) {
        this.invoiceCollection = invoiceCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Appuser)) {
            return false;
        }
        Appuser other = (Appuser) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.apu.seedshop.jpa.Appuser[ userId=" + userId + " ]";
    }
    
}
