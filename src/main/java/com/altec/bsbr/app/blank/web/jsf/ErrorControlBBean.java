package com.altec.bsbr.app.blank.web.jsf;

import javax.faces.context.FacesContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component(value="ErrorControlBBean")
@Scope("request")
public class ErrorControlBBean {
    public boolean getHaveMessages() {
        return FacesContext.getCurrentInstance().getMessages(null).hasNext();
    }
}
