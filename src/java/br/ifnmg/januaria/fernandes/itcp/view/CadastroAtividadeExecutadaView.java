package br.ifnmg.januaria.fernandes.itcp.view;

import br.ifnmg.januaria.fernandes.itcp.bean.AtividadeExecutadaBean;
import br.ifnmg.januaria.fernandes.itcp.bean.EmpreendimentoBean;
import br.ifnmg.januaria.fernandes.itcp.bean.MensagensBean;
import br.ifnmg.januaria.fernandes.itcp.bean.ParceiroBean;
import br.ifnmg.januaria.fernandes.itcp.bean.UsuarioBean;
import br.ifnmg.januaria.fernandes.itcp.dao.AtividadeExecutadaDAO;
import br.ifnmg.januaria.fernandes.itcp.dao.EmpreendimentoDAO;
import br.ifnmg.januaria.fernandes.itcp.dao.ParceiroDAO;
import br.ifnmg.januaria.fernandes.itcp.dao.UsuarioDAO;
import br.ifnmg.januaria.fernandes.itcp.domain.AtividadeExecutada;
import br.ifnmg.januaria.fernandes.itcp.domain.AtividadeParceiro;
import br.ifnmg.januaria.fernandes.itcp.domain.Empreendimento;
import br.ifnmg.januaria.fernandes.itcp.domain.Parceiro;
import br.ifnmg.januaria.fernandes.itcp.domain.Usuario;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FlowEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TransferEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.DualListModel;

/**
 *
 * @author alisson
 */
@ManagedBean(name = "CadastroAtividadeExecutadaView")
@ViewScoped
public class CadastroAtividadeExecutadaView implements Serializable {

    private AtividadeExecutada atividadeCadastrar = new AtividadeExecutada();
    private AtividadeExecutadaBean bean = new AtividadeExecutadaBean();

    private DualListModel<Parceiro> listaParceiros;
    private ParceiroBean parceiroBean = new ParceiroBean();
    
    private DualListModel<Usuario> listaUsuarios;
    private UsuarioBean usrBean = new UsuarioBean();

    private boolean objetoFoiCadastrado;

    public CadastroAtividadeExecutadaView() {
        List<Parceiro> themesSource2 = parceiroBean.listarBean();
        List<Parceiro> themesTarget2 = new ArrayList<Parceiro>();
        listaParceiros = new DualListModel<Parceiro>(themesSource2, themesTarget2);
        
        List<Usuario> themesSource = usrBean.listarBean();
        List<Usuario> themesTarget = new ArrayList<Usuario>();
        listaUsuarios = new DualListModel<Usuario>(themesSource, themesTarget);
    }

    //Métodos
    /*public void teste(AtividadeExecutada obj) {
        System.out.println("Parceiro SIZE: " + obj.getAtividadeParceiroList().size());
        System.out.println("Usuario SIZE: " + obj.getAtividadeUsuarioList().size());
        
        ParceiroDAO da = new ParceiroDAO();
        List<AtividadeParceiro> themesTarget = new ArrayList<>();
        List<Parceiro> par = new ArrayList<>();
        
        par = da.listarTodosParceiros();
        for (Parceiro par1 : par) {
            
        }
    }**/
    
    public void onTransfer2(TransferEvent event) {
        StringBuilder builder = new StringBuilder();
        for(Object item : event.getItems()) {
            builder.append(((Parceiro) item).getNomeParceiro()).append("<br />");
        }
         
        FacesMessage msg = new FacesMessage();
        msg.setSeverity(FacesMessage.SEVERITY_INFO);
        msg.setSummary("Sucesso!");
        msg.setDetail("Tranasferência realizada");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void onTransfer(TransferEvent event) {
        StringBuilder builder = new StringBuilder();
        for(Object item : event.getItems()) {
            builder.append(((Usuario) item).getNomeUsuario()).append("<br />");
        }
        FacesMessage msg = new FacesMessage();
        msg.setSeverity(FacesMessage.SEVERITY_INFO);
        msg.setSummary("Sucesso!");
        msg.setDetail("Tranasferência realizada");
         
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void salvarView() {
        try {
            //atividadeCadastrar.setAtividadeParceiroList(themesTarget2);
            bean.salvarBean(atividadeCadastrar);
            objetoFoiCadastrado = true;
        } catch (RuntimeException ex) {
            System.out.println("RuntimeException: " + ex);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Erro inesperado", "Erro ao tentar savar a atividade, contate o administrador do sistema!");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }
    }
    
    public String conveteData(Date data) {
        if (data != null) {
            SimpleDateFormat forma = new SimpleDateFormat("dd/MM/yyyy");
            return forma.format(data);
        } else {
            return "";
        }
    }

    //SETS E GETS
    public AtividadeExecutada getAtividadeCadastrar() {
        return atividadeCadastrar;
    }

    public void setAtividadeCadastrar(AtividadeExecutada atividadeCadastrar) {
        this.atividadeCadastrar = atividadeCadastrar;
    }

    public boolean isObjetoFoiCadastrado() {
        return objetoFoiCadastrado;
    }

    public void setObjetoFoiCadastrado(boolean objetoFoiCadastrado) {
        this.objetoFoiCadastrado = objetoFoiCadastrado;
    }

    public DualListModel<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(DualListModel<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public DualListModel<Parceiro> getListaParceiros() {
        return listaParceiros;
    }

    public void setListaParceiros(DualListModel<Parceiro> listaParceiros) {
        this.listaParceiros = listaParceiros;
    }
}
