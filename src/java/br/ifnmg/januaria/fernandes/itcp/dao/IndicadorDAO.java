package br.ifnmg.januaria.fernandes.itcp.dao;

import br.ifnmg.januaria.fernandes.itcp.domain.Indicador;
import java.util.List;

/**
 *
 * @author alisson
 */
public class IndicadorDAO extends DaoGenerico<Indicador> {
    EntityManagerCriador emc;
    
    public IndicadorDAO() {
    }
    
    public Indicador salvarDao(Indicador obj){
        return salvarGenerico(obj);
    }
    
    public List<Indicador> listarDao() {
        return listarObjsGenerico("Indicador");
    }
    
    public void excluirDao(Indicador obj){
        excluirGenerico(obj);
    }
    
    public void iniciarIndicadoresDAO(List<Indicador> listaSalvar){
            salvarListaGenerico(listaSalvar);
    }
}
