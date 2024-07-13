package br.com.alura.med.voll.api.domain.consulta.validacoes.agendamento;

import br.com.alura.med.voll.api.domain.ValidacaoException;
import br.com.alura.med.voll.api.domain.consulta.DadosAgendamentoConulta;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component("ValidadorHorarioAntecedenciaAgendamento")
public class ValidadorHorarioAntecedencia implements ValidadorAgendamentoDeConsulta {

    public void validar(DadosAgendamentoConulta dadosAgendamentoConulta) {
        var dataConsulta = dadosAgendamentoConulta.data();
        var agora = LocalDateTime.now();
        var diferencaEmMinutos = Duration.between(agora, dataConsulta).toMinutes();

        if(diferencaEmMinutos < 30) {
            throw new ValidacaoException("Consulta deve ser agendada com antecedência mínima de 30 minutos.");
        }
    }
}
