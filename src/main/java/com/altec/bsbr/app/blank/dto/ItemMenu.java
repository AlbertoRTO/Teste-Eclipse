package com.altec.bsbr.app.blank.dto;

import java.util.ArrayList;
import java.util.List;

import com.altec.bsbr.fw.security.authorization.menu.MBSMenuItem;
import com.altec.bsbr.fw.security.authorization.menu.MenuItem;

/**
 * Classe que contm atributos necessrios para criao do menu. 
 * @data   06/09/2012 
 * @author Felipe Mozena Antunes
 */
public class ItemMenu extends MBSMenuItem implements MenuItem {

	private List<ItemMenu> menu;
	
	public ItemMenu() {
	}
	
	//Constructor
	public ItemMenu(MBSMenuItem item ) {
		super.setDescricao( item.getDescricao() );
		super.setNomeFuncao( item.getNomeFuncao() );
		super.setNomeFuncaoPai( item.getNomeFuncaoPai() );
		super.setToken( item.getToken() );
		super.setUrl( item.getUrl()  ); 
		this.setMenu( new ArrayList<ItemMenu>() );
	}
	
	public List<ItemMenu> getMenu() {
		return menu;
	}

	public void setMenu(List<ItemMenu> menu) {
		this.menu = menu;
	}

}
