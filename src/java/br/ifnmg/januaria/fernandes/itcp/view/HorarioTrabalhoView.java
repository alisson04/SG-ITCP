package br.ifnmg.januaria.fernandes.itcp.view;

import br.ifnmg.januaria.fernandes.itcp.bean.HorarioTrabalhoBean;
import br.ifnmg.januaria.fernandes.itcp.bean.UsuarioBean;
import br.ifnmg.januaria.fernandes.itcp.domain.HorarioTrabalho;
import br.ifnmg.januaria.fernandes.itcp.domain.Usuario;
import br.ifnmg.januaria.fernandes.itcp.util.MensagensGenericas;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author alisson
 */
@ManagedBean(name = "HorarioTrabalhoView")
@ViewScoped
public class HorarioTrabalhoView extends MensagensGenericas implements Serializable {

    //VARIAVEIS
    private HorarioTrabalho obj;
    private Usuario usrLogado;
    private HorarioTrabalhoBean bean = new HorarioTrabalhoBean();
    private List<HorarioTrabalho> listaHorarios;
    private List<HorarioTrabalho> listaHorariosFiltrados;

    //CONSTRUTOR
    public HorarioTrabalhoView() {
        listaHorarios = bean.listarBean();

        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = (HttpSession) request.getSession();
        usrLogado = (Usuario) session.getAttribute("USUARIOLogado");

        obj = bean.buscaHorarioBean(usrLogado);
        if (obj == null) {
            obj = new HorarioTrabalho();
            obj.setUsuario(usrLogado);
        }
    }

    //METODOS
    public void salvar() {
        if (verificaHorario()) {
            UsuarioBean u = new UsuarioBean();
            u.buscarPorCodigoBean(usrLogado);
//bean.salvarBean(obj);
        } else {
            msgPanelErro("Verifique os horários", "O horário de 'iníco' deve ser antes do horário de 'fim'!");
        }

    }

    public boolean verificaHorario() {
        boolean aux = true;

        if (obj.getDomIni() != null && obj.getDomFim() != null) {
            if (obj.getDomIni().after(obj.getDomFim())) {
                aux = false;
            }
        }

        if (obj.getSegIni() != null && obj.getSegFim() != null) {
            if (obj.getSegIni().after(obj.getSegFim())) {
                aux = false;
            }
        }

        if (obj.getTerIni() != null && obj.getTerFim() != null) {
            if (obj.getTerIni().after(obj.getTerFim())) {
                aux = false;
            }
        }

        if (obj.getQuaIni() != null && obj.getQuaFim() != null) {
            if (obj.getQuaIni().after(obj.getQuaFim())) {
                aux = false;
            }
        }

        if (obj.getQuiIni() != null && obj.getQuiFim() != null) {
            if (obj.getQuiIni().after(obj.getQuiFim())) {
                aux = false;
            }
        }

        if (obj.getSexIni() != null && obj.getSexFim() != null) {
            if (obj.getSexIni().after(obj.getSexFim())) {
                aux = false;
            }
        }

        if (obj.getSabIni() != null && obj.getSabFim() != null) {
            if (obj.getSabIni().after(obj.getSabFim())) {
                aux = false;
            }
        }

        return aux;
    }

    public String conveteData(Date data) {
        if (data != null) {
            SimpleDateFormat forma = new SimpleDateFormat("HH:mm");
            return forma.format(data);
        } else {
            return "";
        }
    }

    /*public String geraHorario(int aux) {
        String auxString = String.valueOf(aux);
        String horarios = "";
        int pos;

        if (auxString != null) {
            pos = auxString.indexOf("1");
            if (pos != -1) {
                horarios = "Manhã";
            }

            pos = auxString.indexOf("2");
            if (pos != -1) {
                horarios += " Tarde";
            }

            pos = auxString.indexOf("3");
            if (pos != -1) {
                horarios += " Noite";
            }
        }

        return horarios;
    }**/
    //SETS E GETS
    public HorarioTrabalho getObj() {
        return obj;
    }

    public void setObj(HorarioTrabalho obj) {
        this.obj = obj;
    }

    public List<HorarioTrabalho> getListaHorarios() {
        return listaHorarios;
    }

    public void setListaHorarios(List<HorarioTrabalho> listaHorarios) {
        this.listaHorarios = listaHorarios;
    }

    public List<HorarioTrabalho> getListaHorariosFiltrados() {
        return listaHorariosFiltrados;
    }

    public void setListaHorariosFiltrados(List<HorarioTrabalho> listaHorariosFiltrados) {
        this.listaHorariosFiltrados = listaHorariosFiltrados;
    }
}
