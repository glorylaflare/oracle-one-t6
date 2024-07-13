package br.com.alura.med.voll.api.domain.consulta.validacoes.agendamento;

import br.com.alura.med.voll.api.domain.ValidacaoException;
import br.com.alura.med.voll.api.domain.consulta.DadosAgendamentoConulta;
import br.com.alura.med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteAtivo implements ValidadorAgendamentoDeConsulta {

    @Autowired
    private PacienteRepository pacienteRepository;

    public void validar(DadosAgendamentoConulta dadosAgendamentoConulta) {
        var pacienteEstaAtivo = pacienteRepository.findAtivoById(dadosAgendamentoConulta.idPaciente());
        if(!pacienteEstaAtivo) {
            throw new ValidacaoException("Consulta não pode ser agendada com paciente excluído.");
        }
    }
}
