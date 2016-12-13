package br.ifnmg.januaria.fernandes.itcp.dao;

import br.ifnmg.januaria.fernandes.itcp.domain.Usuario;
import java.util.List;
import javax.faces.FacesException;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author alisson
 */
public class UsuarioDAO extends DaoGenerico<Usuario> {

    EntityManagerCriador emc;

    public UsuarioDAO() {
    }

    public void teste() {
        try {
            Usuario u = new Usuario();
            if (u.getId() > 1) {
                System.out.println("ROLORLROLROLROLROLROLRO");
            }
        } catch (NullPointerException e) {
            System.out.println("EXEXEXEXEXEXEXE");
            throw new FacesException(e);
        }
    }

    public List<Usuario> listarTodosUsuarios() {
        return listarObjsGenerico("Usuario");
    }

    public Usuario salvarUsr(Usuario usr) {
        return salvarGenerico(usr);
    }

    public void excluirUsrDao(Usuario usr) {
        excluirGenerico(usr);
    }

    public Usuario buscarPorEmail(Usuario user) {
        return listarSingleObjGenerico("Usuario", "email", user.getEmail());
    }

    public Usuario buscarPorCpfDAO(Usuario user) {
        return listarSingleObjGenerico("Usuario", "cpf", user.getCpf());
    }

    public Usuario buscarPorRgDAO(Usuario user) {
        return listarSingleObjGenerico("Usuario", "rg", user.getRg());
    }

    public Usuario buscarPorCodigo(Usuario usr) {
        List<Usuario> listaUsrs;

        listaUsrs = listarObjsFiltradosIntGenerico("Usuario", "id", usr.getId());

        if (listaUsrs.size() > 0) {
            usr = listaUsrs.get(0);
            System.out.println("NUM: " + listaUsrs.size());
        } else {
            usr = null;
            System.out.println("NUM: ");
        }
        return usr;
    }

    public Usuario logar(String email, String senha) {
        EntityManager em = emc.gerarEntityManager();
        Usuario usuario;
        List<Usuario> listaObjs;
        em.getTransaction().begin();

        Query consulta = em.createQuery("SELECT u FROM Usuario u WHERE u.email = :email AND u.senha = :senha");
        consulta.setParameter("email", email);
        consulta.setParameter("senha", senha);
        listaObjs = consulta.getResultList();//Pega a lista de objs
        System.out.println("Numero de users encontrados: " + listaObjs.size());
        if (listaObjs.size() > 0 && listaObjs.size() < 2) {
            usuario = listaObjs.get(0);
            return usuario;
        } else {
            return null;
        }
    }

    public long contarLinhasDAO() {
        return contarLinhasGenerico("Usuario");
    }
}
