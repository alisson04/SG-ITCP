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
    private PlanoAcaoDAO dao;
    private PlanoAcao planoEditar = null;//deve receber null
    private int i=0;
    
    public PlanoAcaoBean(){
        dao = new PlanoAcaoDAO();
        System.out.println( i + "º ACESSO =============");
    }
    
    public void editarPlanoBean(PlanoAcao plano){
        contar();
        planoEditar = plano;
    }
    
    public void contar(){
        i = i + 1;
        System.out.println( i + "º ACESSO =============");
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
    
    //SETS E GETS
    public PlanoAcao getPlanoEditar() {
        return planoEditar;
    }

    public void setPlanoEditar(PlanoAcao planoEditar) {
        this.planoEditar = planoEditar;
    }
}