package gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import genetic.GA;
import genetic.GeneticConfig;

public class MainWindow extends JFrame {
	
	private GA genetic;
	private String outputPath;
	
	private JPanel main_panel, lateral_panel, info_rna_panel, info_ga_panel,
				log_panel;
	//private PlotPanel graph_panel;
	private LineGraph graph_panel;
	private JButton btn_start, btn_save, btn_config_rna, btn_config_ga;
	private JTextArea log_area;
	
	private GridBagConstraints gbc = new GridBagConstraints();
	
	public MainWindow() {
		genetic = new GA();
		genetic.setWindow(this);
		setSize(1080, 720);
		setTitle("Algoritmo genetico para entrenar una RNA");
		initComponents();
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void initComponents() {
		main_panel = new JPanel(new BorderLayout());
		double [] x_plot = {};
		double [] y_plot = {};
		//graph_panel = new PlotPanel("Individuos", x_plot, y_plot);
		graph_panel = new LineGraph("Comportamiento");
		lateral_panel = new JPanel(new BorderLayout());
		info_rna_panel = new JPanel(new GridBagLayout());
		btn_config_rna = new JButton("Configurar");
		btn_config_rna.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Abrimos otra ventana xd");
			}
		});
		String info_rna = processRNAConfigs();
		gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        gbc.ipadx = 5;
        gbc.ipady = 5;
        gbc.insets = new Insets(3,2,3,2);
        gbc.anchor = GridBagConstraints.LINE_START;
        info_rna_panel.add(new JLabel(info_rna), gbc);
        gbc.gridy = 1;
        info_rna_panel.add(btn_config_rna, gbc);
		lateral_panel.add(info_rna_panel, BorderLayout.NORTH);
		info_rna_panel.add(new JLabel());
		btn_start = new JButton("Start");
		btn_start.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				genetic.Genetic();
			}
		});
		main_panel.add(graph_panel, BorderLayout.CENTER);
		main_panel.add(lateral_panel, BorderLayout.EAST);
		this.add(main_panel);
	}
	
	private String processRNAConfigs() {
		StringBuffer log = new StringBuffer();
		log.append("Nombre del archivo: [------]\n");
		log.append("Numero de clases: [------]\n");
		log.append("Numero de instancias: [------]\n");
		log.append("Rango de capas: [" + GeneticConfig.HIDDEN_RANGE[0] + ", " + GeneticConfig.HIDDEN_RANGE[1] +"]\n");
		log.append("Rango de neuronas: [" + GeneticConfig.NEURONS_RANGE[0] + ", " + GeneticConfig.NEURONS_RANGE[1] +"]\n");
		log.append("Rango de epocas: [" + GeneticConfig.EPOCHS_RANGE[0] + ", " + GeneticConfig.EPOCHS_RANGE[1] +"]\n");
		log.append("Rango de learning: [" + GeneticConfig.LEARNING_RANGE[0] + ", " + GeneticConfig.LEARNING_RANGE[1] +"]\n");
		log.append("Rango de momentum: [" + GeneticConfig.MOMENTUM_RANGE[0] + ", " + GeneticConfig.MOMENTUM_RANGE[1] +"]\n");
		return log.toString();
	}
	/*
	
	public void updatePlot(Poblacion poblacion) {
		//ArrayList<Double> x_data = new ArrayList<Double>();
		//ArrayList<Double> y_data = new ArrayList<Double>();
		System.out.println("--------------");
		System.out.println("Actualizateeeee");
		System.out.println("--------------");
		double [] x_data = new double[poblacion.parents.tam];
		double [] y_data = new double[poblacion.parents.tam];
		Nodo temp = poblacion.parents.inicio;
		int n = 0;
		while(temp != null) {
			x_data[n] = n;
			y_data[n++] = temp.t.fitness;
			temp = temp.sig;
		}
		for(double x : y_data)
			System.out.print(x + ", ");
		System.out.println("");
		graph_panel.updateData(x_data, y_data);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("###################################");
	}
	*/


}
