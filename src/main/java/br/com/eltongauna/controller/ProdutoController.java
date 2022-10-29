package br.com.eltongauna.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.eltongauna.DAO.ProdutoDAO;
import br.com.eltongauna.model.Produto;
import lombok.AllArgsConstructor;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping
@AllArgsConstructor
public class ProdutoController {
	
	@Autowired
	private ProdutoDAO dao;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping("/produto")
	public List<Produto> listarTodos(){
		return dao.findAll();
	}
	@GetMapping("/produto/{id}")
	public Produto buscaPorId(@PathVariable String id) {
		return dao.findById(id).get();
	}
	
	@PostMapping("/produto")
	@ResponseStatus(HttpStatus.CREATED)
	public Produto salvar(@RequestBody Produto produto) {
		return dao.save(produto);
	}
	
	@DeleteMapping("/produto/{id}")
	public void deletar(@PathVariable String id) {
		dao.deleteById(id);
	}
	
	@PutMapping("/produto/{id}")
    public void atualizarCidadao(@PathVariable("id") String id, @RequestBody Produto produto){
        dao.findById(id)
                .map(produtobase -> {
                    modelMapper.map(produto, produtobase);
                    salvar(produtobase);
                     return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto nao encontrado."));
    }

}
