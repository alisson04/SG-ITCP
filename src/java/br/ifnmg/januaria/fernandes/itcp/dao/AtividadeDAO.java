package br.ifnmg.januaria.fernandes.itcp.dao;

import br.ifnmg.januaria.fernandes.itcp.domain.Atividade;

/**
 *
 * @author alisson
 */
public class AtividadeDAO extends DaoGenerico<Atividade>{
    public void TestaAtividade(){
        Atividade at = new Atividade();
        salvarGenerico(at);
    }
}
