
import Interfaces.CorreiosInterface;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
import com.ifpe.ifpe.Entities.Endereco;
import com.ifpe.ifpe.Services.EnderecoService;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 *
 * @author Thayna
 * Cenário: Realizar cadastro de usuário
 * Ids caso de teste: T_5 e T_6
 * T_5: Preencher automaticamento os campos de endereço a partir das informações
 * existentes nos Correios
 * T_6: Cadastrar usuário informando CEP inválido
 * Número total de casos testes: 2.
 */


@ExtendWith(MockitoExtension.class)
public class EnderecoThaynaTest {

   @InjectMocks
    private EnderecoService enderecoService;

    @Mock
    private CorreiosInterface correiosInterface;

    @Test
    void T5_devePreencherEnderecoAutomaticamenteAoInformarCepValido() {

        Endereco enderecoMock = new Endereco(
                "50740340",
                "Rua Poloni",
                "Várzea",
                "Recife",
                "PE"
        );

        when(correiosInterface.buscarEnderecoPorCep("50740340"))
                .thenReturn(enderecoMock);

        Endereco enderecoPreenchido = enderecoService.buscarEnderecoPorCep("50740340");

        assertNotNull(enderecoPreenchido);
        assertEquals("50740340", enderecoPreenchido.getCep());
        assertEquals("Rua Poloni", enderecoPreenchido.getLogradouro());
        assertEquals("Várzea", enderecoPreenchido.getBairro());
        assertEquals("Recife", enderecoPreenchido.getCidade());
        assertEquals("PE", enderecoPreenchido.getEstado());

        verify(correiosInterface, times(1)).buscarEnderecoPorCep("50740340");
    }

    @Test
    void T6_naoDeveBuscarEnderecoQuandoCepForInvalido() {

        String cepInvalido = "123";

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> enderecoService.buscarEnderecoPorCep(cepInvalido));

        assertTrue(exception.getMessage().contains("CEP inválido"));

        verify(correiosInterface, never()).buscarEnderecoPorCep(anyString());
    }
}