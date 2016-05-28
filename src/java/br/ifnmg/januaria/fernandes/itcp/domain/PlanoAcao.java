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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author alisson
 */
@Entity
@Table(name = "PlanoAcao")
@NamedQueries({
    @NamedQuery(name = "PlanoAcao.findAll", query = "SELECT p FROM PlanoAcao p")})
public class PlanoAcao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPlanoAcao")
    private Integer idPlanoAcao;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "dataInicio")
    private String dataInicio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "dataFim")
    private String dataFim;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "descricao")
    private String descricao;
    @JoinColumn(name = "idEmpreendimento", referencedColumnName = "idEpt")
    @ManyToOne(optional = false)
    private Empreendimento empreendimento;

    public PlanoAcao() {
    }

    public PlanoAcao(Integer idPlanoAcao) {
        this.idPlanoAcao = idPlanoAcao;
    }

    public PlanoAcao(Integer idPlanoAcao, String dataInicio, String dataFim, String descricao) {
        this.idPlanoAcao = idPlanoAcao;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.descricao = descricao;
    }

    public Integer getIdPlanoAcao() {
        return idPlanoAcao;
    }

    public void setIdPlanoAcao(Integer idPlanoAcao) {
        this.idPlanoAcao = idPlanoAcao;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataFim() {
        return dataFim;
    }

    public void setDataFim(String dataFim) {
        this.dataFim = dataFim;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Empreendimento getEmpreendimento() {
        return empreendimento;
    }

    public void setEmpreendimento(Empreendimento empreendimento) {
        this.empreendimento = empreendimento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPlanoAcao != null ? idPlanoAcao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PlanoAcao)) {
            return false;
        }
        PlanoAcao other = (PlanoAcao) object;
        if ((this.idPlanoAcao == null && other.idPlanoAcao != null) || (this.idPlanoAcao != null && !this.idPlanoAcao.equals(other.idPlanoAcao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.ifnmg.januaria.fernandes.itcp.domain.PlanoAcao[ idPlanoAcao=" + idPlanoAcao + " ]";
    }
    
}
