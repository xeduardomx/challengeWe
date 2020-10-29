package com.wenance.challenge.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.wenance.challenge.modelo.CriptoMoneda;

@Service
public class GetCriptoPriceService extends Thread{
	
	@Autowired
    private MongoTemplate mongoTemplate;
	
	/** 
     *Obtenemos al precio al inicializar
	 *invocamos al demonio para que actualice
	 *cada 10 segundos.
     */
	public void GetCriptoPrice() {
		this.GetAndSavePrice();
		setDaemon(true);
		start();
	}
	
    @Override
    public void run() {
	    while (true) {
	    try{
	    	sleep(10000);
	    }catch(InterruptedException e) {
	    	e.getMessage();
	    }
	    	this.GetAndSavePrice();
	    }
    } 
    
    public void GetAndSavePrice() {
    	final String url = "https://cex.io/api/last_price/BTC/USD";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url, String.class);
        final Gson gson = new Gson();
        CriptoMoneda criptoMoneda = gson.fromJson(result, CriptoMoneda.class);
        //Al ser un hilo no necesitamos usar flux, es un proceso ejecutandose
        //en otro hilo.
        mongoTemplate.insert(criptoMoneda);
    }
    
    
}