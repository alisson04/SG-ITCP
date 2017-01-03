package br.ifnmg.januaria.fernandes.itcp.dao;

import br.ifnmg.januaria.fernandes.itcp.domain.Empreendimento;
import br.ifnmg.januaria.fernandes.itcp.domain.EmpreendimentoIndicador;
import br.ifnmg.januaria.fernandes.itcp.domain.NotaMaturidade;
import java.util.ArrayList;
import java.util.List;
import javax.faces.FacesException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author alisson
 */
public class NotaMaturidadeDAO extends DaoGenerico<NotaMaturidade> {

    EntityManagerCriador emc;

    public NotaMaturidadeDAO() {
    }

    public NotaMaturidade salvarEpt(NotaMaturidade obj) {
        return salvarGenerico(obj);
    }

    public List<NotaMaturidade> listarUltimasNotasDao(List<Empreendimento> listaEpt) {
        String PU = "sigitecPU";
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PU);
        EntityManager em = emf.createEntityManager();

        List<NotaMaturidade> listaObjsFiltrados = new ArrayList<>();//Cria alista que será retornada
        for (int i = 0; i < listaEpt.size(); i++) {//Roda todos os Ept coletando as notas, com maior data
            try {//Precisa estar dentro do FOR
                em = emf.createEntityManager();
                em.getTransaction().begin();
                NotaMaturidade notaAux;//Cria o OBJ que receberá p resultado da consulta

                //A consulta retorna a instancia de maior data com tal IdEmpreendimento
                Query consulta = em.createQuery("SELECT o FROM NotaMaturidade o WHERE o.empreendimento.id = :id "
                        + "AND o.dataNota = (SELECT MAX(x.dataNota) FROM NotaMaturidade x "
                        + "WHERE x.empreendimento.id = :id )");
                consulta.setParameter("id", listaEpt.get(i).getId());

                notaAux = (NotaMaturidade) consulta.getSingleResult();//Pega o resultado

                if (notaAux != null) {//Só uma prevenção visto que o NoResultException foi declarado
                    listaObjsFiltrados.add(notaAux);

                    System.out.println("DAOOOOO: " + notaAux.getEmpreendimento().getId());
                }
            } catch (NoResultException ex) {
                System.out.println("EPT " + i + " não tem lançamentos");
            } catch (Exception ex) {
                throw new FacesException(ex);
            }
        }
        em.close();

        for (int i = 0; i < listaObjsFiltrados.size(); i++) {//
            System.out.println("FOR: " + listaObjsFiltrados.get(i).getEmpreendimento().getId());
        }
        return listaObjsFiltrados;
    }

    public List<NotaMaturidade> listarNotasPorEesDao(Empreendimento ees) {
        String PU = "sigitecPU";
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PU);
        EntityManager em = emf.createEntityManager();

        List<NotaMaturidade> listaObjsFiltrados = new ArrayList();//Cria alista que será retornada

        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();

            Query consulta = em.createQuery("SELECT o FROM NotaMaturidade o WHERE o.empreendimento.id = :id ");
            consulta.setParameter("id", ees.getId());

            listaObjsFiltrados = consulta.getResultList();//Pega o resultado
        } catch (NoResultException ex) {

        } catch (Exception ex) {
            throw new FacesException(ex);
        } finally {
            em.close();
        }

        return listaObjsFiltrados;
    }
}
