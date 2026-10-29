package br.com.ordensservico.aberturaordensservico.Service;

import org.springframework.stereotype.Service;

import br.com.ordensservico.aberturaordensservico.Repository.SetorRepository;

import br.com.ordensservico.aberturaordensservico.Model.Setor;
import java.util.Optional;
import java.util.List;

@Service
public class SetorService {
    
    private final SetorRepository setorRepository;

    public SetorService(SetorRepository setorRepository) {
        this.setorRepository = setorRepository;
    }

    public Setor cadastrar(Setor setor) {
        return setorRepository.save(setor);
    }

    public List<Setor> listar() {
        return setorRepository.findAll();
    }

    public Optional<Setor> buscarPorId(Integer id) {
        return setorRepository.findById(id);
    }

    public Optional<Setor> atualizar(Integer Id, Setor novosdados) {
        Optional<Setor> setorExistente = setorRepository.findById(Id);
        if(setorExistente.isEmpty()) {
            return Optional.empty();
        }
        Setor setor = setorExistente.get();
        setor.setNome(novosdados.getNome());
        return Optional.of(setorRepository.save(setor));
    }

    public boolean deletar(Integer id) {

        if (setorRepository.findById(id).isEmpty()) {
            return false;
        }

        setorRepository.deleteById(id);
        return true;
    }

}