package br.ifnmg.januaria.fernandes.itcp.dao;

import br.ifnmg.januaria.fernandes.itcp.domain.Empreendimento;

/**
 *
 * @author alisson
 */
public class EmpreendimentoDAO extends DaoGenerico<Empreendimento> {

    EntityManagerCriador emc;
    
    public EmpreendimentoDAO() {
    }
    
    public void salvarEpt(Empreendimento ept){
        salvarGenerico(ept);
    }
}
