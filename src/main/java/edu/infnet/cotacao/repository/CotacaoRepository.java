/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.infnet.cotacao.repository;

import edu.infnet.cotacao.model.Cotacao;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Marcelo Vergara <http://marcelo-vergara.codes/>
 */
public interface CotacaoRepository extends CrudRepository<Cotacao, Long> {

}
