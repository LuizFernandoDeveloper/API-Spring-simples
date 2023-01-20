package com.luiz.primeiroexemplo.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.luiz.primeiroexemplo.model.Produto;


public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
    
}
