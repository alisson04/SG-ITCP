package br.ifnmg.januaria.fernandes.itcp.bean;

import br.ifnmg.januaria.fernandes.itcp.dao.PlanoAcaoDAO;
import br.ifnmg.januaria.fernandes.itcp.domain.PlanoAcao;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.List;
import org.primefaces.context.RequestContext;
/**
 *
 * @author alisson
 */
@ManagedBean
@SessionScoped
public class PlanoAcaoBean implements Serializable{
    private PlanoAcaoDAO dao;
    private PlanoAcao planoEditar = null;//deve receber null
    
    public PlanoAcaoBean(){
        dao = new PlanoAcaoDAO();
    }
    
    public PlanoAcao salvarBean(PlanoAcao obj) {
        return dao.salvarPlano(obj);
    }
    
    public void excluirBean(PlanoAcao obj){
        dao.excluirPlanoDao(obj);
    }
    
    public List<PlanoAcao> listarBean() {
        return dao.listarTodosPlanos();
    }
    
    //SETS E GETS
    public PlanoAcao getPlanoEditar() {
        return planoEditar;
    }

    public void setPlanoEditar(PlanoAcao planoEditar) {
        this.planoEditar = planoEditar;
    }
}