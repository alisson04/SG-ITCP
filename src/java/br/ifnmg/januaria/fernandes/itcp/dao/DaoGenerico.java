package br.ifnmg.januaria.fernandes.itcp.dao;

import java.util.ArrayList;
import java.util.List;
import javax.faces.FacesException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TransactionRequiredException;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

/**
 *
 * @author alisson
 */
@Transactional
public abstract class DaoGenerico<TipoClasse> extends EntityManagerCriador {

    private EntityManager em;

    //Construtor
    public DaoGenerico() {
    }

    //Salvar um objeto no BD
    public TipoClasse salvarGenerico(TipoClasse objeto) {
        em = gerarEntityManager();
        try {
            em.getTransaction().begin();
            objeto = em.merge(objeto);
            em.getTransaction().commit();
            return objeto;
        } catch (Exception ex) {
            em.getTransaction().rollback();
            throw new FacesException(ex);
        } finally {
            em.close();
        }
    }

    //Salvar uma lista de objetos no BD
    public void salvarListaGenerico(List<TipoClasse> listaSalvar) throws TransactionRequiredException {
        em = gerarEntityManager();
        try {
            em.getTransaction().begin();
            for (int i = 0; i < listaSalvar.size(); i++) {
                em.merge(listaSalvar.get(i));
            }

            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
            throw new FacesException(ex);
        } finally {
            em.close();
        }
    }

    //Excluir um objeto do BD
    public void excluirGenerico(TipoClasse objeto) {
        em = gerarEntityManager();
        try {
            em.getTransaction().begin();
            objeto = em.merge(objeto);//Evita o bug de entidade sendo usada
            em.remove(objeto);
            em.getTransaction().commit();
        } catch (ConstraintViolationException ex) {
            em.getTransaction().rollback();
            throw new FacesException(ex);
        } finally {
            em.close();
        }
    }

    //Pegar um objeto por String
    public TipoClasse listarSingleObjGenerico(String classe, String atributoClasse, String paramComparacao) {
        em = gerarEntityManager();
        TipoClasse objResultado;

        try {
            em.getTransaction().begin();
            Query consulta = em.createQuery("SELECT o FROM " + classe + " o WHERE o." + atributoClasse + " = :paramComparacao");
            consulta.setParameter("paramComparacao", paramComparacao);

            objResultado = (TipoClasse) consulta.getSingleResult();//Pega a lista de usuarios
        }  catch (NoResultException ex) {
            objResultado = null;
            em.getTransaction().rollback();
            return objResultado;
        } catch (Exception ex) {
            objResultado = null;
            em.getTransaction().rollback();
            throw new FacesException(ex);
        } finally {
            em.close();
        }
        return objResultado;
    }

    //Lista todos os objetos de uma classe no BD
    public List<TipoClasse> listarObjsGenerico(String classe) {
        em = gerarEntityManager();
        List<TipoClasse> listaObjs;
        listaObjs = new ArrayList<>();
        try {
            em.getTransaction().begin();
            Query consulta = em.createQuery("SELECT o FROM " + classe + " o");
            listaObjs = consulta.getResultList();//Pega a lista de objs
        } catch (Exception ex) {
            em.getTransaction().rollback();
            throw new FacesException(ex);
        } finally {
            em.close();
        }
        return listaObjs;
    }

    //Lista todos os objetos de uma classe no BD filtrados por um parametro STRING
    public List<TipoClasse> listarObjsFiltradosGenerico(String classe, String atributoClasse, String paramComparacao) {
        em = gerarEntityManager();
        List<TipoClasse> listaObjsFiltrados;
        listaObjsFiltrados = new ArrayList<>();

        try {
            em.getTransaction().begin();
            Query consulta = em.createQuery("SELECT o FROM " + classe + " o WHERE o." + atributoClasse + " = :paramComparacao");
            consulta.setParameter("paramComparacao", paramComparacao);

            listaObjsFiltrados = consulta.getResultList();//Pega a lista de usuarios
        } catch (Exception ex) {
            em.getTransaction().rollback();
            throw new FacesException(ex);
        } finally {
            em.close();
        }
        return listaObjsFiltrados;
    }

    //Lista todos os objetos de uma classe no BD filtrados por um parametro INT
    public List<TipoClasse> listarObjsFiltradosIntGenerico(String classe, String atributoDaClasse, int paramComparacao) {
        em = gerarEntityManager();
        List<TipoClasse> listaObjsFiltrados;
        listaObjsFiltrados = new ArrayList<>();

        try {
            em.getTransaction().begin();
            Query consulta = em.createQuery("SELECT o FROM " + classe + " o WHERE o." + atributoDaClasse + " = :paramComparacao");
            consulta.setParameter("paramComparacao", paramComparacao);

            listaObjsFiltrados = consulta.getResultList();//Pega a lista de objetos
        } catch (Exception ex) {
            em.getTransaction().rollback();
            throw new FacesException(ex);
        } finally {
            em.close();
        }
        return listaObjsFiltrados;
    }

    //Conta linha de uma tabela no BD
    public long contarLinhasGenerico(String classe) {
        em = gerarEntityManager();
        try {
            em.getTransaction().begin();
            Query consulta = em.createQuery("SELECT COUNT(o.id) FROM " + classe + " o ");
            long numLinhas = (long) consulta.getSingleResult();//Pega a lista de usuarios
            System.out.println("__________DAOGenerico(contarLinhasGenerico) número de linhas: " + numLinhas);
            return numLinhas;
        } catch (ConstraintViolationException ex) {
            em.getTransaction().rollback();
            throw new FacesException(ex);
        } finally {
            em.close();
        }
    }
}
