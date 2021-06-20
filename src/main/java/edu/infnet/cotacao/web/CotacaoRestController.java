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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    //exportação CSV
    @GetMapping(value = "/getCsv/{prodId}", produces = "text/csv")
    public void getCotacoes(@PathVariable String prodId, HttpServletResponse response) throws IOException {

        //buscando as cotações
        List<Cotacao> cotacaoList = cotacaoService.findByProduto(prodId);

        //buscar o nome do produto pelo seu id
        String nomePrd = buscaProdSvc.buscaNomeProd(prodId);

        //para abrir diretamente um arquivo no browser
        response.setHeader("Content-Type", "application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=cotacoes.csv");

        CsvExport.exportCotacoes(response.getWriter(), cotacaoList);
    }

    //Api para uso do Http DELETE
    @DeleteMapping("/api/delete/{idCotacao}")
    public Map<String, Boolean> deletarCotacao(@PathVariable("idCotacao") String idCotacao, Model model) {

        //declaração para retorno REST
        Map<String, Boolean> response = new HashMap<>();

        //buscar a cotação pelo Id dela
        Cotacao cotacao = cotacaoService.findByIdCotacao(idCotacao);

        //teste para previnir erro HTTP 500
        if (cotacao == null) {
            response.put("Cotação inexistente", Boolean.TRUE);
            return response;
        }

        //excluir cotação
        cotacaoService.deleteByIdCotacao(Long.parseLong(idCotacao));

        response.put("Deletado com sucesso!", Boolean.TRUE);

        return response;
    }

    //Api para uso do Http PUT
    @PutMapping("/api/update/{idCotacao}")
    public Map<String, Boolean> replaceNews(@RequestBody Cotacao newCotacao, @PathVariable String idCotacao) {

        //declaração para retorno REST
        Map<String, Boolean> response = new HashMap<>();

        Cotacao cot = cotacaoService.findByIdCotacao(idCotacao);

        //teste para previnir erro HTTP 500
        if (cot == null) {
            response.put("Cotação inexistente", Boolean.TRUE);
            return response;
        }

        cot.setDataCotacao(newCotacao.getDataCotacao());
        cot.setFornecedor(newCotacao.getFornecedor());
        cot.setValidadeCotacao(newCotacao.getValidadeCotacao());
        cot.setValor(newCotacao.getValor());

        cotacaoService.save(cot);

        response.put("Cotação alterada com sucesso!", Boolean.TRUE);

        return response;

    }
}
