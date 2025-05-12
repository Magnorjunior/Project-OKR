package br.dev.rafael.okr.repositorios;

import br.dev.rafael.okr.entities.Objective;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface de repositório para a entidade {@link Objective}.
 * 
 * Extende a interface {@link JpaRepository} para fornecer operações básicas de
 * persistência para a entidade {@link Objective}, como salvar, deletar, 
 * atualizar e consultar registros.
 * 
 * O repositório oferece métodos automáticos para acessar dados relacionados aos
 * objetivos na base de dados.
 */
public interface ObjectiveRepository extends JpaRepository<Objective, Long> {
}
