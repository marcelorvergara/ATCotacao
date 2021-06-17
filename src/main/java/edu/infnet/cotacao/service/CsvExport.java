/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.infnet.cotacao.service;

import com.opencsv.CSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvException;
import edu.infnet.cotacao.model.Cotacao;
import java.io.PrintWriter;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Marcelo Vergara <http://marcelo-vergara.codes/>
 */
public class CsvExport {

    private static final Logger LOGGER = LoggerFactory.getLogger(CsvExport.class);

    public static void exportCotacoes(PrintWriter writer, List<Cotacao> cotacoes) {

        try {
            ColumnPositionMappingStrategy<Cotacao> mapStrategy = new ColumnPositionMappingStrategy<>();

            mapStrategy.setType(Cotacao.class);

            String[] columns = new String[]{"idCotacao", "fornecedor", "dataCotacao", "validadeCotacao", "valor"};

            mapStrategy.setColumnMapping(columns);

            StatefulBeanToCsv<Cotacao> btcsv = new StatefulBeanToCsvBuilder<Cotacao>(writer)
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                    .withMappingStrategy(mapStrategy)
                    .withSeparator(',')
                    .build();

            btcsv.write(cotacoes);

        } catch (CsvException ex) {
            LOGGER.error("Error mapping Bean to CSV", ex);
        }
    }
}
