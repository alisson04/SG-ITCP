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
@Table(name = "MembroEmpreendimento")
@NamedQueries({
    @NamedQuery(name = "MembroEmpreendimento.findAll", query = "SELECT m FROM MembroEmpreendimento m")})
public class MembroEmpreendimento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idMembroEmpreendimento")
    private Integer idMembroEmpreendimento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nomeMembroEmpreendimento")
    private String nomeMembroEmpreendimento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "apelidoMembroEmpreendimento")
    private String apelidoMembroEmpreendimento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nomeMaeMembroEmpreendimento")
    private String nomeMaeMembroEmpreendimento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "enderecoMembroEmpreendimento")
    private String enderecoMembroEmpreendimento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "telefoneMembroEmpreendimento")
    private String telefoneMembroEmpreendimento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "telefoneAlternativoMembroEmpreendimento")
    private String telefoneAlternativoMembroEmpreendimento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "rgMembroEmpreendimento")
    private String rgMembroEmpreendimento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nisMembroEmpreendimento")
    private String nisMembroEmpreendimento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "dataNascimentoMembroEmpreendimento")
    private String dataNascimentoMembroEmpreendimento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "sexoMembroEmpreendimento")
    private String sexoMembroEmpreendimento;
    @JoinColumn(name = "empreendimentoFK", referencedColumnName = "idEpt")
    @ManyToOne(optional = false)
    private Empreendimento empreendimento;

    public MembroEmpreendimento() {
    }

    public MembroEmpreendimento(Integer idMembroEmpreendimento) {
        this.idMembroEmpreendimento = idMembroEmpreendimento;
    }

    public MembroEmpreendimento(Integer idMembroEmpreendimento, String nomeMembroEmpreendimento, String apelidoMembroEmpreendimento, String nomeMaeMembroEmpreendimento, String enderecoMembroEmpreendimento, String telefoneMembroEmpreendimento, String telefoneAlternativoMembroEmpreendimento, String rgMembroEmpreendimento, String nisMembroEmpreendimento, String dataNascimentoMembroEmpreendimento, String sexoMembroEmpreendimento) {
        this.idMembroEmpreendimento = idMembroEmpreendimento;
        this.nomeMembroEmpreendimento = nomeMembroEmpreendimento;
        this.apelidoMembroEmpreendimento = apelidoMembroEmpreendimento;
        this.nomeMaeMembroEmpreendimento = nomeMaeMembroEmpreendimento;
        this.enderecoMembroEmpreendimento = enderecoMembroEmpreendimento;
        this.telefoneMembroEmpreendimento = telefoneMembroEmpreendimento;
        this.telefoneAlternativoMembroEmpreendimento = telefoneAlternativoMembroEmpreendimento;
        this.rgMembroEmpreendimento = rgMembroEmpreendimento;
        this.nisMembroEmpreendimento = nisMembroEmpreendimento;
        this.dataNascimentoMembroEmpreendimento = dataNascimentoMembroEmpreendimento;
        this.sexoMembroEmpreendimento = sexoMembroEmpreendimento;
    }

    public Integer getIdMembroEmpreendimento() {
        return idMembroEmpreendimento;
    }

    public void setIdMembroEmpreendimento(Integer idMembroEmpreendimento) {
        this.idMembroEmpreendimento = idMembroEmpreendimento;
    }

    public String getNomeMembroEmpreendimento() {
        return nomeMembroEmpreendimento;
    }

    public void setNomeMembroEmpreendimento(String nomeMembroEmpreendimento) {
        this.nomeMembroEmpreendimento = nomeMembroEmpreendimento;
    }

    public String getApelidoMembroEmpreendimento() {
        return apelidoMembroEmpreendimento;
    }

    public void setApelidoMembroEmpreendimento(String apelidoMembroEmpreendimento) {
        this.apelidoMembroEmpreendimento = apelidoMembroEmpreendimento;
    }

    public String getNomeMaeMembroEmpreendimento() {
        return nomeMaeMembroEmpreendimento;
    }

    public void setNomeMaeMembroEmpreendimento(String nomeMaeMembroEmpreendimento) {
        this.nomeMaeMembroEmpreendimento = nomeMaeMembroEmpreendimento;
    }

    public String getEnderecoMembroEmpreendimento() {
        return enderecoMembroEmpreendimento;
    }

    public void setEnderecoMembroEmpreendimento(String enderecoMembroEmpreendimento) {
        this.enderecoMembroEmpreendimento = enderecoMembroEmpreendimento;
    }

    public String getTelefoneMembroEmpreendimento() {
        return telefoneMembroEmpreendimento;
    }

    public void setTelefoneMembroEmpreendimento(String telefoneMembroEmpreendimento) {
        this.telefoneMembroEmpreendimento = telefoneMembroEmpreendimento;
    }

    public String getTelefoneAlternativoMembroEmpreendimento() {
        return telefoneAlternativoMembroEmpreendimento;
    }

    public void setTelefoneAlternativoMembroEmpreendimento(String telefoneAlternativoMembroEmpreendimento) {
        this.telefoneAlternativoMembroEmpreendimento = telefoneAlternativoMembroEmpreendimento;
    }

    public String getRgMembroEmpreendimento() {
        return rgMembroEmpreendimento;
    }

    public void setRgMembroEmpreendimento(String rgMembroEmpreendimento) {
        this.rgMembroEmpreendimento = rgMembroEmpreendimento;
    }

    public String getNisMembroEmpreendimento() {
        return nisMembroEmpreendimento;
    }

    public void setNisMembroEmpreendimento(String nisMembroEmpreendimento) {
        this.nisMembroEmpreendimento = nisMembroEmpreendimento;
    }

    public String getDataNascimentoMembroEmpreendimento() {
        return dataNascimentoMembroEmpreendimento;
    }

    public void setDataNascimentoMembroEmpreendimento(String dataNascimentoMembroEmpreendimento) {
        this.dataNascimentoMembroEmpreendimento = dataNascimentoMembroEmpreendimento;
    }

    public String getSexoMembroEmpreendimento() {
        return sexoMembroEmpreendimento;
    }

    public void setSexoMembroEmpreendimento(String sexoMembroEmpreendimento) {
        this.sexoMembroEmpreendimento = sexoMembroEmpreendimento;
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
        hash += (idMembroEmpreendimento != null ? idMembroEmpreendimento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MembroEmpreendimento)) {
            return false;
        }
        MembroEmpreendimento other = (MembroEmpreendimento) object;
        if ((this.idMembroEmpreendimento == null && other.idMembroEmpreendimento != null) || (this.idMembroEmpreendimento != null && !this.idMembroEmpreendimento.equals(other.idMembroEmpreendimento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.ifnmg.januaria.fernandes.itcp.domain.MembroEmpreendimento[ idMembroEmpreendimento=" + idMembroEmpreendimento + " ]";
    }
    
}
