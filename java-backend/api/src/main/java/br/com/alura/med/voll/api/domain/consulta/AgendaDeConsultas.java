package br.com.alura.med.voll.api.domain.consulta;

import br.com.alura.med.voll.api.domain.ValidacaoException;
import br.com.alura.med.voll.api.domain.consulta.validacoes.agendamento.ValidadorAgendamentoDeConsulta;
import br.com.alura.med.voll.api.domain.consulta.validacoes.cancelamento.ValidadorCancelamentoDeConsulta;
import br.com.alura.med.voll.api.domain.medico.Medico;
import br.com.alura.med.voll.api.domain.medico.MedicoRepository;
import br.com.alura.med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaDeConsultas {

    @Autowired
    private ConsultaRepository consultaRepository;
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private List<ValidadorAgendamentoDeConsulta> validadorAgendamentoDeConsultas;
    @Autowired
    private List<ValidadorCancelamentoDeConsulta> validadorCancelamentoDeConsultas;

    public DadosDetalhamentoConsulta agendar(DadosAgendamentoConulta dadosAgendamentoConulta) {
        if(!pacienteRepository.existsById(dadosAgendamentoConulta.idPaciente())) {
            throw new ValidacaoException("Id do paciente informado não existe!");
        }

        if(dadosAgendamentoConulta.idMedico() != null && !medicoRepository.existsById(dadosAgendamentoConulta.idMedico())) {
            throw new ValidacaoException("Id do médico informado não existe!");
        }

        validadorAgendamentoDeConsultas.forEach(v -> v.validar(dadosAgendamentoConulta));

        var paciente = pacienteRepository.getReferenceById(dadosAgendamentoConulta.idPaciente());
        var medico = escolherMedico(dadosAgendamentoConulta);
        if(medico == null) {
            throw new ValidacaoException("Não existe médico disponível nesta data!");
        }
        var consulta = new Consulta(null, medico, paciente, dadosAgendamentoConulta.data(), null);
        consultaRepository.save(consulta);

        return new DadosDetalhamentoConsulta(consulta);
    }

    private Medico escolherMedico(DadosAgendamentoConulta dadosAgendamentoConulta) {
        if(dadosAgendamentoConulta.idMedico() != null) {
            return medicoRepository.getReferenceById(dadosAgendamentoConulta.idMedico());
        }

        if(dadosAgendamentoConulta.especialidade() == null) {
            throw new ValidacaoException("Especialidade é obrigatória quando um médico não for escolhido");
        }

        return medicoRepository.escolherMedicoAleatorioLivreNaData(dadosAgendamentoConulta.especialidade(), dadosAgendamentoConulta.data());
    }

    public void cancelar(DadosCancelamentoConsulta dadosCancelamentoConsulta) {
        if(!consultaRepository.existsById(dadosCancelamentoConsulta.idConsulta())) {
            throw new ValidacaoException("Id da consulta informado não existe.");
        }

        validadorCancelamentoDeConsultas.forEach(v -> v.validar(dadosCancelamentoConsulta));

        var consulta = consultaRepository.getReferenceById(dadosCancelamentoConsulta.idConsulta());
        consulta.cancelar(dadosCancelamentoConsulta.motivoCancelamento());
    }
}
