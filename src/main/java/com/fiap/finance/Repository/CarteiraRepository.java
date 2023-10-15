package com.fiap.finance.Repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.fiap.finance.Model.Carteira;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CarteiraRepository extends ReactiveMongoRepository<Carteira, String> {
    Mono<Carteira> findByNome(String nome);

    Flux<Carteira> findAll();

}
