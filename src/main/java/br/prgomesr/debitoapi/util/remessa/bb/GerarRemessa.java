package br.prgomesr.debitoapi.util.remessa.bb;

import br.prgomesr.debitoapi.model.Convenio;
import br.prgomesr.debitoapi.model.Empresa;
import br.prgomesr.debitoapi.model.Lancamento;
import org.jrimum.texgit.FlatFile;
import org.jrimum.texgit.Record;

import java.io.IOException;
import java.util.List;

public interface GerarRemessa {

    public void exportarRemessa(List<Lancamento> lancamentos, Convenio convenio, Empresa empresa) throws IOException;
    public Record createHeader(FlatFile<Record> flatFile, Convenio convenio, Empresa empresa);
    public List<Record> createDetalhe(FlatFile<Record> flatFile, List<Lancamento> lancamentos);
    public Record createTrailler(FlatFile<Record> flatFile);

}
