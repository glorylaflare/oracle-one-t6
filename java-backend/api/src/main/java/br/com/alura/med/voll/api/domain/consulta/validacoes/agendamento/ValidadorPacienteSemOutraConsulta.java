package br.com.alura.med.voll.api.domain.consulta.validacoes.agendamento;

import br.com.alura.med.voll.api.domain.ValidacaoException;
import br.com.alura.med.voll.api.domain.consulta.ConsultaRepository;
import br.com.alura.med.voll.api.domain.consulta.DadosAgendamentoConulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteSemOutraConsulta implements ValidadorAgendamentoDeConsulta {

    @Autowired
    private ConsultaRepository consultaRepository;

    public void validar(DadosAgendamentoConulta dadosAgendamentoConulta) {
        var primeiroHorario = dadosAgendamentoConulta.data().withHour(7);
        var ultimoHorario = dadosAgendamentoConulta.data().withHour(18);
        var pacientePossuiOutraConsultaNoDia = consultaRepository.existsByPacienteIdAndDataBetween(dadosAgendamentoConulta.idPaciente(), primeiroHorario, ultimoHorario);
        if(pacientePossuiOutraConsultaNoDia) {
            throw new ValidacaoException("Paciente já possui uma consulta agendada neste dia.");
        }
    }
}
