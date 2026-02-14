package com.conde.gestaoprojetosarq.service;

import com.conde.gestaoprojetosarq.infrastructure.exceptions.ConflictException;
import com.conde.gestaoprojetosarq.infrastructure.exceptions.ResourceNotFoundException;
import com.conde.gestaoprojetosarq.model.Cliente;
import com.conde.gestaoprojetosarq.model.Projeto;
import com.conde.gestaoprojetosarq.model.converters.ClienteConverter;
import com.conde.gestaoprojetosarq.model.dto.ClienteDTO;
import com.conde.gestaoprojetosarq.model.dto.ProjetoDTO;
import com.conde.gestaoprojetosarq.repository.ClienteRepository;
import com.conde.gestaoprojetosarq.repository.ProjetoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final ProjetoRepository projetoRepository;
    private final ClienteConverter clienteConverter;

    public ClienteDTO salvarCliente(Cliente cliente) {
        emailExiste(cliente.getEmail());
        cpfExiste(cliente.getCpf());
        return clienteConverter.paraDTO(clienteRepository.save(cliente));
    }

    public void emailExiste(String email) {
        if(verificaEmailExistente(email)){
            throw new ConflictException("Email já existente: " + email);
        }
    }

    public void cpfExiste(String cpf) {
       if(verificaCpfExistente(cpf)){
           throw new ConflictException("CPF já existente: " + cpf);
       }
    }

    public ClienteDTO buscarClientePorCpf(String cpf) {
        Cliente cliente = clienteRepository.findByCpf(cpf)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado com esse cpf: " + cpf));

        return clienteConverter.paraDTO(cliente);
    }

    public List<ProjetoDTO> buscarProjetosPorCpfDoCliente(String cpf) {
        List<Projeto> projetos = projetoRepository.findByClienteCpf(cpf);
        if (projetos.isEmpty()) {
            throw new ResourceNotFoundException("Nenhum Projeto encontrado associado a esse Cliente");
        }

        return clienteConverter.paraListaProjetoDTO(projetos);
    }

    public List<ClienteDTO> listarTodos() {
        List<Cliente> clientes = clienteRepository.findAll();
        return clienteConverter.paraListaClienteDTO(clientes);

    }

    public ClienteDTO atualizarCliente(Long id, ClienteDTO dto) {
        Cliente clienteExistente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));

        atualizarCampos(clienteExistente, dto);

        Cliente clienteAtualizado = clienteRepository.save(clienteExistente);

        return clienteConverter.paraDTO(clienteAtualizado);
    }

    public void atualizarCampos(Cliente cliente, ClienteDTO dto){
        atualizarNome(cliente, dto);
        atualizarEmail(cliente, dto);
        atualizarCpf(cliente, dto);
        atualizarTelefoneContato(cliente, dto);
    }

    public void atualizarNome(Cliente cliente, ClienteDTO dto){
        if(dto.getNome() != null){
            cliente.setNome(dto.getNome());
        }
    }

    public void atualizarEmail(Cliente cliente, ClienteDTO dto){
        if(dto.getEmail() != null && !dto.getEmail().equals(cliente.getEmail())){
            verificaEmailExistente(dto.getEmail());
            cliente.setEmail(dto.getEmail());
        }
    }

    public void atualizarCpf(Cliente cliente, ClienteDTO dto){
        if(dto.getCpf() != null && !dto.getCpf().equals(cliente.getCpf())){
            verificaCpfExistente(dto.getCpf());
            cliente.setCpf(dto.getCpf());
        }
    }

    public void atualizarTelefoneContato(Cliente cliente, ClienteDTO dto){
        if(dto.getTelefoneContato() != null && !dto.getTelefoneContato().equals(cliente.getTelefoneContato())){
            cliente.setTelefoneContato(dto.getTelefoneContato());
        }
    }

    public int deletarCliente(Long id){
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));

        List<Projeto> projetos = projetoRepository.findByClienteCpf(cliente.getCpf());
        int quantidadeProjetos = projetos.size();

        if(!projetos.isEmpty()){
            System.out.println("Deletando " + projetos.size() + " projeto(s) do cliente "
                                + cliente.getNome());

            projetos.forEach(p -> projetoRepository.delete(p));
        }

        System.out.println("Cliente " + cliente.getNome() + " deletado com sucesso!");
        clienteRepository.delete(cliente);

        return quantidadeProjetos;
    }

    public boolean verificaEmailExistente(String email) {
        return clienteRepository.existsByEmail(email);
    }

    public boolean verificaCpfExistente(String cpf) {
        return clienteRepository.existsByCpf(cpf);
    }
}
