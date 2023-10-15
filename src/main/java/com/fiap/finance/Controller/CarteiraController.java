package com.fiap.finance.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fiap.finance.Model.Carteira;
import com.fiap.finance.Request.CarteiraComAcoesRequest;
import com.fiap.finance.Service.CarteiraService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/carteiras")
public class CarteiraController {

    @Autowired
    private CarteiraService carteiraService;

    @GetMapping
    public Flux<Carteira> getAll() {
        return carteiraService.findAll();
    }

    @PostMapping
    public Mono<Carteira> save(@RequestBody CarteiraComAcoesRequest carteira) {
        return carteiraService.save(carteira.nomeCarteira, carteira.acoes);
    }

    @GetMapping("/{carteiraNome}/rentabilidade")
    public Mono<ResponseEntity<Double>> calcularRentabilidade(@PathVariable String carteiraNome) {
        return carteiraService.calcularRentabilidade(carteiraNome)
                .map(totalAmount -> ResponseEntity.ok(totalAmount))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
   
}
