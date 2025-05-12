package br.dev.rafael.okr.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * Representa um Objetivo (Objective) dentro do modelo de OKRs.
 * Um objetivo é uma meta qualitativa que pode conter vários resultados-chave (KeyResults).
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Objective {

    /**
     * Identificador único do objetivo.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Título do objetivo.
     */
    private String titulo;

    /**
     * Descrição detalhada do objetivo.
     */
    private String descricao;

    /**
     * Porcentagem de conclusão do objetivo (de 0 a 100), geralmente calculada com base nos KeyResults.
     */
    private Double porcentagemConclusao;

    /**
     * Lista de resultados-chave (KeyResults) associados a este objetivo.
     * Os resultados são ordenados por ID de forma crescente.
     * Utiliza {@link JsonManagedReference} para evitar problemas de serialização cíclica.
     */
    @OrderBy("id ASC")
    @OneToMany(mappedBy = "objective", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<KeyResult> keyResults;
}
