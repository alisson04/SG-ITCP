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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author alisson
 */
@Entity
@Table(name = "Meta")
@NamedQueries({
    @NamedQuery(name = "Meta.findAll", query = "SELECT m FROM Meta m")})
public class Meta implements Serializable, EntityConverter {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idMeta")
    private Integer idMeta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nome")
    private String nome;
    @Basic(optional = false)
    @NotNull
    @Column(name = "prazo")
    @Temporal(TemporalType.DATE)
    private Date prazo;
    @Size(max = 200)
    @Column(name = "descricao")
    private String descricao;
    @JoinColumn(name = "idPlanoAcaoFk", referencedColumnName = "id")
    @ManyToOne
    private PlanoAcao planoAcao;
    @OneToMany(mappedBy = "meta")
    private List<AtividadePlanejada> atividadePlanejadaList;

    public Meta() {
    }

    public Meta(Integer idMeta) {
        this.idMeta = idMeta;
    }

    public Meta(Integer idMeta, String nome, Date prazo) {
        this.idMeta = idMeta;
        this.nome = nome;
        this.prazo = prazo;
    }
    
    @Override
    public Integer getIdConverter(){
        return idMeta;
    }

    public Integer getIdMeta() {
        return idMeta;
    }

    public void setIdMeta(Integer idMeta) {
        this.idMeta = idMeta;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getPrazo() {
        return prazo;
    }

    public void setPrazo(Date prazo) {
        this.prazo = prazo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public PlanoAcao getPlanoAcao() {
        return planoAcao;
    }

    public void setPlanoAcao(PlanoAcao planoAcao) {
        this.planoAcao = planoAcao;
    }

    public List<AtividadePlanejada> getAtividadePlanejadaList() {
        return atividadePlanejadaList;
    }

    public void setAtividadePlanejadaList(List<AtividadePlanejada> atividadePlanejadaList) {
        this.atividadePlanejadaList = atividadePlanejadaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMeta != null ? idMeta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Meta)) {
            return false;
        }
        Meta other = (Meta) object;
        if ((this.idMeta == null && other.idMeta != null) || (this.idMeta != null && !this.idMeta.equals(other.idMeta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.ifnmg.januaria.fernandes.itcp.domain.Meta[ idMeta=" + idMeta + " ]";
    }
    
}
