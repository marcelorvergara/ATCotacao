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
@FeignClient(url = "http://localhost:8081/", name = "BuscaProdSvc")
public interface BuscaProdSvc {

    @GetMapping("/produtoList")
    List<Produto> buscaProdSvc();

    @GetMapping("/nomeProd/{id}")
    String buscaNomeProd(@PathVariable("id") String id);

}
