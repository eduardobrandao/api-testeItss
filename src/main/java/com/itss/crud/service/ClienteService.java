package com.itss.crud.service;

import com.itss.crud.core.enums.MensagemDeErroEnuns;
import com.itss.crud.core.exception.ClienteNaoEncontradoException;
import com.itss.crud.core.exception.EntidadeEmUsoException;
import com.itss.crud.core.mapper.ClienteMapper;
import com.itss.crud.dto.ClienteDTO;
import com.itss.crud.model.Cliente;
import com.itss.crud.repository.ClienteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private ClienteRepository clienteRepository;
    private ClienteMapper clienteMapper;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository, ClienteMapper clienteMapper) {
        this.clienteRepository = clienteRepository;
        this.clienteMapper = clienteMapper;
    }

    public List<ClienteDTO> getAllClienteDTO() {
        List<Cliente> clientes = clienteRepository.findAll();
        return clienteMapper.getAllClienteDTO(clientes);
    }

    public ClienteDTO create(ClienteDTO clienteDTO) {
        validarEmailRequest(clienteDTO.getEmail());
        Cliente cliente = clienteMapper.getCliente(clienteDTO);
        Cliente clienteSalvo = clienteRepository.save(cliente);
        return clienteMapper.getClienteDTO(clienteSalvo);
    }

    public ClienteDTO uptade(Long id, ClienteDTO clienteDTO) {
        Cliente cliente = clienteNaoCadastro(id);
        BeanUtils.copyProperties(clienteDTO, cliente, "id", "email");
        Cliente edit = clienteRepository.save(cliente);
        return clienteMapper.getClienteDTO(edit);
    }

    public ClienteDTO show(Long id) {
        clienteNaoCadastro(id);
        Cliente cliente = clienteRepository.findById(id).get();
        return clienteMapper.getClienteDTO(cliente);
    }

    public void destroy(Long id) {
        clienteNaoCadastro(id);
        clienteRepository.deleteById(id);
    }

    private void validarEmailRequest(String email) {
        Optional<Cliente> cliente = clienteRepository.findByEmail(email);
        if(cliente.isPresent()) {
            throw new EntidadeEmUsoException(
                    MensagemDeErroEnuns.MSG_ERRO_EMAIL_CADASTRADO.getMensagem()
            );
        }
    }

    private Cliente clienteNaoCadastro(Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if(!cliente.isPresent()) {
          throw new ClienteNaoEncontradoException(MensagemDeErroEnuns.MSG_ERRO_CODIGO_INVALIDO.getMensagem(), id);
        }
        return cliente.get();
    }



}
