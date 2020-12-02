package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import genetic.Individuo;
import genetic.Poblation;

public class ChartsWindow extends JFrame {
	
	private JPanel main_panel;

	public ChartsWindow() {
		setSize(1080, 720);
		setTitle("Graficas");
		initComponents();
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void initComponents() {
		main_panel = new JPanel(new GridLayout(2, 2));
		this.add(main_panel);
	}
	
	public void plotTotalPercent(Poblation population) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		int cont = 0;
		for(Individuo individual : population.P) 
			dataset.addValue(individual.fitness, "Individuo", String.valueOf(cont++));
		JFreeChart chart = ChartFactory.createLineChart(
				"Porcentajes de la poblacion final",
				"Individuos", 
				"Porcentaje",
				dataset,
				PlotOrientation.VERTICAL,
				true, true, false
		);
		ChartPanel panel = new ChartPanel(chart);
		main_panel.add(panel);
	}
	
	public void plotPercentPerGeneration(ArrayList<Float> percent) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		int cont = 0;
		for(float per : percent) 
			dataset.addValue(per, "Generacion", String.valueOf(cont++));
		JFreeChart chart = ChartFactory.createBarChart(
				"Porcentajes por generación",
				"Generación", 
				"Porcentaje",
				dataset,
				PlotOrientation.VERTICAL,
				true, true, false
		);
		ChartPanel panel = new ChartPanel(chart);
		main_panel.add(panel);
	}
	
	public void plotTimePerGeneration(ArrayList<Double> time) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		int cont = 0;
		for(double per : time) 
			dataset.addValue(per, "Generacion", String.valueOf(cont++));
		JFreeChart chart = ChartFactory.createBarChart(
				"Tiempo de ejecución por generación",
				"Generación", 
				"Tiempo (m)",
				dataset,
				PlotOrientation.VERTICAL,
				true, true, false
		);
		ChartPanel panel = new ChartPanel(chart);
		main_panel.add(panel);
	}
	
	public void plotPopulationSizePerGeneration(ArrayList<Integer> size) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		int cont = 0;
		for(double per : size) 
			dataset.addValue(per, "Generacion", String.valueOf(cont++));
		JFreeChart chart = ChartFactory.createLineChart(
				"Tamaño de poblacion por generación",
				"Generación", 
				"Tamaño",
				dataset,
				PlotOrientation.VERTICAL,
				true, true, false
		);
		ChartPanel panel = new ChartPanel(chart);
		main_panel.add(panel);
	}
	
	
	
}
