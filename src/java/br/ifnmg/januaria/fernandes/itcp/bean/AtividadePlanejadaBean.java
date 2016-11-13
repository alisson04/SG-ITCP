package br.ifnmg.januaria.fernandes.itcp.bean;

import br.ifnmg.januaria.fernandes.itcp.dao.AtividadePlanejadaDAO;
import br.ifnmg.januaria.fernandes.itcp.domain.AtividadePlanejada;
import br.ifnmg.januaria.fernandes.itcp.domain.AtividadeUsuario;
import br.ifnmg.januaria.fernandes.itcp.domain.Meta;
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
public class AtividadePlanejadaBean implements Serializable{
    private AtividadePlanejadaDAO dao = new AtividadePlanejadaDAO();
    
    public AtividadePlanejadaBean(){
    }
    
    public AtividadePlanejada salvarBean(AtividadePlanejada obj) {
        return dao.salvarDao(obj);
    }
    
    public List<AtividadePlanejada> listarBean() {
        return dao.listarDao();
    }
    
    public void excluirBean(AtividadePlanejada obj){
        dao.excluirDao(obj);
    }
    
    public List<AtividadePlanejada> buscarAtividadesPorMetaBean(Meta obj) {
        return dao.buscarAtividadesPorMetaDao(obj);
    }
    
    public List<AtividadeUsuario> listarAtividadesPorUserBean(Usuario user) {
        return dao.listarAtividadesPorUserDao(user);
    }
}
