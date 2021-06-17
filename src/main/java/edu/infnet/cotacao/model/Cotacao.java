/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.infnet.cotacao.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Marcelo Vergara <http://marcelo-vergara.codes/>
 */
@Entity
@Table(name = "cotacao")
public class Cotacao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long idCotacao;
    private String produto;
    private String fornecedor;
    private LocalDate dataCotacao;
    private int validadeCotacao;
    private BigDecimal valor;

    public Cotacao() {
    }

    /**
     * @return the idCotacao
     */
    public Long getIdCotacao() {
        return idCotacao;
    }

    /**
     * @param idCotacao the idCotacao to set
     */
    public void setIdCotacao(Long idCotacao) {
        this.idCotacao = idCotacao;
    }

    /**
     * @return the produto
     */
    public String getProduto() {
        return produto;
    }

    /**
     * @param produto the produto to set
     */
    public void setProduto(String produto) {
        this.produto = produto;
    }

    /**
     * @return the fornecedor
     */
    public String getFornecedor() {
        return fornecedor;
    }

    /**
     * @param fornecedor the fornecedor to set
     */
    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    /**
     * @return the dataCotacao
     */
    public LocalDate getDataCotacao() {
        return dataCotacao;
    }

    /**
     * @param dataCotacao the dataCotacao to set
     */
    public void setDataCotacao(LocalDate dataCotacao) {
        this.dataCotacao = dataCotacao;
    }

    /**
     * @return the validadeCotacao
     */
    public int getValidadeCotacao() {
        return validadeCotacao;
    }

    /**
     * @param validadeCotacao the validadeCotacao to set
     */
    public void setValidadeCotacao(int validadeCotacao) {
        this.validadeCotacao = validadeCotacao;
    }

    /**
     * @return the valor
     */
    public BigDecimal getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

}
