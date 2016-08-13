package br.ifnmg.januaria.fernandes.itcp.bean;

import br.ifnmg.januaria.fernandes.itcp.dao.HorarioTrabalhoDAO;
import br.ifnmg.januaria.fernandes.itcp.domain.HorarioTrabalho;
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
public class HorarioTrabalhoBean implements Serializable{
    
    private HorarioTrabalhoDAO dao;
    
    public HorarioTrabalhoBean(){
        dao = new HorarioTrabalhoDAO();
    }
    
    public HorarioTrabalho salvarBean(HorarioTrabalho obj) {
        return dao.salvarHorario(obj);
    }
    
    public List<HorarioTrabalho> listarBean() {
        return dao.listarTodosHorarios();
    }
    
    public HorarioTrabalho buscaHorarioBean(Usuario usr){
        return dao.buscaHorarioDao(usr);
    }
}