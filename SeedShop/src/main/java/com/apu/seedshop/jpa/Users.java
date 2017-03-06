/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apu.seedshop.jpa;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author apu
 */
@Entity
@Table(name = "users")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u")
    , @NamedQuery(name = "Users.findByUserId", query = "SELECT u FROM Users u WHERE u.userId = :userId")
    , @NamedQuery(name = "Users.findByArea", query = "SELECT u FROM Users u WHERE u.area = :area")
    , @NamedQuery(name = "Users.findByBirthday", query = "SELECT u FROM Users u WHERE u.birthday = :birthday")
    , @NamedQuery(name = "Users.findByCity", query = "SELECT u FROM Users u WHERE u.city = :city")
    , @NamedQuery(name = "Users.findByCountry", query = "SELECT u FROM Users u WHERE u.country = :country")
    , @NamedQuery(name = "Users.findByDiscount", query = "SELECT u FROM Users u WHERE u.discount = :discount")
    , @NamedQuery(name = "Users.findByEmail", query = "SELECT u FROM Users u WHERE u.email = :email")
    , @NamedQuery(name = "Users.findByFirstName", query = "SELECT u FROM Users u WHERE u.firstName = :firstName")
    , @NamedQuery(name = "Users.findByPhones", query = "SELECT u FROM Users u WHERE u.phones = :phones")
    , @NamedQuery(name = "Users.findByRegion", query = "SELECT u FROM Users u WHERE u.region = :region")
    , @NamedQuery(name = "Users.findBySecName", query = "SELECT u FROM Users u WHERE u.secName = :secName")
    , @NamedQuery(name = "Users.findByThirdName", query = "SELECT u FROM Users u WHERE u.thirdName = :thirdName")
    , @NamedQuery(name = "Users.findByGenderId", query = "SELECT u FROM Users u WHERE u.genderId = :genderId")})
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "user_id")
    private Integer userId;
    @Size(max = 30)
    @Column(name = "area")
    private String area;
    @Column(name = "birthday")
    @Temporal(TemporalType.DATE)
    private Date birthday;
    @Size(max = 30)
    @Column(name = "city")
    private String city;
    @Size(max = 20)
    @Column(name = "country")
    private String country;
    @Basic(optional = false)
    @NotNull
    @Column(name = "discount")
    private long discount;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "first_name")
    private String firstName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "phones")
    private String phones;
    @Size(max = 30)
    @Column(name = "region")
    private String region;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "sec_name")
    private String secName;
    @Size(max = 20)
    @Column(name = "third_name")
    private String thirdName;
    @Column(name = "gender_id")
    private Integer genderId;

    public Users() {
    }

    public Users(Integer userId) {
        this.userId = userId;
    }

    public Users(Integer userId, long discount, String email, String firstName, String phones, String secName) {
        this.userId = userId;
        this.discount = discount;
        this.email = email;
        this.firstName = firstName;
        this.phones = phones;
        this.secName = secName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public long getDiscount() {
        return discount;
    }

    public void setDiscount(long discount) {
        this.discount = discount;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPhones() {
        return phones;
    }

    public void setPhones(String phones) {
        this.phones = phones;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSecName() {
        return secName;
    }

    public void setSecName(String secName) {
        this.secName = secName;
    }

    public String getThirdName() {
        return thirdName;
    }

    public void setThirdName(String thirdName) {
        this.thirdName = thirdName;
    }

    public Integer getGenderId() {
        return genderId;
    }

    public void setGenderId(Integer genderId) {
        this.genderId = genderId;
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
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.apu.seedshop.jpa.Users[ userId=" + userId + " ]";
    }
    
}
