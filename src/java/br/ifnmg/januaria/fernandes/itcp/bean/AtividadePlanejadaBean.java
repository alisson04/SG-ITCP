package br.ifnmg.januaria.fernandes.itcp.bean;

import br.ifnmg.januaria.fernandes.itcp.dao.AtividadePlanejadaDAO;
import br.ifnmg.januaria.fernandes.itcp.domain.AtividadePlanejada;
import br.ifnmg.januaria.fernandes.itcp.domain.AtividadeUsuario;
import br.ifnmg.januaria.fernandes.itcp.domain.Meta;
import br.ifnmg.januaria.fernandes.itcp.domain.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
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
    private AtividadePlanejadaDAO dao;
    
    public AtividadePlanejadaBean(){
        dao = new AtividadePlanejadaDAO();
    }
    
    //Salva uma atividade
    public AtividadePlanejada salvarBean(AtividadePlanejada obj) {
        return dao.salvarDao(obj);
    }
    
    //Lita todas as atividades
    public List<AtividadePlanejada> listarBean() {
        return dao.listarDao();
    }
    
    //Exclui uma atividade
    public void excluirBean(AtividadePlanejada obj){
        dao.excluirDao(obj);
    }
    
    public List<AtividadePlanejada> buscarAtividadesPorMetaBean(Meta obj) {
        return dao.buscarAtividadesPorMetaDao(obj);
    }
    
    public List<AtividadeUsuario> listarAtividadesPorUserBean(Usuario user) {
        return dao.listarAtividadesPorUserDao(user);
    }
    
    //Filtra atividade por Status
    public List<AtividadePlanejada> filtraAtividadesPorStatus(String status, List<AtividadePlanejada> listaAtv) {
        List<AtividadePlanejada> listaFiltrada;
        listaFiltrada= new ArrayList();
        
        for(int i=0; i<listaAtv.size(); i++){
            if(listaAtv.get(i).getStatus().equals(status)){
                listaFiltrada.add(listaAtv.get(i));
            }
        }
        return listaFiltrada;
    }
    
    //Filtra metas por Status
    public List<Meta> filtraMetasPorStatus(String status, List<Meta> listaAtv) {
        List<Meta> listaFiltrada;
        listaFiltrada= new ArrayList();
        
        for(int i=0; i<listaAtv.size(); i++){
            if(listaAtv.get(i).getStatus().equals(status)){
                listaFiltrada.add(listaAtv.get(i));
            }
        }
        return listaFiltrada;
    }
}
