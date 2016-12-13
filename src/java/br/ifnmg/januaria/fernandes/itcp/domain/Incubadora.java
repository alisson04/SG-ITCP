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
@Table(name = "Incubadora")
@NamedQueries({
    @NamedQuery(name = "Incubadora.findAll", query = "SELECT i FROM Incubadora i")})
public class Incubadora implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "nome")
    private String nome;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dataCriacao")
    @Temporal(TemporalType.DATE)
    private Date dataCriacao;
    @Size(max = 255)
    @Column(name = "redeItcp")
    private String redeItcp;
    @Size(max = 255)
    @Column(name = "universidade")
    private String universidade;
    @Size(max = 255)
    @Column(name = "fotoFundoLogin")
    private String fotoFundoLogin;
    @Size(max = 255)
    @Column(name = "fotoTelaGeral")
    private String fotoTelaGeral;

    public Incubadora() {
    }

    public Incubadora(Integer id) {
        this.id = id;
    }

    public Incubadora(Integer id, String nome, Date dataCriacao) {
        this.id = id;
        this.nome = nome;
        this.dataCriacao = dataCriacao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getRedeItcp() {
        return redeItcp;
    }

    public void setRedeItcp(String redeItcp) {
        this.redeItcp = redeItcp;
    }

    public String getUniversidade() {
        return universidade;
    }

    public void setUniversidade(String universidade) {
        this.universidade = universidade;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Incubadora)) {
            return false;
        }
        Incubadora other = (Incubadora) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.ifnmg.januaria.fernandes.itcp.domain.Incubadora[ id=" + id + " ]";
    }

    public String getFotoFundoLogin() {
        return fotoFundoLogin;
    }

    public void setFotoFundoLogin(String fotoFundoLogin) {
        this.fotoFundoLogin = fotoFundoLogin;
    }

    public String getFotoTelaGeral() {
        return fotoTelaGeral;
    }

    public void setFotoTelaGeral(String fotoTelaGeral) {
        this.fotoTelaGeral = fotoTelaGeral;
    }
    
}
