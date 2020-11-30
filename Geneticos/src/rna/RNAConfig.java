package rna;

import Exceptions.InvalidChromosomeSize;
import Exceptions.ParameterOutOfRange;
import genetic.GeneticConfig;

public class RNAConfig {
	
	public int hidden_layers, neurons_per_layer, epochs;
	public float learning_rate, momentum;
	
	public RNAConfig(char [] chromosome) throws ParameterOutOfRange, InvalidChromosomeSize {
		if(chromosome.length != GeneticConfig.CHROMOSOME_SIZE)
			throw new InvalidChromosomeSize("El cromosoma recibido no tiene el tamaño necesario");
		StringBuffer temp = new StringBuffer();
		int i, r;
		r = GeneticConfig.HIDDEN_BITS;
		for(i = 0; i < r; i++) {
			temp.append(chromosome[i]);
		}
		this.hidden_layers = assingHidden(temp.toString());
		//System.out.println(temp.toString() + " | " + this.hidden_layers);
		r = i + GeneticConfig.NEURONS_BITS;
		temp = new StringBuffer();
		for(; i < r; i++) {
			temp.append(chromosome[i]);
		}
		this.neurons_per_layer = assingNeurons(temp.toString());
		//System.out.println(temp.toString() + " | " + this.neurons_per_layer);	
		r = i + GeneticConfig.EPOCH_BITS;
		temp = new StringBuffer();
		for(; i < r; i++) {
			temp.append(chromosome[i]);
		}
		this.epochs = assingEpochs(temp.toString());
		//System.out.println(temp.toString() + " | " + this.epochs);
		r = i + GeneticConfig.LEARNING_BITS;
		temp = new StringBuffer();
		for(; i < r; i++) {
			temp.append(chromosome[i]);
		}
		this.learning_rate = assingLearning(temp.toString());
		//System.out.println(temp.toString() + " | " + this.learning_rate);
		r = i + GeneticConfig.MOMENTUM_BITS;
		temp = new StringBuffer();
		for(; i < r; i++) {
			temp.append(chromosome[i]);
		}
		this.momentum = assingMomentum(temp.toString());
		//System.out.println(temp.toString() + " | " + this.momentum);
	}
	
	private int assingHidden(String bits) throws ParameterOutOfRange {
		int real = (Integer.parseInt(bits, 2) * GeneticConfig.HIDDEN_DELTA) + GeneticConfig.HIDDEN_RANGE[0];
		if(!GeneticConfig.inRange(GeneticConfig.HIDDEN_RANGE, real)) {
			throw new ParameterOutOfRange("El parametro [CAPAS_OCULTAS] esta fuera de rango");
		} 
		return real;
	}
	
	private int assingNeurons(String bits) throws ParameterOutOfRange {
		int real = (Integer.parseInt(bits, 2) * GeneticConfig.NEURONS_DELTA) + GeneticConfig.NEURONS_RANGE[0];
		if(!GeneticConfig.inRange(GeneticConfig.NEURONS_RANGE, real)) {
			throw new ParameterOutOfRange("El parametro [NUMERO_NEURONAS] esta fuera de rango");
		} 
		return real;
	}
	
	private int assingEpochs(String bits) throws ParameterOutOfRange {
		int real = (Integer.parseInt(bits, 2) * GeneticConfig.EPOCHS_DELTA) + GeneticConfig.EPOCHS_RANGE[0];
		if(!GeneticConfig.inRange(GeneticConfig.EPOCHS_RANGE, real)) {
			throw new ParameterOutOfRange("El parametro [EPOCAS] esta fuera de rango");
		} 
		return real;
	}
	
	private float assingLearning(String bits) throws ParameterOutOfRange {
		float real = Integer.parseInt(bits, 2) * (GeneticConfig.LEARNING_RANGE[1] / GeneticConfig.LEARNING_DELTA);
		if(!GeneticConfig.inRange(GeneticConfig.LEARNING_RANGE, real)) {
			throw new ParameterOutOfRange("El parametro [LEARNING_RATE] esta fuera de rango");
		} 
		return real;
	}
	
	private float assingMomentum(String bits) throws ParameterOutOfRange {
		float real = Integer.parseInt(bits, 2) * (GeneticConfig.MOMENTUM_RANGE[1] / GeneticConfig.MOMENTUM_DELTA);
		if(!GeneticConfig.inRange(GeneticConfig.MOMENTUM_RANGE, real)) {
			throw new ParameterOutOfRange("El parametro [MOMENTUM] esta fuera de rango");
		} 
		return real;
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
