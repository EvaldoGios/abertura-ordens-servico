package br.com.ordensservico.aberturaordensservico.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import br.com.ordensservico.aberturaordensservico.Model.Setor;

@Repository 
public interface SetorRepository
        extends JpaRepository<Setor, Integer> {
                
        }