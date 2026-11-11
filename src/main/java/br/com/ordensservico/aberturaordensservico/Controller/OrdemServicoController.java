package br.com.ordensservico.aberturaordensservico.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ordensservico.aberturaordensservico.Model.OrdemServico;
import br.com.ordensservico.aberturaordensservico.Service.OrdemServicoService;
import br.com.ordensservico.aberturaordensservico.dto.EncerramentoOrdemServicoRequest;
import br.com.ordensservico.aberturaordensservico.dto.OrdemServicoRequest;
import jakarta.validation.Valid;

@RestController 
@RequestMapping ("/ordens-servico")
public class OrdemServicoController {

    private final OrdemServicoService ordemServicoService;

    public OrdemServicoController(OrdemServicoService ordemServicoService) {
        this.ordemServicoService = ordemServicoService;
    }

    @PostMapping
    public ResponseEntity<OrdemServico> cadastrar(
        @Valid @RequestBody OrdemServicoRequest ordemServicoRequest) {

        Optional<OrdemServico> ordemServicoCadastrada =
                ordemServicoService.cadastrar(ordemServicoRequest);

        if (ordemServicoCadastrada.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ordemServicoCadastrada.get());
    }
    
    @GetMapping
    public ResponseEntity<List<OrdemServico>> listar() {

    List<OrdemServico> ordensServico = ordemServicoService.listar();

    return ResponseEntity.ok(ordensServico);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdemServico> buscarPorId(@PathVariable Integer id) {

    Optional<OrdemServico> ordemServico = ordemServicoService.buscarPorId(id);

    if (ordemServico.isEmpty()) {
        return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(ordemServico.get());
    }

    @PatchMapping("/{id}/encerramento")
    public ResponseEntity<OrdemServico> encerrar(
            @PathVariable Integer id,
            @Valid @RequestBody EncerramentoOrdemServicoRequest encerramentoRequest) {

        Optional<OrdemServico> ordemServico = ordemServicoService.encerrar(id, encerramentoRequest);

        if (ordemServico.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(ordemServico.get());
    }

    @GetMapping("/abertas")
    public ResponseEntity<List<OrdemServico>> listarOrdensEmAberto() {
        List<OrdemServico> ordensEmAberto = ordemServicoService.listarOrdensEmAberto();
        return ResponseEntity.ok(ordensEmAberto);
    }

}
