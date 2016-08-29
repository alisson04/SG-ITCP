package br.ifnmg.januaria.fernandes.itcp.bean;

import br.ifnmg.januaria.fernandes.itcp.dao.AcompanhamentoEptDAO;
import br.ifnmg.januaria.fernandes.itcp.domain.AcompanhamentoEpt;
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
public class AcompanhamentoEptBean implements Serializable{
    private AcompanhamentoEptDAO dao = new AcompanhamentoEptDAO();
    
    public AcompanhamentoEptBean(){
    }
    
    public AcompanhamentoEpt salvarBean(AcompanhamentoEpt obj) {
        return dao.salvarDao(obj);
    }
    
    public List<AcompanhamentoEpt> listarBean() {
        return dao.listarDao();
    }
    
    public void excluirBean(AcompanhamentoEpt obj){
        dao.excluirDao(obj);
    }
}