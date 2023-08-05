package com.example.vendas.repository;

import com.example.vendas.model.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class Clientes {

    private static String insert = "insert into cliente (nome) values (?) ";
    private static String selectAll = "select * from cliente";
    private static String update = "update cliente set nome = ? where id = ? ";
    private static String delete = "delete from cliente where id = ? ";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Cliente salvar(Cliente cliente) {
        jdbcTemplate.update(insert, cliente.getNome());
        return cliente;
    }

    public Cliente atualizar(Cliente cliente) {
        jdbcTemplate.update(update, cliente.getNome(), cliente.getId());
        return cliente;
    }

    public void deletar(Cliente cliente) {
        jdbcTemplate.update(delete, cliente.getId());
    }

    public List<Cliente> buscarPorNome(String nome) {
        return jdbcTemplate.query(selectAll.concat(" where nome like ? "),
        new Object[]{"%" + nome + "%"},
        obterClienteMapper());
    }

    public List<Cliente> listarTodos() {
        return jdbcTemplate.query(selectAll, obterClienteMapper());
    }

    private static RowMapper<Cliente> obterClienteMapper() {
        return new RowMapper<Cliente>() {
            @Override
            public Cliente mapRow(ResultSet rs, int rowNum) throws SQLException {
                Integer id = rs.getInt("id");
                String nome = rs.getString("nome");
                return new Cliente(id, nome);
            }
        };
    }
}
