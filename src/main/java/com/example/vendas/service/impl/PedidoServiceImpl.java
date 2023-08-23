package com.example.vendas.service.impl;

import com.example.vendas.domain.repository.PedidosRepository;
import com.example.vendas.service.PedidoService;
import org.springframework.stereotype.Service;

@Service
public class PedidoServiceImpl implements PedidoService {

    private PedidosRepository repository;

    public PedidoServiceImpl(PedidosRepository repository) {
        this.repository = repository;
    }


}
