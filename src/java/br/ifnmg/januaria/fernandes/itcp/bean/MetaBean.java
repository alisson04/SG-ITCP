package br.ifnmg.januaria.fernandes.itcp.bean;

import br.ifnmg.januaria.fernandes.itcp.dao.MetaDAO;
import br.ifnmg.januaria.fernandes.itcp.domain.Meta;
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
public class MetaBean implements Serializable{
    
    private MetaDAO dao;
    
    public MetaBean(){
        dao = new MetaDAO();
    }
    
    public Meta salvarBean(Meta metaSalvar) {
        return dao.salvarDao(metaSalvar);
    }
    
    public List<Meta> listarBean() {
        return dao.listarDao();
    }
    
    public void excluirBean(Meta meta){
        dao.excluirDao(meta);
    }
}