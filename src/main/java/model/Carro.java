package model;

import lombok.Data;

@Data
public class Carro {
    private String placa;
    private String marca;
    private String modelo;
    private int ano;
    private String cor;
}