package br.ifnmg.januaria.fernandes.itcp.view;

import br.ifnmg.januaria.fernandes.itcp.bean.LoginBean;
import br.ifnmg.januaria.fernandes.itcp.bean.UsuarioBean;
import br.ifnmg.januaria.fernandes.itcp.domain.Usuario;
import br.ifnmg.januaria.fernandes.itcp.util.MensagensGenericas;
import br.ifnmg.januaria.fernandes.itcp.util.SessionUtil;
import java.io.IOException;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author alisson
 */
@ViewScoped
@Named("LoginView")
public class LoginView extends MensagensGenericas implements Serializable {

    private Usuario usuarioLogado = new Usuario();
    private LoginBean bean = new LoginBean();
    private UsuarioBean usrBean;
    private boolean existeUser;

    public LoginView() {
        usrBean = new UsuarioBean();
        existeUser = true;

        if (usrBean.contarLinhasBean() == 0) {
            existeUser = false;
        }
    }

    public String logar() {
        try {
            usuarioLogado = bean.logar(usuarioLogado);
            if (usuarioLogado != null) {
                SessionUtil.setParam("USUARIOLogado", usuarioLogado);
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage("Successful", "Your message: "));
                FacesContext.getCurrentInstance().getExternalContext().redirect("/sigitec/inicio.xhtml");
                return "/sigitec/inicio.xhtml";
            } else {
                System.out.println("__________BEAN(loginBean): Não encontrou o e-mail");
                msgGrowlErroCustomizavel("E-mail ou senha invalidos", "E-mail ou senha invalidos!");
                return null;
            }
        } catch (RuntimeException | IOException ex) {
            System.out.println("RuntimeException: " + ex);
            msgPanelErroInesperadoGeneric();
            return null;
        }
    }

    public String sair() {
        return bean.sair(usuarioLogado);
    }

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    public boolean isExisteUser() {
        return existeUser;
    }
}
