package br.prgomesr.debitoapi.util.remessa.bb;

import org.jrimum.texgit.FlatFile;
import org.jrimum.texgit.Record;
import org.jrimum.texgit.Texgit;
import org.jrimum.utilix.ClassLoaders;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ArquivoRemessa {

    private Cabecalho cabecalho;
    private List<Transacao> transacoes;
    private Sumario sumario;

    private final String nomeArquivo = "LayoutBBCNAB240Envio.txg.xml";
    private FlatFile<Record> arquivoTexto;

    public ArquivoRemessa() {
        carregarLayout();
        carregarInformacoes();
    }

    private void carregarInformacoes() {
        this.cabecalho = new Cabecalho(this.arquivoTexto.getRecord("Header"));
        this.sumario = new Sumario(this.arquivoTexto.getRecord("Trailler"));
        Collection<Record> registroTransaceos =  this.arquivoTexto.getRecords("Detalhe");
        this.transacoes = new ArrayList<>(registroTransaceos.size());
        for (Record registro : registroTransaceos) {
            transacoes.add(new Transacao(registro));
        }
    }

    private void carregarLayout() {
        InputStream inputStream = ClassLoaders.getResourceAsStream(nomeArquivo,
                this.getClass());
        this.arquivoTexto = Texgit.createFlatFile(inputStream);
    }

    public Cabecalho getCabecalho() {
        return cabecalho;
    }

    public List<Transacao> getTransacoes() {
        return transacoes;
    }

    public Sumario getSumario() {
        return sumario;
    }
}
