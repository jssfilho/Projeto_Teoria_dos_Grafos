package br.ucsal.jgraph;

import java.util.HashMap;

import javax.swing.JOptionPane;

import com.mxgraph.view.mxGraph;


public class AdicionarLinha extends Acoes {
	public AdicionarLinha(){
		Object parent = this.getGraph().getDefaultParent();
		Object v1 = this.getM().get(JOptionPane.showInputDialog("Digite o grafo 1:"));
        Object v2 = this.getM().get(JOptionPane.showInputDialog("Digite o grafo 2:"));
        String nome = JOptionPane.showInputDialog("Digite o nome da linha:");
        this.getGraph().insertEdge(parent, null, nome, v1, v2);
        
	}

}
