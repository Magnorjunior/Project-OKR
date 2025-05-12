package br.dev.rafael.okr.controllers;

import br.dev.rafael.okr.entities.Objective;
import br.dev.rafael.okr.repositorios.ObjectiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST responsável por gerenciar operações relacionadas aos Objetivos (Objectives).
 */
@RestController
@RequestMapping("/objetivos")
@CrossOrigin(origins = "*")
public class ObjectiveController {

    @Autowired
    private ObjectiveRepository repository;

    /**
     * Retorna uma lista com todos os objetivos cadastrados.
     *
     * @return lista de {@link Objective}
     */
    @GetMapping
    public List<Objective> listar() {
        return repository.findAll();
    }

    /**
     * Busca um objetivo pelo seu ID.
     *
     * @param id ID do objetivo
     * @return {@link ResponseEntity} com o objetivo encontrado ou 404 se não existir
     */
    @GetMapping("/{id}")
    public ResponseEntity<Objective> buscarPorId(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Cria um novo objetivo. Caso a porcentagem de conclusão não seja informada, será iniciada em 0.0.
     *
     * @param objetivo novo objetivo a ser criado
     * @return objetivo salvo
     */
    @PostMapping
    public Objective criar(@RequestBody Objective objetivo) {
        if (objetivo.getPorcentagemConclusao() == null) {
            objetivo.setPorcentagemConclusao(0.0);
        }
        return repository.save(objetivo);
    }

    /**
     * Atualiza os dados de um objetivo existente.
     *
     * @param id       ID do objetivo a ser atualizado
     * @param novoObj  objeto com os novos dados
     * @return objetivo atualizado
     */
    @PutMapping("/{id}")
    public Objective atualizar(@PathVariable Long id, @RequestBody Objective novoObj) {
        Objective antigo = repository.findById(id).orElseThrow();
        antigo.setTitulo(novoObj.getTitulo());
        antigo.setDescricao(novoObj.getDescricao());
        antigo.setPorcentagemConclusao(novoObj.getPorcentagemConclusao());
        return repository.save(antigo);
    }

    /**
     * Atualiza apenas a porcentagem de conclusão de um objetivo.
     *
     * @param id          ID do objetivo
     * @param porcentagem nova porcentagem de conclusão
     * @return objetivo com porcentagem atualizada
     */
    @PutMapping("/{id}/porcentagem")
    public Objective atualizarPorcentagem(@PathVariable Long id, @RequestBody Double porcentagem) {
        Objective obj = repository.findById(id).orElseThrow();
        obj.setPorcentagemConclusao(porcentagem);
        return repository.save(obj);
    }

    /**
     * Remove um objetivo com base no seu ID.
     *
     * @param id ID do objetivo a ser removido
     * @return {@link ResponseEntity} com status 204 (No Content)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
