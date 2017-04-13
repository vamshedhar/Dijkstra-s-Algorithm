/**
 * 
 * @author Vamshedhar Reddy Chintala
 */

import java.util.ArrayList;
import java.util.Collections;

public class Reachable {
	private Graph G;
	private Vertex startVertex;
	private ArrayList<String> reachable;
	
	public Reachable(Graph g, Vertex startVertex){
		this.G = g;
		this.reachable = new ArrayList<String>();
		this.startVertex = startVertex;
		this.resetVertices();
		this.traverseGraph(startVertex);
	}
	
	public void resetVertices(){
		for(Vertex V : G.vertices.values()){
			V.reset();
		}
	}
	
	public void traverseGraph(Vertex startVertex){
		
		startVertex.color = "Grey";
		
		for(Edge E : startVertex.adjacent){
			if(E.active && E.endVertex.active && E.endVertex.color.equals("White")){
				reachable.add(E.endVertex.name);
				this.traverseGraph(E.endVertex);
			}
		}
		
		startVertex.color = "Black";
	}

	@Override
	public String toString() {
		String output = this.startVertex.name;
		
		Collections.sort(this.reachable);
		
		for(String vertex : this.reachable){
			output += "\n    " + vertex;
		}
		
		return output;
	}
	
}
