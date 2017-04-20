/**
 * 
 * @author Vamshedhar Reddy Chintala
 */

/**
 * Implementation of Dijkstra's Algorithm to find shortest path between two nodes of a vertex
 * 
 * @author vamshedhar
 *
 */
public class Dijkstra {
	private Graph G;			// Graph on which Algorithm has to be performed
	private MinHeap vertices;	// Priority Queue to sort vertices based on their distances from start vertex

	public Dijkstra(Graph g) {
		this.G = g;
	}
	
	/**
	 * Add all vertices in the graph to the priority queue
	 */
	private void resetVertices(){
		this.vertices = new MinHeap();
		for(Vertex V : G.vertices.values()){
			V.reset();
			this.vertices.insert(V);
		}
	}
	
	/**
	 * Prints path traversing reverse from the end vertex
	 * @param V End vertex on the path
	 * @return Path from start vertex to end vertex and shortest distance
	 */
	public String getPath(Vertex V){
		
		String path = V.name + " " + Math.round(V.distance * 100D) / 100D;
		
		while(V.previous != null){
			path = V.previous.name + " " + path;
			V = V.previous;
		}
		
		return path;
	}
	
	/**
	 * Algorithm for finding shortest path
	 * 
	 * @param startVertexName String start vertex name
	 * @param endVertexName String end vertex name
	 */
	public void findShortestPath(String startVertexName, String endVertexName){
		Vertex startVertex = G.vertices.get(startVertexName);
		Vertex endVertex = G.vertices.get(endVertexName);
		
		if(startVertex == null){
			System.out.println("Source vertex not found!");
			return;
		} else if(endVertex == null){
			System.out.println("Destination vertex not found!");
			return;
		}
		
		this.resetVertices();
		
		/*
		 * Set start vertex distance to zero and its color to Grey (as visited)
		 */
		this.vertices.increasePriority(startVertex.position, 0.0);
		startVertex.color = "Grey";
		
		Vertex V;
		
		/*
		 * Extract vertex with minimum distance from the start vertex.
		 * GREEDY STEP
		 */
		while((V = this.vertices.extractMin()) != null){
			
			/*
			 * Check distances to all its adjacent vertices 
			 * Only for the vertices which are active and if edge to that vertex is active
			 */
			for(Edge E : V.adjacent){
				if(E.active && E.endVertex.active && !E.endVertex.color.equals("Black")){
					
					/*
					 * If new distance to that vertex is less than the previously calculated distance,
					 * update the distance and increase priority in the min heap
					 */
					if(V.distance + E.weight < E.endVertex.distance){
						E.endVertex.previous = V;
						this.vertices.increasePriority(E.endVertex.position, V.distance + E.weight);
					}
					
					/*
					 * Set vertex color to Grey (as visited)
					 */
					E.endVertex.color = "Grey";
				}
			}
			
			/*
			 * Set vertex color to Black as the vertex is processed
			 */
			V.color = "Black";
			
		}
		
		/*
		 * Print the shortest path
		 */
		System.out.println(this.getPath(endVertex));
		
	}
	
}
