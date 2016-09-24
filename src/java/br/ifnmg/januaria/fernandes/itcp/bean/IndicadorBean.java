package br.ifnmg.januaria.fernandes.itcp.bean;

import br.ifnmg.januaria.fernandes.itcp.dao.IndicadorDAO;
import br.ifnmg.januaria.fernandes.itcp.domain.Indicador;
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
public class IndicadorBean implements Serializable{
    
    private IndicadorDAO dao;
    
    public IndicadorBean(){
        dao = new IndicadorDAO();
    }
    
    public Indicador salvarBean(Indicador objSalvar) {
        return dao.salvarDao(objSalvar);
    }
    
    public List<Indicador> listarBean() {
        return dao.listarDao();
    }
    
    public void excluirBean(Indicador obj){
        dao.excluirDao(obj);
    }
    
    public void iniciarIndicadoresBean(List<Indicador> listaSalvar){
        dao.iniciarIndicadoresDAO(listaSalvar);
    }
}