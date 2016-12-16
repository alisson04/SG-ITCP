package br.ifnmg.januaria.fernandes.itcp.bean;

import br.ifnmg.januaria.fernandes.itcp.dao.HorasTrabalhadasDAO;
import br.ifnmg.januaria.fernandes.itcp.domain.AcompanhamentoEpt;
import br.ifnmg.januaria.fernandes.itcp.domain.AtividadePlanejada;
import br.ifnmg.januaria.fernandes.itcp.domain.HorasTrabalhadas;
import br.ifnmg.januaria.fernandes.itcp.domain.Usuario;
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
public class HorasTrabalhadasBean implements Serializable{
    private HorasTrabalhadasDAO dao = new HorasTrabalhadasDAO();
    
    public HorasTrabalhadasBean(){
    }
    
    public HorasTrabalhadas salvarBean(HorasTrabalhadas obj) {
        return dao.salvarDao(obj);
    }
    
    public List<HorasTrabalhadas> listarBean() {
        return dao.listarDao();
    }
    
    public void excluirBean(HorasTrabalhadas obj){
        dao.excluirDao(obj);
    }
    
    public List<HorasTrabalhadas> listarPorUserAtividadeBean(Usuario user, AtividadePlanejada atv){
        return dao.listarPorUserDao(user, atv);
    }
}
