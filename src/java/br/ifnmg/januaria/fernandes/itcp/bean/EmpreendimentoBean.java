package br.ifnmg.januaria.fernandes.itcp.bean;

import br.ifnmg.januaria.fernandes.itcp.dao.EmpreendimentoDAO;
import br.ifnmg.januaria.fernandes.itcp.domain.Empreendimento;
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
public class EmpreendimentoBean implements Serializable {

    private EmpreendimentoDAO dao;
    
    public EmpreendimentoBean() {
        dao = new EmpreendimentoDAO();
    }

    public void salvarEptBean(Empreendimento ept) {
        dao.salvarEpt(ept);
    }
    
    public void excluirEptBean(Empreendimento ept){
        dao.excluirEptDao(ept);
    }
    
    public List<Empreendimento> listarTodosEptsBean() {
        return dao.listarTodosEptsDao();
    }
    
    public Empreendimento buscarPorCodigoBean(Empreendimento ept){
        return dao.buscarPorCodigo(ept);
    }
}
