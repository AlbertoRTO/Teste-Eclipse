package com.altec.bsbr.app.blank.web.jsf;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.altec.bsbr.app.blank.dto.Pessoa;
import com.altec.bsbr.fw.web.jsf.BasicBBean;

@Component
@Scope("session")
public class FirstPageBBean extends BasicBBean {

    private String text;

    private String[] componentItems;
    
    private String selectedComponent;
    
    private String[] selecteds;
    
    private String selectedsRadio;

    private SelectItem[] selectedsItens;
    
    private List<Pessoa> pessoas;
    
    private boolean popup;
    
    
    private static final String[] COMPONENT_ITEMS = new String[]{
    		"Elemento 1 ",
    		"Elemento 2 " ,
    		"Elemento 3 " 
    };

    
    private static final SelectItem[] SELECTEDS_ITENS = new SelectItem[]{
        new SelectItem("Exemplo 1"),
        new SelectItem("Exemplo 2"),
        new SelectItem("Exemplo 3"),
        new SelectItem("Exemplo 4"),
    };

    public FirstPageBBean() {
		try{
	        	//Inicializa a classe Pessoa responsvel por gerar nossa dataGrid(Table)
	        	Pessoa pessoa = new Pessoa("123", "Felipe", "Mozena Antunes", "25", "339.329.329 - 03", "20/10/1986", "(11) 5854-5163", "(11) 99184-5434", "teste@prservicos.com.br" );
	        	pessoas = new ArrayList<Pessoa>();
	        	pessoas.add( pessoa );

	        	pessoa = new Pessoa("456", "Marco", "Aurelio Principe", "48", "339.354.544 - 03", "20/10/1964", "(11) 5854-5163", "(11) 99184-5434", "marco@prservicos.com.br" );
	        	pessoas.add( pessoa );

	        	//Inicializa a variavel responsavel por denderizar o popup
	        	popup = true;
	        	
        } catch (Exception e) {
        	addMessage(FacesMessage.SEVERITY_ERROR, null, "Acesso negado!");
        	logger.warn(e.getMessage());
        }
    }

    public void close( ActionEvent e) {
		popup = !popup;
    }

    public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String[] getComponentItems() {
		return COMPONENT_ITEMS;
	}

	public void setComponentItems(String[] componentItems) {
		this.componentItems = componentItems;
	}

	public String getSelectedComponent() {
		return selectedComponent;
	}

	public void setSelectedComponent(String selectedComponent) {
		this.selectedComponent = selectedComponent;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	public String[] getSelecteds() {
		return selecteds;
	}

	public void setSelecteds(String[] selecteds) {
		this.selecteds = selecteds;
	}

	public SelectItem[] getSelectedsItens() {
		return SELECTEDS_ITENS;
	}

	public void setSelectedsItens(SelectItem[] selectedsItens) {
		this.selectedsItens = selectedsItens;
	}

	public String getSelectedsRadio() {
		return selectedsRadio;
	}

	public void setSelectedsRadio(String selectedsRadio) {
		this.selectedsRadio = selectedsRadio;
	}

	public boolean isPopup() {
		return popup;
	}

	public void setPopup(boolean popup) {
		this.popup = popup;
	}
	
	public boolean getPopup() {
		return popup;
	}
}
