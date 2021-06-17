
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.infnet.cotacao.web;

import edu.infnet.cotacao.model.Cotacao;
import edu.infnet.cotacao.clientsvc.BuscaProdSvc;
import edu.infnet.cotacao.model.Produto;
import edu.infnet.cotacao.service.CotacaoService;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Marcelo Vergara <http://marcelo-vergara.codes/>
 */
@Controller
public class CotacaoController {

    private static final Logger log = LoggerFactory.getLogger(CotacaoController.class);

    //busca produtos pela api
    @Autowired
    public BuscaProdSvc buscaProdSvc;

    //interface db
    @Autowired
    public CotacaoService cotacaoService;

    @GetMapping({"/cotacao", "/cotacao.html"})
    public String cotacao(Model model) {

        Cotacao cotacaoForm = new Cotacao();

        //carregar lista de produtos
        model.addAttribute("cotacaoForm", cotacaoForm);

        //pegar a lista de produtos
        List<Produto> prodLst = buscaProdSvc.buscaProdSvc();

        model.addAttribute("prodLst", prodLst);

        return "cotacao";
    }

    @PostMapping("/cadCotacao")
    public String gravaCot(@RequestParam("produto") String produto,
            @RequestParam("fornecedor") String fornecedor,
            @RequestParam("dataCotacao") String dataCotacao,
            @RequestParam("validadeCotacao") String validadeCotacao,
            @RequestParam("valor") String valor) {

        DateTimeFormatter DATEFORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate lDate = LocalDate.parse(dataCotacao, DATEFORMATTER);

        System.out.println("produto: " + produto);
        System.out.println("valor: " + valor);
        System.out.println("validade cotação: " + validadeCotacao);
        System.out.println("data cotação: " + dataCotacao);

        Cotacao cotacao = new Cotacao(produto, fornecedor, lDate, Integer.parseInt(validadeCotacao), new BigDecimal(valor.toString()));

        cotacaoService.save(cotacao);

        return "redirect:/cotacao";
    }

    //página que retornará a lista de produtos para posteriormente buscar as cotações
    @GetMapping("/buscaCotacao")
    public String buscaCotacao(Model model) {

        Cotacao cotacaoForm = new Cotacao();

        //carregar lista de produtos
        model.addAttribute("cotacaoForm", cotacaoForm);

        //pegar a lista de produtos
        List<Produto> prodLst = buscaProdSvc.buscaProdSvc();

        model.addAttribute("prodLst", prodLst);

        return "cotacao_por_prd";
    }

    //listar as cotações de acordo com o id do produto
    @PostMapping("/listaCotacoes")
    public String listaCotacoes(@RequestParam("produto") String produto, Model model) {

        //buscando as cotações
        List<Cotacao> cotacaoList = cotacaoService.findByProduto(produto);

        //buscar o nome do produto pelo seu id
        String nomePrd = buscaProdSvc.buscaNomeProd(produto);

        //carregar a lista de cotações do produto
        model.addAttribute("cotacaoList", cotacaoList);
        model.addAttribute("produto", nomePrd);

        return "cotacao_por_prd_result";
    }
}
