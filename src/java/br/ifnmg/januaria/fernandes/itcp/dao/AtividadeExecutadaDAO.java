package br.ifnmg.januaria.fernandes.itcp.dao;

import br.ifnmg.januaria.fernandes.itcp.domain.AtividadeExecutada;
import java.util.List;


/**
 *
 * @author alisson
 */
public class AtividadeExecutadaDAO extends DaoGenerico<AtividadeExecutada>{
    public void TestaAtividade(){
        AtividadeExecutada at = new AtividadeExecutada();
        salvarGenerico(at);
    }
    
    public void salvarEpt(AtividadeExecutada atividadeSalvar){
        salvarGenerico(atividadeSalvar);
    }
    
    public List<AtividadeExecutada> listarTodosParceiros() {
        return listarObjsGenerico("AtividadeExecutada");
    }
}
