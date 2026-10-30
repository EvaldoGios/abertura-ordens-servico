package br.com.ordensservico.aberturaordensservico.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import br.com.ordensservico.aberturaordensservico.Model.Setor;
import br.com.ordensservico.aberturaordensservico.Service.SetorService;
import jakarta.validation.Valid;

@RestController
@RequestMapping ("/setores")
public class SetorController {
    
    private final SetorService setorService;

    public SetorController(SetorService setorService) {
        this.setorService = setorService;
    }

     @PostMapping
    public ResponseEntity<Setor> cadastrar(
        @Valid @RequestBody Setor setor) {

        Setor setorCadastrado = setorService.cadastrar(setor);

        return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(setorCadastrado);
    }

    @GetMapping 
    public ResponseEntity<List<Setor>> listar() {

        List<Setor> alunos = setorService.listar();

        return ResponseEntity.ok(alunos);
    }

    @GetMapping ("/{id}")
    public ResponseEntity<Setor> buscarPorId(
        @PathVariable Integer id) {

        Optional<Setor> aluno = setorService.buscarPorId(id);

        if (aluno.isPresent()) {
            return ResponseEntity.ok(aluno.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping ("/{id}")  
    public ResponseEntity<Setor> atualizar(
        @PathVariable Integer id,
        @Valid @RequestBody Setor novosDados) {

        Optional<Setor> alunoAtualizado = setorService.atualizar(id, novosDados);

        if (alunoAtualizado.isPresent()) {
            return ResponseEntity.ok(alunoAtualizado.get());
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<Void> deletar(
        @PathVariable Integer id) {

        boolean deletado = setorService.deletar(id);

        if (deletado) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
        
}
