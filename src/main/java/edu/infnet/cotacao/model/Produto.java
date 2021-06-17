/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.infnet.cotacao.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author Marcelo Vergara <http://marcelo-vergara.codes/>
 */
public class Produto {

    @JsonIgnoreProperties(ignoreUnknown = true)

    @JsonProperty("codigoProd")
    private String codigoProd;
    @JsonProperty("nome")
    private String nome;
    @JsonProperty("descricao")
    private String descricao;
    @JsonProperty("imagem")
    private String imagem;

    public Produto() {
    }

    /**
     * @return the codigoProd
     */
    public String getCodigoProd() {
        return codigoProd;
    }

    /**
     * @param codigoProd the codigoProd to set
     */
    public void setCodigoProd(String codigoProd) {
        this.codigoProd = codigoProd;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return the imagem
     */
    public String getImagem() {
        return imagem;
    }

    /**
     * @param imagem the imagem to set
     */
    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

}
