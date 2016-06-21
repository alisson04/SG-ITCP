package br.ifnmg.januaria.fernandes.itcp.bean;

import br.ifnmg.januaria.fernandes.itcp.dao.UsuarioDAO;
import br.ifnmg.januaria.fernandes.itcp.domain.Usuario;
import br.ifnmg.januaria.fernandes.itcp.util.EnviarEmail;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author alisson
 */
@ManagedBean
@SessionScoped
public class UsuarioBean implements Serializable{
    private UsuarioDAO dao;
    
    public UsuarioBean(){
        dao = new UsuarioDAO();
    }
    
    public void salvarUserBean(Usuario user){
        dao.salvarUsr(user);
    }
    
    public void excluirUsrBean(Usuario usr){
        dao.excluirUsrDao(usr);
    }
    
    public List<Usuario> listarTodosUsuariosBean() {
        return dao.listarTodosUsuarios();
    }
    
    public Usuario buscarPorEmailBean(String email){
        return dao.buscarPorEmail(email);
    }
    
    public void enviarEmail(String enviarPara, String assunto, String mensagem) {
        System.out.println("__________BEAN(enviarEmail): Para:" + enviarPara);
        System.out.println("__________BEAN(enviarEmail): Assunto" + assunto);
        System.out.println("__________BEAN(enviarEmail): Mensagem" + mensagem);
        EnviarEmail enviarEmail = new EnviarEmail();
        enviarEmail.enviarEmail(enviarPara, assunto, mensagem);
        System.out.println("__________BEAN(enviarEmail): Fim");
    }
    
    public Usuario buscarPorCodigoBean(Usuario user){
        return dao.buscarPorCodigo(user);
    }
}
