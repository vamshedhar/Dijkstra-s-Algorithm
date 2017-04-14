/**
 * 
 * @author Vamshedhar Reddy Chintala
 */

import java.util.ArrayList;
import java.util.Collections;

/**
 * Reachable class to find all the nodes in the graph 
 * reachable from a valid start vertex.
 * 
 * Here we use Depth First Search(DFS) to find the reachable vertices.
 * We don't use Dijkistra's as we are not concerned about the distances.
 *  
 * In DFS starting from a vertex and reaching back to the same vertex 
 * we would have traversed to all the vertices that are reachable from the start vertex 
 * 
 * Every time we discover a new vertex we store its name into a List of reachable vertices
 * 
 * @author vamshedhar
 *
 */
public class Reachable {
	private Graph G;
	private Vertex startVertex;
	private ArrayList<String> reachable;
	
	/**
	 * Initializes the class. Traverses the graph using DFS from start vertex.
	 * Stores all the encountered(reachable) vertices into the list.
	 * 
	 * @param g 			Graph to find reachable vertices
	 * @param startVertex	Vertex of which reachable vertices are to be found
	 */
	public Reachable(Graph g, Vertex startVertex){
		/*
		 * resetVertices takes O(|V|) time. Check specific function for explanation.
		 * traverseGraph takes O(|E|) time. Check specific function for explanation.
		 * 
		 * Over all the following algorithm takes O(|V| + |E|) time in worst case.
		 */
		this.G = g;
		this.reachable = new ArrayList<String>();
		this.startVertex = startVertex;
		this.resetVertices();
		this.traverseGraph(startVertex);
	}
	
	/**
	 * Resets all extra parameters of the vertices to their default values.
	 * Sets vertex color to White. Parent as null.
	 */
	public void resetVertices(){
		/*
		 * This loop runs O(|V|) times. |V| is number of vertices.
		 */
		for(Vertex V : G.vertices.values()){
			V.reset();
		}
	}
	
	/**
	 * Traverse the graph recursively and store the reachable vertices in a List
	 * 
	 * @param startVertex Vertex for which we have to find out reachable vertices
	 */
	public void traverseGraph(Vertex startVertex){
		
		/*
		 * Set the discovered vertex color to grey.
		 * Then check for all the outgoing UP edges from this vertex.
		 * If the vertex is active and is being visited for the first 
		 * time add it to the reachable list and recursively traverse
		 * starting from that vertex.
		 * 
		 * This function on a worst case is recursively called one for 
		 * each vertex. And for each vertex it runs a loop on all its
		 * edges ie., O(|adj(V)|). 
		 * 
		 * Total number of times the function runs is SUM of outgoing 
		 * edges of all the vertices ie., O(|E|)
		 */
		startVertex.color = "Grey";
		
		for(Edge E : startVertex.adjacent){
			if(E.active && E.endVertex.active && E.endVertex.color.equals("White")){
				/*
				 * Add to list and recursively traverse starting from this vertex
				 */
				reachable.add(E.endVertex.name);
				this.traverseGraph(E.endVertex);
			}
		}
		
		/*
		 * When all the outgoing edges from the vertex have been processed,
		 * mark the vertex as completed by setting its color to black
		 */
		startVertex.color = "Black";
	}
	
	/**
	 * Returns the start vertex along with all the reachable vertices
	 * in sorted order of their name. 
	 */
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
