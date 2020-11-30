package genetic;

public class Nodo {
	public Individuo t;
    public Nodo sig, ant;
    public Nodo(Individuo ta){
        sig=ant = null;
        t = ta;
    }
    
    public Nodo(Individuo ta,Nodo s,Nodo a){
        sig = s;
        ant = a;
        t = ta;
    }
}
