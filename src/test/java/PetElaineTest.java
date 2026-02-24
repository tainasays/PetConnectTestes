
import com.ifpe.ifpe.Entities.Pet;
import com.ifpe.ifpe.Entities.Usuario;
import com.ifpe.ifpe.Repositories.PetRepository;
import com.ifpe.ifpe.Services.PetService;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author elaine
 */
@ExtendWith(MockitoExtension.class)
public class PetElaineTest {

    @Mock
    private PetRepository petRepository;

    @InjectMocks
    private PetService petService;

    private Pet petValido;
    private Usuario tutorAutenticado;

    @BeforeEach
    void setUp() {
        // Criando um tutor mockado para os testes
        tutorAutenticado = new Usuario("Dono do Pet", "email@email.com", "12345678901", "Senha123@", "01-01-1990", "81999999999", "Rua A", "Bairro", "Cidade", "10", "PE", "50000000", null);
        
        // Base para o E_1
        petValido = new Pet("Thor", "Cachorro", "Masculino", "Labrador", "5", "10", "Vacinas em dia; castrado", "imagem1.JPG", tutorAutenticado);
    }

    @Test
    void E1_deveCadastrarPetComSucesso() {
        when(petRepository.contarPetsPorTutor(tutorAutenticado.getCpf())).thenReturn(0);
        
        petService.cadastrarPet(petValido);
        
        verify(petRepository, times(1)).salvar(petValido);
    }

    @Test
    void E2_naoDeveCadastrarPetComNomeVazio() {
        petValido.setNome(""); // Simulando campo vazio
        
        Exception ex = assertThrows(IllegalArgumentException.class, () -> petService.cadastrarPet(petValido));
        
        assertTrue(ex.getMessage().contains("O campo Nome é obrigatório"));
        verify(petRepository, never()).salvar(any());
    }

    @Test
    void E3_naoDeveCadastrarPetComTipoVazio() {
        petValido.setTipo("");

        Exception ex = assertThrows(IllegalArgumentException.class, () -> petService.cadastrarPet(petValido));

        assertTrue(ex.getMessage().contains("O campo Tipo é obrigatório"));
        verify(petRepository, never()).salvar(any());
    }

    @Test
    void E4_naoDeveCadastrarPetComSexoVazio() {
        petValido.setSexo("");

        Exception ex = assertThrows(IllegalArgumentException.class, () -> petService.cadastrarPet(petValido));

        assertTrue(ex.getMessage().contains("O campo Sexo é obrigatório"));
        verify(petRepository, never()).salvar(any());
    }

    @Test
    void E5_naoDeveCadastrarPetComRacaVazia() {
        petValido.setRaca("");

        Exception ex = assertThrows(IllegalArgumentException.class, () -> petService.cadastrarPet(petValido));

        assertTrue(ex.getMessage().contains("O campo Raça é obrigatório"));
        verify(petRepository, never()).salvar(any());
    }

    @Test
    void E6_naoDeveCadastrarPetComIdadeVazia() {
        petValido.setIdade("");

        Exception ex = assertThrows(IllegalArgumentException.class, () -> petService.cadastrarPet(petValido));

        assertTrue(ex.getMessage().contains("O campo Idade é obrigatório"));
        verify(petRepository, never()).salvar(any());
    }

    @Test
    void E7_naoDeveCadastrarPetComPesoVazio() {
        petValido.setPeso("");

        Exception ex = assertThrows(IllegalArgumentException.class, () -> petService.cadastrarPet(petValido));

        assertTrue(ex.getMessage().contains("O campo Peso é obrigatório"));
        verify(petRepository, never()).salvar(any());
    }

    @Test
    void E8_naoDeveCadastrarPetComSaudeVazia() {
        petValido.setSaude("");

        Exception ex = assertThrows(IllegalArgumentException.class, () -> petService.cadastrarPet(petValido));

        assertTrue(ex.getMessage().contains("O campo Saúde é obrigatório"));
        verify(petRepository, never()).salvar(any());
    }

    @Test
    void E9_naoDeveCadastrarPetComFotoVazia() {
        petValido.setFoto("");

        Exception ex = assertThrows(IllegalArgumentException.class, () -> petService.cadastrarPet(petValido));

        assertTrue(ex.getMessage().contains("O campo Foto é obrigatório"));
        verify(petRepository, never()).salvar(any());
    }
    @Test
    void E10_naoDeveCadastrarPetSeLimiteMaximoAtingido() {
        // Simulando que o tutor já tem 30 pets
        when(petRepository.contarPetsPorTutor(tutorAutenticado.getCpf())).thenReturn(30);
        
        Exception ex = assertThrows(IllegalArgumentException.class, () -> petService.cadastrarPet(petValido));
        
        assertTrue(ex.getMessage().contains("Pet não cadastrado. Limite máximo atingido"));
        verify(petRepository, never()).salvar(any());
    }

    @Test
    void E11_naoDeveCadastrarPetComTipoInvalido() {
        petValido.setTipo("Dinossauro");
        
        Exception ex = assertThrows(IllegalArgumentException.class, () -> petService.cadastrarPet(petValido));
        
        assertTrue(ex.getMessage().contains("O campo Tipo é inválido"));
        verify(petRepository, never()).salvar(any());
    }

    @Test
    void E12_deveRetornarErroDoSistemaAoFalharNoSalvamento() {
        when(petRepository.contarPetsPorTutor(tutorAutenticado.getCpf())).thenReturn(0);
        // Simulando um erro do banco de dados na hora de salvar
        doThrow(new RuntimeException("Database error")).when(petRepository).salvar(any());
        
        Exception ex = assertThrows(RuntimeException.class, () -> petService.cadastrarPet(petValido));
        
        assertTrue(ex.getMessage().contains("Erro ao salvar. Tente novamente."));
    }
}