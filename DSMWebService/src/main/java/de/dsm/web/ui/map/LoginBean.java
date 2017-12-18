package de.dsm.web.ui.map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@RequestScoped
@ManagedBean

public class LoginBean {
    private String name;
    //proper getter
    public String getName() {
        return this.name;
    }
    //proper setter
    public void setName(String name) {
        this.name = name;
    }
}