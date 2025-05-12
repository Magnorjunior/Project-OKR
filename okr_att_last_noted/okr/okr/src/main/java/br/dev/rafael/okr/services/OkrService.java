package br.dev.rafael.okr.services;

import br.dev.rafael.okr.entities.Initiative;
import br.dev.rafael.okr.entities.KeyResult;
import br.dev.rafael.okr.entities.Objective;
import br.dev.rafael.okr.repositorios.InitiativeRepository;
import br.dev.rafael.okr.repositorios.KeyResultRepository;
import br.dev.rafael.okr.repositorios.ObjectiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Serviço para gerenciar e atualizar as porcentagens de conclusão de 
 * Key Results, Iniciativas e Objetivos dentro do sistema de OKRs.
 * 
 * Este serviço realiza os cálculos das porcentagens de conclusão baseados 
 * nas iniciativas de um Key Result e nos Key Results de um Objetivo.
 */
@Service
public class OkrService {

    @Autowired
    private InitiativeRepository initiativeRepository;

    @Autowired
    private KeyResultRepository keyResultRepository;

    @Autowired
    private ObjectiveRepository objectiveRepository;

    /**
     * Atualiza a porcentagem de conclusão de um Key Result com base nas 
     * porcentagens das iniciativas relacionadas a ele.
     * 
     * @param krId ID do Key Result.
     */
    @Transactional
    public void atualizarPorcentagemKr(Long krId) {
        KeyResult kr = keyResultRepository.findById(krId).orElseThrow();
        List<Initiative> iniciativas = kr.getIniciativas();

        if (iniciativas == null || iniciativas.isEmpty()) {
            kr.setPorcentagemConclusao(0.0);
        } else {
            double mediaPorcentagem = iniciativas.stream()
                    .map(Initiative::getPorcentagemConclusao)
                    .map(porcentagem -> porcentagem == null ? 0.0 : porcentagem) 
                    .mapToDouble(Double::doubleValue)
                    .average()
                    .orElse(0.0);

            kr.setPorcentagemConclusao(mediaPorcentagem);
        }

        keyResultRepository.save(kr);

        if (kr.getObjective() != null) {
            atualizarPorcentagemObjetivo(kr.getObjective().getId());
        }
    }

    /**
     * Atualiza a porcentagem de conclusão de um Objetivo com base nas 
     * porcentagens dos Key Results relacionados a ele.
     * 
     * @param objectiveId ID do Objetivo.
     */
    @Transactional
    public void atualizarPorcentagemObjetivo(Long objectiveId) {
        Objective objetivo = objectiveRepository.findById(objectiveId).orElseThrow();
        List<KeyResult> keyResults = objetivo.getKeyResults();

        if (keyResults == null || keyResults.isEmpty()) {
            objetivo.setPorcentagemConclusao(0.0);
        } else {
            double mediaPorcentagem = keyResults.stream()
                    .map(KeyResult::getPorcentagemConclusao)
                    .map(porcentagem -> porcentagem == null ? 0.0 : porcentagem) 
                    .mapToDouble(Double::doubleValue)
                    .average()
                    .orElse(0.0);

            objetivo.setPorcentagemConclusao(mediaPorcentagem);
        }

        objectiveRepository.save(objetivo);
    }

    /**
     * Atualiza a porcentagem de conclusão de uma Iniciativa e, se necessário, 
     * atualiza a porcentagem do Key Result relacionado.
     * 
     * @param initiativeId ID da Iniciativa.
     * @param porcentagem Nova porcentagem de conclusão da Iniciativa.
     * @return A Iniciativa atualizada.
     */
    @Transactional
    public Initiative atualizarPorcentagemIniciativa(Long initiativeId, Double porcentagem) {
        Initiative iniciativa = initiativeRepository.findById(initiativeId).orElseThrow();
        iniciativa.setPorcentagemConclusao(porcentagem);
        initiativeRepository.save(iniciativa);
        
        if (iniciativa.getKeyResult() != null) {
            atualizarPorcentagemKr(iniciativa.getKeyResult().getId());
        }
        
        return iniciativa;
    }
}
