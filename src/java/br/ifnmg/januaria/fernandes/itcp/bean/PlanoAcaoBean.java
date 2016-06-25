package br.ifnmg.januaria.fernandes.itcp.bean;

import br.ifnmg.januaria.fernandes.itcp.dao.PlanoAcaoDAO;
import br.ifnmg.januaria.fernandes.itcp.domain.PlanoAcao;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.List;
/**
 *
 * @author alisson
 */
@ManagedBean
@SessionScoped
public class PlanoAcaoBean implements Serializable{
    PlanoAcaoDAO dao;
    
    public PlanoAcaoBean(){
        dao = new PlanoAcaoDAO();
    }
    
    public void salvarParceiroBd(PlanoAcao planoAcao) {
        dao.salvarPlano(planoAcao);
    }
    
    public void excluirPlanoBean(PlanoAcao planoAcao){
        dao.excluirPlanoDao(planoAcao);
    }
    
    public List<PlanoAcao> listarTodosPlanos() {
        return dao.listarTodosPlanos();
    }
}