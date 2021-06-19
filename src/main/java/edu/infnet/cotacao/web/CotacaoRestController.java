/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.infnet.cotacao.web;

import edu.infnet.cotacao.clientsvc.BuscaProdSvc;
import edu.infnet.cotacao.model.Cotacao;
import edu.infnet.cotacao.service.CotacaoService;
import edu.infnet.cotacao.service.CsvExport;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Marcelo Vergara <http://marcelo-vergara.codes/>
 */
@RestController
public class CotacaoRestController {

    //busca produtos pela api
    @Autowired
    public BuscaProdSvc buscaProdSvc;

    //interface db
    @Autowired
    public CotacaoService cotacaoService;

    @GetMapping(value = "/getCsv/{prodId}", produces = "text/csv")
    public void getCotacoes(@PathVariable String prodId, HttpServletResponse response) throws IOException {

        //buscando as cotações
        List<Cotacao> cotacaoList = cotacaoService.findByProduto(prodId);

        //buscar o nome do produto pelo seu id
        String nomePrd = buscaProdSvc.buscaNomeProd(prodId);

        CsvExport.exportCotacoes(response.getWriter(), cotacaoList);
    }

    @DeleteMapping("/api/delete/{idCotacao}")
    public Map<String, Boolean> deletarCotacao(@PathVariable("idCotacao") String idCotacao, Model model) {

        System.out.println("id cotação: " + idCotacao);

        //buscar a cotação pelo Id dela
        Cotacao cotacao = cotacaoService.findById(Long.parseLong(idCotacao));

        //excluir cotação
        cotacaoService.deleteByIdCotacao(Long.parseLong(idCotacao));

        Map<String, Boolean> response = new HashMap<>();

        response.put("Deletado com sucesso!", Boolean.TRUE);

        return response;
    }

}
