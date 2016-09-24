package br.ifnmg.januaria.fernandes.itcp.dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.validation.ConstraintViolationException;

/**
 *
 * @author alisson
 */
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
        } catch (Exception x) {
            em.getTransaction().rollback();
            throw new RuntimeException("__________DAOGenerico(salvarGenerico): Erro ao salvar objetos: ", x);
        } finally {
            em.close();
        }
    }

    //Salvar uma lista de objetos no BD
    public void salvarListaGenerico(List<TipoClasse> listaSalvar) {
        em = gerarEntityManager();
        try {
            em.getTransaction().begin();

            System.out.println("TAMANHO lista: " + listaSalvar.size());
            for (int i = 0; i < listaSalvar.size(); i++) {
                System.out.println("LISTA: " + i);
                em.persist(listaSalvar.get(i));
                System.out.println("LISTA: " + i);
            }

            em.getTransaction().commit();
        } catch (Exception x) {
            em.getTransaction().rollback();
            throw new RuntimeException("__________DAOGenerico(salvarGenerico): Erro ao salvar objetos: ", x);
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
        } catch (ConstraintViolationException x) {
            System.out.println("__________DAOGenerico(SalvarGenerico) - getConstraintViolations():");
            System.out.println(x.getConstraintViolations());
            System.out.println("__________DAOGenerico(SalvarGenerico) - getConstraintViolations():");

            em.getTransaction().rollback();
            throw new RuntimeException("__________DAOGenerico(salvarGenerico): Erro ao salvar objetos: ", x);
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
        } catch (Exception e) {
            objResultado = null;
            em.getTransaction().rollback();
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
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException("__________DAOGenerico(listarTudoGenerico): Erro a o buscar objetos: ", e);
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
            System.out.println("__________DAOGenerico(listarObjsFiltradosGenerico): Numero de objs " + paramComparacao + " na lista: " + listaObjsFiltrados.size());
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("__________DAOGenerico(listarObjsFiltradosGenerico): Erro ao buscar objetos filtrados: " + e);
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

            listaObjsFiltrados = consulta.getResultList();//Pega a lista de usuarios
            System.out.println("__________DAOGenerico(listarObjsFiltradosIntGenerico): Numero de objs com id " + paramComparacao
                    + " na lista: " + listaObjsFiltrados.size());
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("__________DAOGenerico(listarObjsFiltradosIntGenerico): Erro ao buscar objetos filtrados: " + e);
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
        } catch (ConstraintViolationException x) {
            System.out.println("__________DAOGenerico(contarLinhasGenerico) - getConstraintViolations():");
            System.out.println(x.getConstraintViolations());
            em.getTransaction().rollback();
            throw new RuntimeException("__________DAOGenerico(contarLinhasGenerico): Erro ao salvar objetos: ", x);
        } finally {
            em.close();
        }
    }
}
