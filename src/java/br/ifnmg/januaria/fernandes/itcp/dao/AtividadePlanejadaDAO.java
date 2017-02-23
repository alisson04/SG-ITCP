package br.ifnmg.januaria.fernandes.itcp.dao;

import br.ifnmg.januaria.fernandes.itcp.domain.AtividadePlanejada;
import br.ifnmg.januaria.fernandes.itcp.domain.AtividadeUsuario;
import br.ifnmg.januaria.fernandes.itcp.domain.Meta;
import br.ifnmg.januaria.fernandes.itcp.domain.Usuario;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.FacesException;
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

    public List<AtividadeUsuario> listarAtividadesPorUserDao(Usuario user) {//
        String PU = "sigitecPU";
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PU);
        EntityManager em = emf.createEntityManager();

        List<AtividadeUsuario> listaObjsFiltrados;//Cria alista que será retornada
        listaObjsFiltrados = new ArrayList();

        em = emf.createEntityManager();
        em.getTransaction().begin();
        Query consulta = em.createQuery("SELECT o FROM AtividadeUsuario o "
                + "WHERE o.idUsuarioFk = :id ");
        consulta.setParameter("id", user.getId());

        listaObjsFiltrados = consulta.getResultList();//Pega a lista de objs

        em.close();

        return listaObjsFiltrados;
    }

    public List<AtividadePlanejada> listarPorIntervaloDao(Date dataIni, Date dataFim) {//
        String PU = "sigitecPU";
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PU);
        EntityManager em;
        em = emf.createEntityManager();

        List<AtividadePlanejada> listaObjsFiltrados;//Cria alista que será retornada
        listaObjsFiltrados = new ArrayList();

        try {
            em.getTransaction().begin();
            Query consulta = em.createQuery("SELECT o FROM AtividadePlanejada o "
                    + "WHERE (o.dataInicio between :dataIni AND :dataFim) AND (o.dataFim between :dataIni AND :dataFim) ");
            consulta.setParameter("dataIni", dataIni);
            consulta.setParameter("dataFim", dataFim);
            listaObjsFiltrados = consulta.getResultList();//Pega a lista de objs
            return listaObjsFiltrados;
        } catch (Exception ex) {
            throw new FacesException(ex);
        } finally {
            em.close();
        }
    }
}
