package genetic;

import java.util.ArrayList;

import gui.MainWindow;

public class GA {

	public Poblation poblaciones;
	ArrayList<Double> time_per_gen;
	ArrayList<Float> average_per_gen;
	ArrayList<Integer> population_per_gen;
	
	long start, stop;
	double time;
	
	public GA(){
		poblaciones=new Poblation();
	}
	public Individuo Genetic(){
		System.out.println("[GA]>Start");
		start = System.currentTimeMillis();
		poblaciones.createPoblation();
		System.out.println("///////");
		System.out.println("Fitness inicial");
		poblaciones.fitness();
		int generacion=0;
		System.out.println("Creacion primera poblacion");
		stop = System.currentTimeMillis();
		time = ((double) ((stop - start)/1000) / 60);
		
		time_per_gen = new ArrayList<Double>();
		average_per_gen = new ArrayList<Float>();
		population_per_gen = new ArrayList<Integer>();
		
		time_per_gen.add(time);
		average_per_gen.add(poblaciones.promFitness());
		population_per_gen.add(poblaciones.P.size());
		
		while(poblaciones.MaxFitness < GeneticConfig.LIMIT && generacion < GeneticConfig.MAX_GEN){
			start = System.currentTimeMillis();
			System.out.println("[" + generacion + "]Prom: "+poblaciones.promFitness());
			System.out.println("[" + generacion + "]Poblacion actual: "+poblaciones.P.size());
			System.out.println("[" + generacion + "]Generacion: "+generacion);
			poblaciones.Selection();
			System.out.println("[" + generacion + "]Poblacion sobreviviente: "+poblaciones.PS.size());
			poblaciones.crossover();
			System.out.println("[" + generacion + "]Poblacion nueva: "+poblaciones.PS.size());
			poblaciones.mutation();
			poblaciones.udDate();
			System.out.println("//////////");
			System.out.println("Entrando a fitness");
			poblaciones.fitness();
			generacion++;
			System.out.println("[" + generacion + "]FitnessMax "+poblaciones.P.get(0).fitness);
			stop = System.currentTimeMillis();
			time = ((double) ((stop - start)/1000) / 60);
			time_per_gen.add(time);
			average_per_gen.add(poblaciones.promFitness());
			population_per_gen.add(poblaciones.P.size());
		}
		return poblaciones.P.get(0);
	}
	
	
}
