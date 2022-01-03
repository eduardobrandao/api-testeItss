package com.itss.crud.core.mapper;

import com.itss.crud.dto.ClienteDTO;
import com.itss.crud.model.Cliente;

import java.util.List;

public interface IClienteMapper {

     Cliente getCliente(ClienteDTO clienteDTO);
     ClienteDTO getClienteDTO(Cliente cliente);
     List<ClienteDTO> getAllClienteDTO(List<Cliente> cliente);
}
