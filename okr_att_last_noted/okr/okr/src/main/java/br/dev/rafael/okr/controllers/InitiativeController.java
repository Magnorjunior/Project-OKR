package br.dev.rafael.okr.controllers;

import br.dev.rafael.okr.entities.Initiative;
import br.dev.rafael.okr.entities.KeyResult;
import br.dev.rafael.okr.repositorios.InitiativeRepository;
import br.dev.rafael.okr.repositorios.KeyResultRepository;
import br.dev.rafael.okr.services.OkrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador responsável por gerenciar as operações relacionadas às iniciativas (Initiative).
 */
@RestController
@RequestMapping("/iniciativas")
@CrossOrigin(origins = "*")
public class InitiativeController {

    @Autowired
    private InitiativeRepository initiativeRepository;

    @Autowired
    private KeyResultRepository keyResultRepository;

    @Autowired
    private OkrService okrService;

    /**
     * Lista todas as iniciativas cadastradas.
     *
     * @return Lista de iniciativas.
     */
    @GetMapping
    public List<Initiative> listar() {
        return initiativeRepository.findAll();
    }

    /**
     * Busca uma iniciativa pelo seu ID.
     *
     * @param id ID da iniciativa a ser buscada.
     * @return ResponseEntity com a iniciativa encontrada ou status 404 se não encontrada.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Initiative> buscarPorId(@PathVariable Long id) {
        return initiativeRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Cria uma nova iniciativa vinculada a um Key Result.
     *
     * @param idKr ID do Key Result ao qual a iniciativa será vinculada.
     * @param iniciativa Objeto da iniciativa a ser criada.
     * @return A iniciativa salva.
     */
    @PostMapping("/{idKr}")
    public Initiative criar(@PathVariable Long idKr, @RequestBody Initiative iniciativa) {
        KeyResult kr = keyResultRepository.findById(idKr).orElseThrow();
        iniciativa.setKeyResult(kr);

        Initiative saved = initiativeRepository.save(iniciativa);

        okrService.atualizarPorcentagemKr(idKr);

        return saved;
    }

    /**
     * Atualiza o status de conclusão de uma tarefa específica dentro de uma iniciativa.
     *
     * @param id ID da iniciativa.
     * @param index Índice da tarefa na lista.
     * @param concluida Novo status de conclusão (true ou false).
     * @return ResponseEntity com a iniciativa atualizada ou erro 400 se o índice for inválido.
     */
    @PatchMapping("/{id}/tasks/{index}")
    public ResponseEntity<Initiative> atualizarStatusTask(@PathVariable Long id,
                                                          @PathVariable int index,
                                                          @RequestParam boolean concluida) {
        Initiative iniciativa = initiativeRepository.findById(id).orElseThrow();
        if (index >= 0 && index < iniciativa.getTasks().size()) {
            iniciativa.getTasks().get(index).setConcluida(concluida);
            initiativeRepository.save(iniciativa);
            return ResponseEntity.ok(iniciativa);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Atualiza os dados de uma iniciativa existente.
     *
     * @param id ID da iniciativa a ser atualizada.
     * @param nova Objeto com os novos dados da iniciativa.
     * @return A iniciativa atualizada.
     */
    @PutMapping("/{id}")
    public Initiative atualizar(@PathVariable Long id, @RequestBody Initiative nova) {
        Initiative antiga = initiativeRepository.findById(id).orElseThrow();
        antiga.setTitulo(nova.getTitulo());
        antiga.setDescricao(nova.getDescricao());
        antiga.setPorcentagemConclusao(nova.getPorcentagemConclusao());

        initiativeRepository.save(antiga);

        if (antiga.getKeyResult() != null) {
            okrService.atualizarPorcentagemKr(antiga.getKeyResult().getId());
        }

        return antiga;
    }

    /**
     * Atualiza somente a porcentagem de conclusão de uma iniciativa.
     *
     * @param id ID da iniciativa.
     * @param porcentagem Nova porcentagem de conclusão.
     * @return A iniciativa com a porcentagem atualizada.
     */
    @PutMapping("/{id}/porcentagem")
    public Initiative atualizarPorcentagem(@PathVariable Long id, @RequestBody Double porcentagem) {
        return okrService.atualizarPorcentagemIniciativa(id, porcentagem);
    }

    /**
     * Deleta uma iniciativa com base no ID informado.
     *
     * @param id ID da iniciativa a ser deletada.
     * @return ResponseEntity com status 204 se a exclusão for bem-sucedida.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        Initiative iniciativa = initiativeRepository.findById(id).orElseThrow();
        Long krId = null;

        if (iniciativa.getKeyResult() != null) {
            krId = iniciativa.getKeyResult().getId();
        }

        initiativeRepository.deleteById(id);

        if (krId != null) {
            okrService.atualizarPorcentagemKr(krId);
        }

        return ResponseEntity.noContent().build();
    }
} 
