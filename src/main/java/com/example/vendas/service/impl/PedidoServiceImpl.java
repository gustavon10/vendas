package com.example.vendas.service.impl;

import com.example.vendas.domain.entity.Cliente;
import com.example.vendas.domain.entity.ItemPedido;
import com.example.vendas.domain.entity.Pedido;
import com.example.vendas.domain.entity.Produto;
import com.example.vendas.domain.enums.StatusPedido;
import com.example.vendas.domain.repository.ClientesRepository;
import com.example.vendas.domain.repository.ItensPedidoRepository;
import com.example.vendas.domain.repository.PedidosRepository;
import com.example.vendas.domain.repository.ProdutosRepository;
import com.example.vendas.exception.PedidoNaoEncontradoException;
import com.example.vendas.exception.RegraNegocioException;
import com.example.vendas.rest.dto.ItemPedidoDTO;
import com.example.vendas.rest.dto.PedidoDTO;
import com.example.vendas.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor // cria o construtor com todos os argumentos obrigatorios
public class PedidoServiceImpl implements PedidoService {

    private final PedidosRepository repository;
    private final ClientesRepository clientesRepository;
    private final ProdutosRepository produtosRepository;
    private final ItensPedidoRepository itensPedidoRepository;


    @Override
    @Transactional
    public Pedido salvar(PedidoDTO dto) {
        Integer idCliente = dto.getCliente();
        Cliente cliente = clientesRepository
                .findById(idCliente)
                .orElseThrow( () -> new RegraNegocioException("código de cliente inválido."));

        Pedido pedido = new Pedido();
        pedido.setTotal(dto.getTotal());
        pedido.setDataPedido(LocalDate.now());
        pedido.setClienteId(cliente);
        pedido.setStatus(StatusPedido.REALIZADO);

        List<ItemPedido> itensPedidos = converterItens(pedido, dto.getItens());
        repository.save(pedido);
        itensPedidoRepository.saveAll(itensPedidos);
        pedido.setItens(itensPedidos);
        return pedido;
    }

    @Override
    public Optional<Pedido> obterPedidoCompleto(Integer id) {
        return repository.findByIdFetchItens(id);
    }

    @Override
    @Transactional
    public void atualizaStatus(Integer id, StatusPedido statusPedido) {
        repository.findById(id)
                .map( pedido -> {
                    pedido.setStatus(statusPedido);
                    return repository.save(pedido);
                }).orElseThrow( () -> new PedidoNaoEncontradoException());
    }

    private List<ItemPedido> converterItens(Pedido pedido, List<ItemPedidoDTO> itens) {
        if (itens.isEmpty()) {
            throw new RegraNegocioException("Sem itens no pedido.");
        }
        return itens.stream()
                .map( dto -> {
                    Integer idProduto = dto.getProduto();
                    Produto produto = produtosRepository
                            .findById(idProduto)
                            .orElseThrow( () -> new RegraNegocioException(
                                            "código de produto inválido: " + idProduto));

                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setQuantidade(dto.getQuantidade());
                    itemPedido.setPedidoId(pedido);
                    itemPedido.setProdutoId(produto);
                    return itemPedido;
                }).collect(Collectors.toList());
    }
}
