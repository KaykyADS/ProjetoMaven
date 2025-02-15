package model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Carro {
    private String placa;
    private String marca;
    private String modelo;
    private int ano;
    private String cor;
}