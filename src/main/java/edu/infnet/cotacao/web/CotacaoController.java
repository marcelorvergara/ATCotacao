
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
import org.springframework.web.bind.annotation.PathVariable;
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
    @GetMapping("/listaCotacoes/{produto}")
    public String listaCotacoes(@PathVariable("produto") String produto, Model model) {

        //buscando as cotações
        List<Cotacao> cotacaoList = cotacaoService.findByProduto(produto);

        if (cotacaoList.isEmpty()) {
            return "redirect:/buscaCotacao";
        }

        //buscar o nome do produto pelo seu id
        String nomePrd = buscaProdSvc.buscaNomeProd(produto);

        //carregar a lista de cotações do produto
        model.addAttribute("cotacaoList", cotacaoList);
        model.addAttribute("produto", nomePrd);

        return "cotacao_por_prd_result";
    }

    @GetMapping("/editar/{id}")
    public String alterarCotacao(@PathVariable("id") String id, Model model) {

        //buscar os dados da cotação para alterar
        Cotacao cotacao = cotacaoService.findByIdCotacao(id);

        //buscar o nome do produto pelo seu id
        String nomePrd = buscaProdSvc.buscaNomeProd(cotacao.getProduto());

        model.addAttribute("cotacaoForm", cotacao);
        model.addAttribute("nomeProduto", nomePrd);

        return "alterar_cotacao";
    }

    @PostMapping("/gravaAlteracao")
    public String gravarAlteracao(@RequestParam("idCotacao") String idCotacao,
            @RequestParam("produto") String produto,
            @RequestParam("fornecedor") String fornecedor,
            @RequestParam("dataCotacao") String dataCotacao,
            @RequestParam("validadeCotacao") String validadeCotacao,
            @RequestParam("valor") String valor) {

        int dia = Integer.parseInt(dataCotacao.split("/")[2]);
        int mes = Integer.parseInt(dataCotacao.split("/")[1]);
        int ano = Integer.parseInt(dataCotacao.split("/")[0]);

        LocalDate validCot = LocalDate.of(dia, mes, ano);

        Cotacao cotacao = new Cotacao(Long.parseLong(idCotacao), produto, fornecedor, validCot, Integer.parseInt(validadeCotacao), new BigDecimal(valor));

        Cotacao newCotacao = cotacaoService.findByIdCotacao(idCotacao);

        if (newCotacao != null) {
            newCotacao.setProduto(cotacao.getProduto());
            newCotacao.setFornecedor(cotacao.getFornecedor());
            newCotacao.setDataCotacao(cotacao.getDataCotacao());
            newCotacao.setValidadeCotacao(cotacao.getValidadeCotacao());
            newCotacao.setValor(cotacao.getValor());

            cotacaoService.save(newCotacao);
        } else {
            newCotacao.setIdCotacao(Long.parseLong(idCotacao));
            newCotacao.setProduto(cotacao.getProduto());
            newCotacao.setFornecedor(cotacao.getFornecedor());
            newCotacao.setDataCotacao(cotacao.getDataCotacao());
            newCotacao.setValidadeCotacao(cotacao.getValidadeCotacao());
            newCotacao.setValor(cotacao.getValor());

            cotacaoService.save(newCotacao);
        }

        return "redirect:/listaCotacoes/" + produto;
    }

    @GetMapping("/deletar/{idCotacao}")
    public String deletarCotacao(@PathVariable("idCotacao") String idCotacao, Model model) {

        System.out.println("id cotação: " + idCotacao);

        //buscar a cotação pelo Id dela
        Cotacao cotacao = cotacaoService.findByIdCotacao(idCotacao);

        //excluir cotação
        cotacaoService.deleteByIdCotacao(Long.parseLong(idCotacao));

        return "redirect:/listaCotacoes/" + cotacao.getProduto();
    }
}
