package br.ifnmg.januaria.fernandes.itcp.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author alisson
 */
public class EntityManagerCriador {
    private static final String PU = "sigitecPU";
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory(PU);
    
    public static EntityManager gerarEntityManager(){
        return emf.createEntityManager();
    }
    
    public static void fecharEntityManager(){
        emf.close();
    }
}