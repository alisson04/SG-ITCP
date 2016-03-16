/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifnmg.januaria.fernandes.itcp.view;

/**
 *
 * @author alisson
 */
import br.ifnmg.januaria.fernandes.itcp.domain.Usuario;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
 
@ManagedBean(name="ListarUsuariosView")
@ViewScoped
public class ListarUsuariosView implements Serializable {
    private Usuario usuarioSelecionado;

    public ListarUsuariosView() {
    }

    public Usuario getUsuarioSelecionado() {
        System.out.println("____GET");
        return usuarioSelecionado;
    }

    public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
        System.out.println("____SET");
        this.usuarioSelecionado = usuarioSelecionado;
    }
}
