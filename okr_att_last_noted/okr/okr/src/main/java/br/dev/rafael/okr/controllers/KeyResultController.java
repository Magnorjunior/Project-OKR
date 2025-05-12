package br.dev.rafael.okr.controllers;

import br.dev.rafael.okr.entities.KeyResult;
import br.dev.rafael.okr.entities.Objective;
import br.dev.rafael.okr.repositorios.KeyResultRepository;
import br.dev.rafael.okr.repositorios.ObjectiveRepository;
import br.dev.rafael.okr.services.OkrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST responsável por gerenciar operações relacionadas aos Key Results.
 */
@RestController
@RequestMapping("/krs")
@CrossOrigin(origins = "*")
public class KeyResultController {

    @Autowired
    private KeyResultRepository krRepository;

    @Autowired
    private ObjectiveRepository objRepository;

    @Autowired
    private OkrService okrService;

    /**
     * Retorna a lista de todos os Key Results cadastrados.
     *
     * @return lista de KeyResults
     */
    @GetMapping
    public List<KeyResult> listar() {
        return krRepository.findAll();
    }

    /**
     * Retorna um Key Result com base no ID informado.
     *
     * @param id ID do Key Result
     * @return ResponseEntity com o Key Result ou 404 se não encontrado
     */
    @GetMapping("/{id}")
    public ResponseEntity<KeyResult> buscarPorId(@PathVariable Long id) {
        return krRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Cria um novo Key Result associado a um Objective existente.
     *
     * @param idObjetivo ID do Objective ao qual o Key Result será vinculado
     * @param kr objeto Key Result a ser criado
     * @return Key Result salvo
     */
    @PostMapping("/{idObjetivo}")
    public KeyResult criar(@PathVariable Long idObjetivo, @RequestBody KeyResult kr) {
        Objective obj = objRepository.findById(idObjetivo).orElseThrow();
        kr.setObjective(obj);

        KeyResult saved = krRepository.save(kr);

        okrService.atualizarPorcentagemObjetivo(idObjetivo);

        return saved;
    }

    /**
     * Atualiza os dados de um Key Result existente.
     *
     * @param id ID do Key Result a ser atualizado
     * @param novoKr objeto com os novos dados do Key Result
     * @return Key Result atualizado
     */
    @PutMapping("/{id}")
    public KeyResult atualizar(@PathVariable Long id, @RequestBody KeyResult novoKr) {
        KeyResult antigo = krRepository.findById(id).orElseThrow();
        antigo.setDescricao(novoKr.getDescricao());
        antigo.setMeta(novoKr.getMeta());
        antigo.setPorcentagemConclusao(novoKr.getPorcentagemConclusao());

        krRepository.save(antigo);

        if (antigo.getObjective() != null) {
            okrService.atualizarPorcentagemObjetivo(antigo.getObjective().getId());
        }

        return antigo;
    }

    /**
     * Atualiza apenas a porcentagem de conclusão de um Key Result.
     *
     * @param id ID do Key Result
     * @param porcentagem novo valor da porcentagem
     * @return Key Result atualizado
     */
    @PutMapping("/{id}/porcentagem")
    public KeyResult atualizarPorcentagem(@PathVariable Long id, @RequestBody Double porcentagem) {
        KeyResult kr = krRepository.findById(id).orElseThrow();
        kr.setPorcentagemConclusao(porcentagem);
        krRepository.save(kr);

        if (kr.getObjective() != null) {
            okrService.atualizarPorcentagemObjetivo(kr.getObjective().getId());
        }

        return kr;
    }

    /**
     * Remove um Key Result com base no ID informado.
     *
     * @param id ID do Key Result
     * @return ResponseEntity com status 204 (No Content)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        KeyResult kr = krRepository.findById(id).orElseThrow();
        Long objId = null;

        if (kr.getObjective() != null) {
            objId = kr.getObjective().getId();
        }

        krRepository.deleteById(id);

        if (objId != null) {
            okrService.atualizarPorcentagemObjetivo(objId);
        }

        return ResponseEntity.noContent().build();
    }
}
