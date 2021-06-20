/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.infnet.cotacao.service;

import edu.infnet.cotacao.model.Cotacao;
import edu.infnet.cotacao.repository.CotacaoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Marcelo Vergara <http://marcelo-vergara.codes/>
 */
@Service
public class CotacaoServiceImpl implements CotacaoService {

    @Autowired
    CotacaoRepository cotacaoRepository;

    @Override
    public void save(Cotacao cotacao) {
        cotacaoRepository.save(cotacao);
    }

    @Override
    public List<Cotacao> findByProduto(String produto) {
        List<Cotacao> cotacoes;
        cotacoes = cotacaoRepository.findByProduto(produto);
        return cotacoes;
    }

    @Override
    public Cotacao findByIdCotacao(String id) {
        return cotacaoRepository.findByIdCotacao(Long.parseLong(id));
    }

    @Override
    public void deleteByIdCotacao(Long idCotacao) {
        cotacaoRepository.deleteById(idCotacao);
    }

    @Override
    public Optional<Cotacao> findById(Long id) {
        return cotacaoRepository.findById(id);
    }

}
