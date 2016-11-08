package br.ifnmg.januaria.fernandes.itcp.dao;

import br.ifnmg.januaria.fernandes.itcp.domain.AtividadePlanejada;
import br.ifnmg.januaria.fernandes.itcp.domain.Meta;
import br.ifnmg.januaria.fernandes.itcp.domain.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author alisson
 */
public class AtividadePlanejadaDAO extends DaoGenerico<AtividadePlanejada> {

    public AtividadePlanejada salvarDao(AtividadePlanejada obj) {
        return salvarGenerico(obj);
    }

    public List<AtividadePlanejada> listarDao() {
        return listarObjsGenerico("AtividadePlanejada");
    }

    public void excluirDao(AtividadePlanejada obj) {
        excluirGenerico(obj);
    }

    public List<AtividadePlanejada> buscarAtividadesPorMetaDao(Meta obj) {
        return listarObjsFiltradosIntGenerico("AtividadePlanejada", "meta.idMeta", obj.getIdMeta());
    }

    public List<AtividadePlanejada> listarAtividadesPorUserDao(Usuario user) {//
        String PU = "sigitecPU";
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PU);
        EntityManager em = emf.createEntityManager();

        List<AtividadePlanejada> listaObjsFiltrados;//Cria alista que será retornada
        listaObjsFiltrados = new ArrayList();

        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            Query consulta = em.createQuery("SELECT o FROM AtividadeUsuario o "
                    + "WHERE o.empreendimentoIndicadorPK.idEmpreendimentoFk = :id " );
            consulta.setParameter("id", user.getId());
            

        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        System.out.println("TAMANANANANA: " + listaObjsFiltrados.size());
        return listaObjsFiltrados;
    }
}
