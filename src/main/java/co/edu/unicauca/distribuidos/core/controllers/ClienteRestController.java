package co.edu.unicauca.distribuidos.core.controllers;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unicauca.distribuidos.core.models.Cliente;
import co.edu.unicauca.distribuidos.core.services.IClienteService;


@RestController
@RequestMapping("/api")
public class ClienteRestController {

	@Autowired
	private IClienteService clienteService;
	
	@GetMapping("/clientes")
	public List<Cliente> index() {
		return clienteService.findAll();
	}
		
	@GetMapping("/clientes/{id}")
	public Cliente show(@PathVariable Integer id) {
		Cliente objCliente = null;		
		objCliente = clienteService.findById(id);		
		return objCliente;
	}

	@GetMapping("clientes2/{name}/{age}") 
    public String getMessage(@PathVariable("name") String name, 
				@PathVariable("age") String edad) {        
        var msg = String.format("%s es %s años viejo", name, edad);
        System.out.println(msg);
        return msg;
    }

	@GetMapping("consultarClientes") 
    public String getMessageParametros(@RequestParam String nombres, 
	    @RequestParam String apellidos,
            @RequestParam("date")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fecha) {
        var msg = String.format("buscando un cliente por nombre: %s, apellidos: %s fecha %s", nombres, apellidos,fecha);
        System.out.println(msg);
        return msg;
    }
	
	
	@PostMapping("/clientes")
	public Cliente create(@RequestBody Cliente cliente) {	
		Cliente objCliente = null;
		objCliente =  clienteService.save(cliente);
		return objCliente;
	}
	

	@PutMapping("/clientes/{id}")
	public Cliente update(@RequestBody Cliente cliente, @PathVariable Integer id) {
		Cliente objCliente = null;
		Cliente clienteActual = clienteService.findById(id);
		if(clienteActual!=null)	
		{
			objCliente = clienteService.update(id,cliente);
		}
		return objCliente;
	}
	
 
	
	@DeleteMapping("/clientes/{id}")
	public Boolean delete(@PathVariable Integer id) {				
		Boolean bandera=false;
		Cliente clienteActual = clienteService.findById(id);
		if(clienteActual!=null)		
		{
			bandera = clienteService.delete(id);
		}
		return bandera;
		
	}

	@GetMapping("/clientes/listarCabeceras")
	public void listarCabeceras(@RequestHeader Map<String, String> headers) {
		System.out.println("cabeceras");
		headers.forEach((key, value) -> {
			System.out.println(String.format("Cabecera '%s' = %s", key, value));
		});
	}
			
}
