package br.ifnmg.januaria.fernandes.itcp.dao;

import br.ifnmg.januaria.fernandes.itcp.domain.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author alisson
 */
public class UsuarioDAO extends DaoGenerico<Usuario> {

    EntityManagerCriador emc;

    public UsuarioDAO() {

    }
    
    public void salvarUsr(Usuario usr){
        salvarGenerico(usr);
    }

    public Usuario buscarPorEmail(String emailUsuario) {
        List<Usuario> listaUsuarios;
        Usuario usr;

        listaUsuarios = listarObjsFiltradosGenerico("Usuario", "emailUsuario", emailUsuario);
        if (listaUsuarios.size() > 0) {
            usr = listaUsuarios.get(0);
            System.out.println("NUM: " + listaUsuarios.size());
        } else {
            usr = null;
            System.out.println("NUM: ");
        }

        return usr;
    }

    public Usuario buscarPorCodigo(Integer idUsuario) {
        EntityManager em = emc.gerarEntityManager();
        Usuario usuario = null;

        try {
            em.getTransaction().begin();
            Query consulta = em.createNamedQuery("Usuario.buscarPorCodigo");
            consulta.setParameter("idUsuario", idUsuario); // O primeiro "id" é do Domain.Usuario; o segundo do que foi criado nesse metodo
            usuario = (Usuario) consulta.getSingleResult();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar por codigo", e);
        } finally {
            em.close();
        }
        return usuario;
    }

    public Usuario logar(String emailUsuario, String senhaUsuario) {
        EntityManager em = emc.gerarEntityManager();
        Usuario usuario;
        List<Usuario> listaObjs;
        try {
            em.getTransaction().begin();

            Query consulta = em.createNamedQuery("Usuario.logar");
            consulta.setParameter("emailUsuario", emailUsuario);
            consulta.setParameter("senhaUsuario", senhaUsuario);
            listaObjs = consulta.getResultList();//Pega a lista de objs
            System.out.println("Numero de users encontrados: " + listaObjs.size());
            if (listaObjs.size() > 0 && listaObjs.size() < 2) {
                System.out.println("NUM: " + listaObjs.size());
                System.out.println("EMAIL: " + listaObjs.get(0).getEmailUsuario());
                usuario = listaObjs.get(0);
                System.out.println("NUM: " + listaObjs.size());
                System.out.println("DAO(logar): SENHA: " + usuario.getSenhaUsuario());
                System.out.println("DAO(logar): EMAIL: " + usuario.getEmailUsuario());
                return usuario;
            } else {
                System.out.println("dfsfsdf sdf : ");
                usuario = null;
                return usuario;
            }
        } catch (Exception e) {
            
            throw new RuntimeException("DAO(logar): Erro ao logar", e);
        } finally {
            em.close();
        }
    }
}
