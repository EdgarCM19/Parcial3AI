package genetic;

import gui.MainWindow;

public class GA {

	public Poblation poblaciones;
	private MainWindow gui;
	
	public GA(){
		poblaciones=new Poblation();
	}
	public Individuo Genetic(){
		System.out.println("[GA]>Start");
		poblaciones.createPoblation();
		System.out.println("///////");
		System.out.println("Fitness inicial");
		poblaciones.fitness();
		int generacion=0;
		System.out.println("Creacion primera poblacion");
		
		while(poblaciones.MaxFitness < GeneticConfig.LIMIT && generacion < GeneticConfig.MAX_GEN){
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
		}
		return poblaciones.P.get(0);
	}
	
	public void setWindow(MainWindow window) {
		this.gui = window;
	}
	
}
