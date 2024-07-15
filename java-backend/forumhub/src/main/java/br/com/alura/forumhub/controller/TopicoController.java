package br.com.alura.forumhub.controller;

import br.com.alura.forumhub.domain.topico.*;
import br.com.alura.forumhub.domain.usuario.Usuario;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarTopico(@RequestBody @Valid DadosCadastroTopico dados, UriComponentsBuilder builder, @AuthenticationPrincipal Usuario usuarioLogado) {

        var exists = topicoRepository.existsByTituloAndMensagem(dados.titulo(), dados.mensagem());
        if(exists) return ResponseEntity.status(HttpStatus.CONFLICT).body("O tópico já existe no banco de dados!");

        var topico = new Topico(dados);
        topico.setAutor(usuarioLogado.getUsername());
        topicoRepository.save(topico);
        var uri = builder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoTopico(topico));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemTopico>> listarTopicos(@PageableDefault(size = 10, sort = "dataCriacao", direction = Sort.Direction.ASC) Pageable paginacao) {

        var page = topicoRepository.findAll(paginacao).map(DadosListagemTopico::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity listarTopicosPorId(@PathVariable Long id) {

        Optional<Topico> topicoOptional = topicoRepository.findById(id);
        if (topicoOptional.isPresent()) {
            var topico = topicoOptional.get();
            return ResponseEntity.ok(new DadosCompletosTopico(topico));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tópico não encontrado");
        }
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity atualizarTopico(@PathVariable Long id, @RequestBody @Valid DadosAtualizaTopico dados) {

        Optional<Topico> topicoOptional = topicoRepository.findById(id);
        if (topicoOptional.isPresent()) {
            var topico = topicoOptional.get();
            topico.atualizaInformacoes(dados);
            return ResponseEntity.ok(new DadosCompletosTopico(topico));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tópico não encontrado.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarTopico(@PathVariable Long id) {
        Optional<Topico> topicoOptional = topicoRepository.findById(id);

        if (topicoOptional.isPresent()) {
            topicoRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tópico não encontrado.");
        }
    }
}
