/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifnmg.januaria.fernandes.itcp.bean;

import br.ifnmg.januaria.fernandes.itcp.dao.UsuarioDAO;
import br.ifnmg.januaria.fernandes.itcp.domain.Usuario;
import br.ifnmg.januaria.fernandes.itcp.util.SessionUtil;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import static javax.faces.context.FacesContext.getCurrentInstance;

/**
 *
 * @author alisson
 */
@ManagedBean
@RequestScoped
public class LoginBean implements Serializable {

    Usuario usuarioLogado;
    UsuarioDAO usuarioDAO;

    public LoginBean() {
        usuarioLogado = new Usuario();
        usuarioDAO = new UsuarioDAO();
    }

    public String logar() {
        try {
            if ((usuarioDAO.buscarPorEmail(usuarioLogado.getEmailUsuario()) != null)) {
                System.out.println("__________BEAN(loginBean): confirma user");

                Object b = new Object();
                SessionUtil.setParam("USUARIOLogado", b);
                
                FacesContext.getCurrentInstance().getExternalContext().redirect("/sigitec/inicio.xhtml");
                return "/sigitec/inicio.xhtml";
            } else {
                System.out.println("__________BEAN(loginBean): Não encontrou o e-mail");
                return null;
            }
        } catch (IOException ex) {
            Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public String sair(){
        try {
            SessionUtil.remove("USUARIOLogado");
            System.out.println("__________LoginBean(sair): Inicio");
            FacesContext.getCurrentInstance().getExternalContext().redirect("/sigitec/Login.xhtml");
            return "/sigitec/Login.xhtml";
        } catch (IOException ex) {
            Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }
    
    
}
