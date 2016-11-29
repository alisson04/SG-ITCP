package br.ifnmg.januaria.fernandes.itcp.bean;

import br.ifnmg.januaria.fernandes.itcp.dao.UsuarioDAO;
import br.ifnmg.januaria.fernandes.itcp.domain.Usuario;
import br.ifnmg.januaria.fernandes.itcp.util.EnviarEmail;
import java.io.Serializable;
import java.util.List;
import java.util.Random;
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
    
    public Usuario salvarBean(Usuario user){
        return dao.salvarUsr(user);
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
    
    public String[] geraTiposDeCargosBean(){
        String[] cargos;//para a tela de listar usuarios
        
        cargos = new String[8];
        cargos[0] = "Coordenador";
        cargos[1] = "Professor";
        cargos[2] = "Técnico Administrativo";
        cargos[3] = "Estagiário Remunerado";
        cargos[4] = "Estagiário Voluntário";
        cargos[5] = "Bolsista - PIBED";
        cargos[6] = "Bolsista - PIBIC";
        cargos[7] = "Bolsista - PROEXT";
        
        return cargos;
    }
}
