package br.ifnmg.januaria.fernandes.itcp.bean;

import br.ifnmg.januaria.fernandes.itcp.dao.UsuarioDAO;
import br.ifnmg.januaria.fernandes.itcp.domain.Usuario;
import br.ifnmg.januaria.fernandes.itcp.util.EnviarEmail;
import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import org.apache.commons.mail.EmailException;

/**
 *
 * @author alisson
 */
@SessionScoped
@Named
public class UsuarioBean implements Serializable{
    private UsuarioDAO dao;
    
    public UsuarioBean(){
        dao = new UsuarioDAO();
    }
    
    public void salvarBean(Usuario user){
        dao.salvarUsr(user);
    }
    
    public void excluirBean(Usuario usr){
        dao.excluirUsrDao(usr);
    }
    
    public List<Usuario> listarBean() {
        return dao.listarTodosUsuarios();
    }
    
    public Usuario buscarPorEmailBean(Usuario user){
        return dao.buscarPorEmail(user);
    }
    
    public Usuario buscarPorCpfBean(Usuario user){
        return dao.buscarPorCpfDAO(user);
    }
    
    public Usuario buscarPorRgBean(Usuario user){
        return dao.buscarPorRgDAO(user);
    }
    
    public void enviarEmail(String enviarPara, String assunto, String mensagem) throws EmailException{
        System.out.println("__________BEAN(enviarEmail): Para:" + enviarPara);
        System.out.println("__________BEAN(enviarEmail): Assunto" + assunto);
        System.out.println("__________BEAN(enviarEmail): Mensagem" + mensagem);
        EnviarEmail enviarEmail = new EnviarEmail();
        enviarEmail.enviarEmail(enviarPara, assunto, mensagem);
    }
    
    public Usuario buscarPorCodigoBean(Usuario user){
        return dao.buscarPorCodigo(user);
    }
    
    public long contarLinhasBean(){
        return dao.contarLinhasDAO();
    }
}
