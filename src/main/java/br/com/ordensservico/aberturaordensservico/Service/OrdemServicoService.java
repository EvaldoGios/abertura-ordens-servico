package br.com.ordensservico.aberturaordensservico.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.ordensservico.aberturaordensservico.Model.Equipamento;
import br.com.ordensservico.aberturaordensservico.Model.OrdemServico;
import br.com.ordensservico.aberturaordensservico.Repository.EquipamentoRepository;
import br.com.ordensservico.aberturaordensservico.Repository.OrdemServicoRepository;
import br.com.ordensservico.aberturaordensservico.dto.EncerramentoOrdemServicoRequest;
import br.com.ordensservico.aberturaordensservico.dto.OrdemServicoRequest;
import java.time.LocalDateTime;

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
        ordemServico.setDataAbertura(LocalDateTime.now());  
        OrdemServico ordemServicoCadastrada = ordemServicoRepository.save(ordemServico);
        return Optional.of(ordemServicoCadastrada);
    }

    public List<OrdemServico> listar() {
        return ordemServicoRepository.findAll();
    }

    public Optional<OrdemServico> buscarPorId(Integer id) {
        return ordemServicoRepository.findById(id);
    }

    public Optional<OrdemServico> encerrar(Integer id, EncerramentoOrdemServicoRequest encerramentoRequest) {

        Optional<OrdemServico> ordemServicoOptional = ordemServicoRepository.findById(id);

        if (ordemServicoOptional.isPresent()) {

            OrdemServico ordemServico = ordemServicoOptional.get();
            ordemServico.setSolucao(encerramentoRequest.getSolucao());
            ordemServico.setDataFechamento(LocalDateTime.now());
            ordemServicoRepository.save(ordemServico);
            return Optional.of(ordemServico);

        }
        return Optional.empty();
    }

    public List<OrdemServico> listarOrdensEmAberto() {
        return ordemServicoRepository.findByDataFechamentoIsNull();
    }
    
}
