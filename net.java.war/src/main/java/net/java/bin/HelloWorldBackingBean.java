package net.java.bin;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class HelloWorldBackingBean {
	private String helloProperty = "Африканские и азиатские слоны. Боевые, рабочие и декоративные. Цены от производителя. Доставка в кратчайшие сроки !";

	public String getHelloProperty() {
		return helloProperty;
	}

	public void setHelloProperty(String helloProperty) {
		this.helloProperty = helloProperty;
	}
}
