package com.itss.crud.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO {

    private Long id;
    @NotBlank
    private String nome;
    @NotBlank
    private String cpf;
    private LocalDate dataNasimento;
    @NotBlank
    private String celular;
    private String telefone;
    private String email;
    private String cep;
    private String rua;
    private int numero;
    private String bairro;
    private String cidade;
    private String estado;
}
