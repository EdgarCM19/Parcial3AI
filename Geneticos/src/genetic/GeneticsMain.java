package genetic;

import Exceptions.InvalidChromosomeSize;
import Exceptions.ParameterOutOfRange;
import rna.RNAConfig;

public class GeneticsMain {

	public static void main(String[] args) {		
		GA gnr=new GA();
        Individuo ind=gnr.Genetic();
        System.out.println("El mejor individuo fue:");
        ind.mostrar();
        System.out.println("Que bonito y entendible es:");
        System.out.println(ind.params);
        System.out.println("Fitness"+ind.fitness);
	}

}

/*
char [] test = GeneticConfig.randomChromosome();
		for(char c : test)
			System.out.print(c);
		try {
			RNAConfig rna_conf = new RNAConfig(test);
			System.out.println(rna_conf.hidden_layers);
			System.out.println(rna_conf.neurons_per_layer);
			System.out.println(rna_conf);
		} catch (ParameterOutOfRange e) {
			System.out.println("Error si que si");
			e.printStackTrace();
		} catch (InvalidChromosomeSize e) {
			e.printStackTrace();
		}
*/
