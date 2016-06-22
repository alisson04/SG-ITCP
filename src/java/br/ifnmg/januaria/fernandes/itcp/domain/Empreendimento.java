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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author alisson
 */
@Entity
@Table(name = "Empreendimento")
@NamedQueries({
    @NamedQuery(name = "Empreendimento.findAll", query = "SELECT e FROM Empreendimento e")})
public class Empreendimento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idEpt")
    private Integer idEpt;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nomeEpt")
    private String nomeEpt;
    @Size(max = 45)
    @Column(name = "emailEpt")
    private String emailEpt;
    @Size(max = 45)
    @Column(name = "telefoneEpt")
    private String telefoneEpt;
    @Size(max = 45)
    @Column(name = "telefoneAlternativoEpt")
    private String telefoneAlternativoEpt;
    @Size(max = 45)
    @Column(name = "cnpjEpt")
    private String cnpjEpt;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "enderecoEpt")
    private String enderecoEpt;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "situacaoEpt")
    private String situacaoEpt;
    @Column(name = "dataCriacaoEpt")
    @Temporal(TemporalType.DATE)
    private Date dataCriacaoEpt;
    @Column(name = "dataIncubacaoEpt")
    @Temporal(TemporalType.DATE)
    private Date dataIncubacaoEpt;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nomeFantasiaEpt")
    private String nomeFantasiaEpt;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "tipoEpt")
    private String tipoEpt;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "AtividadeExercidaEpt")
    private String atividadeExercidaEpt;
    @Size(max = 45)
    @Column(name = "siteEpt")
    private String siteEpt;
    @Size(max = 45)
    @Column(name = "faturamentoMedioMensalEmp")
    private String faturamentoMedioMensalEmp;
    @Size(max = 45)
    @Column(name = "faturamentoMedioAnualEmp")
    private String faturamentoMedioAnualEmp;
    @OneToMany(mappedBy = "empreendimento")
    private List<PlanoAcao> planoAcaoList;

    public Empreendimento() {
    }

    public Empreendimento(Integer idEpt) {
        this.idEpt = idEpt;
    }

    public Empreendimento(Integer idEpt, String nomeEpt, String enderecoEpt, String situacaoEpt, String nomeFantasiaEpt, String tipoEpt, String atividadeExercidaEpt) {
        this.idEpt = idEpt;
        this.nomeEpt = nomeEpt;
        this.enderecoEpt = enderecoEpt;
        this.situacaoEpt = situacaoEpt;
        this.nomeFantasiaEpt = nomeFantasiaEpt;
        this.tipoEpt = tipoEpt;
        this.atividadeExercidaEpt = atividadeExercidaEpt;
    }

    public Integer getIdEpt() {
        return idEpt;
    }

    public void setIdEpt(Integer idEpt) {
        this.idEpt = idEpt;
    }

    public String getNomeEpt() {
        return nomeEpt;
    }

    public void setNomeEpt(String nomeEpt) {
        this.nomeEpt = nomeEpt;
    }

    public String getEmailEpt() {
        return emailEpt;
    }

    public void setEmailEpt(String emailEpt) {
        this.emailEpt = emailEpt;
    }

    public String getTelefoneEpt() {
        return telefoneEpt;
    }

    public void setTelefoneEpt(String telefoneEpt) {
        this.telefoneEpt = telefoneEpt;
    }

    public String getTelefoneAlternativoEpt() {
        return telefoneAlternativoEpt;
    }

    public void setTelefoneAlternativoEpt(String telefoneAlternativoEpt) {
        this.telefoneAlternativoEpt = telefoneAlternativoEpt;
    }

    public String getCnpjEpt() {
        return cnpjEpt;
    }

    public void setCnpjEpt(String cnpjEpt) {
        this.cnpjEpt = cnpjEpt;
    }

    public String getEnderecoEpt() {
        return enderecoEpt;
    }

    public void setEnderecoEpt(String enderecoEpt) {
        this.enderecoEpt = enderecoEpt;
    }

    public String getSituacaoEpt() {
        return situacaoEpt;
    }

    public void setSituacaoEpt(String situacaoEpt) {
        this.situacaoEpt = situacaoEpt;
    }

    public Date getDataCriacaoEpt() {
        return dataCriacaoEpt;
    }

    public void setDataCriacaoEpt(Date dataCriacaoEpt) {
        this.dataCriacaoEpt = dataCriacaoEpt;
    }

    public Date getDataIncubacaoEpt() {
        return dataIncubacaoEpt;
    }

    public void setDataIncubacaoEpt(Date dataIncubacaoEpt) {
        this.dataIncubacaoEpt = dataIncubacaoEpt;
    }

    public String getNomeFantasiaEpt() {
        return nomeFantasiaEpt;
    }

    public void setNomeFantasiaEpt(String nomeFantasiaEpt) {
        this.nomeFantasiaEpt = nomeFantasiaEpt;
    }

    public String getTipoEpt() {
        return tipoEpt;
    }

    public void setTipoEpt(String tipoEpt) {
        this.tipoEpt = tipoEpt;
    }

    public String getAtividadeExercidaEpt() {
        return atividadeExercidaEpt;
    }

    public void setAtividadeExercidaEpt(String atividadeExercidaEpt) {
        this.atividadeExercidaEpt = atividadeExercidaEpt;
    }

    public String getSiteEpt() {
        return siteEpt;
    }

    public void setSiteEpt(String siteEpt) {
        this.siteEpt = siteEpt;
    }

    public String getFaturamentoMedioMensalEmp() {
        return faturamentoMedioMensalEmp;
    }

    public void setFaturamentoMedioMensalEmp(String faturamentoMedioMensalEmp) {
        this.faturamentoMedioMensalEmp = faturamentoMedioMensalEmp;
    }

    public String getFaturamentoMedioAnualEmp() {
        return faturamentoMedioAnualEmp;
    }

    public void setFaturamentoMedioAnualEmp(String faturamentoMedioAnualEmp) {
        this.faturamentoMedioAnualEmp = faturamentoMedioAnualEmp;
    }

    public List<PlanoAcao> getPlanoAcaoList() {
        return planoAcaoList;
    }

    public void setPlanoAcaoList(List<PlanoAcao> planoAcaoList) {
        this.planoAcaoList = planoAcaoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEpt != null ? idEpt.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empreendimento)) {
            return false;
        }
        Empreendimento other = (Empreendimento) object;
        if ((this.idEpt == null && other.idEpt != null) || (this.idEpt != null && !this.idEpt.equals(other.idEpt))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.ifnmg.januaria.fernandes.itcp.domain.Empreendimento[ idEpt=" + idEpt + " ]";
    }
    
}
