package genetic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Poblation  {
	public int tamP;
	public ArrayList<Individuo >P;
	public ArrayList<Individuo >PS;
	public float MaxFitness;
	public Poblation(){
		P=new ArrayList<>();
		PS=new ArrayList<>();
		tamP=0;
		MaxFitness=0f;
	}
	public void createPoblation(){
		for(int i=0;i<GeneticConfig.INITIAL_POPULATION;i++){
			P.add(new Individuo());
		}
	}
	public void fitness(){
		System.out.println("#########");
		System.out.println("#########");
		System.out.println("#########");
		System.out.println("#########");
		float max=-1f;
		for(int i=0;i<P.size();i++){
			P.get(i).Fitness(); 
			if(P.get(i).fitness>max){
				max=P.get(i).fitness;
			}
		}
		MaxFitness=max;
		System.out.println(">>>>" + MaxFitness);
		System.out.println("-----------");
		System.out.println("-----------");
		System.out.println("-----------");
		
		
	}
	public float promFitness(){
		float n=0f;
		for(int i=0;i<P.size();i++){
			n+=P.get(i).fitness;
		}
		n=n/P.size();
		return n;
	}
	public float sumFitness(){
		float n=0f;
		for(Individuo e:P){
			n+=e.fitness;
		}
		return n;
	}
	public void Selection(){
		int select=Math.round(((1-GeneticConfig.SURVIVAL_FACTOR )*P.size()));
		Collections.sort(P, (Individuo p1, Individuo p2) -> new Float(p2.fitness).compareTo(new Float(p1.fitness)) // Aqui esta el truco, ahora comparamos p2 con p1 y no al reves como antes
		);
		for(int i=0;i<select;i++){
			PS.add(P.get(i));
		}
		
	}
	
	public boolean contains(char [] chromo) {
		for(Individuo i : PS)
			if(Arrays.equals(i.cromosoma, chromo))
				return true;
		return false;
	}
	
	public int pair(){
		return Math.round((GeneticConfig.SURVIVAL_FACTOR*P.size())/2);
	}
	public void crossover(){
		selectPairs();
	}
	public void selectPairs() {
		pairSelecitonA(parejasPorPorcen(40));
		pairSelecitonB(parejasPorPorcen(20));
		pairSelecitonC(parejasPorPorcen(20));
		pairSelecitonD(parejasPorPorcen(20));
	}
	
	private int totalPairs() {
		return Math.round((GeneticConfig.SURVIVAL_FACTOR * P.size())  / 2);
	}

	public int parejasPorPorcen(int porcent) { // Cambiar por pairsByPercentage
		int total = totalPairs();
		return Math.round((porcent * total) / 100);
	}
	
	public void pairSelecitonA(int n) {
		for(int i = 0, control = 0; control < n;  control++, i+=2) {
			boolean flag = false;
			do {				
				flag = cruzar(P.get(i), P.get(i + 1));
			} while(!flag);
		}
	}
	
	public void pairSelecitonB(int n) {
		for(int i = 0; i < n; i++) {
			boolean flag = false;
			int middle = P.size() / 2;
			do {				
				flag = cruzar(P.get(middle - i), P.get(P.size()-1 - i));
			} while(!flag);
		}
	}
	
	public void pairSelecitonC(int n) {
		for(int i = 0; i < n; i++) {
			boolean flag = false;
			int rnd_a, rnd_b;
			Random rnd = new Random();
			do {				
				rnd_a = rnd.nextInt(P.size());
				rnd_b = rnd.nextInt(P.size());
			} while(rnd_a == rnd_b);
			do {				
				flag = cruzar(P.get(rnd_a), P.get(rnd_b));
			} while(!flag);
		}
	}
	
	public void pairSelecitonD(int n) {
		for(int i = 0; i < n; i++) {
			boolean flag = false;
			int rnd_a;
			Random rnd = new Random();
			do {				
				rnd_a = rnd.nextInt(P.size());
			} while(rnd_a == i);
			do {				
				flag = cruzar(P.get(i), P.get(rnd_a));
			} while(!flag);
		}
	}
	
	public boolean cruzar(Individuo IA, Individuo IB){
		
		char H1[]=copy(IA.cromosoma);
		char H2[]=copy(IB.cromosoma);
		char H3[]=copy(IB.cromosoma);
		char H4[]=copy(IA.cromosoma);
		int j=0,i;
		boolean t=false;
		int n=(GeneticConfig.HIDDEN_BITS /2);
		for(i=j;i<GeneticConfig.HIDDEN_BITS  ;i++){
			if(i>=n){
				H1[i]=IB.cromosoma[i];
				H2[i]=IA.cromosoma[i];
			}
			if(t){
				H3[i]=IA.cromosoma[i];
				H4[i]=IB.cromosoma[i];
			}
			t=!t;
		}
		j=i;
		 
		n=(GeneticConfig.NEURONS_BITS /2)+j;
		for(i=j;i<GeneticConfig.NEURONS_BITS +j ;i++){
			if(i>=n){
				H1[i]=IB.cromosoma[i];
				H2[i]=IA.cromosoma[i];
			}
			if(t){
				H3[i]=IA.cromosoma[i];
				H4[i]=IB.cromosoma[i];
			}
			t=!t;
		}
		j=i;
		n=(GeneticConfig.EPOCH_BITS/2)+j;
		for(i=j;i<GeneticConfig.EPOCH_BITS+j ;i++){
			if(i>=n){
				H1[i]=IB.cromosoma[i];
				H2[i]=IA.cromosoma[i];
				H4[i]=IB.cromosoma[i];
			}
			if(t){
				H3[i]=IA.cromosoma[i];
			}
			t=!t;
		}
		j=i;
		n=(GeneticConfig.LEARNING_BITS/2)+j;
		for(i=j;i<GeneticConfig.LEARNING_BITS+j ;i++){
			if(i>=n){
				H1[i]=IB.cromosoma[i];
				H2[i]=IA.cromosoma[i];
			}
			if(t){
				H3[i]=IA.cromosoma[i]; 
			}else{
				H4[i]=IB.cromosoma[i];
			}
			t=!t;
		}
		j=i;
		n=(GeneticConfig.MOMENTUM_BITS /2)+j;
		for(i=j;i<IA.cromosoma.length ;i++){
			if(i>=n){
				H1[i]=IB.cromosoma[i];
				H2[i]=IA.cromosoma[i];
			}else{
				H4[i]=IB.cromosoma[i];
			}
			if(t){
				H3[i]=IA.cromosoma[i];
			}
			t=!t;
		}
		if(!contains(H2))
			PS.add(new Individuo(H2));
		if(!contains(H1))
			PS.add(new Individuo(H1));
		if(!contains(H3))
			PS.add(new Individuo(H3));
		if(!contains(H4))
			PS.add(new Individuo(H4));
		return true;
		
	}
	public void mutation(){
		int por=Math.round((GeneticConfig.MUTATION*PS.size())/100);
		int r;
		ArrayList<Integer> l=new ArrayList<Integer>();
		while(por!=0){
			r=(int)(Math.random()*PS.size());
			if(!l.contains(r)){
				l.add(r);
				por--;
				PS.get(r).mutar();
			}
		}
		System.out.println("Nuemero de indi mutados: " + l.size());
	}
	public void udDate(){
		P.clear();
		P.addAll(PS);
		PS.clear();
	}
	public void prFuction(){
		float n=sumFitness();
		for(int i=0;i<P.size();i++){
			P.get(i).pr=P.get(i).fitness/n;
		}
	}
	public char[]copy(char C[]){
		char N[]=new char[C.length];
		for(int i=0;i<C.length;i++){
			N[i]=C[i];
		}
		return N;
	}
	
	
}

