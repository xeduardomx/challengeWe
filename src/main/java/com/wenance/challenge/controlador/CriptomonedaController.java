package com.wenance.challenge.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.wenance.challenge.modelo.CriptoMoneda;
import com.wenance.challenge.servicio.CriptoMonedaService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class CriptomonedaController {

    @Autowired
    private CriptoMonedaService criptoMonedaService;

    @PostMapping("/criptomoneda")
    @ResponseStatus(code = HttpStatus.CREATED)
    private Mono<CriptoMoneda> save(@RequestBody CriptoMoneda criptoMoneda) {
    	return this.criptoMonedaService.save(criptoMoneda);
    }

    @DeleteMapping("/criptomoneda/{id}")
    private Mono<ResponseEntity<CriptoMoneda>> delete(@PathVariable("id") String id) {
        return this.criptoMonedaService.delete(id)
                .flatMap(criptoMoneda -> Mono.just(ResponseEntity.ok(criptoMoneda)))
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    
    }

    @PutMapping("/criptomoneda/{id}")
    private Mono<ResponseEntity<CriptoMoneda>> update(@PathVariable("id") String id, @RequestBody CriptoMoneda criptoMoneda) {
        return this.criptoMonedaService.update(id, criptoMoneda)
                .flatMap(criptoMoneda1 -> Mono.just(ResponseEntity.ok(criptoMoneda1)))
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    
    }
 
    @GetMapping("/criptomoneda/fechaReg/{fechaReg}")
    private Mono<CriptoMoneda> findAllByFechaReg(@PathVariable("fechaReg") String fechaReg) {
    	return this.criptoMonedaService.findByFechaReg(fechaReg);
    }
    
    //Tambien se puede hacer un DTO que tenga ambos campos de fecha.
    //Se hace obtiene el parametro con bodyRequest DtoConsulta (que tiene ambos campos)
    //De esa forma se podria llamar con el metodo POST, y se envia un Json con ambos valores.
    //Por temas de practicidad lo hice con un get, pero esta claro que seria una mejor opcion un DTO.
    @GetMapping("/criptomoneda/fechaReg/{fechaReg1}/{fechaReg2}")
    private Flux<CriptoMoneda> findAllBetweenFechaReg(@PathVariable("fechaReg1") String fechaReg1,@PathVariable("fechaReg2") String fechaReg2) {
    	return this.criptoMonedaService.findBetweenFechaReg(fechaReg1,fechaReg2);
    }
    
    //Tambien se puede hacer un DTO que tenga ambos campos de fecha.
    //Se hace obtiene el parametro con bodyRequest DtoConsulta (que tiene ambos campos)
    //De esa forma se podria llamar con el metodo POST, y se envia un Json con ambos valores.
    //Por temas de practicidad lo hice con un get, pero esta claro que seria una mejor opcion un DTO.
    @GetMapping("/criptomoneda/calcular/{fechaReg1}/{fechaReg2}")
    private Flux<CriptoMoneda> calculos(@PathVariable("fechaReg1") String fechaReg1,@PathVariable("fechaReg2") String fechaReg2) {
    	return this.criptoMonedaService.calcular(fechaReg1,fechaReg2);
    }
    
    @GetMapping("/criptomoneda/curr1/{curr1}")
    private Flux<CriptoMoneda> findAllByCurr1(@PathVariable("curr1") String curr1) {
        return this.criptoMonedaService.findByCurr1(curr1);
    }

    @GetMapping(value = "/criptomoneda")
    private Flux<CriptoMoneda> findAll() {
        return this.criptoMonedaService.findAll();
    }

}
