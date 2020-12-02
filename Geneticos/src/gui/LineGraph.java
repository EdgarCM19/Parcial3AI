package gui;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class LineGraph extends JPanel {

	private DefaultCategoryDataset dataset;
	private JFreeChart chart;
	private ChartPanel panel;
	
	private String name;

	public LineGraph(String name) {
		super(new BorderLayout());
		this.name = name;
		dataset = new DefaultCategoryDataset();
		chart = ChartFactory.createLineChart(
				this.name,
				"Individuos", 
				"Porcentaje",
				dataset,
				PlotOrientation.VERTICAL,
				true, true, false
		);
		panel = new ChartPanel(chart);
		this.add(panel, BorderLayout.CENTER);
	}
	
	public void updateData(double [] x, double [] y) {
		System.out.println("A C T U A L I Z A N D O . . .");
		for(int i = 0; i < x.length; i++)
			dataset.addValue(y[i], "Individuo", String.valueOf(x[i]));
		chart = ChartFactory.createLineChart(
				this.name,
				"Individuos", 
				"Porcentaje",
				dataset,
				PlotOrientation.VERTICAL,
				true, true, false
		);	
		//this.removeAll();
		panel = new ChartPanel(chart);
		//panel.removeAll();
		//panel.add(new ChartPanel(chart));
		panel.updateUI();
		//this.add(panel, BorderLayout.CENTER);
		updateUI();	
		
	}
	
	
}
