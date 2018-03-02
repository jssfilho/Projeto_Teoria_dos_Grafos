package br.ucsal.jgraph;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;


public class Acoes extends JFrame {
	protected static mxGraph graph = new mxGraph();
	protected static HashMap m = new HashMap();
	private mxGraphComponent graphComponent;
	private JTextField texto;
	private JButton botaoAdd;
	private JButton botaoDel;
	private JButton botaoLigar;
	private Object cell;	
	
	public static HashMap getM() {
		return m;
	}

	public static mxGraph getGraph() {
		return graph;
	}

	public Acoes(){
		super("JGraph - Fluxograma");
		initGUI();
	}

	private void initGUI() {
		setSize(700, 500);
		setLocationRelativeTo(null);
		
		graphComponent = new mxGraphComponent(graph);
		graphComponent.setPreferredSize(new Dimension(670, 380));
		getContentPane().add(graphComponent);
		
		texto = new JTextField();
		getContentPane().add(texto);
        texto.setPreferredSize(new Dimension(420, 21));
        setLayout(new FlowLayout(FlowLayout.LEFT));
		
        botaoAdd = new JButton("Adicionar");
        getContentPane().add(botaoAdd);
        botaoAdd.addActionListener(new ActionListener() {
                        
            public void actionPerformed(ActionEvent e) {
            	AdicionarGrafo add = new AdicionarGrafo(texto.getText());
                texto.setText("");
            }
        });
        
        botaoDel = new JButton("Deletar");
        getContentPane().add(botaoDel);
        botaoDel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				graph.getModel().remove(cell);
				
			}
		});
        
        botaoLigar = new JButton("Ligar");
        getContentPane().add(botaoLigar);
        botaoLigar.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
               AdicionarLinha linha = new AdicionarLinha();
            	
            }
        });
        
        graphComponent.getGraphControl().addMouseListener(new MouseAdapter()
		{
		
			public void mouseReleased(MouseEvent e)
			{
				cell = graphComponent.getCellAt(e.getX(), e.getY());		
			}
		});
	}

}