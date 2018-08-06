package br.prgomesr.debitoapi.util.remessa.bb;

import br.prgomesr.debitoapi.model.Cliente;
import br.prgomesr.debitoapi.model.Convenio;
import br.prgomesr.debitoapi.model.Empresa;
import org.jrimum.texgit.FlatFile;
import org.jrimum.texgit.Record;

import java.io.IOException;
import java.util.List;

public interface GerarRemessaInclusao {

    public void exportarRemessa(List<Cliente> clientes, Convenio convenio, Empresa empresa) throws IOException;
    public Record createHeader(FlatFile<Record> flatFile, Convenio convenio, Empresa empresa);
    public List<Record> createDetalhe(FlatFile<Record> flatFile, List<Cliente> clientes);
    public Record createTrailler(FlatFile<Record> flatFile);
}
