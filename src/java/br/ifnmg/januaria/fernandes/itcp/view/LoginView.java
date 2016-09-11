package br.ifnmg.januaria.fernandes.itcp.view;

import br.ifnmg.januaria.fernandes.itcp.bean.LoginBean;
import br.ifnmg.januaria.fernandes.itcp.bean.UsuarioBean;
import br.ifnmg.januaria.fernandes.itcp.domain.Usuario;
import br.ifnmg.januaria.fernandes.itcp.util.SessionUtil;
import java.io.IOException;
import java.io.Serializable;
import java.util.Random;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.mail.internet.AddressException;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.mail.EmailException;
import org.primefaces.context.RequestContext;

/**
 *
 * @author alisson
 */
@ViewScoped
@Named("LoginView")
public class LoginView implements Serializable {

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
        try {
            obj.setStatusSistema("Ativo");//SETA O STATUS
            obj.setSenha(gerarSenhaAleatoria());//GERA A SENHA ALEATORIA e SETA
            obj.setCargo("Coordenador");//SETA O CARGO
            usrBean.enviarEmail(obj.getEmail(), "Sistema Sigitec", "Sua senha é: " + obj.getSenha());//Manda o emaill
            obj.setSenha(DigestUtils.md5Hex(obj.getSenha()));//CRIPTOGRAFA A SENHA ALEATORIA
            usrBean.salvarBean(obj);//Salva o usuário
            obj = new Usuario();//Limpa o usuário salvo
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("SUCESSO!", "Informações salvas. "));
        } catch (EmailException ex) {
            System.out.println("ERRO no Endereço de e-mail");
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "A operação falhou", "Verifique o e-mail e a conexão com a internet ");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }
    }

    public void testeNet() {
        try {
            java.net.URL mandarMail = new java.net.URL("https://www.google.com.br");
            java.net.URLConnection conn = mandarMail.openConnection();
            java.net.HttpURLConnection httpConn = (java.net.HttpURLConnection) conn;
            httpConn.connect();
            int x = httpConn.getResponseCode();
            if (x == 200) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "SUCESSO", "Conexão bem sucedida!"));
            }
        } catch (java.net.MalformedURLException urlmal) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Erro de conexão", "Verifique sua conexão com a internet!");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        } catch (java.io.IOException ioexcp) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Erro de conexão", "Verifique sua conexão com a internet!");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
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
                return null;
            }
        } catch (RuntimeException | IOException ex) {
            System.out.println("RuntimeException: " + ex);
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
