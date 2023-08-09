package com.example.vendas.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(length = 100)
    private String nome;
    @OneToMany(mappedBy = "clienteId", fetch = FetchType.LAZY) // um cliente para muitos pedidos
    private Set<Pedido> pedidoId;

    public Integer getId() {
        return id;
    }

    public Cliente() {
    }

    public Cliente(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Cliente(String nome) {
        this.nome = nome;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Set<Pedido> getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Set<Pedido> pedidoId) {
        this.pedidoId = pedidoId;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}
