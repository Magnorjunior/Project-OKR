package br.dev.rafael.okr.entities;

import jakarta.persistence.*;
import lombok.*;

/**
 * Representa uma tarefa (Task) associada a uma iniciativa (Initiative) no modelo de OKRs.
 * Cada tarefa pode ser marcada como concluída ou não, ajudando no acompanhamento do progresso da iniciativa.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Task {

    /**
     * Identificador único da tarefa.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Descrição detalhada da tarefa.
     */
    private String descricao;

    /**
     * Indica se a tarefa foi concluída ou não. Valor booleano.
     */
    private boolean concluida;

    /**
     * A iniciativa (Initiative) à qual esta tarefa pertence.
     * Define o relacionamento bidirecional entre a tarefa e a iniciativa.
     */
    @ManyToOne
    @JoinColumn(name = "initiative_id")
    private Initiative initiative;
}
