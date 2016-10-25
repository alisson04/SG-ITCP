package br.ifnmg.januaria.fernandes.itcp.dao;

import br.ifnmg.januaria.fernandes.itcp.domain.Empreendimento;
import br.ifnmg.januaria.fernandes.itcp.domain.EmpreendimentoIndicador;
import br.ifnmg.januaria.fernandes.itcp.domain.Indicador;
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

        List<EmpreendimentoIndicador> listaObjsFiltrados;//Cria alista que será retornada
        listaObjsFiltrados = new ArrayList<>();//Instancia  a lista

        for (int i = 0; i < 48; i++) {//Roda 48 vezes coletando os indicadores do ESS com maior data
            try {
                em = emf.createEntityManager();
                em.getTransaction().begin();
                EmpreendimentoIndicador indAux;//Cria o OBJ que receberá p resultado da consulta
                //A consulta retorna a instancia de maior data com tal IdEmpreendimento e IDindicador
                Query consulta = em.createQuery("SELECT o FROM EmpreendimentoIndicador o "
                        + "WHERE o.empreendimentoIndicadorPK.idEmpreendimentoFk = :id "
                        + "AND o.empreendimentoIndicadorPK.idIndicador = :idIndicador "
                        + "AND o.empreendimentoIndicadorPK.dataNota = "
                        + "(SELECT MAX(x.empreendimentoIndicadorPK.dataNota) FROM EmpreendimentoIndicador x "
                        + "WHERE x.empreendimentoIndicadorPK.idEmpreendimentoFk = :id "
                        + "AND x.empreendimentoIndicadorPK.idIndicador = :idIndicador)");
                consulta.setParameter("id", obj.getId());
                consulta.setParameter("idIndicador", i + 1);
                indAux = (EmpreendimentoIndicador) consulta.getSingleResult();//Pega o indicador com maior data para tal EES e ind

                if (indAux != null) {
                    listaObjsFiltrados.add(indAux);
                }

            } catch (NoResultException e) {
                em.getTransaction().rollback();
                System.out.println("IND " + (i + 1) + " Não preenchido");
            } finally {
                em.close();
            }
        }
        System.out.println("TAMANANANANA: " + listaObjsFiltrados.size());
        return listaObjsFiltrados;
    }

    //Busca uma lista por EES de uma categoria    
    public List<EmpreendimentoIndicador> listarEesIndisPorcategoriaDAO(Empreendimento ees, List<Indicador> indisDaCategoria) {
        String PU = "sigitecPU";
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PU);
        EntityManager em = emf.createEntityManager();

        List<EmpreendimentoIndicador> listaObjsFiltrados;//Cria a lista que será retornada
        listaObjsFiltrados = new ArrayList<>();//Instancia  a lista

        for (int i = 0; i < indisDaCategoria.size(); i++) {//Roda 48 vezes coletando os indicadores do ESS com maior data
            try {
                em = emf.createEntityManager();
                em.getTransaction().begin();
                //A consulta retorna a instancia de maior data com tal IdEmpreendimento e IDindicador
                Query consulta = em.createQuery("SELECT o FROM EmpreendimentoIndicador o "//Selecione os EmpreendimentoIndicadores
                        + "WHERE o.empreendimentoIndicadorPK.idEmpreendimentoFk = :id "//Enquanto o ID do EES for igual o IDrecebido
                        + "AND o.empreendimentoIndicadorPK.idIndicador = :idIndicador ");//E o ID do INDI for igual ao ID rodado
                consulta.setParameter("id", ees.getId());//Seta o ID do EES como paramentro
                consulta.setParameter("idIndicador", indisDaCategoria.get(i).getId());//Seta o ID do IND rodadno como paramentro
                listaObjsFiltrados.addAll(consulta.getResultList());//Pega os EmpreendimentoIndicadores para aquele ESS da categoria recebida
            } catch (Exception e) {
                em.getTransaction().rollback();
            } finally {
                em.close();
            }
        }
        System.out.println("Encontrados DAO: " + listaObjsFiltrados.size());
        return listaObjsFiltrados;
    }

    public long contarLinhasDAO() {
        return contarLinhasGenerico("EmpreendimentoIndicador");
    }
}
