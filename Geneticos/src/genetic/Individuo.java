package genetic;

import Exceptions.InvalidChromosomeSize;
import Exceptions.ParameterOutOfRange;
import rna.RNAConfig;

public class Individuo {
	
	public char cromosoma[];
	public float fitness;
	public RNAConfig params;
	
	public Individuo() throws ParameterOutOfRange, InvalidChromosomeSize{
		fitness=0; 
		cromosoma=new char[GeneticConfig.CHROMOSOME_SIZE];
		createRamdom();
		params = new RNAConfig(cromosoma);
	}
	
	public Individuo(char[]crom) throws ParameterOutOfRange, InvalidChromosomeSize{
		fitness=0; 
		cromosoma=crom;
		params = new RNAConfig(cromosoma);
	}
	
	public void createRamdom(){
		/*
		for(int i = 0;i < cromosoma.length; i++){
			cromosoma[i] = Character.forDigit(((int)(Math.random() * 2)) , 10);
		}*/
		cromosoma = GeneticConfig.randomChromosome();
	}
	
	public void fitness(){
		/*
		float n = 0f;
		for(char c : cromosoma){
			if (c == '1')
				n++;
		}
		fitness = n;
		*/
		fitness = Fitness.fit(params);
	}
	
	public void mutar(){
		do{
			char cromx[] = cromosoma;
			int r = (int)(Math.random() * cromx.length);
			cromx[r] = (char)(cromx[r]^1);
		}while(!validar());
	}
	
	
	//--------
	public boolean validar(){
		return true;
	}
	//---------
	
	public char[] intToChar(int n){
		String binary=Integer.toBinaryString(n);
		char C[]=binary.toCharArray();
		return C;
	}
	
	public void mostrar(){
		for(char cr : cromosoma){
			System.out.print(cr);
		}
		System.out.println("\n");
	}

}
