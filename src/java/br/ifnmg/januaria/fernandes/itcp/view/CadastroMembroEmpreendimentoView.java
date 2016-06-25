package br.ifnmg.januaria.fernandes.itcp.view;

import br.ifnmg.januaria.fernandes.itcp.bean.EmpreendimentoBean;
import br.ifnmg.januaria.fernandes.itcp.bean.MembroEmpreendimentoBean;
import br.ifnmg.januaria.fernandes.itcp.bean.MensagensBean;
import br.ifnmg.januaria.fernandes.itcp.dao.EmpreendimentoDAO;
import br.ifnmg.januaria.fernandes.itcp.domain.Empreendimento;
import br.ifnmg.januaria.fernandes.itcp.domain.MembroEmpreendimento;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author alisson
 */
@ManagedBean(name = "CadastroMembroEmpreendimentoView")
@ViewScoped
public class CadastroMembroEmpreendimentoView implements Serializable {

    private MembroEmpreendimento membroEptCadastrado = new MembroEmpreendimento();
    private MensagensBean mensagensBean = new MensagensBean();
    private MembroEmpreendimentoBean membroEmpreendimentoBean = new MembroEmpreendimentoBean();
    private boolean membroEptSendoVisualizado;
    private List<Empreendimento> listaEpts;
    private String[] listaNomeEpts;
    EmpreendimentoBean empreendimentoBean = new EmpreendimentoBean();
//variáveis para campos não obrigatórios
    private String apelido;
    private String nomeMae;
    private String endereco;
    private String rg;
    private String nis;
    private String telefone;
    private String telefoneAlternativo;
    private String dataNascimento;
    private String empreedimentoSelecionado;
    
    public CadastroMembroEmpreendimentoView() {
        listaEpts = empreendimentoBean.listarTodosEptsBean();
        listaNomeEpts = new String[listaEpts.size()];
        for (int i = 0; i < listaEpts.size(); i++) {
            listaNomeEpts[i] = listaEpts.get(i).getNomeEpt();
        }
    }

    //Métodos
    public void salvarMembroEpt() {
        //Verificação de campos em branco
        if (apelido.equals("")) {
            membroEptCadastrado.setApelidoMembroEmpreendimento("Não registrado");
        } else {
            membroEptCadastrado.setApelidoMembroEmpreendimento(apelido);
        }
        if (nomeMae.equals("")) {
            membroEptCadastrado.setNomeMaeMembroEmpreendimento("Não registrado");
        } else {
            membroEptCadastrado.setNomeMaeMembroEmpreendimento(nomeMae);
        }
        if (endereco.equals("")) {
            membroEptCadastrado.setEnderecoMembroEmpreendimento("Não registrado");
        } else {
            membroEptCadastrado.setEnderecoMembroEmpreendimento(endereco);
        }
        if (rg.equals("")) {
            membroEptCadastrado.setRgMembroEmpreendimento("Não registrado");
        } else {
            membroEptCadastrado.setRgMembroEmpreendimento(rg);
        }
        if (nis.equals("")) {
            membroEptCadastrado.setNisMembroEmpreendimento("Não registrado");
        } else {
            membroEptCadastrado.setNisMembroEmpreendimento(nis);
        }
        if (telefone.equals("")) {
            membroEptCadastrado.setTelefoneMembroEmpreendimento("Não registrado");
        } else {
            membroEptCadastrado.setTelefoneMembroEmpreendimento(telefone);
        }
        if (telefoneAlternativo.equals("")) {
            membroEptCadastrado.setTelefoneAlternativoMembroEmpreendimento("Não registrado");
        } else {
            membroEptCadastrado.setTelefoneAlternativoMembroEmpreendimento(telefoneAlternativo);
        }
        if (dataNascimento.equals("")) {
            membroEptCadastrado.setDataNascimentoMembroEmpreendimento("Não registrado");
        } else {
            membroEptCadastrado.setDataNascimentoMembroEmpreendimento(dataNascimento);
        }

        for (int i = 0; i < listaEpts.size(); i++) {
            if (listaEpts.get(i).getNomeEpt().equals(empreedimentoSelecionado)) {
                membroEptCadastrado.setEmpreendimento(listaEpts.get(i));
            }
            // else{
            //    mensagensBean.messagemCaixa("ERROR", "Erro no E-mail", "Este E-mail já esta cadastrado no sistema");
            //}
        }

        membroEmpreendimentoBean.salvarMembroEpt(membroEptCadastrado);
        membroEptSendoVisualizado = true;
    }

    //SETS e GETS
    public void setEmpreedimentoSelecionado(String empreedimentoSelecionado) {
        this.empreedimentoSelecionado = empreedimentoSelecionado;
    }

    public String getEmpreedimentoSelecionado() {
        return empreedimentoSelecionado;
    }

    public String[] getListaNomeEpts() {
        return listaNomeEpts;
    }

    public void setListaNomeEpts(String[] listaNomeEpts) {
        this.listaNomeEpts = listaNomeEpts;
    }

    public List<Empreendimento> getListaEpts() {
        return listaEpts;
    }

    public void setListaEpts(List<Empreendimento> listaEpts) {
        this.listaEpts = listaEpts;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getNomeMae() {
        return nomeMae;
    }

    public void setNomeMae(String nomeMae) {
        this.nomeMae = nomeMae;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getNis() {
        return nis;
    }

    public void setNis(String nis) {
        this.nis = nis;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public MembroEmpreendimento getMembroEptCadastrado() {
        return membroEptCadastrado;
    }

    public void setMembroEptCadastrado(MembroEmpreendimento membroEptCadastrado) {
        this.membroEptCadastrado = membroEptCadastrado;
    }

    public boolean isMembroEptSendoVisualizado() {
        return membroEptSendoVisualizado;
    }

    public void setMembroEptSendoVisualizado(boolean membroEptSendoVisualizado) {
        this.membroEptSendoVisualizado = membroEptSendoVisualizado;
    }

    public String getTelefoneAlternativo() {
        return telefoneAlternativo;
    }

    public void setTelefoneAlternativo(String telefoneAlternativo) {
        this.telefoneAlternativo = telefoneAlternativo;
    }

}
