import java.util.ArrayList;
import java.util.Collections;

public class Reachable {
	public Graph G;
	public String startVertexName;
	public ArrayList<String> reachable;
	
	public Reachable(Graph g, String startVertexName){
		this.G = g;
		this.reachable = new ArrayList<String>();
		this.startVertexName = startVertexName;
		this.resetVertices();
		this.traverseGraph();
	}
	
	public void resetVertices(){
		for(Vertex V : G.vertices.values()){
			V.reset();
		}
	}
	
	public void traverseGraph(){
		Vertex startVertex = this.G.getVertex(this.startVertexName);
		this.traverseGraph(startVertex);
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
		String output = this.startVertexName;
		
		Collections.sort(this.reachable);
		
		for(String vertexName : this.reachable){
			output += "\n    " + vertexName;
		}
		
		return output;
	}
	
}
