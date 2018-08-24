package br.prgomesr.debitoapi.util.retorno.bb;

import br.prgomesr.debitoapi.model.Convenio;
import br.prgomesr.debitoapi.model.Lancamento;
import org.apache.commons.io.FileUtils;
import org.jrimum.texgit.FlatFile;
import org.jrimum.texgit.Record;
import org.jrimum.texgit.Texgit;
import org.jrimum.utilix.ClassLoaders;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ArquivoRetorno {

    private Cabecalho cabecalho;
    private Sumario sumario;
    private List<Transacao> transacoes;

    private FlatFile<Record> arquivoTexto;

    public ArquivoRetorno(File arquivo, Convenio convenio) {
        carregarLayout(convenio);
        carregarLinhas(arquivo);
        carregarInformacoes();
    }

    private void carregarInformacoes() {
        this.cabecalho = new Cabecalho(this.arquivoTexto.getRecord("Header"));
        this.sumario = new Sumario(this.arquivoTexto.getRecord("Trailler"));
        Collection<Record> registroTransacoes = this.arquivoTexto.getRecords("Detalhe");

        this.transacoes = new ArrayList<>(registroTransacoes.size());
        for (Record record : registroTransacoes) {
            transacoes.add(new Transacao(record));
        }
    }

    private void carregarLinhas(File arquivo) {
        List<String> linhas;
        try {
            linhas = FileUtils.readLines(arquivo);
        } catch (IOException e) {
            throw new RuntimeException("Erro lendo linhas do arquivo de retorno");
        }
        this.arquivoTexto.read(linhas);
    }

    private void carregarLayout(Convenio convenio) {
        String layout = "";

        switch (convenio.getId().toString()) {
            case "1":
                layout = "layouts/LayoutBBCNAB240Retorno.txg.xml";
                break;
        }

        InputStream inputStream = ClassLoaders.getResourceAsStream(layout, this.getClass());
        this.arquivoTexto = Texgit.createFlatFile(inputStream);
    }

    public Cabecalho getCabecalho() {
        return cabecalho;
    }

    public Sumario getSumario() {
        return sumario;
    }

    public List<Transacao> getTransacoes() {
        return transacoes;
    }

}
