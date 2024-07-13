package br.com.alura.med.voll.api.domain.paciente;

import br.com.alura.med.voll.api.domain.endereco.Endereco;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "pacientes")
@Entity(name = "Paciente")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Paciente {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String cpf;
    @Embedded
    private Endereco endereco;
    private Boolean ativo;

    public Paciente(DadosCadastroPaciente dadosCadastroPaciente) {
        this.ativo = true;
        this.nome = dadosCadastroPaciente.nome();
        this.email = dadosCadastroPaciente.email();
        this.telefone = dadosCadastroPaciente.telefone();
        this.cpf = dadosCadastroPaciente.cpf();
        this.endereco = new Endereco(dadosCadastroPaciente.endereco());
    }

    public void atualizarInformacoes(DadosAtualizaPaciente dadosAtualizaPaciente) {
        if (dadosAtualizaPaciente.nome() != null) {
            this.nome = dadosAtualizaPaciente.nome();
        }
        if (dadosAtualizaPaciente.telefone() != null) {
            this.telefone = dadosAtualizaPaciente.telefone();
        }
        if (dadosAtualizaPaciente.endereco() != null) {
            this.endereco.atualizarEndereco(dadosAtualizaPaciente.endereco());
        }

    }

    public void excluir() {
        this.ativo = false;
    }
}