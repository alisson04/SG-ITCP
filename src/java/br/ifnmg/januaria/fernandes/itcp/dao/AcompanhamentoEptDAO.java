package br.ifnmg.januaria.fernandes.itcp.dao;

import br.ifnmg.januaria.fernandes.itcp.domain.AcompanhamentoEpt;
import br.ifnmg.januaria.fernandes.itcp.domain.AtividadePlanejada;
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
public class AcompanhamentoEptDAO extends DaoGenerico<AcompanhamentoEpt>{
    public AcompanhamentoEpt salvarDao(AcompanhamentoEpt obj){
        return salvarGenerico(obj);
    }
    
    public List<AcompanhamentoEpt> listarDao() {
        return listarObjsGenerico("AcompanhamentoEpt");
    }
    
    public void excluirDao(AcompanhamentoEpt obj){
        excluirGenerico(obj);
    }

    public List<AcompanhamentoEpt> listarPorIntervaloDao(Date dataIni, Date dataFim) {//
        String PU = "sigitecPU";
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PU);
        EntityManager em;
        em = emf.createEntityManager();

        List<AcompanhamentoEpt> listaObjsFiltrados;//Cria alista que será retornada
        listaObjsFiltrados = new ArrayList();

        try {
            em.getTransaction().begin();
            Query consulta = em.createQuery("SELECT o FROM AcompanhamentoEpt o "
                    + "WHERE (o.dataAcompanhamento between :dataIni AND :dataFim)");
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