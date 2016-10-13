package br.ifnmg.januaria.fernandes.itcp.dao;

import br.ifnmg.januaria.fernandes.itcp.domain.Empreendimento;
import br.ifnmg.januaria.fernandes.itcp.domain.EmpreendimentoIndicador;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author alisson
 */
public class EmpreendimentoIndicadorDAO extends DaoGenerico<EmpreendimentoIndicador> {

    public EmpreendimentoIndicadorDAO() {
    }

    public void salvarListaDAO(List<EmpreendimentoIndicador> listaEptInd) {//Savar lista de objetos
        salvarListaGenerico(listaEptInd);
    }

    public void excluirDao(EmpreendimentoIndicador ip) {//Excluir objeto
        excluirGenerico(ip);
    }

    public List<EmpreendimentoIndicador> listarTodosDAO() {//Listar todos os objetos
        return listarObjsGenerico("EmpreendimentoIndicador");
    }

    public List<EmpreendimentoIndicador> buscarListaPorCodigo(Empreendimento obj) {//Busca uma lista por código
        String PU = "sigitecPU";
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PU);
        EntityManager em = emf.createEntityManager();
        
        List<EmpreendimentoIndicador> listaObjsFiltrados;
        listaObjsFiltrados = new ArrayList<>();

        for (int i = 0; i < 48; i++) {//Roda 48 vezes coletando os indicadores do ESS com maior data
            try {
                em = emf.createEntityManager();
                em.getTransaction().begin();
                EmpreendimentoIndicador indAux;
                //A consulta retorna a instancia de maior data com tal IdEmpreendimento e IDindicador
                Query consulta = em.createQuery("SELECT o FROM EmpreendimentoIndicador o "
                        + "WHERE o.empreendimento.id = :id "
                        + "AND o.idIndicador = :idIndicador "
                        + "AND o.dataNota = "
                        + "(SELECT MAX(x.dataNota) FROM EmpreendimentoIndicador x WHERE x.empreendimento.id = :id "
                        + "AND x.idIndicador = :idIndicador)");
                consulta.setParameter("id", obj.getId());
                consulta.setParameter("idIndicador", i+1);
                indAux = (EmpreendimentoIndicador) consulta.getSingleResult();//Pega o indicador com maior data para tal EES e ind
                
                if (indAux != null) {
                    listaObjsFiltrados.add(indAux);
                }
                
            } catch (NoResultException e) {
                em.getTransaction().rollback();
                System.out.println("IND " + (i+1) + " Não preenchido");
            } finally {
                em.close();
            }
        }
        System.out.println("TAMANANANANA: " + listaObjsFiltrados.size());
        return listaObjsFiltrados;
    }

    public long contarLinhasDAO() {
        return contarLinhasGenerico("EmpreendimentoIndicador");
    }
}
