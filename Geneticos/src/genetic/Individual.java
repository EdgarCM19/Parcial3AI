package genetic;

import java.util.Arrays;
import java.util.Random;

import rna.RNA;
import rna.RNAParams;

public class Individual {
	
	char cromosoma[];
	public float fitness;
	float pr;
	RNAParams params;
	
	
	public Individual(){
		fitness = -1f; 
		cromosoma = GeneticConfig.randomChromosome();
		pr = 0f;
		params = new RNAParams(cromosoma);
	}
	
	public Individual(char[]crom){
		fitness = -1f; 
		cromosoma = crom;
		pr = 0f;
		params = new RNAParams(cromosoma);
	}
	
	public void fitness(int index){
		this.fitness = RNA.trainRNA(params.hidden_layers, 
				params.neurons_per_layer, 
				params.epochs, 
				params.learning_rate, 
				params.momentum, 
				index);
		
	}
	
	public void mutate(){ 
		RNAParams validation;
		char [] cromx;
		cromx = Arrays.copyOf(cromosoma, cromosoma.length);
		int r = new Random().nextInt(cromosoma.length - 1);
		cromx[r]=(char)(cromx[r]^1);
		validation = new RNAParams(cromx);
		if(validation.valid) {
			cromosoma = cromx;
			params = new RNAParams(cromosoma);
			fitness = -1;
		}
	}
	
	public boolean validate(){
		return params.valid;
	}
	
	public void mostrar(){
		for(char cr : cromosoma){
			System.out.print(cr);
		}
		System.out.println("\n");
	}

}
