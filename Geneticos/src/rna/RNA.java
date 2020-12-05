package rna;

import java.io.File;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import genetic.GeneticConfig;
import weka.classifiers.Evaluation;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.core.Instances;
import weka.core.Utils;

public class RNA {

	public static float trainRNA(int hidden_layers, int neurons_per_layer, int epochs, 
			float learning_rate, float momentum, int index) {
		System.out.println("\n**********************");
		System.out.println(getDate() + "-[RNA]>Inicio de entrenamiento " + index);
		try {
			FileReader fr = new FileReader(new File(GeneticConfig.DATA_SET_PATH));
			Instances train = new Instances(fr);
			train.setClassIndex(train.numAttributes() - 1);
			MultilayerPerceptron mlp = new MultilayerPerceptron();
			String options = optionsToString(hidden_layers, neurons_per_layer, epochs, learning_rate, momentum);
			mlp.setOptions(Utils.splitOptions(options));
			mlp.buildClassifier(train);
			Evaluation eval = new Evaluation(train);
			eval.evaluateModel(mlp, train);
			float perce = (float) ((eval.correct() * 100) / (eval.correct() + eval.incorrect()));
			System.out.println(getDate() + "-[RNA]>Pocentaje obtenido: " + perce);
			System.out.println(getDate() + "-[RNA]>Configuracion: " + options);
			System.out.println("[RNA]>Fin del entrenamiento " + index);
			return perce;
		} catch (Exception e) {	 
			e.printStackTrace();
		}
		return -1f;
	}
	
	public static String optionsToString(int hidden_layers, int neurons_per_layer, int epochs, 
			float learning_rate, float momentum) {
		StringBuffer options = new StringBuffer();
		options.append("-L " + String.valueOf(learning_rate) + " ");
		options.append("-M " + String.valueOf(momentum) + " ");
		options.append("-N " + String.valueOf(epochs) + " ");
		options.append("-V 0 -S 0 -E 20 -H ");
		for(int i = 0; i < hidden_layers; i++) {
			if(hidden_layers > 1 )
				options.append(String.valueOf(neurons_per_layer) + ((i < hidden_layers - 1) ? "," : ""));
			else
				options.append(String.valueOf(neurons_per_layer));
		}
		return options.toString();
	}
	
	private static String getDate() {
		return DateTimeFormatter.ofPattern("dd/MM/yy HH:mm:ss").format(LocalDateTime.now());
	}
	
}
