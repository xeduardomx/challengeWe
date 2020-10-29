package com.wenance.challenge.servicio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Service;

import com.wenance.challenge.modelo.CriptoMoneda;
import com.wenance.challenge.repositorio.CriptoMonedaRepository;

import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CriptoMonedaServiceImpl implements CriptoMonedaService  {

    @Autowired
    private CriptoMonedaRepository criptoMonedaRepository;
    
    @Override
    public Mono<CriptoMoneda> save(CriptoMoneda criptoMoneda) {
    	return this.criptoMonedaRepository.save(criptoMoneda);
    }

    @Override
    public Mono<CriptoMoneda> delete(String id) {
        return this.criptoMonedaRepository
                .findById(id)
                .flatMap(p -> this.criptoMonedaRepository.deleteById(p.getId()).thenReturn(p));
	
    }

    @Override
    public Mono<CriptoMoneda> update(String id, CriptoMoneda criptoMoneda) {
        return this.criptoMonedaRepository.findById(id)
                .flatMap(criptoMoneda1 -> {
                	criptoMoneda.setId(id);
                    return save(criptoMoneda);
                })
                .switchIfEmpty(Mono.empty());
    }
    
    @Override
    public Mono<CriptoMoneda> findByFechaReg(String fechaReg) {
        return this.criptoMonedaRepository.findOneByFechaReg(fechaReg);
    }
    
    @Override
    public Flux<CriptoMoneda> findBetweenFechaReg(String fechaReg1,String fechaReg2) {
    	return this.criptoMonedaRepository.findAllBetweenFechaReg(fechaReg1,fechaReg2);
    }
    
    @Override
    public Flux<CriptoMoneda> calcular(String fechaReg1,String fechaReg2) {
    	
    	
    	List<CriptoMoneda> criptomonedas = new ArrayList<>();
    	
    	//Claramente Esta cargado con los objetos
    	//Para ellos mostramos los datos obtenidos del subscribe
    	this.criptoMonedaRepository.findAllBetweenFechaReg(fechaReg1,fechaReg2).subscribe(System.out::println);
    	
    	//Aclaratoria
    	//Despues de tener los valores conocer el promedio de valor entre dos timestamps no supondria un problema.
    	// ni tampoco la diferencia porcentual entre ese valor promedio y el valor máximo almacenado para toda la serie temporal disponible.
    	
    
    	//Esto se supone deberia cargarme la lista pero no lo hace.
    	//No puedo acceder a los datos para poder terminar el calculo.
    	this.criptoMonedaRepository.findAllBetweenFechaReg(fechaReg1,fechaReg2).subscribe(criptomonedas::add);

    	
    	//SOLUCION
    	
    	//b.1
    	//Para conocer el promedio de valor entre dos timestamps, sumaria todos los lprice de todos los registros y se divide
    	//entre la cantidad de registros obtenidos.
    	
    	//b.2
    	//Para calcular la diferencia porcentual entre dos números, tendremos que restarle el valor antiguo al valor nuevo y 
    	//dividir la cifra resultante entre el valor antiguo y multiplicarlo a continuación por 100.
    	
    	return this.criptoMonedaRepository.findAllBetweenFechaReg(fechaReg1,fechaReg2);
    }
    
    //Solucion de paginacion
    //podemos utilizar el mix y max, asi vamos obteniendo cierta cantidad de
    //registros por pagina, segun la pagina en la que este y la cantidad de registros
    //vamos generando la consulta.
    //db.CriptoMoneda.find().min({_id:0}).max({_id:3})
    //db.CriptoMoneda.find().min({_id:3}).max({_id:6})
    
    @Override
    public Flux<CriptoMoneda> findAllByFechaReg(String fechaReg) {
    	return this.criptoMonedaRepository.findByFechaReg(fechaReg);
    }
    
    @Override
    public Flux<CriptoMoneda> findByCurr1(String curr1) {
        return this.criptoMonedaRepository.findByCurr1(curr1);
    }
 
    @Override
    public Flux<CriptoMoneda> findAll() {
        return this.criptoMonedaRepository.findAll();
    }
}