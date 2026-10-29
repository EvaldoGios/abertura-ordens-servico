package br.com.ordensservico.aberturaordensservico.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.ordensservico.aberturaordensservico.Model.Equipamento;
import br.com.ordensservico.aberturaordensservico.Model.Setor;
import br.com.ordensservico.aberturaordensservico.Repository.EquipamentoRepository;



@Service 
public class EquipamentoService {

    private final EquipamentoRepository equipamentoRepository;

    public EquipamentoService(EquipamentoRepository equipamentoRepository) {
        this.equipamentoRepository = equipamentoRepository;
    }
    
    public Equipamento cadastrar(Equipamento equipamento) {
        return equipamentoRepository.save(equipamento);
    }

    public List<Equipamento> listar() {
        return equipamentoRepository.findAll();
    }

    public Optional<Equipamento> buscarPorId(Integer id) {
        return equipamentoRepository.findById(id);
    }

    public Optional<Equipamento> atualizar(Integer Id, Equipamento novosdados) {
        Optional<Equipamento> equipamentoExistente = equipamentoRepository.findById(Id);
        if(equipamentoExistente.isEmpty()) {
            return Optional.empty();
        }
        Equipamento equipamento = equipamentoExistente.get();
        equipamento.setNome(novosdados.getNome());
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
