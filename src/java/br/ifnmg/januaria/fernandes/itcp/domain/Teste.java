/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifnmg.januaria.fernandes.itcp.domain;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author alisson
 */
@Entity
@Table(name = "teste")
@NamedQueries({
    @NamedQuery(name = "Teste.findAll", query = "SELECT t FROM Teste t")})
public class Teste implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idteste")
    private Integer idteste;
    @Size(max = 45)
    @Column(name = "testecol")
    private String testecol;

    public Teste() {
    }

    public Teste(Integer idteste) {
        this.idteste = idteste;
    }

    public Integer getIdteste() {
        return idteste;
    }

    public void setIdteste(Integer idteste) {
        this.idteste = idteste;
    }

    public String getTestecol() {
        return testecol;
    }

    public void setTestecol(String testecol) {
        this.testecol = testecol;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idteste != null ? idteste.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Teste)) {
            return false;
        }
        Teste other = (Teste) object;
        if ((this.idteste == null && other.idteste != null) || (this.idteste != null && !this.idteste.equals(other.idteste))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.ifnmg.januaria.fernandes.itcp.domain.Teste[ idteste=" + idteste + " ]";
    }
    
}
