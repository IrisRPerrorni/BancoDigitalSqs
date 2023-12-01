package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransferenciaModel {
    private double valor;
    private String numeroConta;
    private String agenciaConta;
    private String origem;
    private String tipoPagamento;

}
