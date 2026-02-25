/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ifpe.ifpe.Services;

import com.ifpe.ifpe.Entities.Pet;
import com.ifpe.ifpe.Repositories.PetRepository;

/**
 *
 * @author elaine
 */
public class PetService {

    private final PetRepository repository;

    public PetService(PetRepository repository) {
        this.repository = repository;
    }

    public void cadastrarPet(Pet pet) {
        if (pet == null) {
            throw new IllegalArgumentException("Pet não pode ser nulo");
        }
        
        // Validações de campos obrigatórios (E_2 a E_9)
        if (isNullOrEmpty(pet.getNome())) throw new IllegalArgumentException("O campo Nome é obrigatório");
        if (isNullOrEmpty(pet.getTipo())) throw new IllegalArgumentException("O campo Tipo é obrigatório");
        if (isNullOrEmpty(pet.getSexo())) throw new IllegalArgumentException("O campo Sexo é obrigatório");
        if (isNullOrEmpty(pet.getRaca())) throw new IllegalArgumentException("O campo Raça é obrigatório");
        if (isNullOrEmpty(pet.getIdade())) throw new IllegalArgumentException("O campo Idade é obrigatório");
        if (isNullOrEmpty(pet.getPeso())) throw new IllegalArgumentException("O campo Peso é obrigatório");
        if (isNullOrEmpty(pet.getSaude())) throw new IllegalArgumentException("O campo Saúde é obrigatório");
        if (isNullOrEmpty(pet.getFoto())) throw new IllegalArgumentException("O campo Foto é obrigatório");

        // Validação de Tipo (E_11) - Aceitando apenas Cachorro ou Gato como exemplo
        if (!pet.getTipo().equalsIgnoreCase("Cachorro") && !pet.getTipo().equalsIgnoreCase("Gato")) {
            throw new IllegalArgumentException("O campo Tipo é inválido");
        }

        // Validação de Limite de Pets por Tutor (E_10)
        if (pet.getTutor() != null && pet.getTutor().getCpf() != null) {
            int totalPets = repository.contarPetsPorTutor(pet.getTutor().getCpf());
            if (totalPets >= 30) {
                throw new IllegalArgumentException("Pet não cadastrado. Limite máximo atingido");
            }
        }

        // Salvar (E_1 e E_12)
        try {
            repository.salvar(pet);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar. Tente novamente.");
        }
    }

    private boolean isNullOrEmpty(String s) {
        return s == null || s.trim().isEmpty();
    }
}