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
import javax.persistence.CascadeType;
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
public class Empreendimento implements Serializable, EntityConverter {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empreendimento")
    private List<AcompanhamentoEpt> visitaEptList;

    @OneToMany(mappedBy = "empreendimento")
    private List<PlanoAcao> planoAcaoList;

    @OneToMany(mappedBy = "empreendimento")
    private List<MembroEmpreendimento> membroEmpreendimentoList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idEpt")
    private Integer idEpt;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nome")
    private String nome;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="E-mail inválido")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 45)
    @Column(name = "email")
    private String email;
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
    @Size(max = 45)
    @Column(name = "razaoSocial")
    private String razaoSocial;
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

    public Empreendimento() {
    }

    public Empreendimento(Integer idEpt) {
        this.idEpt = idEpt;
    }

    public Empreendimento(Integer idEpt, String nome, String enderecoEpt, String situacaoEpt, String tipoEpt, String atividadeExercidaEpt) {
        this.idEpt = idEpt;
        this.nome = nome;
        this.enderecoEpt = enderecoEpt;
        this.situacaoEpt = situacaoEpt;
        this.tipoEpt = tipoEpt;
        this.atividadeExercidaEpt = atividadeExercidaEpt;
    }
    
    @Override
    public Integer getIdConverter(){
        return idEpt;
    }

    public Integer getIdEpt() {
        return idEpt;
    }

    public void setIdEpt(Integer idEpt) {
        this.idEpt = idEpt;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
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

    public List<MembroEmpreendimento> getMembroEmpreendimentoList() {
        return membroEmpreendimentoList;
    }

    public void setMembroEmpreendimentoList(List<MembroEmpreendimento> membroEmpreendimentoList) {
        this.membroEmpreendimentoList = membroEmpreendimentoList;
    }

    public List<PlanoAcao> getPlanoAcaoList() {
        return planoAcaoList;
    }

    public void setPlanoAcaoList(List<PlanoAcao> planoAcaoList) {
        this.planoAcaoList = planoAcaoList;
    }

    public List<AcompanhamentoEpt> getVisitaEptList() {
        return visitaEptList;
    }

    public void setVisitaEptList(List<AcompanhamentoEpt> visitaEptList) {
        this.visitaEptList = visitaEptList;
    }
    
}
