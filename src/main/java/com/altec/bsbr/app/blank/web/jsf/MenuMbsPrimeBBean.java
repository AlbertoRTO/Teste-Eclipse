package com.altec.bsbr.app.blank.web.jsf;

import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.altec.bsbr.app.blank.dto.ItemMenu;
import com.altec.bsbr.fw.security.SecurityInfo;
import com.altec.bsbr.fw.security.authorization.Authorization;
import com.altec.bsbr.fw.security.authorization.menu.MBSMenuItem;
import com.altec.bsbr.fw.web.jsf.BasicBBean;

@Component
@Scope("session")
public class MenuMbsPrimeBBean extends BasicBBean implements InitializingBean{


	@Autowired 
	private Authorization authorization;

	private MenuModel modelMenu;

	public void afterPropertiesSet() throws Exception {
		modelMenu = new DefaultMenuModel();
		DefaultSubMenu subMenu;
		DefaultMenuItem itemMenu;

		SecurityInfo securityInfo = new SecurityInfo();

		try {
			//Recupera o usurio corrente da rede
			securityInfo = SecurityInfo.getCurrent();

			//Variaveis utilizadas para possiveis testes
			//securityInfo.setUserName("teste");
			//securityInfo.setSystemId("EX");


			if (securityInfo == null ) {

				addMessage(FacesMessage.SEVERITY_ERROR, null, "Acesso negado!");

			} else {

				//Alterar a sigla do sistema utilizado.
				securityInfo.setUserName("teste");
				securityInfo.setSystemId("EX");

			} 

		} catch (Exception e) {
			e.printStackTrace();
			logger.warn(e.getMessage());
			addMessage(FacesMessage.SEVERITY_ERROR, null, "Acesso negado!");
		}


		try {

			//Realiza a pesquisa no MBS. Retornando a lista de acesso que o usuario possui. 
			List<MBSMenuItem> listMbs  = authorization.getMenuItems( securityInfo.getUserName(), securityInfo.getSystemId() );


			for (MBSMenuItem item : listMbs){

				if ( item != null && item.getNomeFuncaoPai().equals("0")){
					if ( item != null && item.getNomeFuncaoPai().equalsIgnoreCase("0")){
						subMenu = findSubMenu(item,listMbs);

						if (subMenu != null){
							modelMenu.addElement(subMenu);
						}
						else{
							itemMenu = new DefaultMenuItem(item.getNomeFuncao());
							//item.getDescricao();
							itemMenu.setUrl(item.getUrl());
							itemMenu.setParam("token", item.getToken());

							modelMenu.addElement(itemMenu);
						}

					}
				}


			}


		} catch (Exception e) {
			addMessage(FacesMessage.SEVERITY_ERROR, null, "Acesso negado!");
			logger.warn(e.getMessage());
			//modelMenu = null;
		}
	}

	public DefaultSubMenu findSubMenu(MBSMenuItem item, List<MBSMenuItem> menu){
		DefaultSubMenu result = new DefaultSubMenu(item.getNomeFuncao());
		DefaultSubMenu subMenu;
		DefaultMenuItem itemSubMenu;

		for (MBSMenuItem itemMenu : menu){

			if ( itemMenu != null && itemMenu.getNomeFuncaoPai().equalsIgnoreCase(item.getNomeFuncao())){
				subMenu = findSubMenu(itemMenu,menu);

				if (subMenu != null){
					result.addElement(subMenu);
				}
				else{
					itemSubMenu = new DefaultMenuItem(itemMenu.getNomeFuncao());
					//itemMenu.getDescricao();
					itemSubMenu.setUrl(itemMenu.getUrl());
					itemSubMenu.setParam("token", itemMenu.getToken());

					result.addElement(itemSubMenu);
				}

			}


		}

		if (result.getElementsCount() == 0){
			result = null;
		}

		return result;
	}


	public MenuModel getModelMenu() {
		return modelMenu;
	}

	public void setModelMenu(MenuModel modelMenu) {
		this.modelMenu = modelMenu;
	}




}
