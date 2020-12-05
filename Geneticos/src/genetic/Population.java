package genetic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Population  {
	public ArrayList<Individual >P;
	public ArrayList<Individual >PS;
	public float maxFitness;
	public Individual maxIndividuo;
	
	public Population(){
		P = new ArrayList<Individual>();
		PS = new ArrayList<Individual>();
		maxFitness = 0f;
		maxIndividuo = null;
	}
	
	public void createPoblation(){
		for(int i = 0; i < GeneticConfig.INITIAL_POPULATION; i++)
			P.add(new Individual());
	} 
	
	public void fitness(){
		float max = -1f;
		Individual maxI = null;
		for(int i = 0; i < P.size(); i++){
			if(P.get(i).fitness == -1f) 			
				P.get(i).fitness(i); 
			if(P.get(i).fitness > max){
				max = P.get(i).fitness;
				maxI = P.get(i);
			}
		}
		if(max > maxFitness) {
			maxFitness = max;
			maxIndividuo = maxI;
		}
	}
	
	public float promFitness(){
		float n = 0f;
		for(int i = 0; i < P.size(); i++)
			n += P.get(i).fitness;
		n /= P.size();
		return n;
	}
	
	public float sumFitness(){
		float n = 0f;
		for(Individual e:P)
			n += e.fitness;
		return n;
	}
	
	public void selection(){
		int select = Math.round((( 1 - (GeneticConfig.SURVIVAL_FACTOR / 100) ) * P.size()));
		Collections.sort(P, (Individual p1, Individual p2) -> 
			new Float(p2.fitness).compareTo(new Float(p1.fitness))
		);
		for(int i = 0; i < select; i++)
			PS.add(P.get(i));

	}
	
	public boolean contains(char [] chromo) {
		for(Individual i : PS)
			if(Arrays.equals(i.cromosoma, chromo))
				return true;
		return false;
	}

	
	public void crossover() {
		pairSelecitonA(pairsByPercentage(GeneticConfig.SELECT_A_PERCENT));
		pairSelecitonB(pairsByPercentage(GeneticConfig.SELECT_B_PERCENT));
		pairSelecitonC(pairsByPercentage(GeneticConfig.SELECT_C_PERCENT));
		pairSelecitonD(pairsByPercentage(GeneticConfig.SELECT_D_PERCENT));
	}
	
	private int totalPairs() {
		return Math.round(((GeneticConfig.SURVIVAL_FACTOR / 100) * P.size())  / 2);
	}

	public int pairsByPercentage(int porcent) { 
		int total = totalPairs();
		return Math.round((porcent * total) / 100);
	}
	
	public void pairSelecitonA(int n) {
		for(int i = 0, control = 0; control < n;  control++, i += 2) {
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
	
	public boolean cruzar(Individual IA, Individual IB){
		
		char H1[] = copy(IA.cromosoma);
		char H2[] = copy(IB.cromosoma);
		char H3[] = copy(IA.cromosoma);
		int j = 0, i;
		boolean t = false;
		float pA = IA.fitness / (IA.fitness + IB.fitness);
		float pB = IB.fitness / (IA.fitness + IB.fitness);
		
		int nA = Math.round(GeneticConfig.HIDDEN_BITS * pA);
		int nB = Math.round(GeneticConfig.HIDDEN_BITS * pB);
		for(i = j; i < GeneticConfig.HIDDEN_BITS; i++){
			if(i >= nA)
				H1[i] = IB.cromosoma[i];
			if(i >= nB)
				H2[i] = IA.cromosoma[i];
			if(t)
				H3[i] = IB.cromosoma[i];
			t = !t;
		}
		j = i;
		
		nA = Math.round(GeneticConfig.NEURONS_BITS * pA) + j;
		nB = Math.round(GeneticConfig.NEURONS_BITS * pB) + j;
		for(i = j; i < GeneticConfig.NEURONS_BITS + j; i++){
			if(i >= nA)
				H1[i] = IB.cromosoma[i];
			if(i >= nB)
				H2[i] = IA.cromosoma[i];
			if(t)
				H3[i] = IB.cromosoma[i];
			t = !t;
		}
		j = i;
		
		nA = Math.round(GeneticConfig.EPOCH_BITS * pA) + j;
		nB = Math.round(GeneticConfig.EPOCH_BITS * pB) + j;
		for(i = j; i < GeneticConfig.EPOCH_BITS + j; i++){
			if(i >= nA) {
				H1[i] = IB.cromosoma[i];
				H3[i] = IB.cromosoma[i];
			}
			if(i >= nB)
				H2[i] = IA.cromosoma[i];
		}
		j = i;
		
		nA = Math.round(GeneticConfig.LEARNING_BITS * pA) + j;
		nB = Math.round(GeneticConfig.LEARNING_BITS * pB) + j;
		for(i = j; i < GeneticConfig.LEARNING_BITS + j; i++){
			if(i >= nA)
				H1[i] = IB.cromosoma[i];
			if(i >= nB)
				H2[i] = IA.cromosoma[i];
			if(t)
				H3[i] = IB.cromosoma[i]; 
			t = !t;
		}
		j = i;
		
		nA = Math.round(GeneticConfig.MOMENTUM_BITS * pA) + j;
		nB = Math.round(GeneticConfig.MOMENTUM_BITS * pB) + j;
		for(i = j; i < IA.cromosoma.length; i++){
			if(i >= nA){
				H1[i] = IB.cromosoma[i];
				H3[i] = IB.cromosoma[i];
			}
			if(i >= nB)
				H2[i] = IB.cromosoma[i];
			
		}
		if(!contains(H1))
			PS.add(new Individual(H1));
		if(!contains(H2))
			PS.add(new Individual(H2));
		if(!contains(H3))
			PS.add(new Individual(H3));
		return true;
		
	}
	
	public void mutation(){
		int por = Math.round((GeneticConfig.MUTATION * PS.size()) / 100);
		int r;
		ArrayList<Integer> l = new ArrayList<Integer>();
		while(por != 0){
			r = (int)(Math.random() * PS.size());
			if(!l.contains(r)){
				l.add(r);
				por--;
				PS.get(r).mutate();
			}
		}
	}
	
	public void update(){
		P.clear();
		P.addAll(PS);
		PS.clear();
	}
	
	public char[]copy(char C[]){
		char N[] = new char[C.length];
		for(int i = 0; i < C.length; i++){
			N[i] = C[i];
		}
		return N;
	}
}

