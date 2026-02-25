/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ifpe.ifpe.Repositories;

import com.ifpe.ifpe.Entities.Endereco;

/**
 *
 * @author thayn
 */
public interface EnderecoRepository {

    Endereco buscarEnderecoPorCep(String cep);
}
