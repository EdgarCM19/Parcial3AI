package genetic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import rna.RNA;
import rna.RNAConfig;

public class Individuo {
	
	char cromosoma[];
	float fitness;
	float pr;
	RNAConfig params;
	
	
	public Individuo(){
	  fitness=0; 
	  cromosoma=new char[GeneticConfig.CHROMOSOME_SIZE];
	  createRamdom();
	  pr=0f;
	  params = new RNAConfig(cromosoma);
	}
	public Individuo(char[]crom){
	  fitness=0; 
	  cromosoma=crom;
	  pr=0f;
	  params = new RNAConfig(cromosoma);
	}
	public void createRamdom(){
		/*
		for(int i=0;i<cromosoma.length;i++){
			cromosoma[i]=Character.forDigit(((int)(Math.random()*2)) , 10);
		}
		*/
		cromosoma = GeneticConfig.randomChromosome();
	}
	public void Fitness(){
		/*
		float n=0f;
		for(char c:cromosoma){
			if (c=='1')
				n++;
		}
		fitness=n;
		*/
		this.fitness = RNA.trainRNA(params.hidden_layers, 
				params.neurons_per_layer, 
				params.epochs, 
				params.learning_rate, 
				params.momentum);
	}
	public void mutar(){ //Cambiar a boolean y manejar desde poblacion
		System.out.println("Entro en el mutra de individuo");
		ArrayList<Integer> l =new ArrayList<Integer>();
		RNAConfig validation;
		char [] cromx;
		/*
		do{
			cromx = Arrays.copyOf(cromosoma, cromosoma.length);
			//int r=(int)(Math.random()*cromx.length);
			int r = new Random().nextInt(cromosoma.length - 1);
			if(!l.contains(r)){
				l.add(r);
				cromx[r]=(char)(cromx[r]^1);   
			}
			validation = new RNAConfig(cromx);
		}while(!validation.valid && l.size() != cromx.length);
		*/
		cromx = Arrays.copyOf(cromosoma, cromosoma.length);
		int r = new Random().nextInt(cromosoma.length - 1);
		cromx[r]=(char)(cromx[r]^1);
		validation = new RNAConfig(cromx);
		if(validation.valid) cromosoma = cromx;
		params = new RNAConfig(cromosoma);
		System.out.println("Se muto algo");
	}
	public boolean validar(){
		return params.valid;
	}
	
	public char[] intToChar(int n){
		String binary=Integer.toBinaryString(n);
		char C[]=binary.toCharArray();
		return C;
	}
	
	public void mostrar(){
		for(char cr:cromosoma){
			System.out.print(cr);
		}
		System.out.println("\n");
	}

}
