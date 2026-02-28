/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Jorge;

import Interfaces.CorreiosInterface;
import com.ifpe.ifpe.Services.EnderecoService;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.anyString;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 *
 * @author jorge
 */
@ExtendWith(MockitoExtension.class)
public class EnderecoJorgeTest {
    @InjectMocks
    private EnderecoService enderecoService;
    
    @Mock
    private CorreiosInterface correiosInterface;
    
    @Test
    void J_15_naoDeveEditarComCepInvalido() {
        String cepInvalido = "12345678921";
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> enderecoService.buscarEnderecoPorCep(cepInvalido));

        assertTrue(exception.getMessage().contains("CEP inválido"));

        verify(correiosInterface, never()).buscarEnderecoPorCep(anyString());
    }
    
}
