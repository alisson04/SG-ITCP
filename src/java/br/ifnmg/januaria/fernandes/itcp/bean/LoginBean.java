/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifnmg.januaria.fernandes.itcp.bean;

import br.ifnmg.januaria.fernandes.itcp.dao.AtividadeDAO;
import br.ifnmg.januaria.fernandes.itcp.dao.MembroEmpreendimentoDAO;
import br.ifnmg.januaria.fernandes.itcp.dao.UsuarioDAO;
import br.ifnmg.januaria.fernandes.itcp.domain.Usuario;
import br.ifnmg.januaria.fernandes.itcp.util.SessionUtil;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import static javax.faces.context.FacesContext.getCurrentInstance;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author alisson
 */
@ManagedBean
public class LoginBean implements Serializable {

    private Usuario usuarioLogado;
    private UsuarioDAO usuarioDAO;
    MensagensBean mensagensBean;

    public LoginBean() {
        usuarioLogado = new Usuario();
        usuarioDAO = new UsuarioDAO();
        mensagensBean = new MensagensBean();
    }

    public String logar() {
        
        try {
            //CRIPTOGRAFA A SENHA ALEATORIA
            usuarioLogado.setSenhaUsuario(DigestUtils.md5Hex(usuarioLogado.getSenhaUsuario()));
            usuarioLogado = usuarioDAO.logar(usuarioLogado.getEmailUsuario(), usuarioLogado.getSenhaUsuario());
            System.out.println("__________BEAN(loginBean): EXXXXXXX1111111111");
            if (usuarioLogado != null) {

                SessionUtil.setParam("USUARIOLogado", usuarioLogado);
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage("Successful", "Your message: "));
                FacesContext.getCurrentInstance().getExternalContext().redirect("/sigitec/inicio.xhtml");
                return "/sigitec/inicio.xhtml";
            } else {
                System.out.println("__________BEAN(loginBean): Não encontrou o e-mail");
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "E-mail e senha invalidos:", "Verifique os dados e tente novamente!"));
                return null;
            }
        } catch (IOException ex) {
            System.out.println("__________BEAN(loginBean): EXXXXXXX");

            Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Erro, contate o admin:", ex.getMessage()));
            return null;
        }
    }

    public String sair() {
        try {
            System.out.println("EM SAIR " + usuarioLogado.getEmailUsuario());
            HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            HttpServletRequest request = (HttpServletRequest) req;
            HttpSession session = (HttpSession) request.getSession();
            usuarioLogado = (Usuario) session.getAttribute("USUARIOLogado");

            System.out.println("EM SAIR " + usuarioLogado.getEmailUsuario());
            usuarioLogado = null;
            SessionUtil.remove("USUARIOLogado");
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
