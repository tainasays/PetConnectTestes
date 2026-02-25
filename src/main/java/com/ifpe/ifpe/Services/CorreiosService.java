/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ifpe.ifpe.Services;

import Interfaces.CorreiosInterface;
import Utils.ViaCepResponse;
import com.ifpe.ifpe.Entities.Endereco;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author thayn
 */
public class CorreiosService implements CorreiosInterface {

    private final RestTemplate restTemplate = new RestTemplate();

    public Endereco buscarEnderecoPorCep(String cep) {

        String url = "https://viacep.com.br/ws/" + cep + "/json/";

        ViaCepResponse response =
                restTemplate.getForObject(url, ViaCepResponse.class);

        if (response == null || response.getCep() == null) {
            return null;
        }

        return new Endereco(
                response.getCep(),
                response.getLogradouro(),
                response.getBairro(),
                response.getLocalidade(),
                response.getUf()
        );
    }
}