package rna;

import genetic.GeneticConfig;


public class RNAParams {
	
	public int hidden_layers, neurons_per_layer, epochs;
	public float learning_rate, momentum;
	public boolean valid;
	
	public RNAParams(char [] chromosome) {
		valid = true;
		if(chromosome.length != GeneticConfig.CHROMOSOME_SIZE) {
			System.err.println("Tamaño incorrectooooooooo");
			valid = false;
		}
		StringBuffer temp = new StringBuffer();
		int i, r;
		r = GeneticConfig.HIDDEN_BITS;
		for(i = 0; i < r; i++) {
			temp.append(chromosome[i]);
		}
		if((this.hidden_layers = assingHidden(temp.toString())) == -1) valid = false;
		r = i + GeneticConfig.NEURONS_BITS;
		temp = new StringBuffer();
		for(; i < r; i++) {
			temp.append(chromosome[i]);
		}
		if((this.neurons_per_layer = assingNeurons(temp.toString())) == -1) valid = false;
		r = i + GeneticConfig.EPOCH_BITS;
		temp = new StringBuffer();
		for(; i < r; i++) {
			temp.append(chromosome[i]);
		}
		if((this.epochs = assingEpochs(temp.toString())) == -1) valid = false;
		r = i + GeneticConfig.LEARNING_BITS;
		temp = new StringBuffer();
		for(; i < r; i++) {
			temp.append(chromosome[i]);
		}
		if((this.learning_rate = assingLearning(temp.toString())) == -1) valid = false;
		r = i + GeneticConfig.MOMENTUM_BITS;
		temp = new StringBuffer();
		for(; i < r; i++) {
			temp.append(chromosome[i]);
		}
		if((this.momentum = assingMomentum(temp.toString())) == -1) valid = false;
	}
	
	private int assingHidden(String bits) {
		int real = (Integer.parseInt(bits, 2) * GeneticConfig.HIDDEN_DELTA) + GeneticConfig.HIDDEN_RANGE[0];
		if(GeneticConfig.inRange(GeneticConfig.HIDDEN_RANGE, real)) {
			return real;
		} 
		return -1;
	}
	
	private int assingNeurons(String bits) {
		int real = (Integer.parseInt(bits, 2) * GeneticConfig.NEURONS_DELTA) + GeneticConfig.NEURONS_RANGE[0];
		if(GeneticConfig.inRange(GeneticConfig.NEURONS_RANGE, real)) {
			return real;
		} 
		return -1;
	}
	
	private int assingEpochs(String bits) {
		int real = (Integer.parseInt(bits, 2) * GeneticConfig.EPOCHS_DELTA) + GeneticConfig.EPOCHS_RANGE[0];
		if(GeneticConfig.inRange(GeneticConfig.EPOCHS_RANGE, real)) {
			return real;
		} 
		return -1;
	}
	
	private float assingLearning(String bits){
		float real = Integer.parseInt(bits, 2) * (GeneticConfig.LEARNING_RANGE[1] / GeneticConfig.LEARNING_DELTA);
		if(GeneticConfig.inRange(GeneticConfig.LEARNING_RANGE, real)) {
			return real;
		} 
		return -1;
	}
	
	private float assingMomentum(String bits) {
		float real = Integer.parseInt(bits, 2) * (GeneticConfig.MOMENTUM_RANGE[1] / GeneticConfig.MOMENTUM_DELTA);
		if(GeneticConfig.inRange(GeneticConfig.MOMENTUM_RANGE, real)) {
			return real;
		} 
		return -1;
	}
	
	@Override
	public String toString() {
		return  "[HIDDEN]>" 	+ this.hidden_layers 		+ "\n" + 
				"[NEURONS]>" 	+ this.neurons_per_layer 	+ "\n" +
				"[EPOCHS]>" 	+ this.epochs 				+ "\n" +
				"[LEARNING]>" 	+ this.learning_rate 		+ "\n" +
				"[MOMENTUM]>" 	+ this.momentum 			+ "\n";
	}

}
