package genetic;

import java.awt.Window;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import weka.core.pmml.jaxbbindings.LocalTransformations;

public class GA {

	public Population poblaciones;
	ArrayList<Double> time_per_gen;
	ArrayList<Float> average_per_gen;
	ArrayList<Integer> population_per_gen;
	
	public long start, stop;
	public double time;
	public int generacion;

	
	public GA(){
		poblaciones=new Population();
		generacion = 0;
	}
	
	public Individual Genetic(){
		System.out.println(getDate() + "-[GA]>Inicio del algoritmo");
		start = System.currentTimeMillis();
		System.out.println(getDate() + "-[GA]>Creando la población inicial");
		poblaciones.createPoblation();
		poblaciones.fitness();
		generacion = 0;
		stop = System.currentTimeMillis();
		time = ((double) ((stop - start)/1000) / 60);
		System.out.println(getDate() + "-[GA]>Tiempo de ejecución de la primer población: " + time + "minutos");
		
		time_per_gen = new ArrayList<Double>();
		average_per_gen = new ArrayList<Float>();
		population_per_gen = new ArrayList<Integer>();
		
		time_per_gen.add(time);
		average_per_gen.add(poblaciones.promFitness());
		population_per_gen.add(poblaciones.P.size());		
		
		while(poblaciones.maxFitness < GeneticConfig.LIMIT && generacion < GeneticConfig.MAX_GEN){
			start = System.currentTimeMillis();
			System.out.println(getDate() + "-[Generacion " + generacion + "]>Tamaño de la poblacion actual: " + poblaciones.P.size());
			System.out.println(getDate() + "-[Generacion " + generacion + "]>Promedio de la poblacion: " + poblaciones.promFitness());
			poblaciones.selection();
			System.out.println(getDate() + "-[Generacion " + generacion + "]>Poblacion sobreviviente: "+poblaciones.PS.size());
			poblaciones.crossover();
			System.out.println(getDate() + "-[Generacion " + generacion + "]>Poblacion nueva: "+poblaciones.PS.size());
			poblaciones.mutation();
			poblaciones.update();
			poblaciones.fitness();
			generacion++;
			System.out.println(getDate() + "-[Generacion " + generacion + "]>Max fitness: " + poblaciones.maxFitness);
			stop = System.currentTimeMillis();
			time = ((double) ((stop - start)/1000) / 60);
			System.out.println(getDate() + "-[Generacion " + generacion + "]>Tiepo de ejecucion: " + time);
			time_per_gen.add(time);
			average_per_gen.add(poblaciones.promFitness());
			population_per_gen.add(poblaciones.P.size());
		}
		System.out.println(getDate() + "-[GA]>Fin del algoritmo");
		return poblaciones.maxIndividuo;
	}
	
	private String getDate() {
		return DateTimeFormatter.ofPattern("dd/MM/yy HH:mm:ss").format(LocalDateTime.now());
	}
	
}
