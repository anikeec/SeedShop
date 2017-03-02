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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author apu
 */
@Entity
@Table(name = "users_authorization")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsersAuthorization.findAll", query = "SELECT u FROM UsersAuthorization u")
    , @NamedQuery(name = "UsersAuthorization.findByUserId", query = "SELECT u FROM UsersAuthorization u WHERE u.userId = :userId")
    , @NamedQuery(name = "UsersAuthorization.findByLogin", query = "SELECT u FROM UsersAuthorization u WHERE u.login = :login")
    , @NamedQuery(name = "UsersAuthorization.findByPasswdHash", query = "SELECT u FROM UsersAuthorization u WHERE u.passwdHash = :passwdHash")})
public class UsersAuthorization implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "user_id")
    private Integer userId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "login")
    private String login;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "passwd_hash")
    private String passwdHash;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Users users;

    public UsersAuthorization() {
    }

    public UsersAuthorization(Integer userId) {
        this.userId = userId;
    }

    public UsersAuthorization(Integer userId, String login, String passwdHash) {
        this.userId = userId;
        this.login = login;
        this.passwdHash = passwdHash;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
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
        if (!(object instanceof UsersAuthorization)) {
            return false;
        }
        UsersAuthorization other = (UsersAuthorization) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.apu.seedshop.jpa.UsersAuthorization[ userId=" + userId + " ]";
    }
    
}
