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
@Table(name = "users_gender")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsersGender.findAll", query = "SELECT u FROM UsersGender u")
    , @NamedQuery(name = "UsersGender.findByGenderId", query = "SELECT u FROM UsersGender u WHERE u.genderId = :genderId")
    , @NamedQuery(name = "UsersGender.findByName", query = "SELECT u FROM UsersGender u WHERE u.name = :name")})
public class UsersGender implements Serializable {

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
    private Collection<Users> usersCollection;

    public UsersGender() {
    }

    public UsersGender(Integer genderId) {
        this.genderId = genderId;
    }

    public UsersGender(Integer genderId, String name) {
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
    public Collection<Users> getUsersCollection() {
        return usersCollection;
    }

    public void setUsersCollection(Collection<Users> usersCollection) {
        this.usersCollection = usersCollection;
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
        if (!(object instanceof UsersGender)) {
            return false;
        }
        UsersGender other = (UsersGender) object;
        if ((this.genderId == null && other.genderId != null) || (this.genderId != null && !this.genderId.equals(other.genderId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.apu.seedshop.jpa.UsersGender[ genderId=" + genderId + " ]";
    }
    
}
