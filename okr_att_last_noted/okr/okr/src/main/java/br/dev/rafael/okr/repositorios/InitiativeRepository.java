package br.dev.rafael.okr.repositorios;

import br.dev.rafael.okr.entities.Initiative;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface de repositório para a entidade {@link Initiative}.
 * 
 * Extende a interface {@link JpaRepository} para fornecer operações básicas de
 * persistência para a entidade {@link Initiative}, como salvar, deletar, 
 * atualizar e consultar registros.
 * 
 * O repositório fornece métodos automáticos para acessar dados relacionados às 
 * iniciativas na base de dados.
 */
public interface InitiativeRepository extends JpaRepository<Initiative, Long> {
}
