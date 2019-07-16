package com.altec.bsbr.app.blank.web.jsf;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;

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
public class MenuMbsBBean extends BasicBBean implements InitializingBean {

	@Autowired 
    private Authorization authorization;

    private List<ItemMenu> menu = new ArrayList<ItemMenu>();
    
	public void afterPropertiesSet() throws Exception {
        
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
        	
        	List<ItemMenu> listItemMenu  = this.setItemMenu( listMbs );
        	
        	for (Iterator iterator = listItemMenu.iterator(); iterator.hasNext();) {
    			ItemMenu item = (ItemMenu)iterator.next();
    			if( item!=null && item.getNomeFuncaoPai().equals("0")  ){
    				findChild(item, listItemMenu);
    				menu.add(item);
    			}
        	}        		
        
        } catch (Exception e) {
        	addMessage(FacesMessage.SEVERITY_ERROR, null, "Acesso negado!");
        	logger.warn(e.getMessage());
        	menu = null;
        }
    }

	/**
	 * Metodo para converter List<MBSMenuItem> para List<ItemMenu>.
	 * @param List<MBSMenuItem> menu
	 * @return List<ItemMenu> 
	 */
	private List<ItemMenu> setItemMenu(List<MBSMenuItem> menu) {
		List<ItemMenu> itemMenus = new ArrayList<ItemMenu>();  
		for (Iterator iterator = menu.iterator(); iterator.hasNext();) {
			itemMenus.add( new ItemMenu( (MBSMenuItem) iterator.next() ) );
		}
		return itemMenus;
	}

	
	/**
	 * Metodo recursivo para montar a List<ItemMenu> com seus respctivos filhos.
	 * 
	 * @param ItemMenu item
	 * @param List<ItemMenu> menu
	 * 
	 */
	public void findChild(ItemMenu item, List<ItemMenu> menu) {
		for (Iterator iterator = menu.iterator(); iterator.hasNext();) {
			ItemMenu itemMenu = (ItemMenu)iterator.next();
			if( itemMenu.getNomeFuncaoPai().equalsIgnoreCase( item.getNomeFuncao() ) ){
				findChild(itemMenu, menu );
				item.getMenu().add(itemMenu); 
			}
		}
	}
 
	public List<ItemMenu> getMenu() {
		return menu;
	}

	public void setMenu(List<ItemMenu> menu) {
		this.menu = menu;
	}
}
