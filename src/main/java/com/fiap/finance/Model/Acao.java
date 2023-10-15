package com.fiap.finance.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "acoes")
public class Acao {

    @Id
    private String id;
    private String simbolo;
    private double preco; 

    public Acao(String simbolo, double preco) {
        this.simbolo = simbolo;
        this.preco = preco;
    }    

     public double getPreco() {
        return preco;
    }
}
