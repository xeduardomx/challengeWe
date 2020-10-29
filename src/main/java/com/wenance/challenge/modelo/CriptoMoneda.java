package com.wenance.challenge.modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "CriptoMoneda")
public class CriptoMoneda {

    @Id
    private String id = UUID.randomUUID().toString().substring(0, 10);
	
	private String lprice;

    private String curr1;
    
    private String curr2;

    private String fechaReg;

	public CriptoMoneda() {
		super();
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formatDateTime = now.format(formatter);
		this.fechaReg = formatDateTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLprice() {
		return lprice;
	}

	public void setLprice(String lprice) {
		this.lprice = lprice;
	}

	public String getCurr1() {
		return curr1;
	}

	public void setCurr1(String curr1) {
		this.curr1 = curr1;
	}

	public String getCurr2() {
		return curr2;
	}

	public void setCurr2(String curr2) {
		this.curr2 = curr2;
	}

	public String getFechaReg() {
		return fechaReg;
	}

	public void setFechaReg(String fechaReg) {
		this.fechaReg = fechaReg;
	}

	@Override
	public String toString() {
		return "CriptoMoneda [id=" + id + ", lprice=" + lprice + ", curr1=" + curr1 + ", curr2=" + curr2 + ", fechaReg="
				+ fechaReg + "]";
	}

}
