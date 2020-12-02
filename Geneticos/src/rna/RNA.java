package rna;

import java.io.File;
import java.io.FileReader;

import weka.classifiers.Evaluation;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.core.Instances;
import weka.core.Utils;

public class RNA {
	
	private static final String DATA_BASE_PATH = "src/res/dataset/diabetes.arff";

	public static float trainRNA(int hidden_layers, int neurons_per_layer, int epochs, 
			float learning_rate, float momentum) {
		System.out.println("[RNA]>Start");
		try {
			FileReader fr = new FileReader(new File(DATA_BASE_PATH));
			Instances train = new Instances(fr);
			train.setClassIndex(train.numAttributes() - 1);
			MultilayerPerceptron mlp = new MultilayerPerceptron();
			String options = optionsToString(hidden_layers, neurons_per_layer, epochs, learning_rate, momentum);
			System.out.println(options);
			mlp.setOptions(Utils.splitOptions(options));
			mlp.buildClassifier(train);
			Evaluation eval = new Evaluation(train);
			eval.evaluateModel(mlp, train);
			float perce = (float) ((eval.correct() * 100) / (eval.correct() + eval.incorrect()));
			System.out.println("[RNA]>%: " + perce);
			System.out.println("[RNA]>END");
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
	
	
	
}
