/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.infnet.cotacao.clientsvc;

import edu.infnet.cotacao.model.Produto;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author Marcelo Vergara <http://marcelo-vergara.codes/>
 */
//para buscar os produtos em no microsserviço de produtos
@FeignClient(url = "http://localhost:8081/", name = "BuscaProdSvc")
public interface BuscaProdSvc {

    //lista todos os produtos cadastrados
    @GetMapping("/produtoList")
    List<Produto> buscaProdSvc();

    //busca o nome de um produto específico
    @GetMapping("/nomeProd/{id}")
    String buscaNomeProd(@PathVariable("id") String id);

}
