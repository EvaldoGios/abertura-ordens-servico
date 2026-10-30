package br.com.ordensservico.aberturaordensservico.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;



@Entity
@Table(name = "equipamento")
public class Equipamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank (message = "O nome e obrigatorio")
    private String nome;

    @NotBlank (message = "O numero do Patrimonio e obrigatorio")
    private String numeroPatrimonio;

    @ManyToOne
    @JoinColumn(name = "setor_id", nullable = false)
    private Setor setor;

    public Equipamento() {
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNumeroPatrimonio() {
    return this.numeroPatrimonio;
    }

   public void setNumeroPatrimonio(String numeroPatrimonio) {
    this.numeroPatrimonio = numeroPatrimonio;
    }

    public Setor getSetor() {
    return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }
    
}
