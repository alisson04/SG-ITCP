package br.ifnmg.januaria.fernandes.itcp.view;

import br.ifnmg.januaria.fernandes.itcp.bean.LoginBean;
import br.ifnmg.januaria.fernandes.itcp.bean.MensagensBean;
import br.ifnmg.januaria.fernandes.itcp.domain.Usuario;
import br.ifnmg.januaria.fernandes.itcp.util.SessionUtil;
import java.io.IOException;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author alisson
 */
@ManagedBean(name = "loginView")
@ViewScoped
public class loginView implements Serializable{
    private Usuario usuarioLogado;
    private LoginBean bean;
    MensagensBean mensagensBean;
    
    public loginView(){
        bean = new LoginBean();
        usuarioLogado = new Usuario();
    }
    
    public String logar() throws IOException{
        usuarioLogado = bean.logar(usuarioLogado);
        
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
    }
    
    public void testa(){
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Erro, contate o admin:"));
    }
    
    public String sair(){
        return bean.sair(usuarioLogado);
    }

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }
}
