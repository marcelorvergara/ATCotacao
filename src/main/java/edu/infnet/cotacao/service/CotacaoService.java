/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.infnet.cotacao.service;

import edu.infnet.cotacao.model.Cotacao;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author Marcelo Vergara <http://marcelo-vergara.codes/>
 */
@Service
public interface CotacaoService {

    public void save(Cotacao cotacao);

    public List<Cotacao> findByProduto(String produto);

    public Cotacao findByIdCotacao(String id);

    public Cotacao findById(Long idCotacao);

    public void deleteByIdCotacao(Long idCotacao);

}
