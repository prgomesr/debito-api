package br.prgomesr.debitoapi.util.remessa.bb;

import br.prgomesr.debitoapi.model.Convenio;
import br.prgomesr.debitoapi.model.Empresa;
import br.prgomesr.debitoapi.model.Lancamento;
import br.prgomesr.debitoapi.model.Remessa;
import br.prgomesr.debitoapi.service.RemessaService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.jrimum.texgit.FlatFile;
import org.jrimum.texgit.Record;
import org.jrimum.texgit.Texgit;
import org.jrimum.utilix.ClassLoaders;
import org.jrimum.utilix.text.DateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class GerarRemessaImpl implements GerarRemessa {

    private BigDecimal valorTotal = new BigDecimal(0);
    private int count = 2;

    @Autowired
    private RemessaService remessaService;

    @Override
    public void exportarRemessa(List<Lancamento> lancamentos, Convenio convenio, Empresa empresa) throws IOException {
        if (lancamentos.size() > 0) {
            File layout = new File(ClassLoaders.
                    getResource("layouts/LayoutBBCNAB240Envio.txg.xml").getFile());


            if (layout.exists()) {
                FlatFile<Record> ff = Texgit.createFlatFile(layout);

                ff.addRecord(createHeader(ff, convenio, empresa));

                List<Record> records = createDetalhe(ff, lancamentos);
                records.forEach(record -> {
                    ff.addRecord(record);
                });

                ff.addRecord(createTrailler(ff));

                FileUtils.writeLines(new File("src/main/resources/remessa/" +
                        convenio.getNumero() + "_" + convenio.getSequencial() + ".TXT"), ff.write(), "\r\n");

                salvarRemessa(convenio);
            }
        }
    }

    @Override
    public Record createHeader(FlatFile<Record> flatFile, Convenio convenio, Empresa empresa) {
        Record header = flatFile.createRecord("Header");

        header.setValue("CodigoDoConvenio", convenio.getNumero());
        header.setValue("NomeDaEmpresa", StringUtils.abbreviate(empresa.getNome(), 20));
        header.setValue("DataDeGerecao", new Date());
        header.setValue("NumeroSequencial", convenio.getSequencial());

        return header;
    }

    @Override
    public List<Record> createDetalhe(FlatFile<Record> flatFile, List<Lancamento> lancamentos) {
        List<Record> records = new ArrayList<>();

        valorTotal = new BigDecimal(0);
        count = 2;

        lancamentos.forEach(lancamento -> {
            Record detalhe = flatFile.createRecord("Detalhe");

            detalhe.setValue("IdClienteEmpresa", StringUtils.abbreviate(lancamento.getCliente().getIdentificadorBanco(), 13));
            detalhe.setValue("AgenciaParaDebito", lancamento.getCliente().getAgencia());
            detalhe.setValue("IdClienteBanco", lancamento.getCliente().getConta());
            detalhe.setValue("DataVencimento", DateFormat.YYYYMMDD.format(java.sql.Date.valueOf(lancamento.getVencimento())));
            detalhe.setValue("ValorDoDebito", lancamento.getValor());
            detalhe.setValue("CodLancamento", StringUtils.abbreviate(lancamento.getId().toString(), 11));
            detalhe.setValue("UsoEmpresa", StringUtils.abbreviate(lancamento.getCliente().getNome(), 49));

            records.add(detalhe);

            valorTotal = valorTotal.add(lancamento.getValor());
            count++;

        });

        lancamentos.clear();

        return records;
    }

    @Override
    public Record createTrailler(FlatFile<Record> flatFile) {
        Record trailler = flatFile.createRecord("Trailler");

        trailler.setValue("TotalRegistros", count);
        trailler.setValue("ValorTotalRegistros", valorTotal);

        return trailler;
    }

    private void salvarRemessa(Convenio convenio) {
        Remessa remessa = new Remessa();
        remessa.setNome(convenio.getNumero() + "_" + convenio.getSequencial());
        remessa.setValor(valorTotal);
        remessa.setSituacao("NÃO BAIXADA");
        remessa.setData(LocalDate.now());
        remessaService.cadastrar(remessa);
    }
}
