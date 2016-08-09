package br.ifnmg.januaria.fernandes.itcp.dao;

import br.ifnmg.januaria.fernandes.itcp.domain.Empreendimento;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author alisson
 */
public class EmpreendimentoDAO extends DaoGenerico<Empreendimento> {

    EntityManagerCriador emc;
    
    public EmpreendimentoDAO() {
    }
    
    public Empreendimento salvarEpt(Empreendimento ept){
        return salvarGenerico(ept);
    }
    
    public void excluirEptDao(Empreendimento ept){
        excluirGenerico(ept);
    }
    
    public List<Empreendimento> listarTodosEptsDao() {
        return listarObjsGenerico("Empreendimento");
    }
    
    public Empreendimento buscarPorCodigo(Empreendimento ept) {
        EntityManager em = emc.gerarEntityManager();
        try {
            em.getTransaction().begin();
            Query consulta = em.createQuery("SELECT e FROM Empreendimento e WHERE e.idEpt = :idEpt");
            consulta.setParameter("idEpt", ept.getIdEpt()); // O primeiro "id" é do Domain.Usuario; o segundo do que foi criado nesse metodo
            ept = (Empreendimento) consulta.getSingleResult();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar por codigo", e);
        } finally {
            em.close();
        }
        return ept;
    }
}
