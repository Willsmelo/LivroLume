package br.com.fiap.api.LivroLume.controllers;

import br.com.fiap.api.LivroLume.dto.UpdatePFDTO;
import br.com.fiap.api.LivroLume.entities.ClientePF;
import br.com.fiap.api.LivroLume.entities.Livro;
import br.com.fiap.api.LivroLume.enums.Plano;
import br.com.fiap.api.LivroLume.services.ClientePFServices;
import io.swagger.annotations.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/cliente/pf")
@Api(tags = "ClientePF", description = "Operações relacionadas a clientes pessoa física")
public class ClientePFController {

    private final ClientePFServices clientePFServices;

    @Autowired
    public ClientePFController(ClientePFServices clientePFServices) {
        this.clientePFServices = clientePFServices;
    }

    @GetMapping
    @ApiOperation("Obter a lista de todos os clientes pessoa física")
    @ApiResponse(code = 200, message = "Lista de clientes pessoa física obtida com sucesso")
    public ResponseEntity<List<ClientePF>> getAllClientes() {
        List<ClientePF> clientes = clientePFServices.findAll();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/plano/greater/{plano}")
    @ApiOperation("Obter a lista de clientes pessoa física com plano maior ou igual ao fornecido")
    @ApiResponse(code = 200, message = "Lista de clientes pessoa física obtida com sucesso")
    public ResponseEntity<List<ClientePF>> getClientesByPlanoGreaterThanOrEqual(@ApiParam(value = "Plano mínimo desejado", required = true) @PathVariable Plano plano) {
        List<ClientePF> clientes = clientePFServices.findByPlanoisGreaterThanEqual(plano);
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/plano/less/{plano}")
    @ApiOperation("Obter a lista de clientes pessoa física com plano menor ou igual ao fornecido")
    @ApiResponse(code = 200, message = "Lista de clientes pessoa física obtida com sucesso")
    public ResponseEntity<List<ClientePF>> getClientesByPlanoLessThanOrEqual(@ApiParam(value = "Plano máximo desejado", required = true) @PathVariable Plano plano) {
        List<ClientePF> clientes = clientePFServices.findByPlanoLessThanEqual(plano);
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    @ApiOperation("Obter informações de um cliente pessoa física por ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Cliente pessoa física encontrado com sucesso"),
            @ApiResponse(code = 404, message = "Nao foi encontrado um cliente com esse id")
    })
    public ResponseEntity<ClientePF> getClienteById( @ApiParam(value = "ID do cliente pessoa física", required = true) @PathVariable long id) {
        ClientePF cliente = clientePFServices.findById(id);
        return ResponseEntity.ok(cliente);
    }

    @GetMapping("/name/{name}")
    @ApiOperation("Obter informações de um cliente pessoa física por nome")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Cliente pessoa física encontrado com sucesso"),
            @ApiResponse(code = 404, message = "Nao foi encontrado um cliente com esse nome")
    })
    public ResponseEntity<ClientePF> getClienteByName(@PathVariable String name) {
        ClientePF cliente = clientePFServices.findByName(name);
        return ResponseEntity.ok(cliente);
    }

    @GetMapping("/cpf/{cpf}")
    @ApiOperation("Obter informações de um cliente pessoa física por CPF")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Cliente pessoa física encontrado com sucesso"),
            @ApiResponse(code = 404, message = "Cliente pessoa física não encontrado")
    })
    public ResponseEntity<ClientePF> getClienteByCPF(
            @ApiParam(value = "CPF do cliente pessoa física", required = true) @PathVariable String cpf) {
        ClientePF cliente = clientePFServices.findByCPF(cpf);
        return ResponseEntity.ok(cliente);
    }

    @GetMapping("/rg/{rg}")
    @ApiOperation("Obter informações de um cliente pessoa física por RG")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Cliente pessoa física encontrado com sucesso"),
            @ApiResponse(code = 404, message = "Cliente pessoa física não encontrado")
    })
    public ResponseEntity<ClientePF> getClienteByRG(
            @ApiParam(value = "RG do cliente pessoa física", required = true) @PathVariable String rg) {
        ClientePF cliente = clientePFServices.findByRG(rg);
        return ResponseEntity.ok(cliente);
    }

    @PostMapping
    @ApiOperation("Registrar um novo cliente pessoa física")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Cliente pessoa física registrado com sucesso"),
            @ApiResponse(code = 400, message = "Requisição inválida")
    })
    public ResponseEntity<ClientePF> registerCliente(
            @ApiParam(value = "Detalhes do cliente pessoa física", required = true) @Valid @RequestBody ClientePF cliente) {
        ClientePF registeredCliente = clientePFServices.register(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredCliente);
    }

    @Transactional
    @PutMapping("/update")
    @ApiOperation("Atualizar os detalhes de um cliente pessoa física")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Cliente pessoa física atualizado com sucesso"),
            @ApiResponse(code = 400, message = "Requisição inválida"),
            @ApiResponse(code = 404, message = "Cliente pessoa física não encontrado")
    })
    public ResponseEntity<ClientePF> updateCliente(
            @ApiParam(value = "Dados de atualização do cliente pessoa física", required = true) @Valid @RequestBody UpdatePFDTO data) {
        ClientePF updatedCliente = clientePFServices.update(data);
        return ResponseEntity.ok(updatedCliente);
    }

    @Transactional
    @PutMapping("/upgrade-plano/{id}")
    @ApiOperation("Realizar o upgrade do plano de um cliente pessoa física")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Plano do cliente pessoa física atualizado com sucesso"),
            @ApiResponse(code = 400, message = "Requisição inválida"),
            @ApiResponse(code = 404, message = "Cliente pessoa física não encontrado")
    })
    public ResponseEntity<ClientePF> upgradeClientePlano(
            @ApiParam(value = "ID do cliente pessoa física", required = true) @PathVariable long id) {
        ClientePF upgradedCliente = clientePFServices.upgradePlano(id);
        return ResponseEntity.ok(upgradedCliente);
    }

    @Transactional
    @PutMapping("/livros/add/{idLivro}")
    @ApiOperation("Adicionar um livro à lista de livros de um cliente pessoa física")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Livro adicionado com sucesso"),
            @ApiResponse(code = 400, message = "Requisição inválida"),
            @ApiResponse(code = 404, message = "Cliente pessoa física ou livro não encontrado")
    })
    public ResponseEntity<Set<Livro>> adicionarLivro(
            @ApiParam(value = "ID do livro a ser adicionado", required = true) @PathVariable Long idLivro,
            @ApiParam(value = "ID do cliente pessoa física", required = true) @RequestBody Long idCliente) {
        return ResponseEntity.ok(clientePFServices.adicionar(idLivro,idCliente));
    }

    @Transactional
    @PutMapping("/livros/remove/{idLivro}")
    @ApiOperation("Remover um livro da lista de livros de um cliente pessoa física")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Livro removido com sucesso"),
            @ApiResponse(code = 400, message = "Requisição inválida"),
            @ApiResponse(code = 404, message = "Cliente pessoa física ou livro não encontrado")
    })
    public ResponseEntity<Set<Livro>> removerLivro(
            @ApiParam(value = "ID do livro a ser removido", required = true) @PathVariable Long idLivro,
            @ApiParam(value = "ID do cliente pessoa física", required = true) @RequestBody Long idCliente) {
        return ResponseEntity.ok(clientePFServices.remover(idLivro,idCliente));
    }

    @Transactional
    @PutMapping("/downgrade-plano/{id}")
    @ApiOperation("Realizar o downgrade do plano de um cliente pessoa física")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Plano do cliente pessoa física rebaixado com sucesso"),
            @ApiResponse(code = 400, message = "Requisição inválida"),
            @ApiResponse(code = 404, message = "Cliente pessoa física não encontrado")
    })
    public ResponseEntity<ClientePF> downgradeClientePlano(
            @ApiParam(value = "ID do cliente pessoa física", required = true) @PathVariable long id) {
        ClientePF downgradedCliente = clientePFServices.downgradePlano(id);
        return ResponseEntity.ok(downgradedCliente);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Excluir um cliente pessoa física por ID")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Cliente pessoa física excluído com sucesso"),
            @ApiResponse(code = 404, message = "Cliente pessoa física não encontrado")
    })
    public ResponseEntity<Void> deleteClienteById(
            @ApiParam(value = "ID do cliente pessoa física a ser excluído", required = true) @PathVariable long id) {
        clientePFServices.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
