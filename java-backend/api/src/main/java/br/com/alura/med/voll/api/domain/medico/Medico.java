package br.com.alura.med.voll.api.domain.medico;

import br.com.alura.med.voll.api.domain.endereco.Endereco;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "medicos")
@Entity(name = "Medico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;
    @Embedded
    private Endereco endereco;
    private Boolean ativo;

    public Medico(DadosCadastroMedico dadosCadastroMedico) {
        this.nome = dadosCadastroMedico.nome();
        this.email = dadosCadastroMedico.email();
        this.telefone = dadosCadastroMedico.telefone();
        this.crm = dadosCadastroMedico.crm();
        this.especialidade = dadosCadastroMedico.especialidade();
        this.endereco = new Endereco(dadosCadastroMedico.endereco());
        this.ativo = true;
    }

    public void atualizarInformacoes(DadosAtualizaMedico dadosAtualizaMedico) {
        if(dadosAtualizaMedico.nome() != null) {
            this.nome = dadosAtualizaMedico.nome();
        }
        if(dadosAtualizaMedico.telefone() != null) {
            this.telefone = dadosAtualizaMedico.telefone();
        }
        if(dadosAtualizaMedico.endereco() != null) {
            this.endereco.atualizarEndereco(dadosAtualizaMedico.endereco());
        }
    }

    public void deixarInativo() {
        this.ativo = false;
    }
}
