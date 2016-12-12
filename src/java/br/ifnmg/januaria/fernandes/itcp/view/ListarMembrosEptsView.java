package br.ifnmg.januaria.fernandes.itcp.view;

import br.ifnmg.januaria.fernandes.itcp.bean.EmpreendimentoBean;
import br.ifnmg.januaria.fernandes.itcp.bean.MembroEmpreendimentoBean;
import br.ifnmg.januaria.fernandes.itcp.domain.Empreendimento;
import br.ifnmg.januaria.fernandes.itcp.domain.MembroEmpreendimento;
import br.ifnmg.januaria.fernandes.itcp.util.MensagensGenericas;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.FacesException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author alisson
 */
@ManagedBean(name = "ListarMembrosEptsView")
@ViewScoped
public class ListarMembrosEptsView extends MensagensGenericas implements Serializable {

    private MembroEmpreendimentoBean bean;
    private EmpreendimentoBean empreendimentoBean;
    private MembroEmpreendimento membroEmpreendimentoSelecionado;
    private MembroEmpreendimento objSalvar;
    private List<MembroEmpreendimento> listaMembrosEmpreendimentos;
    private List<MembroEmpreendimento> listaMembrosEmpreendimentosFiltrados;
    private List<Empreendimento> listaEmpreendimentos;
    private boolean existeEptBd;

    public ListarMembrosEptsView() {
        try {
            //EES
            empreendimentoBean = new EmpreendimentoBean();
            listaEmpreendimentos = empreendimentoBean.listarBean();

            //Membros
            bean = new MembroEmpreendimentoBean();
            objSalvar = new MembroEmpreendimento();
            listaMembrosEmpreendimentos = bean.listarBean();

            //Verifica se existe Empreendimento no banco de dados
            existeEptBd = true;
            if (empreendimentoBean.contarLinhasBean() == 0) {
                existeEptBd = false;
            }
        } catch (Exception ex) {
            throw new FacesException(ex);
        }
    }

    //METODOS
    public String geraMsgGenericaCampoObrigatorioView() {
        try {
            return msgGenericaCampoObrigatorio();
        } catch (Exception ex) {
            throw new FacesException(ex);
        }
    }

    public void transfereObj() {//Para botão de editar
        try {
            objSalvar = membroEmpreendimentoSelecionado;
        } catch (Exception ex) {
            throw new FacesException(ex);
        }
    }

    public void reiniciaObj() {//Para botão de editar
        try {
            objSalvar = new MembroEmpreendimento();
        } catch (Exception ex) {
            throw new FacesException(ex);
        }
    }

    public void excluirMembroView() {
        try {
            bean.excluirBean(membroEmpreendimentoSelecionado);
            membroEmpreendimentoSelecionado = null;//Volta o usuario para o estado de nulo/ Não retire
            msgGrowDeleteGeneric();
        } catch (Exception ex) {
            throw new FacesException(ex);
        } finally {
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('blockUiGeral').hide()");
        }
    }

    public void salvarView() {
        try {
            bean.salvarBean(objSalvar);
            objSalvar = new MembroEmpreendimento();
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('wVarDlgEditar').hide()");
            context.execute("PF('dlgEdicaoPronta').show()");
        } catch (Exception ex) {
            throw new FacesException(ex);
        } finally {
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('blockUiGeral').hide()");
        }
    }

    public String conveteData(Date data) {
        try {
            if (data != null) {
                SimpleDateFormat forma = new SimpleDateFormat("dd/MM/yyyy");
                return forma.format(data);
            } else {
                return "";
            }
        } catch (Exception ex) {
            throw new FacesException(ex);
        }
    }

    //SETS E GETS
    public MembroEmpreendimento getMembroEmpreendimentoSelecionado() {
        return membroEmpreendimentoSelecionado;
    }

    public void setMembroEmpreendimentoSelecionado(MembroEmpreendimento membroEmpreendimentoSelecionado) {
        this.membroEmpreendimentoSelecionado = membroEmpreendimentoSelecionado;
    }

    public List<MembroEmpreendimento> getListaMembrosEmpreendimentos() {
        return listaMembrosEmpreendimentos;
    }

    public void setListaMembrosEmpreendimentos(List<MembroEmpreendimento> listaMembrosEmpreendimentos) {
        this.listaMembrosEmpreendimentos = listaMembrosEmpreendimentos;
    }

    public List<MembroEmpreendimento> getListaMembrosEmpreendimentosFiltrados() {
        return listaMembrosEmpreendimentosFiltrados;
    }

    public void setListaMembrosEmpreendimentosFiltrados(List<MembroEmpreendimento> listaMembrosEmpreendimentosFiltrados) {
        this.listaMembrosEmpreendimentosFiltrados = listaMembrosEmpreendimentosFiltrados;
    }

    public List<Empreendimento> getListaEmpreendimentos() {
        return listaEmpreendimentos;
    }

    public void setListaEmpreendimentos(List<Empreendimento> listaEmpreendimentos) {
        this.listaEmpreendimentos = listaEmpreendimentos;
    }

    public MembroEmpreendimento getObjSalvar() {
        return objSalvar;
    }

    public void setObjSalvar(MembroEmpreendimento objSalvar) {
        this.objSalvar = objSalvar;
    }

    public boolean isExisteEptBd() {
        return existeEptBd;
    }

    public void setExisteEptBd(boolean existeEptBd) {
        this.existeEptBd = existeEptBd;
    }
}
