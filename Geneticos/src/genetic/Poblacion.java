package genetic;

import Exceptions.InvalidChromosomeSize;
import Exceptions.ParameterOutOfRange;

public class Poblacion {
	
	int tamP;
    Lista parents;
    Lista childs;
    float PromFitness;
    public Poblacion(){
        tamP=0;
        PromFitness=0f;
        parents=new Lista();
        childs=new Lista();
        
    }
    public void CreatePoblationR(){
        for(int i=0;i<GeneticConfig.INITIAL_POPULATION;i++){
            try {
				parents.insertarOrden(new Individuo());
			} catch (ParameterOutOfRange e) {
				e.printStackTrace();
			} catch (InvalidChromosomeSize e) {
				e.printStackTrace();
			}
        }
    }
    
    /*
    private void crossParameter(char [] chromosome_a, char [] chromosome_b, int param) {
    	int desp;
    	for(int i = 0; i < param; i++) desp += GeneticConfig.BIT_PER_PARAM[i];
    	int middle = GeneticConfig.BIT_PER_PARAM[param] + desp / 2;
    	for(int i = desp; ) {
    		chromosome_a[desp + ]
    	}
    }
    */
    
    public void cruzar(Individuo IA, Individuo IB) throws ParameterOutOfRange, InvalidChromosomeSize{
        
        char H1[]=copiar(IA.cromosoma);
        char H2[]=copiar(IB.cromosoma);
        int j=0,i;
        int n=(GeneticConfig.HIDDEN_BITS/2);
        for(i=j;i<GeneticConfig.HIDDEN_BITS ;i++){
            if(i>=n){
                H1[i]=IB.cromosoma[i];
                H2[i]=IA.cromosoma[i];
            }
        }
        j=i;
         
        n=(GeneticConfig.NEURONS_BITS/2)+j;
        for(i=j;i<GeneticConfig.NEURONS_BITS+j ;i++){
            if(i>=n){
                H1[i]=IB.cromosoma[i];
                H2[i]=IA.cromosoma[i];
            }
        }
        j=i;
        n=(GeneticConfig.EPOCH_BITS/2)+j;
        for(i=j;i<GeneticConfig.EPOCH_BITS+j ;i++){
            if(i>=n){
                H1[i]=IB.cromosoma[i];
                H2[i]=IA.cromosoma[i];
            }
        }
        j=i;
        n=(GeneticConfig.LEARNING_BITS/2)+j;
        for(i=j;i<GeneticConfig.LEARNING_BITS+j ;i++){
            if(i>=n){
                H1[i]=IB.cromosoma[i];
                H2[i]=IA.cromosoma[i];
            }
        }
        j=i;
        n=(GeneticConfig.MOMENTUM_BITS /2)+j;
        for(i=j;i<IA.cromosoma.length ;i++){
            if(i>=n){
                H1[i]=IB.cromosoma[i];
                H2[i]=IA.cromosoma[i];
            }
        }
 
		childs.insertarOrden(new Individuo(H2));
		childs.insertarOrden(new Individuo(H1));
        
    }
    public void cruzar2(Individuo IA, Individuo IB){
        
        char H1[]=IA.cromosoma;
        int j=0,i;
        boolean n=true;
        for(i=j;i<IA.cromosoma.length ;i++){
            if(n){
                H1[i]=IB.cromosoma[i];
            }
            n=!n;
        }
        
        
        try {
			childs.insertarOrden(new Individuo(H1));
		} catch (ParameterOutOfRange e) {
			e.printStackTrace();
		} catch (InvalidChromosomeSize e) {
			e.printStackTrace();
		}
        
    }
    
    public char[]copiar(char C[]){
        char N[]=new char[C.length];
        for(int i=0;i<C.length;i++){
            N[i]=C[i];
        }
        return N;
    }
    
}
