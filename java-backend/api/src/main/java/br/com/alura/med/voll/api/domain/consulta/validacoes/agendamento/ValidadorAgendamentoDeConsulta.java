package br.com.alura.med.voll.api.domain.consulta.validacoes.agendamento;

import br.com.alura.med.voll.api.domain.consulta.DadosAgendamentoConulta;

public interface ValidadorAgendamentoDeConsulta {

    void validar(DadosAgendamentoConulta dadosAgendamentoConulta);
}
