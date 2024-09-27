import org.example.dto.CarroDtoInput;
import org.example.dto.CarroDtoOutput;
import org.example.service.CarroService;
import org.junit.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ServiceTest {

    private CarroService carroService = new CarroService();

    @Test
    public void testaInserirCarro() {
        CarroDtoInput carroDtoInput = new CarroDtoInput();
        carroDtoInput.setModelo("Civic");
        carroDtoInput.setPlaca("TES7512");
        carroDtoInput.setChassi("8YdM0NKfsEkmZ3714");
        carroService.inserir(carroDtoInput);
        List<CarroDtoOutput> carrosListados = carroService.listar();
        assertEquals(1, carrosListados.size(), "Após inserir, a lista deve conter um carro.");
        CarroDtoOutput carroInserido = carrosListados.get(0);
        assertEquals("Civic", carroInserido.getModelo());
        assertEquals("TES7512", carroInserido.getPlaca());
    }
}
