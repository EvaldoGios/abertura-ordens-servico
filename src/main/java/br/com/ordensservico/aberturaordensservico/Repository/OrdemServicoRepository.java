package br.com.ordensservico.aberturaordensservico.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ordensservico.aberturaordensservico.Model.OrdemServico;

@Repository 
public interface OrdemServicoRepository
        extends JpaRepository<OrdemServico, Integer> {
                
}
