package com.wenance.challenge.repositorio;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.wenance.challenge.modelo.CriptoMoneda;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CriptoMonedaRepository extends ReactiveMongoRepository<CriptoMoneda, String> {
    Flux<CriptoMoneda> findByFechaReg(String fechaReg);
    Mono<CriptoMoneda> findOneByFechaReg(String fechaReg);
    Flux<CriptoMoneda> findByCurr1(String curr1);
    
    @Query("{ 'fechaReg' : { $gt: ?0, $lt: ?1 } }")
    Flux<CriptoMoneda> findAllBetweenFechaReg(String fechaReg1,String fechaReg2);
    
}
