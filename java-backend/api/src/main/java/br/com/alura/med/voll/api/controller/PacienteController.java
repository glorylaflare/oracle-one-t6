package br.com.alura.med.voll.api.controller;

import br.com.alura.med.voll.api.domain.paciente.DadosListagemPaciente;
import br.com.alura.med.voll.api.domain.paciente.Paciente;
import br.com.alura.med.voll.api.domain.paciente.PacienteRepository;
import br.com.alura.med.voll.api.domain.paciente.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository pacienteRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarPacientes(@RequestBody @Valid DadosCadastroPaciente dadosCadastroPaciente, UriComponentsBuilder uriComponentsBuilder) {
        var paciente = new Paciente(dadosCadastroPaciente);
        pacienteRepository.save(paciente);
        var uri = uriComponentsBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoPaciente(paciente));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemPaciente>> listarPacientes(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = pacienteRepository.findAllByAtivoTrue(paginacao)
                .map(DadosListagemPaciente::new);

        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity detalharMedico(@PathVariable Long id) {
        var paciente = pacienteRepository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoPaciente(paciente));
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizarPaciente(@RequestBody @Valid DadosAtualizaPaciente dadosAtualizaPaciente) {
        var paciente = pacienteRepository.getReferenceById(dadosAtualizaPaciente.id());
        paciente.atualizarInformacoes(dadosAtualizaPaciente);

        return ResponseEntity.ok(new DadosDetalhamentoPaciente(paciente));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluirPaciente(@PathVariable Long id) {
        var paciente = pacienteRepository.getReferenceById(id);
        paciente.excluir();

        return ResponseEntity.noContent().build();
    }
}