package br.com.alura.med.voll.api.controller;

import br.com.alura.med.voll.api.medico.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;

    @PostMapping
    @Transactional
    public void cadastrarMedicos(@RequestBody @Valid DadosCadastroMedico dadosCadastroMedico) {
        medicoRepository.save(new Medico(dadosCadastroMedico));
    }

    @GetMapping
    public Page<DadosListagemMedico> listarMedicos(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        return medicoRepository.findAllByAtivoTrue(paginacao)
                .map(DadosListagemMedico::new);
    }

    @PutMapping
    @Transactional
    public void atualizaMedico(@RequestBody @Valid DadosAtualizaMedico dadosAtualizaMedico) {
        var medico = medicoRepository.getReferenceById(dadosAtualizaMedico.id());
        medico.atualizarInformacoes(dadosAtualizaMedico);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void inativarMedico(@PathVariable Long id) {
        var medico = medicoRepository.getReferenceById(id);
        medico.deixarInativo();
    }
}
