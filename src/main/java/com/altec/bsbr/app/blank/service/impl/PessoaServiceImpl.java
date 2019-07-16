package com.altec.bsbr.app.blank.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.altec.bsbr.app.blank.dto.Pessoa;
import com.altec.bsbr.app.blank.service.PessoaService;

@Service
public class PessoaServiceImpl implements PessoaService{
	
	@Autowired
	private PessoaService service;

	@Override
	public Pessoa retornaPessoa() {
		// TODO Auto-generated method stub
		return null;
	}

}
