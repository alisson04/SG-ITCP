package br.ifnmg.januaria.fernandes.itcp.domain;

import java.io.Serializable;

/**
 *
 * @author alisson
 */
public class Indicador implements Serializable, EntityConverter {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String nome;
    private String categoria;
    private String eixo;
    private int notaMax;

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
    
    @Override
    public Integer getIdConverter(){
        return id;
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

    public int getNotaMax() {
        return notaMax;
    }

    public void setNotaMax(int notaMax) {
        this.notaMax = notaMax;
    }
}
