package br.ucsal.jgraph;

import java.util.HashMap;

import com.mxgraph.view.mxGraph;


public class AdicionarGrafo extends Acoes {	
	
	public AdicionarGrafo(String nome){
		this.getGraph().getModel().beginUpdate();
		Object parent = this.getGraph().getDefaultParent(); 
		Object v1 = this.getGraph().insertVertex(parent, null, nome, 330, 30, 100, 50);
		this.getM().put(nome, v1);
		this.getGraph().getModel().endUpdate();
	}

}