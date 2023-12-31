package com.example.vendas.domain.repository;

import com.example.vendas.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClientesRepository extends JpaRepository<Cliente, Integer> {

    @Query(value = " select * from cliente where nome like '%:nome%' ", nativeQuery = true)
    List<Cliente> buscarPorNome(@Param("nome") String nome);

    @Query(" delete from Cliente c where c.nome = :nome ")
    @Modifying // usa-se quando for fazer atualização no BD (delete ou update)
    void deleteByNome(String nome);
    boolean existsByNome(String nome);

    @Query(" select c from Cliente c left join fetch c.pedidoId where c.id = :id ")
    Cliente findClienteFetchPedidos(@Param("id") Integer id);

}
