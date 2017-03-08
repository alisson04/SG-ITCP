package br.ifnmg.januaria.fernandes.itcp.bean;

import br.ifnmg.januaria.fernandes.itcp.dao.EmpreendimentoDAO;
import br.ifnmg.januaria.fernandes.itcp.domain.Empreendimento;
import br.ifnmg.januaria.fernandes.itcp.domain.Usuario;
import br.ifnmg.januaria.fernandes.itcp.util.RelatoriosManager;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    public Empreendimento salvarBean(Empreendimento ept) {
        return dao.salvarEpt(ept);
    }
    
    public void excluirBean(Empreendimento ept){
        dao.excluirEptDao(ept);
    }
    
    public List<Empreendimento> listarBean() {
        return dao.listarTodosEptsDao();
    }
    
    public List<Empreendimento> listarEesAtivosBean() {
        return dao.listarEesAtivosDao();
    }
    
    public long contarLinhasBean(){
        return dao.contarLinhasDAO();
    }
}
