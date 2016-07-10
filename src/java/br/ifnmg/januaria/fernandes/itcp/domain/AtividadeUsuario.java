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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author alisson
 */
@Entity
@Table(name = "atividadeUsuario")
@NamedQueries({
    @NamedQuery(name = "AtividadeUsuario.findAll", query = "SELECT a FROM AtividadeUsuario a")})
public class AtividadeUsuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "atividadeFk", referencedColumnName = "idAtividade")
    @ManyToOne
    private AtividadeExecutada atividadeExecutada;
    @JoinColumn(name = "usuarioFk", referencedColumnName = "idUsuario")
    @ManyToOne
    private Usuario usuario;

    public AtividadeUsuario() {
    }

    public AtividadeUsuario(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AtividadeExecutada getAtividadeExecutada() {
        return atividadeExecutada;
    }

    public void setAtividadeExecutada(AtividadeExecutada atividadeExecutada) {
        this.atividadeExecutada = atividadeExecutada;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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
        if (!(object instanceof AtividadeUsuario)) {
            return false;
        }
        AtividadeUsuario other = (AtividadeUsuario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.ifnmg.januaria.fernandes.itcp.domain.AtividadeUsuario[ id=" + id + " ]";
    }
    
}
