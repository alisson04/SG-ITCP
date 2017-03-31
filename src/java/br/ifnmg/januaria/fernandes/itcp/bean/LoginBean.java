package br.ifnmg.januaria.fernandes.itcp.bean;

import br.ifnmg.januaria.fernandes.itcp.dao.UsuarioDAO;
import br.ifnmg.januaria.fernandes.itcp.domain.Usuario;
import br.ifnmg.januaria.fernandes.itcp.util.SessionUtil;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author alisson
 */
@Named
@SessionScoped
public class LoginBean implements Serializable {

    private UsuarioDAO usuarioDAO;

    public LoginBean() {
        usuarioDAO = new UsuarioDAO();
    }

    public Usuario logar(Usuario usuarioLogado) {
            //CRIPTOGRAFA A SENHA ALEATORIA
            usuarioLogado.setSenha(DigestUtils.md5Hex(usuarioLogado.getSenha()));
            usuarioLogado = usuarioDAO.logar(usuarioLogado.getEmail(), usuarioLogado.getSenha());
            return usuarioLogado;
    }

    public String sair(Usuario usuarioLogado) {
        try {
            System.out.println("EM SAIR " + usuarioLogado.getEmail());
            HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            HttpServletRequest request = (HttpServletRequest) req;
            HttpSession session = (HttpSession) request.getSession();
            usuarioLogado = (Usuario) session.getAttribute("USUARIOLogado");

            System.out.println("User com ID: " + usuarioLogado.getId()+ " saiu!");
            System.out.println("User com e-mail: " + usuarioLogado.getEmail() + " saiu!");
            usuarioLogado = null;
            SessionUtil.remove("USUARIOLogado");
            FacesContext.getCurrentInstance().getExternalContext().redirect("/sigIncubatecs/Login.xhtml");
            return "/sigIncubatecs/Login.xhtml";
        } catch (IOException ex) {
            Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
            FacesMessage msg = new FacesMessage("IOExceptionl: " + ex.getMessage() );
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return null;
        }
    }
}
