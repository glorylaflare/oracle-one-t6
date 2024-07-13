package br.com.alura.med.voll.api.controller;

import br.com.alura.med.voll.api.domain.consulta.AgendaDeConsultas;
import br.com.alura.med.voll.api.domain.consulta.DadosAgendamentoConulta;
import br.com.alura.med.voll.api.domain.consulta.DadosDetalhamentoConsulta;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("consultas")
@SecurityRequirement(name = "bearer-key")
public class ConsultaController {

    @Autowired
    private AgendaDeConsultas agendaDeConsultas;

    @PostMapping
    @Transactional
    public ResponseEntity agendarConsulta(@RequestBody @Valid DadosAgendamentoConulta dadosAgendamentoConulta) {
        var dto = agendaDeConsultas.agendar(dadosAgendamentoConulta);
        return ResponseEntity.ok(dto);
    }
}
