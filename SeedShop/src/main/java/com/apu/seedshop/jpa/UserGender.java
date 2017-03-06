/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apu.seedshop.jpa;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "user_gender")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserGender.findAll", query = "SELECT u FROM UserGender u")
    , @NamedQuery(name = "UserGender.findByGenderId", query = "SELECT u FROM UserGender u WHERE u.genderId = :genderId")
    , @NamedQuery(name = "UserGender.findByName", query = "SELECT u FROM UserGender u WHERE u.name = :name")})
public class UserGender implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "gender_id")
    private Integer genderId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "genderId")
    private Collection<User> userCollection;

    public UserGender() {
    }

    public UserGender(Integer genderId) {
        this.genderId = genderId;
    }

    public UserGender(Integer genderId, String name) {
        this.genderId = genderId;
        this.name = name;
    }

    public Integer getGenderId() {
        return genderId;
    }

    public void setGenderId(Integer genderId) {
        this.genderId = genderId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public Collection<User> getUserCollection() {
        return userCollection;
    }

    public void setUserCollection(Collection<User> userCollection) {
        this.userCollection = userCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (genderId != null ? genderId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserGender)) {
            return false;
        }
        UserGender other = (UserGender) object;
        if ((this.genderId == null && other.genderId != null) || (this.genderId != null && !this.genderId.equals(other.genderId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.apu.seedshop.jpa.UserGender[ genderId=" + genderId + " ]";
    }
    
}
