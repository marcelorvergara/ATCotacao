
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.infnet.cotacao.web;

import edu.infnet.cotacao.model.Cotacao;
import edu.infnet.cotacao.clientsvc.BuscaProdSvc;
import edu.infnet.cotacao.model.Produto;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Marcelo Vergara <http://marcelo-vergara.codes/>
 */
@Controller
public class CotacaoController {

    private static final Logger log = LoggerFactory.getLogger(CotacaoController.class);

    //cep
    @Autowired
    public BuscaProdSvc buscaProdSvc;

    @GetMapping({"/cotacao", "/cotacao.html"})
    public String cotacao(Model model) {

        Cotacao cotacaoForm = new Cotacao();

        //carregar lista de produtos
        model.addAttribute("cotacaoForm", cotacaoForm);

        //pegar a lista de produtos
        List<Produto> prodLst = buscaProdSvc.buscaProdSvc();

        for (Produto prd : prodLst) {
            System.out.println(prd.getCodigoProd());
        }

        model.addAttribute("prodLst", prodLst);

        return "cotacao";
    }
}
