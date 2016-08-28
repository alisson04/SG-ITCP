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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author alisson
 */
@Entity
@Table(name = "HorarioTrabalho")
@NamedQueries({
    @NamedQuery(name = "HorarioTrabalho.findAll", query = "SELECT h FROM HorarioTrabalho h")})
public class HorarioTrabalho implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idUsuarioFk")
    private Integer idUsuarioFk;
    @Column(name = "horasSemana")
    private Integer horasSemana;
    @Column(name = "domIni")
    @Temporal(TemporalType.TIME)
    private Date domIni;
    @Column(name = "domFim")
    @Temporal(TemporalType.TIME)
    private Date domFim;
    @Column(name = "segIni")
    @Temporal(TemporalType.TIME)
    private Date segIni;
    @Column(name = "segFim")
    @Temporal(TemporalType.TIME)
    private Date segFim;
    @Column(name = "terIni")
    @Temporal(TemporalType.TIME)
    private Date terIni;
    @Column(name = "terFim")
    @Temporal(TemporalType.TIME)
    private Date terFim;
    @Column(name = "quaIni")
    @Temporal(TemporalType.TIME)
    private Date quaIni;
    @Column(name = "quaFim")
    @Temporal(TemporalType.TIME)
    private Date quaFim;
    @Column(name = "quiIni")
    @Temporal(TemporalType.TIME)
    private Date quiIni;
    @Column(name = "quiFim")
    @Temporal(TemporalType.TIME)
    private Date quiFim;
    @Column(name = "sexIni")
    @Temporal(TemporalType.TIME)
    private Date sexIni;
    @Column(name = "sexFim")
    @Temporal(TemporalType.TIME)
    private Date sexFim;
    @Column(name = "sabIni")
    @Temporal(TemporalType.TIME)
    private Date sabIni;
    @Column(name = "sabFim")
    @Temporal(TemporalType.TIME)
    private Date sabFim;
    @JoinColumn(name = "idUsuarioFk", referencedColumnName = "id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Usuario usuario;

    public HorarioTrabalho() {
    }

    public HorarioTrabalho(Integer idUsuarioFk) {
        this.idUsuarioFk = idUsuarioFk;
    }

    public Integer getIdUsuarioFk() {
        return idUsuarioFk;
    }

    public void setIdUsuarioFk(Integer idUsuarioFk) {
        this.idUsuarioFk = idUsuarioFk;
    }

    public Integer getHorasSemana() {
        return horasSemana;
    }

    public void setHorasSemana(Integer horasSemana) {
        this.horasSemana = horasSemana;
    }

    public Date getDomIni() {
        return domIni;
    }

    public void setDomIni(Date domIni) {
        this.domIni = domIni;
    }

    public Date getDomFim() {
        return domFim;
    }

    public void setDomFim(Date domFim) {
        this.domFim = domFim;
    }

    public Date getSegIni() {
        return segIni;
    }

    public void setSegIni(Date segIni) {
        this.segIni = segIni;
    }

    public Date getSegFim() {
        return segFim;
    }

    public void setSegFim(Date segFim) {
        this.segFim = segFim;
    }

    public Date getTerIni() {
        return terIni;
    }

    public void setTerIni(Date terIni) {
        this.terIni = terIni;
    }

    public Date getTerFim() {
        return terFim;
    }

    public void setTerFim(Date terFim) {
        this.terFim = terFim;
    }

    public Date getQuaIni() {
        return quaIni;
    }

    public void setQuaIni(Date quaIni) {
        this.quaIni = quaIni;
    }

    public Date getQuaFim() {
        return quaFim;
    }

    public void setQuaFim(Date quaFim) {
        this.quaFim = quaFim;
    }

    public Date getQuiIni() {
        return quiIni;
    }

    public void setQuiIni(Date quiIni) {
        this.quiIni = quiIni;
    }

    public Date getQuiFim() {
        return quiFim;
    }

    public void setQuiFim(Date quiFim) {
        this.quiFim = quiFim;
    }

    public Date getSexIni() {
        return sexIni;
    }

    public void setSexIni(Date sexIni) {
        this.sexIni = sexIni;
    }

    public Date getSexFim() {
        return sexFim;
    }

    public void setSexFim(Date sexFim) {
        this.sexFim = sexFim;
    }

    public Date getSabIni() {
        return sabIni;
    }

    public void setSabIni(Date sabIni) {
        this.sabIni = sabIni;
    }

    public Date getSabFim() {
        return sabFim;
    }

    public void setSabFim(Date sabFim) {
        this.sabFim = sabFim;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuarioFk != null ? idUsuarioFk.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HorarioTrabalho)) {
            return false;
        }
        HorarioTrabalho other = (HorarioTrabalho) object;
        if ((this.idUsuarioFk == null && other.idUsuarioFk != null) || (this.idUsuarioFk != null && !this.idUsuarioFk.equals(other.idUsuarioFk))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.ifnmg.januaria.fernandes.itcp.domain.HorarioTrabalho[ idUsuarioFk=" + idUsuarioFk + " ]";
    }
    
}
