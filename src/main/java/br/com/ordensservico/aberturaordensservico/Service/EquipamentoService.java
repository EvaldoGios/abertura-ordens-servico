package br.com.ordensservico.aberturaordensservico.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.ordensservico.aberturaordensservico.Model.Equipamento;
import br.com.ordensservico.aberturaordensservico.Model.Setor;
import br.com.ordensservico.aberturaordensservico.Repository.EquipamentoRepository;
import br.com.ordensservico.aberturaordensservico.Repository.SetorRepository;
import br.com.ordensservico.aberturaordensservico.dto.EquipamentoRequest;


@Service 
public class EquipamentoService {

    private final EquipamentoRepository equipamentoRepository;
    private final SetorRepository setorRepository;

    public EquipamentoService(EquipamentoRepository equipamentoRepository, SetorRepository setorRepository) {
        this.equipamentoRepository = equipamentoRepository;
        this.setorRepository = setorRepository;
    }

    public Equipamento cadastrar(EquipamentoRequest request) {
    
    Setor setor = setorRepository.findById(request.getSetorId())
            .orElseThrow(() -> new RuntimeException("Setor não encontrado"));

    Equipamento equipamento = new Equipamento();
    equipamento.setNome(request.getNome());

    equipamento.setNumeroPatrimonio(request.getNumeroPatrimonio());

    equipamento.setSetor(setor);

    return equipamentoRepository.save(equipamento);
}

    public List<Equipamento> listar() {
        return equipamentoRepository.findAll();
    }

    public Optional<Equipamento> buscarPorId(Integer id) {
        return equipamentoRepository.findById(id);
    }

    public Optional<Equipamento> atualizar(Integer id, EquipamentoRequest novosDados) {

    Optional<Equipamento> equipamentoExistente = equipamentoRepository.findById(id);

    if (equipamentoExistente.isEmpty()) {
        return Optional.empty();
    }
    Optional<Setor> setorEncontrado = setorRepository.findById(novosDados.getSetorId());

    if (setorEncontrado.isEmpty()) {
        return Optional.empty();
    }
    Equipamento equipamento = equipamentoExistente.get();

    equipamento.setNome(novosDados.getNome());
    equipamento.setNumeroPatrimonio(novosDados.getNumeroPatrimonio());
    equipamento.setSetor(setorEncontrado.get());

    return Optional.of(equipamentoRepository.save(equipamento));
}

    public boolean deletar(Integer id) {

        if (equipamentoRepository.findById(id).isEmpty()) {
            return false;
        }

        equipamentoRepository.deleteById(id);
        return true;
    }
}
