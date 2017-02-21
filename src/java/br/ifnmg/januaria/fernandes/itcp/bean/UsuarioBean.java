package br.ifnmg.januaria.fernandes.itcp.bean;

import br.ifnmg.januaria.fernandes.itcp.dao.UsuarioDAO;
import br.ifnmg.januaria.fernandes.itcp.domain.Parceiro;
import br.ifnmg.januaria.fernandes.itcp.domain.Usuario;
import br.ifnmg.januaria.fernandes.itcp.util.EnviarEmail;
import br.ifnmg.januaria.fernandes.itcp.util.RelatoriosManager;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.mail.EmailException;

/**
 *
 * @author alisson
 */
@SessionScoped
@Named
public class UsuarioBean implements Serializable  {

    private UsuarioDAO dao;

    public void teste() {
        dao.teste();
    }

    //CONSTRUTOR
    public UsuarioBean() {
        dao = new UsuarioDAO();
    }
    
    //METODOS
    public void gerarRelatorio(List<Usuario> lista) throws Exception {
        Map<String, Object> listaParametros = new HashMap<String, Object>();
        RelatoriosManager m = new RelatoriosManager<Usuario>();
        m.gerarRelatorioGenerico(lista, listaParametros, "/iReport/relatorioUsuarios.jasper", "Relatorio-de-Usuarios.pdf",
                "inline");
    }

    public Usuario salvarBean(Usuario user) throws EmailException {
        if (user.getId() == null) {// CASO ESTEJA CRIANDO UM USER
            if (buscarPorEmailBean(user) == null) {//Não existe esse e-mail no BD: Prossiga
                user.setStatusSistema("Ativo");//SETA O STATUS

                user.setSenha(gerarSenhaAleatoria());//GERA UMA SENHA ALEATORIA
                enviarEmail(user.getEmail(), "Sistema Sigitec", "Sua senha é: " + user.getSenha());//Manda o emaill
                user.setSenha(DigestUtils.md5Hex(user.getSenha()));//CRIPTOGRAFA A SENHA ALEATORIA e seta

                user.setFotoPerfil("profileGeral.png");

                return dao.salvarUsr(user);//SAVA E RETORNA
            } else {//Existe esse e-mail no BD: Volte
                return null;
            }

        }
        //CASO ESTEJA EDITANDO UM USER
        else if (buscarPorEmailBean(user) == null) {//Não existe esse e-mail no BD: Prossiga
            enviarEmail(user.getEmail(), "Sistema Sigitec", "Esse e-mail foi vinculado a conta do usuário "
                    + user.getNome() + " com sucesso. O e-mail " + buscarPorCodigoBean(user).getEmail()
                    + " não é mais válido para esse usuário!");//Manda o emaill avisando sobre o novo vinculo

            enviarEmail(buscarPorCodigoBean(user).getEmail(), "Sistema Sigitec", "Esse e-mail não é mais válido para o"
                    + "usuário " + user.getNome() + ". " + user.getEmail()
                    + " é o novo e-mail válido para essa conta!");//Manda o emaill avisando sobre o novo vinculo
            return dao.salvarUsr(user);//SAVA E RETORNA
        } else {//Existe esse e-mail no BD: Teste abaixo
            if ((buscarPorEmailBean(user).getId().equals(user.getId()))) {//O e-mail é dele(editou outra coisa)
                return dao.salvarUsr(user);//SAVA E RETORNA
            } else {
                return null;//O e-mail NÃO é dele(Tentou usar o e-mail de outra pessoa)
            }
        }
    }

    public void excluirBean(Usuario usr) {
        dao.excluirUsrDao(usr);
    }

    public List<Usuario> listarBean() {
        return dao.listarTodosUsuarios();
    }

    public Usuario buscarPorEmailBean(Usuario user) {
        return dao.buscarPorEmail(user);
    }

    public void enviarEmail(String enviarPara, String assunto, String mensagem) throws EmailException {
        System.out.println("__________BEAN(enviarEmail): Para:" + enviarPara);
        System.out.println("__________BEAN(enviarEmail): Assunto" + assunto);
        System.out.println("__________BEAN(enviarEmail): Mensagem" + mensagem);
        EnviarEmail enviarEmail = new EnviarEmail();
        enviarEmail.enviarEmail(enviarPara, assunto, mensagem);
    }
    
    public Usuario buscarPorId(int id) {
        return dao.buscarPorId(id);
    }

    public Usuario buscarPorCodigoBean(Usuario user) {
        return dao.buscarPorCodigo(user);
    }

    public long contarLinhasBean() {
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

    public String[] geraTiposDeCargosBean() {
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
