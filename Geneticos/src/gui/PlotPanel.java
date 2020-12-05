package gui;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class PlotPanel extends JPanel {
	
	private XYDataset dataset;
	private JFreeChart plot;
	private ChartPanel plotPanel; 
	
	public PlotPanel(String name, double[] x, double [] y) {
		super(new BorderLayout());
		initPlot(name, x, y);
		System.out.println("Ahhhhhh");
		System.out.println("Ahhhhhh");
		System.out.println("Ahhhhhh");
	}
	
	private void initPlot(String name, double [] x, double [] y) {
		dataset = createDataSet(name, x, y);
		plot = createPlot(dataset);
		
		plotPanel = new ChartPanel(plot);
		plotPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		plotPanel.setBackground(Color.cyan);
		this.add(plotPanel, BorderLayout.CENTER);
	}
	
	private XYDataset createDataSet(String name, double [] x, double [] y) {
		DefaultXYDataset meh = new DefaultXYDataset();
		XYSeries series = new XYSeries(name);
		for(int i = 0; i < x.length; i++) {
			series.add(x[i], y[i]);
		}
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);
        return dataset;
	}

	private JFreeChart createPlot(XYDataset dataset) {
		JFreeChart chart = ChartFactory.createXYLineChart(
                "% RNAe",
                "Individuo",
                "% Alcanzado",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        XYPlot plot = chart.getXYPlot();

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));

        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.white);

        chart.getLegend().setFrame(BlockBorder.NONE);

        chart.setTitle(new TextTitle("Comportamiento del algoritmo genetico",
                        new Font("Serif", java.awt.Font.BOLD, 18)
                )
        );

        return chart;
	}
	
	public void updatePanel(String name, double [] x, double [] y) {
		System.out.println("Poke?");
		this.removeAll();
		initPlot(name, x, y);
	}
	
}
