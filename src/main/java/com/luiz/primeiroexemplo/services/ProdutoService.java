package com.luiz.primeiroexemplo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luiz.primeiroexemplo.model.Produto;
import com.luiz.primeiroexemplo.repository.ProdutoRepository;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    /**
     * Retorna uma lista de produtos
     * @return Lista de produtos
     */
    public List<Produto> obterTodos(){
        return produtoRepository.findAll();
    }

    /**
     * Metodod que retorna o produto encontrado pelo seu id
     * @param id do produto que sera localizado 
     * @return retorna um produto caso tenha encontrado
     */
    public Optional<Produto> obterPorId(Integer id){
        return produtoRepository.findById(id);
    }

    /**
     * Metodo para adicionar produtos a lista
     * @param produto que será adicionando
     * @return Retorna o produto que foi adicionado na lista
     */
    public Produto adicionar(Produto produto){
        return produtoRepository.save(produto);
    }

    /**
     * Metodo para deletar um produto pelo id
     * @param id do produto a ser deletado
     */
    public void deletar(Integer id){
        produtoRepository.deleteById(id);
    }

    /**
     * metodo para atualizar um produto
     * @param produto Que será atualizado
     * @param id do produto
     * @returnretorna um produto apos atualizar a lista 
     */
    public Produto atualizar(Integer id, Produto produto){
        produto.setId(id);
        return produtoRepository.save(produto);
    }

}
