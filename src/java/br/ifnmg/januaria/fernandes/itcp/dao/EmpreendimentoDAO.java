package br.ifnmg.januaria.fernandes.itcp.dao;

import br.ifnmg.januaria.fernandes.itcp.domain.Empreendimento;
import java.util.List;

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
    
    public List<Empreendimento> listarTodosEmpreendimentos() {
        List<Empreendimento> listaEmpreendimentos; //
        return listarObjsGenerico("Empreendimento");
    }
}
