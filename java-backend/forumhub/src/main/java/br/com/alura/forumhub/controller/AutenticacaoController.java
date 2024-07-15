package br.com.alura.forumhub.controller;

import br.com.alura.forumhub.domain.usuario.DadosAutenticacao;
import br.com.alura.forumhub.domain.usuario.Usuario;
import br.com.alura.forumhub.security.DadosTokenJWT;
import br.com.alura.forumhub.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacao dados) {
        var tokenAutenticacao = new UsernamePasswordAuthenticationToken(dados.email(), dados.senha());
        var autenticacao = authenticationManager.authenticate(tokenAutenticacao);
        var tokenJWT = tokenService.gerarToken((Usuario) autenticacao.getPrincipal());

        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }
}
