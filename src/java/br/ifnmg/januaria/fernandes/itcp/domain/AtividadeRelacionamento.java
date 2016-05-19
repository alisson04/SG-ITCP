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
@Table(name = "AtividadeRelacionamento")
@NamedQueries({
    @NamedQuery(name = "AtividadeRelacionamento.findAll", query = "SELECT a FROM AtividadeRelacionamento a")})
public class AtividadeRelacionamento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idAtividadeRelacionamento")
    private Integer idAtividadeRelacionamento;
    @JoinColumn(name = "empreendimentoFk", referencedColumnName = "idEpt")
    @ManyToOne
    private Empreendimento empreendimento;
    @JoinColumn(name = "usuarioFk", referencedColumnName = "idUsuario")
    @ManyToOne
    private Usuario usuario;
    @JoinColumn(name = "atividadeFk", referencedColumnName = "idAtividade")
    @ManyToOne(optional = false)
    private Atividade atividade;

    public AtividadeRelacionamento() {
    }

    public AtividadeRelacionamento(Integer idAtividadeRelacionamento) {
        this.idAtividadeRelacionamento = idAtividadeRelacionamento;
    }

    public Integer getIdAtividadeRelacionamento() {
        return idAtividadeRelacionamento;
    }

    public void setIdAtividadeRelacionamento(Integer idAtividadeRelacionamento) {
        this.idAtividadeRelacionamento = idAtividadeRelacionamento;
    }

    public Empreendimento getEmpreendimento() {
        return empreendimento;
    }

    public void setEmpreendimento(Empreendimento empreendimento) {
        this.empreendimento = empreendimento;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Atividade getAtividade() {
        return atividade;
    }

    public void setAtividade(Atividade atividade) {
        this.atividade = atividade;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAtividadeRelacionamento != null ? idAtividadeRelacionamento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AtividadeRelacionamento)) {
            return false;
        }
        AtividadeRelacionamento other = (AtividadeRelacionamento) object;
        if ((this.idAtividadeRelacionamento == null && other.idAtividadeRelacionamento != null) || (this.idAtividadeRelacionamento != null && !this.idAtividadeRelacionamento.equals(other.idAtividadeRelacionamento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.ifnmg.januaria.fernandes.itcp.domain.AtividadeRelacionamento[ idAtividadeRelacionamento=" + idAtividadeRelacionamento + " ]";
    }
    
}
