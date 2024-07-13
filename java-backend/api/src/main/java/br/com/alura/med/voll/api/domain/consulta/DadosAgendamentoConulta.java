package br.com.alura.med.voll.api.domain.consulta;

import br.com.alura.med.voll.api.domain.medico.Especialidade;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosAgendamentoConulta(
        Long idMedico,
        Especialidade especialidade,
        @NotNull
        Long idPaciente,
        @NotNull
        @Future
        LocalDateTime data) {
}
