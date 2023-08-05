package com.example.vendas;

import com.example.vendas.model.Cliente;
import com.example.vendas.repository.Clientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class VendasApplication {

	@Bean
	public CommandLineRunner init(@Autowired Clientes clientes) {
		return args -> {
			System.out.println("SALVANDO CLIENTES");
			clientes.salvar(new Cliente("Pablo"));
			clientes.salvar(new Cliente("Outro cliente"));

			List<Cliente> todosClientes = clientes.listarTodos();
			todosClientes.forEach(System.out::println);

			System.out.println("ATUALIZANDO CLIENTES");
			todosClientes.forEach(c -> {
				c.setNome(c.getNome() + " atualizado.");
				clientes.atualizar(c);
			});

			todosClientes = clientes.listarTodos();
			todosClientes.forEach(System.out::println);

			System.out.println("BUSCANDO CLIENTES");
			clientes.buscarPorNome("cli").forEach(System.out::println);

			System.out.println("DELETANDO CLIENTES");
			clientes.listarTodos().forEach(c -> {
				clientes.deletar(c);
			});

			todosClientes = clientes.listarTodos();
			if (todosClientes.isEmpty()) {
				System.out.println("NENHUM CLIENTE ENCONTRADO!");
			} else {
				todosClientes.forEach(System.out::println);
			}

		};
	}

	public static void main(String[] args) {
		SpringApplication.run(VendasApplication.class, args);
	}

}
