/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.infnet.cotacao.repository;

import edu.infnet.cotacao.model.Cotacao;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Marcelo Vergara <http://marcelo-vergara.codes/>
 */
@Repository("cotacaoService")
public interface CotacaoRepository extends CrudRepository<Cotacao, Long> {

    public List<Cotacao> findByProduto(String produto);

    public Cotacao findByIdCotacao(long parseLong);

}
