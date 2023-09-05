package com.example.vendas.domain.entity;

import com.example.vendas.domain.enums.StatusPedido;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data // @Getter @Setter @ToString @EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pedido")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne // muitos pedidos para um cliente
    @JoinColumn(name = "cliente_id")
    private Cliente clienteId; // chave estrangeira

    @Column(name = "data_pedido")
    private LocalDate dataPedido;

    @Column(length = 20, precision = 20, scale = 2)
    private BigDecimal total;

    @Enumerated(EnumType.STRING) // grava no bd como string
    @Column(name = "status")
    private StatusPedido status;

    @OneToMany(mappedBy = "pedidoId")
    private List<ItemPedido> itens;

}
