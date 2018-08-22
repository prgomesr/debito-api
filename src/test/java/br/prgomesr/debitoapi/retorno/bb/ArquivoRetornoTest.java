package br.prgomesr.debitoapi.retorno.bb;

import br.prgomesr.debitoapi.util.retorno.bb.ArquivoRetorno;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.math.BigDecimal;

public class ArquivoRetornoTest {

    @Test
    public void deve_retornar_valor_total_registros() {
        File file = new File("src/test/DBT92726313082018183713.ret");
        if (file.exists()) {
            ArquivoRetorno arquivoRetorno = new ArquivoRetorno(file);

            BigDecimal valor = arquivoRetorno.getSumario().getValorTotalRegistros();

            Assert.assertEquals(new BigDecimal("13372.79"), valor);
        } else {
            System.out.println("nao leu o arquivo");
        }
    }
}
