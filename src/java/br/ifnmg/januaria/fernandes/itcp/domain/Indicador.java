package br.ifnmg.januaria.fernandes.itcp.domain;

import java.io.Serializable;

/**
 *
 * @author alisson
 */
public class Indicador implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String nome;
    private String categoria;
    private String eixo;

    public Indicador() {
    }

    public Indicador(Integer id) {
        this.id = id;
    }

    public Indicador(Integer id, String nome, String categoria, String eixo) {
        this.id = id;
        this.nome = nome;
        this.categoria = categoria;
        this.eixo = eixo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getEixo() {
        return eixo;
    }

    public void setEixo(String eixo) {
        this.eixo = eixo;
    }
}
