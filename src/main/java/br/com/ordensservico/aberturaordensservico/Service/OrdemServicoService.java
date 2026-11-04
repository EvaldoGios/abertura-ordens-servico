package br.com.ordensservico.aberturaordensservico.Service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.ordensservico.aberturaordensservico.Model.Equipamento;
import br.com.ordensservico.aberturaordensservico.Model.OrdemServico;
import br.com.ordensservico.aberturaordensservico.Repository.EquipamentoRepository;
import br.com.ordensservico.aberturaordensservico.Repository.OrdemServicoRepository;
import br.com.ordensservico.aberturaordensservico.dto.OrdemServicoRequest;

@Service 
public class OrdemServicoService {

    private final OrdemServicoRepository ordemServicoRepository;
    private final EquipamentoRepository equipamentoRepository;

    public OrdemServicoService(OrdemServicoRepository ordemServicoRepository, EquipamentoRepository equipamentoRepository) {
        this.ordemServicoRepository = ordemServicoRepository;
        this.equipamentoRepository = equipamentoRepository;
    }

   public Optional<OrdemServico> cadastrar(OrdemServicoRequest ordemServicoRequest) {
        Optional<Equipamento> equipamentoEncontrado = equipamentoRepository.findById(ordemServicoRequest.getEquipamentoId());

        if (equipamentoEncontrado.isEmpty()) {
            return Optional.empty();
        }

        OrdemServico ordemServico = new OrdemServico();
        
        ordemServico.setDescricao(ordemServicoRequest.getDescricao());
        ordemServico.setEquipamento(equipamentoEncontrado.get());

        OrdemServico ordemServicoCadastrada = ordemServicoRepository.save(ordemServico);
        return Optional.of(ordemServicoCadastrada);
    }
    
}
