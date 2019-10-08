package dad.javafx.calculadora.compleja;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;


public class Complejo {
	private DoubleProperty ParteReal=new SimpleDoubleProperty();
	private DoubleProperty  ParteImaginaria=new SimpleDoubleProperty();
	
	public final DoubleProperty ParteRealProperty() {
		return this.ParteReal;
	}
	
	public final double getParteReal() {
		return this.ParteRealProperty().get();
	}
	
	public final void setParteReal(final double ParteReal) {
		this.ParteRealProperty().set(ParteReal);
	}
	
	public final DoubleProperty ParteImaginariaProperty() {
		return this.ParteImaginaria;
	}
	
	public final double getParteImaginaria() {
		return this.ParteImaginariaProperty().get();
	}
	
	public final void setParteImaginaria(final double ParteImaginaria) {
		this.ParteImaginariaProperty().set(ParteImaginaria);
	}
	
	

	


	
	
	
	

	
	
}
