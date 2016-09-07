package br.ifnmg.januaria.fernandes.itcp.view;

import br.ifnmg.januaria.fernandes.itcp.bean.LoginBean;
import br.ifnmg.januaria.fernandes.itcp.bean.UsuarioBean;
import br.ifnmg.januaria.fernandes.itcp.domain.Usuario;
import br.ifnmg.januaria.fernandes.itcp.util.MensagensGenericas;
import br.ifnmg.januaria.fernandes.itcp.util.SessionUtil;
import java.io.IOException;
import java.io.Serializable;
import java.util.Random;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import org.apache.commons.codec.digest.DigestUtils;
import org.primefaces.context.RequestContext;

/**
 *
 * @author alisson
 */
@ViewScoped
@Named("LoginView")
public class LoginView extends MensagensGenericas implements Serializable {

    private Usuario usuarioLogado;
    private Usuario obj;
    private LoginBean bean;
    private UsuarioBean usrBean;
    private boolean existeUser;

    public LoginView() {
        bean = new LoginBean();
        obj = new Usuario();
        usuarioLogado = new Usuario();
        usrBean = new UsuarioBean();
        existeUser = true;

        if (usrBean.contarLinhasBean() == 0) {
            existeUser = false;
        }
    }

    public void salvarCoordenador() {
        obj.setStatusSistema("Ativo");//SETA O STATUS
        obj.setSenha(gerarSenhaAleatoria());//GERA A SENHA ALEATORIA e SETA
        obj.setCargo("Coordenador");//SETA O CARGO
        usrBean.enviarEmail(obj.getEmail(), "Sistema Sigitec", "Sua senha é: " + obj.getSenha());//Manda o emaill
        obj.setSenha(DigestUtils.md5Hex(obj.getSenha()));//CRIPTOGRAFA A SENHA ALEATORIA
        usrBean.salvarBean(obj);//Salva o usuário
        obj = new Usuario();//Limpa o usuário salvo
        msgGrowSaveGeneric();
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
    
    public String gerarSenhaAleatoria() {
        String letras = "ABCDEFGHIJKLMNOPQRSTUVYWXZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        String senhaAleatoria = "";
        int index = -1;
        for (int i = 0; i < 10; i++) {
            index = random.nextInt(letras.length());
            senhaAleatoria += letras.substring(index, index + 1);
        }
        System.out.println("SENHA: " + senhaAleatoria);
        return senhaAleatoria;
    }

    //SETS E GETS
    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    public boolean isExisteUser() {
        return existeUser;
    }

    public Usuario getObj() {
        return obj;
    }

    public void setObj(Usuario obj) {
        this.obj = obj;
    }
}
