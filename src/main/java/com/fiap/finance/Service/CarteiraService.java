package com.fiap.finance.Service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fiap.finance.Model.Acao;
import com.fiap.finance.Model.Carteira;
import com.fiap.finance.Repository.CarteiraRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CarteiraService {

    @Autowired
    private CarteiraRepository carteiraRepository;
 
    public Mono<Double> calcularRentabilidade(String carteiraNome) {
        return carteiraRepository.findByNome(carteiraNome)
                .map(carteira -> carteira.getAcoes().stream()
                        .mapToDouble(acao -> acao.getPreco())
                        .sum());
    }

    public Flux<Carteira> findAll() {
        return carteiraRepository.findAll();
    }
 
    public Mono<Carteira> save(String nomeCarteira, List<Acao> acoes) {
        var carteira = new Carteira(nomeCarteira, acoes);
        return carteiraRepository.save(carteira);
    }
}
