package dad.javafx.calculadora.compleja;

import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

public class CalculadoraCompleja extends Application {

	private TextField operador1Text;
	private TextField operador1ImaginarioText;
	private TextField operador2Text;
	private TextField operador2ImaginarioText;
	private ComboBox<String> operadoresCombo;
	private TextField resultadoRealText;
	private TextField resultadoImaginarioText;

	private Complejo operando1 = new Complejo();
	private Complejo operando2 = new Complejo();
	private Complejo Resultado = new Complejo();

	private StringProperty operacion = new SimpleStringProperty();

	@Override
	public void start(Stage primaryStage) throws Exception {

		operador1Text = new TextField("0");
		operador1Text.setPrefColumnCount(4);

		operador1ImaginarioText = new TextField("0");
		operador1ImaginarioText.setPrefColumnCount(4);

		operador2Text = new TextField("0");
		operador2Text.setPrefColumnCount(4);

		operador2ImaginarioText = new TextField("0");
		operador2ImaginarioText.setPrefColumnCount(4);

		operadoresCombo = new ComboBox<String>();
		operadoresCombo.getItems().addAll("+", "-", "*", "/");

		resultadoRealText = new TextField("0");
		resultadoRealText.setPrefColumnCount(4);
		resultadoRealText.setDisable(true);

		resultadoImaginarioText = new TextField("0");
		resultadoImaginarioText.setPrefColumnCount(4);
		resultadoImaginarioText.setDisable(true);

		HBox operador1 = new HBox(4, operador1Text, new Label("+"), operador1ImaginarioText, new Label("i"));
		operador1.setAlignment(Pos.CENTER);

		HBox operador2 = new HBox(4, operador2Text, new Label("+"), operador2ImaginarioText, new Label("i"));
		operador2.setAlignment(Pos.CENTER);

		HBox resultadoOperaciones = new HBox(4, resultadoRealText, new Label("+"), resultadoImaginarioText,
				new Label("i"));
		resultadoOperaciones.setAlignment(Pos.CENTER);

		VBox operaciones = new VBox(5, operador1, operador2, new Separator(), resultadoOperaciones);
		operaciones.setAlignment(Pos.CENTER);

		VBox combo = new VBox(5, operadoresCombo);
		combo.setAlignment(Pos.CENTER);

		HBox root = new HBox(4, combo, operaciones);
		root.setAlignment(Pos.CENTER);

		Scene scene = new Scene(root, 320, 200);

		primaryStage.setTitle("Calculadora compleja");
		primaryStage.setScene(scene);
		primaryStage.show();

		// bindeos
		operador1Text.textProperty().bindBidirectional(operando1.ParteRealProperty(), new NumberStringConverter());
		operador2Text.textProperty().bindBidirectional(operando2.ParteRealProperty(), new NumberStringConverter());
		operador1ImaginarioText.textProperty().bindBidirectional(operando1.ParteImaginariaProperty(),
				new NumberStringConverter());
		operador2ImaginarioText.textProperty().bindBidirectional(operando2.ParteImaginariaProperty(),
				new NumberStringConverter());
		resultadoRealText.textProperty().bindBidirectional(Resultado.ParteRealProperty(),
				new NumberStringConverter());
		resultadoImaginarioText.textProperty().bindBidirectional(Resultado.ParteImaginariaProperty(),
				new NumberStringConverter());
		operacion.bind(operadoresCombo.getSelectionModel().selectedItemProperty());

		operacion.addListener((o, ov, nv) -> onOperacionChanged(nv));
		operadoresCombo.getSelectionModel().selectFirst();

	}

	private void onOperacionChanged(String nv) {
		switch (nv) {
		case "+":
			Resultado.ParteRealProperty().bind(operando1.ParteRealProperty().add(operando2.ParteRealProperty()));
			Resultado.ParteImaginariaProperty()
					.bind(operando1.ParteImaginariaProperty().add(operando2.ParteImaginariaProperty()));
			break;
		case "-":
			Resultado.ParteRealProperty().bind(operando1.ParteRealProperty().subtract(operando2.ParteRealProperty()));
			Resultado.ParteImaginariaProperty()
					.bind(operando1.ParteImaginariaProperty().subtract(operando2.ParteImaginariaProperty()));
			break;
		case "*":
			Resultado.ParteRealProperty().bind((operando1.ParteRealProperty().multiply(operando2.ParteRealProperty()))
					.subtract(operando1.ParteImaginariaProperty().multiply(operando2.ParteImaginariaProperty())));
			Resultado.ParteImaginariaProperty()
					.bind((operando1.ParteRealProperty().multiply(operando2.ParteImaginariaProperty()))
							.add(operando1.ParteImaginariaProperty().multiply(operando2.ParteRealProperty())));
			break;

		case "/":
			Resultado.ParteRealProperty().bind((operando1.ParteRealProperty().multiply(operando2.ParteRealProperty())
					.add(operando1.ParteImaginariaProperty().multiply(operando2.ParteImaginariaProperty()))
					.divide((operando2.ParteRealProperty().multiply(operando2.ParteRealProperty()))
							.add(operando2.ParteImaginariaProperty().multiply(operando2.ParteImaginariaProperty())))));

			Resultado.ParteImaginariaProperty().bind((operando1.ParteImaginariaProperty()
					.multiply(operando2.ParteRealProperty())
					.subtract(operando1.ParteRealProperty().multiply(operando2.ParteImaginariaProperty()))
					.divide((operando2.ParteRealProperty().multiply(operando2.ParteRealProperty()))
							.add(operando2.ParteImaginariaProperty().multiply(operando2.ParteImaginariaProperty())))));

			break;
		}
	}

	public static void main(String[] args) {
		launch(args);

	}

}
