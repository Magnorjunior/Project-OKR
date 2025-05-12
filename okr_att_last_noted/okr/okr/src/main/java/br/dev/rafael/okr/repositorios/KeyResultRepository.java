package br.dev.rafael.okr.repositorios;

import br.dev.rafael.okr.entities.KeyResult;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface de repositório para a entidade {@link KeyResult}.
 * 
 * Extende a interface {@link JpaRepository} para fornecer operações básicas de
 * persistência para a entidade {@link KeyResult}, como salvar, deletar, 
 * atualizar e consultar registros.
 * 
 * O repositório oferece métodos automáticos para acessar dados relacionados aos
 * key results na base de dados.
 */
public interface KeyResultRepository extends JpaRepository<KeyResult, Long> {
}
