package genetic;

public class Lista {

	public int tam;
	public Nodo inicio, fin;
	
	public Lista(){
		inicio = fin = null;
		tam = 0;
	}
	public void clean(){
		inicio=fin=null;
		tam=0;
	}
	public boolean vacia(){
		return inicio==null;
	}
	public void insertarF(Individuo t){
	    if(!vacia()){
	        fin = new Nodo(t, null, fin);
	        fin.ant.sig = fin;
	        tam++;
	    }else{
	        inicio = fin = new Nodo(t);
	        tam++;
	    }
	}
	public void insertarI(Individuo t){
		if(!vacia()){
			inicio=new Nodo(t,inicio,null);
			inicio.sig.ant=inicio;
			tam++;
		}else{
			inicio=fin=new Nodo(t);
			tam++;
		}
	}
	public void insertarOrden(Individuo t){
		if(!vacia()){
			if(t.fitness>inicio.t.fitness){
				inicio=new Nodo(t,inicio,null);
				inicio.sig.ant=inicio;
				tam++;
			}else{
				Nodo tem=inicio;
				Nodo aux=inicio.sig;
				boolean b=false;
				while(aux!=null&& !b){
					if(t.fitness>aux.t.fitness){
						Nodo nv=new Nodo(t,aux,tem);
						nv.sig.ant=nv;
						nv.ant.sig=nv;
						tam++;
						b=true;
					}
					tem=tem.sig;
					aux=aux.sig;
				}
				if(!b){
					fin=new Nodo(t,null,fin);
					fin.ant.sig=fin;
					tam++;
				}
			}
		}else{
			inicio=fin=new Nodo(t);
			tam++;
		}
	}
	public Individuo eliminarI(){
		if(!vacia()){
			Individuo te=inicio.t;
			if(inicio==fin){
				inicio=fin=null;
				return te;
			}else{
			   inicio=inicio.sig;
			   inicio.ant=null;
			   return te;
			}   
		}
		return null;
	} 
	public void eliminarP(int n){
		Nodo tem=inicio;
		tam=n;
		while(--n > 0 && tem != null){
			tem=tem.sig;
		}
		tem.sig=null;
		fin=tem; 
	}
	public void mostrarL(){
		if(!vacia()){
			Nodo tem=inicio;
			Nodo aux=inicio.sig;
			while(aux!=null){
				System.out.print(tem.t.fitness+" =>");
				tem=tem.sig;
				aux=aux.sig;
			}
			System.out.print(tem.t.fitness+" => null\n");
			System.out.println();
		}else{
			System.out.println("null");
			System.out.println();
		}
	}
	public void mostrarLCrom(){
		if(!vacia()){
			Nodo tem=inicio;
			Nodo aux=inicio.sig;
			int cont=0;
			while(aux!=null){
				System.out.println("Hijo "+cont+":");
				tem.t.mostrar();
				cont++;
				tem=tem.sig;
				aux=aux.sig;
			}
			System.out.println("Hijo "+cont+":");
			tem.t.mostrar();
			cont++;
			System.out.println();
		}else{
			System.out.println("null");
			System.out.println();
		}
	}
	public float Prom(){
		float n=0f;
		if(!vacia()){
			Nodo tem=inicio;
			Nodo aux=inicio.sig;
			while(aux!=null){
				n+=tem.t.fitness;
				tem=tem.sig;
				aux=aux.sig;
			}
			n+=tem.t.fitness;
		}
		n=n/tam;
		return n;
	}
	public void unir(Lista l){
		Nodo tem=l.inicio;
		Nodo aux=l.inicio.sig;
		while(aux!=null){
			insertarOrden(tem.t);
			tem=tem.sig;
			aux=aux.sig;
		}
		insertarOrden(tem.t);
	}
	public void fitness(){
		if(!vacia()){
			Nodo tem=inicio;
			Nodo aux=inicio.sig;
			while(aux!=null){
				tem.t.fitness();
				tem=tem.sig;
				aux=aux.sig;
			}
			tem.t.fitness();
		}
	}
	
}
