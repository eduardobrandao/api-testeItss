package com.itss.crud.core.mapper;

import com.itss.crud.dto.ClienteDTO;
import com.itss.crud.model.Cliente;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClienteMapper implements IClienteMapper{

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Cliente getCliente(ClienteDTO clienteDTO) {
        return modelMapper.map(clienteDTO, Cliente.class);
    }

    @Override
    public ClienteDTO getClienteDTO(Cliente cliente) {

        return modelMapper.map(cliente, ClienteDTO.class);
    }

    @Override
    public List<ClienteDTO> getAllClienteDTO(List<Cliente> cliente) {
        return cliente
                .stream()
                .map(c -> getClienteDTO(c))
                .collect(Collectors.toList());
    }
}
