/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifnmg.januaria.fernandes.itcp.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author alisson
 */
@Entity
@Table(name = "AtividadeExecutada")
@NamedQueries({
    @NamedQuery(name = "AtividadeExecutada.findAll", query = "SELECT a FROM AtividadeExecutada a")})
public class AtividadeExecutada implements Serializable {

    @JoinTable(name = "AtiviExeParce", joinColumns = {
        @JoinColumn(name = "idAtividadeExe", referencedColumnName = "idAtividade")}, inverseJoinColumns = {
        @JoinColumn(name = "idParceiro", referencedColumnName = "idparceiro")})
    @ManyToMany
    private List<Parceiro> parceiroList;
    @JoinTable(name = "AtiviExeUsua", joinColumns = {
        @JoinColumn(name = "iDAtividadeExe", referencedColumnName = "idAtividade")}, inverseJoinColumns = {
        @JoinColumn(name = "iDUsuario", referencedColumnName = "idUsuario")})
    @ManyToMany
    private List<Usuario> usuarioList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idAtividade")
    private Integer idAtividade;
    @Size(max = 45)
    @Column(name = "nomeAtividade")
    private String nomeAtividade;
    @Size(max = 45)
    @Column(name = "descricaoAtividade")
    private String descricaoAtividade;
    @Size(max = 45)
    @Column(name = "statusAtividade")
    private String statusAtividade;
    @Size(max = 45)
    @Column(name = "dataInicioAtividade")
    private String dataInicioAtividade;
    @Size(max = 45)
    @Column(name = "dataFimAtividade")
    private String dataFimAtividade;

    public AtividadeExecutada() {
    }

    public AtividadeExecutada(Integer idAtividade) {
        this.idAtividade = idAtividade;
    }

    public Integer getIdAtividade() {
        return idAtividade;
    }

    public void setIdAtividade(Integer idAtividade) {
        this.idAtividade = idAtividade;
    }

    public String getNomeAtividade() {
        return nomeAtividade;
    }

    public void setNomeAtividade(String nomeAtividade) {
        this.nomeAtividade = nomeAtividade;
    }

    public String getDescricaoAtividade() {
        return descricaoAtividade;
    }

    public void setDescricaoAtividade(String descricaoAtividade) {
        this.descricaoAtividade = descricaoAtividade;
    }

    public String getStatusAtividade() {
        return statusAtividade;
    }

    public void setStatusAtividade(String statusAtividade) {
        this.statusAtividade = statusAtividade;
    }

    public String getDataInicioAtividade() {
        return dataInicioAtividade;
    }

    public void setDataInicioAtividade(String dataInicioAtividade) {
        this.dataInicioAtividade = dataInicioAtividade;
    }

    public String getDataFimAtividade() {
        return dataFimAtividade;
    }

    public void setDataFimAtividade(String dataFimAtividade) {
        this.dataFimAtividade = dataFimAtividade;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAtividade != null ? idAtividade.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AtividadeExecutada)) {
            return false;
        }
        AtividadeExecutada other = (AtividadeExecutada) object;
        if ((this.idAtividade == null && other.idAtividade != null) || (this.idAtividade != null && !this.idAtividade.equals(other.idAtividade))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.ifnmg.januaria.fernandes.itcp.domain.AtividadeExecutada[ idAtividade=" + idAtividade + " ]";
    }

    public List<Parceiro> getParceiroList() {
        return parceiroList;
    }

    public void setParceiroList(List<Parceiro> parceiroList) {
        this.parceiroList = parceiroList;
    }

    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }
    
}
