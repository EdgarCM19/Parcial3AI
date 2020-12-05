package gui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import genetic.GA;
import genetic.Individual;

public class MainWindow extends JFrame {
	
	private GA genetic;
	private String outputPath;
	
	private JPanel main_panel, lateral_panel, info_rna_panel, info_ga_panel,
				log_panel;
	private LineGraph graph_panel;
	private JButton btn_start, btn_save, btn_config_rna, btn_config_ga;
	private JTextArea log_area;
	
	private GridBagConstraints gbc = new GridBagConstraints();
	
	public MainWindow() {
		genetic = new GA();
		setSize(1080, 720);
		setTitle("Algoritmo genetico para entrenar una RNA");
		initComponents();
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void initComponents() {
		main_panel = new JPanel(new BorderLayout());
		//graph_panel = new PlotPanel("Individuos", x_plot, y_plot);
		graph_panel = new LineGraph("Comportamiento");
		lateral_panel = new JPanel(new BorderLayout());
		btn_start = new JButton("Start");
		btn_start.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						genetic.Genetic();
					}
				});
			}
				
		});
		main_panel.add(graph_panel, BorderLayout.CENTER);
		main_panel.add(btn_start, BorderLayout.WEST);
		this.add(main_panel);
	}
	
	public void updateGraph(ArrayList<Individual> poblacion) {
		System.out.println("++++++\n+++++\n+++++");
		int n = 0;
		double [] x = new double[poblacion.size()];
		double [] y = new double[poblacion.size()]; 
		for(Individual indi : poblacion) {
			x[n] = n;
			y[n++] = indi.fitness;
		}
		graph_panel.updateData(x, y);
	}

}
