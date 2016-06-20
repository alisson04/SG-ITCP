/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifnmg.januaria.fernandes.itcp.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "Usuario")
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idUsuario")
    private Integer idUsuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nomeUsuario")
    private String nomeUsuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "emailUsuario")
    private String emailUsuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "senhaUsuario")
    private String senhaUsuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "telefoneUsuario")
    private String telefoneUsuario;
    @Basic(optional = false)
    @Column(name = "telefoneAlternativoUsuario")
    private String telefoneAlternativoUsuario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dataNascimentoUsuario")
    @Temporal(TemporalType.DATE)
    private Date dataNascimentoUsuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "rgUsuario")
    private String rgUsuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "cpfUsuario")
    private String cpfUsuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "cargoUsuario")
    private String cargoUsuario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dataEntradaUsuario")
    @Temporal(TemporalType.DATE)
    private Date dataEntradaUsuario;
    @Column(name = "dataSaidaUsuario")
    @Temporal(TemporalType.DATE)
    private Date dataSaidaUsuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "sexoUsuario")
    private String sexoUsuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "statusSistemaUsuario")
    private String statusSistemaUsuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "enderecoUsuario")
    private String enderecoUsuario;

    public Usuario() {
    }

    public Usuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Usuario(Integer idUsuario, String nomeUsuario, String emailUsuario, String senhaUsuario, String telefoneUsuario, String telefoneAlternativoUsuario, Date dataNascimentoUsuario, String rgUsuario, String cpfUsuario, String cargoUsuario, Date dataEntradaUsuario, String sexoUsuario, String statusSistemaUsuario, String enderecoUsuario) {
        this.idUsuario = idUsuario;
        this.nomeUsuario = nomeUsuario;
        this.emailUsuario = emailUsuario;
        this.senhaUsuario = senhaUsuario;
        this.telefoneUsuario = telefoneUsuario;
        this.telefoneAlternativoUsuario = telefoneAlternativoUsuario;
        this.dataNascimentoUsuario = dataNascimentoUsuario;
        this.rgUsuario = rgUsuario;
        this.cpfUsuario = cpfUsuario;
        this.cargoUsuario = cargoUsuario;
        this.dataEntradaUsuario = dataEntradaUsuario;
        this.sexoUsuario = sexoUsuario;
        this.statusSistemaUsuario = statusSistemaUsuario;
        this.enderecoUsuario = enderecoUsuario;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public String getSenhaUsuario() {
        return senhaUsuario;
    }

    public void setSenhaUsuario(String senhaUsuario) {
        this.senhaUsuario = senhaUsuario;
    }

    public String getTelefoneUsuario() {
        return telefoneUsuario;
    }

    public void setTelefoneUsuario(String telefoneUsuario) {
        this.telefoneUsuario = telefoneUsuario;
    }

    public String getTelefoneAlternativoUsuario() {
        return telefoneAlternativoUsuario;
    }

    public void setTelefoneAlternativoUsuario(String telefoneAlternativoUsuario) {
        this.telefoneAlternativoUsuario = telefoneAlternativoUsuario;
    }

    public Date getDataNascimentoUsuario() {
        return dataNascimentoUsuario;
    }

    public void setDataNascimentoUsuario(Date dataNascimentoUsuario) {
        this.dataNascimentoUsuario = dataNascimentoUsuario;
    }

    public String getRgUsuario() {
        return rgUsuario;
    }

    public void setRgUsuario(String rgUsuario) {
        this.rgUsuario = rgUsuario;
    }

    public String getCpfUsuario() {
        return cpfUsuario;
    }

    public void setCpfUsuario(String cpfUsuario) {
        this.cpfUsuario = cpfUsuario;
    }

    public String getCargoUsuario() {
        return cargoUsuario;
    }

    public void setCargoUsuario(String cargoUsuario) {
        this.cargoUsuario = cargoUsuario;
    }

    public Date getDataEntradaUsuario() {
        return dataEntradaUsuario;
    }

    public void setDataEntradaUsuario(Date dataEntradaUsuario) {
        this.dataEntradaUsuario = dataEntradaUsuario;
    }

    public Date getDataSaidaUsuario() {
        return dataSaidaUsuario;
    }

    public void setDataSaidaUsuario(Date dataSaidaUsuario) {
        this.dataSaidaUsuario = dataSaidaUsuario;
    }

    public String getSexoUsuario() {
        return sexoUsuario;
    }

    public void setSexoUsuario(String sexoUsuario) {
        this.sexoUsuario = sexoUsuario;
    }

    public String getStatusSistemaUsuario() {
        return statusSistemaUsuario;
    }

    public void setStatusSistemaUsuario(String statusSistemaUsuario) {
        this.statusSistemaUsuario = statusSistemaUsuario;
    }

    public String getEnderecoUsuario() {
        return enderecoUsuario;
    }

    public void setEnderecoUsuario(String enderecoUsuario) {
        this.enderecoUsuario = enderecoUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.ifnmg.januaria.fernandes.itcp.domain.Usuario[ idUsuario=" + idUsuario + " ]";
    }
    
}
