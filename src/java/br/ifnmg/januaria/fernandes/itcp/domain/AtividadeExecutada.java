/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifnmg.januaria.fernandes.itcp.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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

    @OneToMany(mappedBy = "atividadeExecutada")
    private List<AtividadeUsuario> atividadeUsuarioList;
    @OneToMany(mappedBy = "atividadeExecutada")
    private List<AtividadeParceiro> atividadeParceiroList;
    @OneToMany(mappedBy = "atividadeExecutada")
    private List<AtividadeExecutadaPlanejada> atividadeExecutadaPlanejadaList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idAtividade")
    private Integer idAtividade;
    @Size(max = 45)
    @Column(name = "nome")
    private String nome;
    @Size(max = 45)
    @Column(name = "descricao")
    private String descricao;
    @Size(max = 45)
    @Column(name = "status")
    private String status;
    @Column(name = "dataInicio")
    @Temporal(TemporalType.DATE)
    private Date dataInicio;
    @Column(name = "dataFim")
    @Temporal(TemporalType.DATE)
    private Date dataFim;

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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
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

    public List<AtividadeUsuario> getAtividadeUsuarioList() {
        return atividadeUsuarioList;
    }

    public void setAtividadeUsuarioList(List<AtividadeUsuario> atividadeUsuarioList) {
        this.atividadeUsuarioList = atividadeUsuarioList;
    }

    public List<AtividadeParceiro> getAtividadeParceiroList() {
        return atividadeParceiroList;
    }

    public void setAtividadeParceiroList(List<AtividadeParceiro> atividadeParceiroList) {
        this.atividadeParceiroList = atividadeParceiroList;
    }

    public List<AtividadeExecutadaPlanejada> getAtividadeExecutadaPlanejadaList() {
        return atividadeExecutadaPlanejadaList;
    }

    public void setAtividadeExecutadaPlanejadaList(List<AtividadeExecutadaPlanejada> atividadeExecutadaPlanejadaList) {
        this.atividadeExecutadaPlanejadaList = atividadeExecutadaPlanejadaList;
    }
    
}
