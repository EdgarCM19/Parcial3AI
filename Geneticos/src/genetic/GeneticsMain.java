package genetic;

import java.text.DecimalFormat;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import gui.ChartsWindow;
import gui.MainWindow;
import rna.RNA;

public class GeneticsMain {

	public static void main(String[] args) {
		/*
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		System.out.println(systemProperties());
		new MainWindow().setVisible(true);
		*/
		
		System.out.println(systemProperties());
		System.out.println("\n\n");
		GA gnr=new GA();
        Individual ind=gnr.Genetic();
        System.out.println("El mejor individuo fue:");
        ind.mostrar();
        System.out.println(RNA.optionsToString(ind.params.hidden_layers, ind.params.neurons_per_layer, 
        		ind.params.epochs, ind.params.learning_rate, ind.params.momentum));
        System.out.println("Fitness"+ind.fitness);
        ChartsWindow graficas = new ChartsWindow();
        graficas.setVisible(true);
        graficas.plotTotalPercent(gnr.poblaciones);
        graficas.plotPercentPerGeneration(gnr.average_per_gen);
        graficas.plotTimePerGeneration(gnr.time_per_gen);
        graficas.plotPopulationSizePerGeneration(gnr.population_per_gen);
        graficas.repaint();
        

	}
	
	private static String systemProperties() {
		return  "------------[Entorno de ejecucion]------------\n" + 
				"[JVM]>Nucleos disponibles......." + Runtime.getRuntime().availableProcessors() + "\n" + 
				"[JVM]>Memoria libre disponible......." + formatMemory(Runtime.getRuntime().freeMemory()) + "\n" + 
				"[JVM]>Cantidad maxima de memoria....." + formatMemory(Runtime.getRuntime().maxMemory()) + "\n" + 
				"[JVM]>Cantidad total de memeoria....." + formatMemory(Runtime.getRuntime().totalMemory()) ;
	}
	
	private static String formatMemory(long memory) {
		final String [] units = new String[] {"B", "KB", "MB", "GB", "TB"};
		int unitIndex = (int)(Math.log10(memory) / Math.log10(1024));
		DecimalFormat df = new DecimalFormat("#,##0.#");
		String formattedValue = df.format(memory / Math.pow(1024, unitIndex));
		return formattedValue + " " + units[unitIndex];
	}
}
