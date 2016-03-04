package br.ifnmg.januaria.fernandes.itcp.dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author alisson
 */
public abstract class DaoGenerico<TipoClasse> extends EntityManagerCriador {

    //Construtor
    public DaoGenerico() {

    }

    public void salvarGenerico(TipoClasse objeto) {
        EntityManager em = gerarEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(objeto);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException("__________DAOGenerico(salvarGenerico): Erro ao buscar objetos: ", e);
        } finally {
            em.close();
        }
    }

    public List<TipoClasse> listarObjsGenerico(String classe) {
        EntityManager em = gerarEntityManager();
        List<TipoClasse> listaObjs;
        listaObjs = new ArrayList<>();
        try {
            em.getTransaction().begin();
            Query consulta = em.createQuery("SELECT o FROM " + classe + " o");
            listaObjs = consulta.getResultList();//Pega a lista de objs
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException("__________DAOGenerico(listarTudoGenerico): Erro a o buscar objetos: ", e);
        } finally {
            em.close();
        }
        return listaObjs;
    }

    public List<TipoClasse> listarObjsFiltradosGenerico(String classe, String atributoDaClasse, String parametroDeComparacao) {
        EntityManager em = gerarEntityManager();
        List<TipoClasse> listaObjsFiltrados;
        listaObjsFiltrados = new ArrayList<>();

        try {
            em.getTransaction().begin();
            Query consulta = em.createQuery("SELECT o FROM " + classe + " o WHERE o." + atributoDaClasse + " = :" + parametroDeComparacao);
            consulta.setParameter(parametroDeComparacao, parametroDeComparacao);

            listaObjsFiltrados = consulta.getResultList();//Pega a lista de usuarios
            System.out.println("__________DAO(listarUsrAtivos): Numero de usuarios " + parametroDeComparacao + " na lista: " + listaObjsFiltrados.size());
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("__________DAO(listarObjsFiltradosGenerico): Erro ao buscar objetos filtrados: " + e);
        } finally {
            em.close();
        }
        return listaObjsFiltrados;
    }
}