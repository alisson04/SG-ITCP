package br.ifnmg.januaria.fernandes.itcp.bean;

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

    private UsuarioDAO usuarioDAO;
    MensagensBean mensagensBean;

    public LoginBean() {
        usuarioDAO = new UsuarioDAO();
        mensagensBean = new MensagensBean();
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

            System.out.println("EM SAIR " + usuarioLogado.getEmail());
            usuarioLogado = null;
            SessionUtil.remove("USUARIOLogado");
            FacesContext.getCurrentInstance().getExternalContext().redirect("/sigitec/Login.xhtml");
            return "/sigitec/Login.xhtml";
        } catch (IOException ex) {
            Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
            FacesMessage msg = new FacesMessage("IOExceptionl: " + ex.getMessage() );
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return null;
        }
    }
}
