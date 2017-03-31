package br.ifnmg.januaria.fernandes.itcp.dao;

import br.ifnmg.januaria.fernandes.itcp.domain.EventoLog;
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
public class EventoLogDAO extends DaoGenerico<EventoLog>{
    public EventoLog salvarDao(EventoLog obj){
        return salvarGenerico(obj);
    }
    
    public void excluirDao(EventoLog obj){
        excluirGenerico(obj);
    }

    public List<EventoLog> listarPorIntervaloDao(Date dataIni, Date dataFim) {//
        String PU = "sigitecPU";
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PU);
        EntityManager em;
        em = emf.createEntityManager();

        List<EventoLog> listaObjsFiltrados;//Cria alista que será retornada
        listaObjsFiltrados = new ArrayList();

        try {
            em.getTransaction().begin();
            Query consulta = em.createQuery("SELECT o FROM EventoLog o "
                    + "WHERE (o.data between :dataIni AND :dataFim)");
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