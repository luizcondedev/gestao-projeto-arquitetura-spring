package com.conde.gestaoprojetosarq.service;

import com.conde.gestaoprojetosarq.infrastructure.exceptions.ConflictException;
import com.conde.gestaoprojetosarq.model.Cliente;
import com.conde.gestaoprojetosarq.model.Projeto;
import com.conde.gestaoprojetosarq.model.dto.ClienteDTO;
import com.conde.gestaoprojetosarq.model.dto.ProjetoDTO;
import com.conde.gestaoprojetosarq.repository.ClienteRepository;
import com.conde.gestaoprojetosarq.repository.ProjetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProjetoRepository projetoRepository;

    public Cliente salvarCliente(Cliente cliente) {
        emailExiste(cliente.getEmail());
        cpfExiste(cliente.getCpf());
        return clienteRepository.save(cliente);
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
                .orElseThrow(() -> new ConflictException("Cliente não encontrado com esse cpf: " + cpf));

        ClienteDTO dto = new ClienteDTO();
        dto.setNome(cliente.getNome());
        dto.setEmail(cliente.getEmail());
        dto.setCpf(cliente.getCpf());
        dto.setTelefoneContato(cliente.getTelefoneContato());

        return dto;
    }

    public List<ProjetoDTO> buscarProjetosPorCpfDoCliente(String cpf) {
        List<Projeto> projetos = projetoRepository.findByClienteCpf(cpf);
        if (projetos.isEmpty()) {
            throw new ConflictException("Nenhum Projeto encontrado associado a esse Cliente");
        }

        return projetos.stream()
                .map(projeto -> {
                    ProjetoDTO dto = new ProjetoDTO();
                    dto.setNomeProjeto(projeto.getNomeProjeto());
                    dto.setEnderecoProjeto(projeto.getEnderecoProjeto());
                    dto.setFaseProjeto(projeto.getFaseProjeto());
                    dto.setOrcamento(projeto.getOrcamento());
                    dto.setNomeArquiteto(projeto.getArquiteto().getNome());
                    dto.setNomeCliente(projeto.getCliente().getNome());
                    return dto;
                }).toList();
    }

    public List<ClienteDTO> listarTodos() {
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes.stream()
                .map(cliente -> {
                    ClienteDTO dto = new ClienteDTO();
                    dto.setNome(cliente.getNome());
                    dto.setEmail(cliente.getEmail());
                    dto.setCpf(cliente.getCpf());
                    dto.setTelefoneContato(cliente.getTelefoneContato());
                    return dto;
                }).toList();
    }

    public ClienteDTO atualizarCliente(Long id, ClienteDTO dto) {
        Cliente clienteExistente = clienteRepository.findById(id)
                .orElseThrow(() -> new ConflictException("Cliente não encontrado"));

        if (dto.getNome() != null) {
            clienteExistente.setNome(dto.getNome());
        }

        if(dto.getEmail() != null && !dto.getEmail().equals(clienteExistente.getEmail())){
            if(verificaEmailExistente(dto.getEmail())){
                throw new ConflictException("Email já cadastrado em outro cliente: " + dto.getEmail());
            }

            clienteExistente.setEmail(dto.getEmail());
        }

        if(dto.getCpf() != null && !dto.getCpf().equals(clienteExistente.getCpf())){
            if(verificaCpfExistente(dto.getCpf())){
                throw new ConflictException("CPF já cadastrado em outro cliente: " + dto.getCpf());
            }

            clienteExistente.setCpf(dto.getCpf());
        }

        if(dto.getTelefoneContato() != null){
            clienteExistente.setTelefoneContato(dto.getTelefoneContato());
        }

        Cliente clienteAtualizado = clienteRepository.save(clienteExistente);

        return converterParaDTO(clienteAtualizado);

    }

    public void deletarCliente(Long id){
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ConflictException("Cliente não encontrado"));

        List<Projeto> projetos = projetoRepository.findByClienteCpf(cliente.getCpf());

        if(!projetos.isEmpty()){
            System.out.println("Deletando " + projetos.size() + " projeto(s) do cliente "
                                + cliente.getProjetos());

            projetos.forEach(p -> projetoRepository.delete(p));
        }

        System.out.println("Cliente " + cliente.getNome() + " deletado com sucesso!");
        clienteRepository.delete(cliente);
    }

    public boolean verificaEmailExistente(String email) {
        return clienteRepository.existsByEmail(email);
    }

    public boolean verificaCpfExistente(String cpf) {
        return clienteRepository.existsByCpf(cpf);
    }

    private ClienteDTO converterParaDTO(Cliente cliente){
        ClienteDTO dto = new ClienteDTO();
        dto.setNome(cliente.getNome());
        dto.setEmail(cliente.getEmail());
        dto.setCpf(cliente.getCpf());
        dto.setTelefoneContato(cliente.getTelefoneContato());
        return dto;
    }
}
