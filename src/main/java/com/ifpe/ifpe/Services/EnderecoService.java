/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ifpe.ifpe.Services;

import Interfaces.CorreiosInterface;
import com.ifpe.ifpe.Entities.Endereco;

/**
 *
 * @author thayn
 */


public class EnderecoService {

    private final CorreiosInterface correiosInterface;

    public EnderecoService(CorreiosInterface correiosInterface) {
        this.correiosInterface = correiosInterface;
    }

    public Endereco buscarEnderecoPorCep(String cep) {

        if (cep == null || !cep.matches("\\d{8}")) {
            throw new IllegalArgumentException("CEP inválido");
        }

        Endereco endereco = correiosInterface.buscarEnderecoPorCep(cep);

        if (endereco == null) {
            throw new IllegalArgumentException("CEP não encontrado");
        }

        return endereco;
    }
}
