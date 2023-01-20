package com.luiz.primeiroexemplo.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luiz.primeiroexemplo.model.Produto;
import com.luiz.primeiroexemplo.model.exception.ResourceNotFoundException;
import com.luiz.primeiroexemplo.repository.ProdutoRepository;
import com.luiz.primeiroexemplo.shared.ProdutoDTO;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    /**
     * Retorna uma lista de produtos
     * @return Lista de produtos
     */
    public List<ProdutoDTO> obterTodos(){
        List<Produto> produtos = produtoRepository.findAll();
        return produtos.stream()
        .map(produto -> new ModelMapper().map(produtos, ProdutoDTO.class))
        .collect(Collectors.toList());
    }

    /**
     * Metodod que retorna o produto encontrado pelo seu id
     * @param id do produto que sera localizado 
     * @return retorna um produto caso tenha encontrado
     */
    public Optional<ProdutoDTO> obterPorId(Integer id){
        Optional<Produto> produto = produtoRepository.findById(id);

        if(produto.isEmpty()){
            throw new ResourceNotFoundException("Produto com o id: " + id + "não encontrado");
        }
        ProdutoDTO dto = new ModelMapper().map(produto.get(), ProdutoDTO.class);
        return Optional.of(dto);
    }

    /**
     * Metodo para adicionar produtos a lista
     * @param produto que será adicionando
     * @return Retorna o produto que foi adicionado na lista
     */
    public ProdutoDTO adicionar(ProdutoDTO produtoDto){

        produtoDto.setId(null);
        ModelMapper mapper = new ModelMapper();
        Produto produto = mapper.map(produtoDto, Produto.class);
        produto = produtoRepository.save(produto);
        produtoDto.setId(produto.getId());
        return produtoDto;
    }

    /**
     * Metodo para deletar um produto pelo id
     * @param id do produto a ser deletado
     */
    public void deletar(Integer id){
        Optional<Produto> produto = produtoRepository.findById(id);

        if(produto.isEmpty()){
            throw new ResourceNotFoundException("Nap foi possivel deletar o produto com esse id: "+id+". Produto não existente.");
        }

        produtoRepository.deleteById(id);
    }

    /**
     * metodo para atualizar um produto
     * @param produto Que será atualizado
     * @param id do produto
     * @returnretorna um produto apos atualizar a lista 
     */
    public ProdutoDTO atualizar(Integer id, ProdutoDTO produtoDTO){
        produtoDTO.setId(id);

        ModelMapper mapper = new ModelMapper();
        
        Produto produto  = mapper.map(produtoDTO, Produto.class);

        produtoRepository.save(produto);

        return produtoDTO;
    }

}
