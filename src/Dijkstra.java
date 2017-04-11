
public class Dijkstra {
	public Graph G;
	MinHeap vertices;

	public Dijkstra(Graph g) {
		this.G = g;
	}
	
	public void resetVertices(){
		this.vertices = new MinHeap();
		for(Vertex V : G.vertices.values()){
			V.reset();
			this.vertices.insert(V);
		}
	}
	
	public String getPath(Vertex V){
		
		String path = V.name + " " + V.distance;
		
		while(V.previous != null){
			path = V.previous.name + " " + path;
			V = V.previous;
		}
		
		return path;
	}
	
	public void findShortestPath(String startVertexName, String endVertexName){
		Vertex startVertex = G.getVertex(startVertexName);
		Vertex endVertex = G.getVertex(endVertexName);
		
		this.resetVertices();
		
		startVertex.distance = 0;
		startVertex.color = "Grey";
		
		Vertex V;
		this.vertices.BuildMinHeap();
		
		while((V = this.vertices.extractMin()) != null){
			System.out.println(V.name);
			System.out.println(this.vertices);
			for(Edge E : V.adjacent){
				if(E.active && E.endVertex.active && !E.endVertex.color.equals("Black")){
					
					if(V.distance + E.weight < E.endVertex.distance){
						E.endVertex.previous = V;
						E.endVertex.distance = V.distance + E.weight;
					}
					
					E.endVertex.color = "Grey";
				}
			}
			
			V.color = "Black";
		}
		
		System.out.println(this.getPath(endVertex));
		
	}
	
}
