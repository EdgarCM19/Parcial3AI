package genetic;

import rna.RNA;
import rna.RNAConfig;

public class Fitness {
	
	public static float fit(RNAConfig params) {
		return RNA.trainRNA(params.hidden_layers, 
							params.neurons_per_layer, 
							params.epochs, 
							params.learning_rate, 
							params.momentum);
	}

}
