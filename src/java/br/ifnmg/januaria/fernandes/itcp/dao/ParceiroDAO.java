package br.ifnmg.januaria.fernandes.itcp.dao;

import br.ifnmg.januaria.fernandes.itcp.domain.Parceiro;

/**
 *
 * @author alisson
 */
public class ParceiroDAO extends DaoGenerico<Parceiro> {
    EntityManagerCriador emc;
    
    public ParceiroDAO() {
    }
    
    public void salvarEpt(Parceiro parceiroSalvar){
        salvarGenerico(parceiroSalvar);
    }
}
