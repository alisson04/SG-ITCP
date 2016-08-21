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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author alisson
 */
@Entity
@Table(name = "Parceiro")
@NamedQueries({
    @NamedQuery(name = "Parceiro.findAll", query = "SELECT p FROM Parceiro p")})
public class Parceiro implements Serializable, EntityConverter  {

    @ManyToMany(mappedBy = "parceiroList")
    private List<VisitaEpt> visitaEptList;

    @OneToMany(mappedBy = "parceiro")
    private List<AtividadeParceiro> atividadeParceiroList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idparceiro")
    private Integer idparceiro;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nomeParceiro")
    private String nomeParceiro;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "emailParceiro")
    private String emailParceiro;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "telefoneParceiro")
    private String telefoneParceiro;
    @Size(max = 45)
    @Column(name = "telefoneAlternativoParceiro")
    private String telefoneAlternativoParceiro;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "enderecoParceiro")
    private String enderecoParceiro;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "tipoParceiro")
    private String tipoParceiro;

    public Parceiro() {
    }

    public Parceiro(Integer idparceiro) {
        this.idparceiro = idparceiro;
    }

    public Parceiro(Integer idparceiro, String nomeParceiro, String emailParceiro, String telefoneParceiro, String enderecoParceiro, String tipoParceiro) {
        this.idparceiro = idparceiro;
        this.nomeParceiro = nomeParceiro;
        this.emailParceiro = emailParceiro;
        this.telefoneParceiro = telefoneParceiro;
        this.enderecoParceiro = enderecoParceiro;
        this.tipoParceiro = tipoParceiro;
    }
    
    @Override
    public Integer getIdConverter(){
        return idparceiro;
    }

    public Integer getIdparceiro() {
        return idparceiro;
    }

    public void setIdparceiro(Integer idparceiro) {
        this.idparceiro = idparceiro;
    }

    public String getNomeParceiro() {
        return nomeParceiro;
    }

    public void setNomeParceiro(String nomeParceiro) {
        this.nomeParceiro = nomeParceiro;
    }

    public String getEmailParceiro() {
        return emailParceiro;
    }

    public void setEmailParceiro(String emailParceiro) {
        this.emailParceiro = emailParceiro;
    }

    public String getTelefoneParceiro() {
        return telefoneParceiro;
    }

    public void setTelefoneParceiro(String telefoneParceiro) {
        this.telefoneParceiro = telefoneParceiro;
    }

    public String getTelefoneAlternativoParceiro() {
        return telefoneAlternativoParceiro;
    }

    public void setTelefoneAlternativoParceiro(String telefoneAlternativoParceiro) {
        this.telefoneAlternativoParceiro = telefoneAlternativoParceiro;
    }

    public String getEnderecoParceiro() {
        return enderecoParceiro;
    }

    public void setEnderecoParceiro(String enderecoParceiro) {
        this.enderecoParceiro = enderecoParceiro;
    }

    public String getTipoParceiro() {
        return tipoParceiro;
    }

    public void setTipoParceiro(String tipoParceiro) {
        this.tipoParceiro = tipoParceiro;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idparceiro != null ? idparceiro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Parceiro)) {
            return false;
        }
        Parceiro other = (Parceiro) object;
        if ((this.idparceiro == null && other.idparceiro != null) || (this.idparceiro != null && !this.idparceiro.equals(other.idparceiro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.ifnmg.januaria.fernandes.itcp.domain.Parceiro[ idparceiro=" + idparceiro + " ]";
    }

    public List<AtividadeParceiro> getAtividadeParceiroList() {
        return atividadeParceiroList;
    }

    public void setAtividadeParceiroList(List<AtividadeParceiro> atividadeParceiroList) {
        this.atividadeParceiroList = atividadeParceiroList;
    }

    public List<VisitaEpt> getVisitaEptList() {
        return visitaEptList;
    }

    public void setVisitaEptList(List<VisitaEpt> visitaEptList) {
        this.visitaEptList = visitaEptList;
    }
    
}
