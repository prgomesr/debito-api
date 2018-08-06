package br.prgomesr.debitoapi.util.remessa.bb;

import br.prgomesr.debitoapi.model.Cliente;
import br.prgomesr.debitoapi.model.Convenio;
import br.prgomesr.debitoapi.model.Empresa;
import org.apache.commons.io.FileUtils;
import org.jrimum.texgit.FlatFile;
import org.jrimum.texgit.Record;
import org.jrimum.texgit.Texgit;
import org.jrimum.utilix.ClassLoaders;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class GerarRemessaInclusaoImpl implements GerarRemessaInclusao {

    private BigDecimal valorTotal = new BigDecimal(0);
    private int count = 2;

    @Override
    public void exportarRemessa(List<Cliente> clientes, Convenio convenio, Empresa empresa) throws IOException {
        File layout = new File(ClassLoaders.
                getResource("layouts/LayoutBBRegistroBEnvio.txg.xml").getFile());

        FlatFile<Record> ff = Texgit.createFlatFile(layout);

        ff.addRecord(createHeader(ff, convenio, empresa));

        List<Record> records = createDetalhe(ff, clientes);
        records.forEach(record -> {
            ff.addRecord(record);
        });

        ff.addRecord(createTrailler(ff));

        FileUtils.writeLines(new File("src/main/resources/remessa/" +
                convenio.getNumero() + "_" + convenio.getSequencial() + ".TXT"), ff.write(), "\r\n");
    }

    @Override
    public Record createHeader(FlatFile<Record> flatFile, Convenio convenio, Empresa empresa) {
        Record header = flatFile.createRecord("Header");

        header.setValue("CodigoDoConvenio", convenio.getNumero());
        header.setValue("NomeDaEmpresa", empresa.getNome());
        header.setValue("DataDeGerecao", new Date());
        header.setValue("NumeroSequencial", convenio.getSequencial());

        return header;
    }

    @Override
    public List<Record> createDetalhe(FlatFile<Record> flatFile, List<Cliente> clientes) {
        List<Record> records = new ArrayList<>();

        valorTotal = new BigDecimal(0);
        count = 2;

        clientes.forEach(cliente -> {
            Record detalhe = flatFile.createRecord("Detalhe");

            detalhe.setValue("IdClienteEmpresa", cliente.getIdentificadorBanco());
            detalhe.setValue("AgenciaParaDebito", cliente.getAgencia());
            detalhe.setValue("IdClienteBanco", cliente.getConta());
            detalhe.setValue("DataDaOpcao", new Date());

            records.add(detalhe);

        });

        for (int i = 0; i < clientes.size(); i++) {
            count ++;
        }

        clientes.clear();

        return records;
    }

    @Override
    public Record createTrailler(FlatFile<Record> flatFile) {
        Record trailler = flatFile.createRecord("Trailler");

        trailler.setValue("TotalRegistros", count);
        trailler.setValue("ValorTotalRegistros", valorTotal);

        return trailler;
    }
}
