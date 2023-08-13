package br.com.fiap.api.LivroLume.controllers;

import br.com.fiap.api.LivroLume.dto.UpdatePJDTO;
import br.com.fiap.api.LivroLume.entities.ClientePJ;
import br.com.fiap.api.LivroLume.entities.Livro;
import br.com.fiap.api.LivroLume.enums.Plano;
import br.com.fiap.api.LivroLume.services.ClientePJServices;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/cliente/pj")
public class    ClientePJController {

    private final ClientePJServices clientePJServices;

    @Autowired
    public ClientePJController( ClientePJServices clientePJServices) {
        this.clientePJServices = clientePJServices;
    }

    @ApiOperation(value = "Buscar todos os clientes PJ")
    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Clientes PJ encontrados com sucesso"),
            @ApiResponse(code = 500, message = "Erro interno no servidor")
    })
    public ResponseEntity<List<ClientePJ>> getAllClientes() {
        List<ClientePJ> clientes = clientePJServices.findAll();
        return ResponseEntity.ok(clientes);
    }

    @ApiOperation(value = "Buscar clientes PJ por plano maior ou igual ao especificado")
    @GetMapping("/plano/greater/{plano}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Clientes PJ encontrados com sucesso"),
            @ApiResponse(code = 400, message = "Requisição inválida"),
            @ApiResponse(code = 500, message = "Erro interno no servidor")
    })
    public ResponseEntity<List<ClientePJ>> getClientesByPlanoGreaterThanOrEqual(
            @ApiParam(value = "Plano mínimo", required = true) @PathVariable Plano plano) {
        List<ClientePJ> clientes = clientePJServices.findByPlanoGreaterThanEqual(plano);
        return ResponseEntity.ok(clientes);
    }

    @ApiOperation(value = "Buscar clientes PJ por plano menor ou igual ao especificado")
    @GetMapping("/plano/less/{plano}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Clientes PJ encontrados com sucesso"),
            @ApiResponse(code = 400, message = "Requisição inválida"),
            @ApiResponse(code = 500, message = "Erro interno no servidor")
    })
    public ResponseEntity<List<ClientePJ>> getClientesByPlanoLessThanOrEqual(
            @ApiParam(value = "Plano máximo", required = true) @PathVariable Plano plano) {
        List<ClientePJ> clientes = clientePJServices.findByPlanoLessThanEqual(plano);
        return ResponseEntity.ok(clientes);
    }

    @ApiOperation(value = "Buscar cliente PJ por ID")
    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Cliente PJ encontrado com sucesso"),
            @ApiResponse(code = 404, message = "Cliente PJ não encontrado"),
            @ApiResponse(code = 500, message = "Erro interno no servidor")
    })
    public ResponseEntity<ClientePJ> getClienteById(
            @ApiParam(value = "ID do cliente", required = true) @PathVariable long id) {
        ClientePJ cliente = clientePJServices.findById(id);
        return ResponseEntity.ok(cliente);
    }

    @ApiOperation(value = "Buscar cliente PJ por nome")
    @GetMapping("/name/{name}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Cliente PJ encontrado com sucesso"),
            @ApiResponse(code = 404, message = "Cliente PJ não encontrado"),
            @ApiResponse(code = 500, message = "Erro interno no servidor")
    })
    public ResponseEntity<ClientePJ> getClienteByName(
            @ApiParam(value = "Nome do cliente", required = true) @PathVariable String name) {
        ClientePJ cliente = clientePJServices.findByName(name);
        return ResponseEntity.ok(cliente);
    }

    @ApiOperation(value = "Buscar cliente PJ por CNPJ")
    @GetMapping("/cnpj/{cnpj}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Cliente PJ encontrado com sucesso"),
            @ApiResponse(code = 404, message = "Cliente PJ não encontrado"),
            @ApiResponse(code = 500, message = "Erro interno no servidor")
    })
    public ResponseEntity<ClientePJ> getClienteByCNPJ(
            @ApiParam(value = "CNPJ do cliente", required = true) @PathVariable String cnpj) {
        ClientePJ cliente = clientePJServices.findByCNPJ(cnpj);
        return ResponseEntity.ok(cliente);
    }

    @ApiOperation(value = "Buscar cliente PJ por Inscrição Estadual (IE)")
    @GetMapping("/inscricao/{ie}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Cliente PJ encontrado com sucesso"),
            @ApiResponse(code = 404, message = "Cliente PJ não encontrado"),
            @ApiResponse(code = 500, message = "Erro interno no servidor")
    })
    public ResponseEntity<ClientePJ> getClienteByIe(
            @ApiParam(value = "Inscrição Estadual do cliente", required = true) @PathVariable String ie) {
        ClientePJ cliente = clientePJServices.findByInscricao(ie);
        return ResponseEntity.ok(cliente);
    }

    @ApiOperation(value = "Registrar cliente PJ")
    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Cliente PJ registrado com sucesso"),
            @ApiResponse(code = 400, message = "Requisição inválida"),
            @ApiResponse(code = 500, message = "Erro interno no servidor")
    })
    public ResponseEntity<ClientePJ> registerCliente(
            @ApiParam(value = "Dados do cliente", required = true) @Valid @RequestBody ClientePJ cliente) {
        ClientePJ registeredCliente = clientePJServices.register(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredCliente);
    }

    @ApiOperation(value = "Atualizar cliente PJ")
    @Transactional
    @PutMapping("/update")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Cliente PJ atualizado com sucesso"),
            @ApiResponse(code = 400, message = "Requisição inválida"),
            @ApiResponse(code = 404, message = "Cliente PJ não encontrado"),
            @ApiResponse(code = 500, message = "Erro interno no servidor")
    })
    public ResponseEntity<ClientePJ> updateCliente(
            @ApiParam(value = "Dados de atualização do cliente", required = true) @Valid @RequestBody UpdatePJDTO data) {
        ClientePJ updatedCliente = clientePJServices.update(data);
        return ResponseEntity.ok(updatedCliente);
    }

    @ApiOperation(value = "Adicionar livro ao cliente PJ")
    @Transactional
    @PutMapping("/livros/add/{idLivro}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Livro adicionado com sucesso"),
            @ApiResponse(code = 400, message = "Requisição inválida"),
            @ApiResponse(code = 404, message = "Cliente PJ não encontrado"),
            @ApiResponse(code = 500, message = "Erro interno no servidor")
    })
    public ResponseEntity<Set<Livro>> adicionarLivro(
            @ApiParam(value = "ID do livro", required = true) @PathVariable Long idLivro,
            @ApiParam(value = "ID do cliente", required = true) @RequestBody Long idCliente) {
        return ResponseEntity.ok(clientePJServices.adicionar(idLivro,idCliente));
    }

    @ApiOperation(value = "Remover livro do cliente PJ")
    @Transactional
    @PutMapping("/livros/remove/{idLivro}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Livro removido com sucesso"),
            @ApiResponse(code = 400, message = "Requisição inválida"),
            @ApiResponse(code = 404, message = "Cliente PJ não encontrado"),
            @ApiResponse(code = 500, message = "Erro interno no servidor")
    })
    public ResponseEntity<Set<Livro>> removerLivro(
            @ApiParam(value = "ID do livro", required = true) @PathVariable Long idLivro,
            @ApiParam(value = "ID do cliente", required = true) @RequestBody Long idCliente) {
        return ResponseEntity.ok(clientePJServices.remover(idLivro,idCliente));
    }

    @ApiOperation(value = "Realizar upgrade de plano para o cliente PJ")
    @Transactional
    @PutMapping("/upgrade-plano/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Upgrade de plano realizado com sucesso"),
            @ApiResponse(code = 404, message = "Cliente PJ não encontrado"),
            @ApiResponse(code = 500, message = "Erro interno no servidor")
    })
    public ResponseEntity<ClientePJ> upgradeClientePlano(
            @ApiParam(value = "ID do cliente", required = true) @PathVariable long id) {
        ClientePJ upgradedCliente = clientePJServices.upgradePlano(id);
        return ResponseEntity.ok(upgradedCliente);
    }

    @ApiOperation(value = "Realizar downgrade de plano para o cliente PJ")
    @Transactional
    @PutMapping("/downgrade-plano/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Downgrade de plano realizado com sucesso"),
            @ApiResponse(code = 404, message = "Cliente PJ não encontrado"),
            @ApiResponse(code = 500, message = "Erro interno no servidor")
    })
    public ResponseEntity<ClientePJ> downgradeClientePlano(
            @ApiParam(value = "ID do cliente", required = true) @PathVariable long id) {
        ClientePJ downgradedCliente = clientePJServices.downgradePlano(id);
        return ResponseEntity.ok(downgradedCliente);
    }

    @ApiOperation(value = "Deletar cliente PJ por ID")
    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Cliente PJ deletado com sucesso"),
            @ApiResponse(code = 404, message = "Cliente PJ não encontrado"),
            @ApiResponse(code = 500, message = "Erro interno no servidor")
    })
    public ResponseEntity<Void> deleteClienteById(
            @ApiParam(value = "ID do cliente", required = true) @PathVariable long id) {
        clientePJServices.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
