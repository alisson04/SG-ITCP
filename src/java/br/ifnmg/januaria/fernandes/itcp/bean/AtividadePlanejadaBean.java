package br.ifnmg.januaria.fernandes.itcp.bean;

import br.ifnmg.januaria.fernandes.itcp.dao.AtividadeExecutadaDAO;
import br.ifnmg.januaria.fernandes.itcp.dao.AtividadePlanejadaDAO;
import br.ifnmg.januaria.fernandes.itcp.domain.AtividadeExecutada;
import br.ifnmg.januaria.fernandes.itcp.domain.AtividadePlanejada;
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
public class AtividadePlanejadaBean implements Serializable{
    private AtividadePlanejadaDAO dao = new AtividadePlanejadaDAO();
    
    public AtividadePlanejadaBean(){
    }
    
    public void salvarBean(AtividadePlanejada metaSalvar) {
        dao.salvarDao(metaSalvar);
    }
    
    public List<AtividadePlanejada> listarBean() {
        return dao.listarDao();
    }
    
    public void excluirBean(AtividadePlanejada meta){
        dao.excluirDao(meta);
    }
}
