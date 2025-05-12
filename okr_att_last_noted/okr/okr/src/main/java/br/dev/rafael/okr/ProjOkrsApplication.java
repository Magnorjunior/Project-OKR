package br.dev.rafael.okr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe principal para iniciar a aplicação Spring Boot.
 * 
 * Esta classe contém o método `main` que inicializa o contexto da aplicação Spring,
 * iniciando todos os componentes do sistema como controladores, serviços, repositórios, etc.
 */
@SpringBootApplication
public class ProjOkrsApplication {

    /**
     * Método principal que inicializa a aplicação Spring Boot.
     * 
     * @param args Argumentos de linha de comando passados para a aplicação.
     */
    public static void main(String[] args) {
        SpringApplication.run(ProjOkrsApplication.class, args);
    }
}
