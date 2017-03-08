package br.ifnmg.januaria.fernandes.itcp.dao;

import br.ifnmg.januaria.fernandes.itcp.domain.Empreendimento;
import br.ifnmg.januaria.fernandes.itcp.domain.EmpreendimentoIndicador;
import br.ifnmg.januaria.fernandes.itcp.domain.HorasTrabalhadas;
import java.util.List;
import javax.faces.FacesException;
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
    
    public List<Empreendimento> listarEesAtivosDao(){
        EntityManager em = emc.gerarEntityManager();
        try {
            List<Empreendimento> listaObjs;//Cria alista que será retornada
            em.getTransaction().begin();
            String des = "desincubado";
            String nao = "Não incubado";
            
            Query consulta = em.createQuery("SELECT o FROM Empreendimento o "
                    + "where o.situacao != :U AND o.situacao != :A order by o.nome");
            consulta.setParameter("U", des);
            consulta.setParameter("A", nao);
            
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
    
    public long contarLinhasDAO(){
        return contarLinhasGenerico("Empreendimento");
    }
}
