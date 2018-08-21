package br.prgomesr.debitoapi.service;

import br.prgomesr.debitoapi.model.Convenio;
import br.prgomesr.debitoapi.repository.Convenios;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConvenioServiceImpl implements ConvenioService {

    @Autowired
    private Convenios repository;

    @Override
    public List<Convenio> listar() {
        return repository.findAll();
    }

    @Override
    public ResponseEntity<Convenio> listarPorId(Long id) {
        Convenio convenio = buscarRecursoExistente(id);
        return convenio != null ? ResponseEntity.ok(convenio) : ResponseEntity.notFound().build();
    }

    @Override
    public Convenio cadastrar(Convenio convenio) {
        return repository.save(convenio);
    }

    @Override
    public Convenio atualizar(Long id, Convenio convenio) {
        Convenio convenioSalvo = buscarRecursoExistente(id);
        BeanUtils.copyProperties(convenio, convenioSalvo, "id");
        return repository.save(convenioSalvo);
    }

    @Override
    public void atualizarSequencial(Long id, Long sequencial) {
        Convenio convenio = buscarRecursoExistente(id);
        convenio.setSequencial(sequencial);
        repository.save(convenio);
    }

    @Override
    public void remover(Long id) {
        repository.deleteById(id);
    }

    public Convenio buscarRecursoExistente(Long id) {
        Convenio convenio = repository.getOne(id);
        if (convenio == null) {
            throw new EmptyResultDataAccessException(1);
        }
        return convenio;
    }


}
