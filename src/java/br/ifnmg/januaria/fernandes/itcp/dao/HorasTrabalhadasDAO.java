package br.ifnmg.januaria.fernandes.itcp.dao;

import br.ifnmg.januaria.fernandes.itcp.domain.AtividadePlanejada;
import br.ifnmg.januaria.fernandes.itcp.domain.Empreendimento;
import br.ifnmg.januaria.fernandes.itcp.domain.HorasTrabalhadas;
import br.ifnmg.januaria.fernandes.itcp.domain.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.faces.FacesException;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author alisson
 */
public class HorasTrabalhadasDAO extends DaoGenerico<HorasTrabalhadas> {

    EntityManagerCriador emc;

    public HorasTrabalhadas salvarDao(HorasTrabalhadas obj) {
        return salvarGenerico(obj);
    }

    public List<HorasTrabalhadas> listarDao() {
        EntityManager em = emc.gerarEntityManager();
        try {
            List<HorasTrabalhadas> listaObjs;//Cria alista que será retornada
            em.getTransaction().begin();
            Query consulta = em.createQuery("SELECT o FROM HorasTrabalhadas o ORDER BY o.dataTrabalho");
            
            listaObjs = consulta.getResultList();//Pega a lista de objs
            em.getTransaction().commit();
            return listaObjs;
        } catch (Exception ex) {
            em.getTransaction().rollback();
            throw new FacesException(ex);
        } finally {
            em.close();
        }
    }

    public void excluirDao(HorasTrabalhadas obj) {
        excluirGenerico(obj);
    }

    public List<HorasTrabalhadas> listarPorUserAtividadeDao(Usuario user, AtividadePlanejada atv) {
        EntityManager em = emc.gerarEntityManager();
        try {
            List<HorasTrabalhadas> listaObjs;//Cria alista que será retornada
            em.getTransaction().begin();
            Query consulta = em.createQuery("SELECT h FROM HorasTrabalhadas h WHERE h.usuario.id = :U AND h.atividadePlanejada.id = :A");
            consulta.setParameter("U", user.getId());
            consulta.setParameter("A", atv.getId());
            listaObjs = consulta.getResultList();//Pega a lista de objs
            em.getTransaction().commit();
            return listaObjs;
        } catch (Exception ex) {
            em.getTransaction().rollback();
            throw new FacesException(ex);
        } finally {
            em.close();
        }
    }
    
    public List<HorasTrabalhadas> listarPorAtividadeDao(AtividadePlanejada atv) {
        EntityManager em = emc.gerarEntityManager();
        try {
            List<HorasTrabalhadas> listaObjs;//Cria alista que será retornada
            em.getTransaction().begin();
            Query consulta = em.createQuery("SELECT h FROM HorasTrabalhadas h WHERE h.atividadePlanejada.id = :A");
           
            consulta.setParameter("A", atv.getId());
            listaObjs = consulta.getResultList();//Pega a lista de objs
            em.getTransaction().commit();
            return listaObjs;
        } catch (Exception ex) {
            em.getTransaction().rollback();
            throw new FacesException(ex);
        } finally {
            em.close();
        }
    }

    public List<HorasTrabalhadas> listarPorEesDao(Empreendimento ees) {
        System.out.println("ID do EES: " + ees.getId());
        EntityManager em = emc.gerarEntityManager();
        try {
            List<HorasTrabalhadas> listaObjs;//Cria alista que será retornada
            em.getTransaction().begin();
            Query consulta = em.createQuery("SELECT h FROM HorasTrabalhadas h "
                    + "WHERE h.atividadePlanejada.meta.planoAcao.empreendimento.id = :A");
            consulta.setParameter("A", ees.getId());
            listaObjs = consulta.getResultList();//Pega a lista de objs
            em.getTransaction().commit();
            return listaObjs;
        } catch (Exception ex) {
            em.getTransaction().rollback();
            throw new FacesException(ex);
        } finally {
            em.close();
        }
    }
}
