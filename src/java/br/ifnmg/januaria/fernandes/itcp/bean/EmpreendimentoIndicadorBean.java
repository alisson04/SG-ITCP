package br.ifnmg.januaria.fernandes.itcp.bean;

import br.ifnmg.januaria.fernandes.itcp.dao.EmpreendimentoIndicadorDAO;
import br.ifnmg.januaria.fernandes.itcp.domain.Empreendimento;
import br.ifnmg.januaria.fernandes.itcp.domain.EmpreendimentoIndicador;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author alisson
 */
@ManagedBean
@SessionScoped
public class EmpreendimentoIndicadorBean implements Serializable {

    private EmpreendimentoIndicadorDAO dao;
    
    public EmpreendimentoIndicadorBean() {
        dao = new EmpreendimentoIndicadorDAO();
    }

    public void salvarBean(List<EmpreendimentoIndicador> listaEptInd) {
        dao.salvarListaDAO(listaEptInd);
    }
    
    public void excluirBean(EmpreendimentoIndicador ept){
        dao.excluirDao(ept);
    }
    
    public List<EmpreendimentoIndicador> listarBean() {
        return dao.listarTodosDAO();
    }
    
    public long contarLinhasBean(){
        return dao.contarLinhasDAO();
    }
    
    public List<EmpreendimentoIndicador> buscarListaPorCodigoBean(Empreendimento obj){
        return dao.buscarListaPorCodigo(obj);
    }
}