package br.ifnmg.januaria.fernandes.itcp.bean;

import br.ifnmg.januaria.fernandes.itcp.dao.ProdutoDAO;
import br.ifnmg.januaria.fernandes.itcp.domain.Produto;
import br.ifnmg.januaria.fernandes.itcp.util.UploadArquivo;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.List;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author Mara
 */
@ManagedBean(name = "produtoMB")
@SessionScoped
public class produtoMB {

    private Produto produto;
    private List<Produto> listarProdutos;
    private UploadArquivo arquivo = new UploadArquivo();

    public produtoMB() {
        this.produto = new Produto();
        this.listarProdutos = new ArrayList<Produto>();
    }

    public List<Produto> getListarProdutos() {
        return new ProdutoDAO().listar();
        //return this.listarProdutos;
    }

    public void setListarProdutos(List<Produto> listarProdutos) {
        this.listarProdutos = listarProdutos;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public void uploadAction(FileUploadEvent event) {
        this.arquivo.fileUpload(event.getFile().getContents(), new java.util.Date().getTime() + ".jpg", "/image/");
        this.produto.setFoto(this.arquivo.getNome());
    }

    public void salvar() {
        new ProdutoDAO().salvar(produto);
        this.arquivo.gravar();

        this.produto = new Produto();
        this.arquivo = new UploadArquivo();
    }
}
