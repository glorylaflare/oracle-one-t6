package br.com.alura.med.voll.api.domain.consulta.validacoes.agendamento;

import br.com.alura.med.voll.api.domain.ValidacaoException;
import br.com.alura.med.voll.api.domain.consulta.DadosAgendamentoConulta;
import br.com.alura.med.voll.api.domain.medico.MedicoRepository;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoAtivo implements ValidadorAgendamentoDeConsulta {

    private MedicoRepository medicoRepository;

    public void validar(DadosAgendamentoConulta dadosAgendamentoConulta) {
        if(dadosAgendamentoConulta.idMedico() == null) {
            return;
        }
         var medicoEstaAtivo = medicoRepository.findAtivoById(dadosAgendamentoConulta.idMedico());
        if(!medicoEstaAtivo) {
            throw new ValidacaoException("Consulta não pode ser agendada com médico inativo.");
        }
    }
}
