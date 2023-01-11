package com.luiz.primeiroexemplo.repository;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.luiz.primeiroexemplo.model.Produto;

@Repository
public class ProdutoRepository {
    
    private ArrayList<Produto> produtos = new ArrayList<Produto>();

    private Integer ultimoId = 0;

    /**
     * Retorna uma lista de produtos
     * @return Lista de produtos
     */
    public List<Produto> obterTodos(){
        return produtos;
    }

    /**
     * Metodod que retorna o produto encontrado pelo seu id
     * @param id do produto que sera localizado 
     * @return retorna um produto caso tenha encontrado
     */
    public Optional<Produto> obterPorId(Integer id){
        return produtos.stream().filter(produto -> produto.getId() == id).findFirst();
    }
    /**
     * Metodo para adicionar produtos a lista
     * @param produto que será adicionando
     * @return Retorna o produto que foi adicionado na lista
     */
    public Produto adicionar(Produto produto){
        ultimoId++;
        produto.setId(ultimoId);
        produtos.add(produto);
        return produto;
    }
    /**
     * Metodo para deletar um produto pelo id
     * @param id do produto a ser deletado
     */
    public  void deletar(Integer id){
        produtos.removeIf(produto -> produto.getId() == id);
    }
    /**
     * metodo para atualizar um produto
     * @param produto Que será atualizado
     * @returnretorna um produto apos atualizar a lista 
     */
    public Produto atualizar(Produto produto){
        //encontra  o produto na lista 
        Optional<Produto> produtoEncontrado = obterPorId(produto.getId());
        if(produtoEncontrado.isEmpty()){
            throw new InputMismatchException("Produto não encontrado");
        }
        //Remover o produto
        deletar(produto.getId());
        // depois adicionar o produto atualizando a lista
        produtos.add(produto);
        return produto;
    }
}
