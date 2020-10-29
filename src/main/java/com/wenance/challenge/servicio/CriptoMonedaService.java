package com.wenance.challenge.servicio;

import com.wenance.challenge.modelo.CriptoMoneda;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CriptoMonedaService {

	Mono<CriptoMoneda> save(CriptoMoneda criptoMoneda);

	Mono<CriptoMoneda> delete(String id);

	Mono<CriptoMoneda> update(String id, CriptoMoneda criptoMoneda);

	Flux<CriptoMoneda> findAll();

	Mono<CriptoMoneda> findByFechaReg(String fechaReg);

	Flux<CriptoMoneda> findByCurr1(String curr1);

	Flux<CriptoMoneda> findAllByFechaReg(String fechaReg);

	Flux<CriptoMoneda> findBetweenFechaReg(String fechaReg1,String fechaReg2);

	Flux<CriptoMoneda> calcular(String fechaReg1, String fechaReg2);
}
