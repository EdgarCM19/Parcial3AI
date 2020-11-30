package genetic;

import Exceptions.InvalidChromosomeSize;
import Exceptions.ParameterOutOfRange;

public class GA {

Poblacion poblaciones;
	
	public GA(){
		poblaciones=new Poblacion();
	}
	public Individuo Genetic(){
		System.out.println("[GA]>Start");
		poblaciones.CreatePoblationR();
		poblaciones.parents.fitness();
		int generacion=0;
		System.out.println("Creacion primera poblacion");
		
		//while(poblaciones.parents.Prom()<GeneticConfig.LIMIT){
		while(poblaciones.parents.inicio.t.fitness < GeneticConfig.LIMIT && generacion < GeneticConfig.MAX_GEN) {
			int mutationRate=GeneticConfig.MUTATION ;
			System.out.println("[GENERATION]>Prom: "+poblaciones.parents.Prom());
			System.out.println("[GENERATION]>Number: "+generacion);
			Nodo tem=poblaciones.parents.inicio;
			Nodo aux=poblaciones.parents.inicio.sig;
			Nodo next=poblaciones.parents.inicio.sig.sig;
			while(next!=null){
				try {
					poblaciones.cruzar(tem.t, aux.t);
				} catch (ParameterOutOfRange e) {
					System.err.println(e);
					e.printStackTrace();
				} catch (InvalidChromosomeSize e) {
					e.printStackTrace();
				}
				tem=tem.sig;
				aux=aux.sig;
				next=next.sig;
			}
			System.out.println("Cruza ");
			try {
				poblaciones.cruzar(tem.t, aux.t);
			} catch (ParameterOutOfRange | InvalidChromosomeSize e) {
				System.err.println(e);
				e.printStackTrace();
			}
		
			tem=poblaciones.childs.inicio;
			while(tem!=null&&mutationRate>0){
					int r=(int)(Math.random()*60);
					if(r<16){
						tem.t.mutar();
						mutationRate--;
					}
					tem=tem.sig;
			}
			poblaciones.childs.fitness();
			System.out.println("Eliminacion ");
			poblaciones.parents.eliminarP(GeneticConfig.SURVIVAL_FACTOR/2);
			poblaciones.childs.eliminarP(GeneticConfig.SURVIVAL_FACTOR/2);
			poblaciones.parents.unir(poblaciones.childs);
			poblaciones.childs.clean();
			generacion++;
		}
		System.out.println("[GA]>END");
		return poblaciones.parents.inicio.t;
	}
	
}
