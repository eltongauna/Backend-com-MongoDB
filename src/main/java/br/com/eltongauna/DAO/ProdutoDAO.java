package br.com.eltongauna.DAO;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.eltongauna.model.Produto;

@Repository
public interface ProdutoDAO extends MongoRepository<Produto, String>{
	
}
