package org.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.CarroDtoInput;
import org.example.dto.CarroDtoOutput;
import org.example.service.CarroService;
import spark.Spark;

public class CarroController {
    private final CarroService carroService = new CarroService();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public CarroController() {
    }

    //sem parâmetro e sem retorno: OK
    public void respostasRequisicoes() {
        //busco a lista de todos os carros
        Spark.get("/carros", (request, response) -> {
            response.type("application/json");
            response.status(200);
            return this.objectMapper.writeValueAsString(this.carroService.listar());
        });
        //busco um carro específico
        Spark.get("/carros/:id", (req, res) -> {
            int id = Integer.parseInt(req.params(":id"));
            res.type("application/json");
            CarroDtoOutput carro = this.carroService.buscar(id);
            if (carro != null) {
                res.status(200);
                return this.objectMapper.writeValueAsString(carro);
            } else {
                res.status(404);
                return "Erro: Carro não encontrado, tente novamente!";
            }
        });
        Spark.delete("/carros/:id", (req, res) -> {
            int id = Integer.parseInt(req.params(":id"));
            this.carroService.excluir(id);
            res.status(204);
            return "Carro excluído com sucesso!";
        });
        Spark.post("/carros", (request, response) -> {
            CarroDtoInput carroInput = (CarroDtoInput)this.objectMapper.readValue(request.body(), CarroDtoInput.class);
            this.carroService.inserir(carroInput);
            response.status(201);
            return "Carro inserido com sucesso!";
        });
        Spark.put("/carros/:id", (req, res) -> {
            int id = Integer.parseInt(req.params(":id"));
            CarroDtoInput carroInput = (CarroDtoInput)this.objectMapper.readValue(req.body(), CarroDtoInput.class);
            this.carroService.alterar(carroInput, id);
            res.status(200);
            return "Carro alterado  com sucesso!";
        });

    }
}
