package br.ifnmg.januaria.fernandes.itcp.dao;

import br.ifnmg.januaria.fernandes.itcp.domain.AtividadePlanejada;
import br.ifnmg.januaria.fernandes.itcp.domain.HorasTrabalhadas;
import br.ifnmg.januaria.fernandes.itcp.domain.Usuario;
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
        return listarObjsGenerico("HorasTrabalhadas");
    }

    public void excluirDao(HorasTrabalhadas obj) {
        excluirGenerico(obj);
    }

    public List<HorasTrabalhadas> listarPorUserDao(Usuario user, AtividadePlanejada atv) {
        EntityManager em = emc.gerarEntityManager();
        try {
            em.getTransaction().begin();
            List<HorasTrabalhadas> listaObjs;
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
}
