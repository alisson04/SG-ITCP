package br.ifnmg.januaria.fernandes.itcp.dao;

import br.ifnmg.januaria.fernandes.itcp.domain.HorarioTrabalho;
import br.ifnmg.januaria.fernandes.itcp.domain.Usuario;
import java.util.List;

/**
 *
 * @author alisson
 */
public class HorarioTrabalhoDAO extends DaoGenerico<HorarioTrabalho> {
    EntityManagerCriador emc;
    
    public HorarioTrabalhoDAO() {
    }
    
    public void salvarHorario(HorarioTrabalho obj){
        salvarGenerico(obj);
    }
    
    public List<HorarioTrabalho> listarTodosHorarios() {
        return listarObjsGenerico("HorarioTrabalho");
    }
    
    public HorarioTrabalho buscaHorarioDao(Usuario usr){
        List<HorarioTrabalho> listaHorarios;
        HorarioTrabalho horarioAux;
        
        listaHorarios = listarObjsFiltradosIntGenerico("HorarioTrabalho", "usuario.idUsuario", usr.getIdUsuario());
        if (listaHorarios.size() > 0) {
            horarioAux = listaHorarios.get(0);
            System.out.println("NUM: " + listaHorarios.size());
        } else {
            horarioAux = null;
            System.out.println("NUM: ");
        }
        
        return horarioAux;
    }
}
