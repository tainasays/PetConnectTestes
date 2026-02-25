/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ifpe.ifpe.Repositories;

import com.ifpe.ifpe.Entities.Pet;

/**
 *
 * @author elaine
 */
public interface PetRepository {
    void salvar(Pet pet);
    int contarPetsPorTutor(String cpfTutor);
}
