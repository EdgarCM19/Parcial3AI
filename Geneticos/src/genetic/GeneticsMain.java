package genetic;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import gui.MainWindow;

public class GeneticsMain {

	public static void main(String[] args) {
		/*
		GA gnr=new GA();
        Individuo ind=gnr.Genetic();
        System.out.println("El mejor individuo fue:");
        ind.mostrar();
        System.out.println("Que bonito y entendible es:");
        System.out.println(ind.params);
        System.out.println("Fitness"+ind.fitness);
        */
		/*
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		*/
		
		//new Principal().setVisible(true);
		//new MainWindow().setVisible(true);
		
		
		
		GA gnr=new GA();
        Individuo ind=gnr.Genetic();
        System.out.println("El mejor individuo fue:");
        ind.mostrar();
        System.out.println("Fitness"+ind.fitness);
        
		
		/*
		Individuo in = new Individuo();
		System.out.println(in.params);
		System.out.println("Entra: ");
		for(char c : in.cromosoma)
			System.out.print(c);
		System.out.println("");
		in.mutar();
		System.out.println("Sale: ");
		for(char c : in.cromosoma)
			System.out.print(c);
		System.out.println("");
		System.out.println(in.params);
		*/
	}
}