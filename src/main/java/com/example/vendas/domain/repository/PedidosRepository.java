package com.example.vendas.domain.repository;

import com.example.vendas.domain.entity.Cliente;
import com.example.vendas.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidosRepository extends JpaRepository<Pedido, Integer> {

    List<Pedido> findByClienteId(Cliente cliente);
}
