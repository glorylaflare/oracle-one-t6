package br.com.alura.med.voll.api.domain.consulta.validacoes.agendamento;

import br.com.alura.med.voll.api.domain.ValidacaoException;
import br.com.alura.med.voll.api.domain.consulta.ConsultaRepository;
import br.com.alura.med.voll.api.domain.consulta.DadosAgendamentoConulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoComOutraConsultaNoMesmoHorario implements ValidadorAgendamentoDeConsulta {

    @Autowired
    private ConsultaRepository consultaRepository;

    public void validar(DadosAgendamentoConulta dadosAgendamentoConulta) {
        var medicoPossuiOutraConsultaNoMesmoHorario = consultaRepository.existsByMedicoIdAndDataAndMotivoCancelamentoIsNull(dadosAgendamentoConulta.idMedico(), dadosAgendamentoConulta.data());
        if(medicoPossuiOutraConsultaNoMesmoHorario) {
            throw new ValidacaoException("Médico já possui outra consulta agendada nesse mesmo horário.");
        }
    }
}
