package org.example.service;

import org.example.dto.CarroDtoInput;
import org.example.dto.CarroDtoOutput;
import org.example.model.Carro;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class CarroService {
    private final List<Carro> listaCarros = new ArrayList<>();
    private final ModelMapper modelMapper = new ModelMapper();

    public List<CarroDtoOutput> listar() {
        List<CarroDtoOutput> listaDto = new ArrayList<>();
        for (Carro carro : listaCarros) {
            listaDto.add(modelMapper.map(carro, CarroDtoOutput.class));
        }
        return listaDto;
    }

    public void inserir(CarroDtoInput carroDtoInput) {
        Carro carro = modelMapper.map(carroDtoInput, Carro.class);
        // pego o tamanho atual da lista de carros (listaCarros.size()) e adiciono 1 para setar o id
        carro.setId(listaCarros.size() + 1);
        listaCarros.add(carro);
    }

    public void alterar(CarroDtoInput carroDtoInput, int id) {
        Carro carroAlterado = modelMapper.map(carroDtoInput, Carro.class);
        carroAlterado.setId(id);

        for (int i = 0; i < listaCarros.size(); i++) {
            Carro carroExistente = listaCarros.get(i);
            if (carroExistente.getId() == id) {
                listaCarros.set(i, carroAlterado); // Substitui o carro existente
                return; // Sai do método após a alteração
            } else {
                throw new IllegalArgumentException("Carro não encontrado.");
            }
        }
    }

    public CarroDtoOutput buscar(int id) {
        for (Carro carro : listaCarros) {
            if (carro.getId() == id) {
                return modelMapper.map(carro, CarroDtoOutput.class);
            }
        }
        return null;
    }

    //sem retorno nesse método
    public void excluir(int id) {
        listaCarros.removeIf(carro -> carro.getId() == id);
    }
}
