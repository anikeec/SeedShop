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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author apu
 */
@Entity
@Table(name = "user_authorization")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserAuthorization.findAll", query = "SELECT u FROM UserAuthorization u")
    , @NamedQuery(name = "UserAuthorization.findByAuthId", query = "SELECT u FROM UserAuthorization u WHERE u.authId = :authId")
    , @NamedQuery(name = "UserAuthorization.findByLogin", query = "SELECT u FROM UserAuthorization u WHERE u.login = :login")
    , @NamedQuery(name = "UserAuthorization.findByPasswdHash", query = "SELECT u FROM UserAuthorization u WHERE u.passwdHash = :passwdHash")
    , @NamedQuery(name = "UserAuthorization.findByUsed", query = "SELECT u FROM UserAuthorization u WHERE u.used = :used")})
public class UserAuthorization implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "auth_id")
    private Long authId;
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
    @Column(name = "used")
    private Boolean used;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private Appuser userId;

    public UserAuthorization() {
    }

    public UserAuthorization(Long authId) {
        this.authId = authId;
    }

    public UserAuthorization(Long authId, String login, String passwdHash) {
        this.authId = authId;
        this.login = login;
        this.passwdHash = passwdHash;
    }

    public Long getAuthId() {
        return authId;
    }

    public void setAuthId(Long authId) {
        this.authId = authId;
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

    public Boolean getUsed() {
        return used;
    }

    public void setUsed(Boolean used) {
        this.used = used;
    }

    public Appuser getUserId() {
        return userId;
    }

    public void setUserId(Appuser userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (authId != null ? authId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserAuthorization)) {
            return false;
        }
        UserAuthorization other = (UserAuthorization) object;
        if ((this.authId == null && other.authId != null) || (this.authId != null && !this.authId.equals(other.authId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.apu.seedshop.jpa.UserAuthorization[ authId=" + authId + " ]";
    }
    
}
