/**
 * 
 * @author Vamshedhar Reddy Chintala
 */

import java.util.ArrayList;
import java.util.Collections;

/**
 * Vertex class which represent's vertex in a graph.
 * Takes vertex name as parameter
 * 
 * @author vamshedhar
 */
public class Vertex implements Comparable<Vertex>{
	public String name;					// Name of the vertex
	public ArrayList<Edge> adjacent;	// List of all the edges connecting adjacent vertices
	public boolean active;				// Status of vertex UP/DOWN
	
	/*
	 * these arguments are for performing BFS/DFS
	 */
	public String color;				// Color of vertex on traversing
	public double distance;				// Shortest distance from source vertex
	public Vertex previous;				// Previous vertex on the shortest path
	public int position;				// Position of vertex in the MinHeap Array for Dijkstra's
	
	
	/**
	 * Initializes vertex with no adjacent vertices
	 * 
	 * @param name String vertex name
	 */
	public Vertex(String name) {
		this.name = name;
		this.adjacent = new ArrayList<Edge>();
		this.active = true;
		this.reset();
	}
	
	/**
	 * Checks if given vertex is adjacent to the current vertex
	 * 
	 * @param V Vertex 
	 * @return True if vertex V is adjacent to current vertex else false
	 */
	public boolean isAdjacent(Vertex V){
		return this.adjacent.contains(V);
	}
	
	/**
	 * Resets traversal parameters to their defaults
	 */
	public void reset(){
		this.distance = Double.POSITIVE_INFINITY;
		this.previous = null;
		this.color = "White";
	}
	
	/**
	 * Compares two vertices based on their distance from the source vertex.
	 */
	@Override
	public int compareTo(Vertex V) {
		// TODO Auto-generated method stub
		return Double.compare(this.distance, V.distance);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	/**
	 * Two vertices are equal if they have same name
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vertex other = (Vertex) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	/**
	 * Prints Vertex and its adjacent vertices in sorted order of their names in specified format
	 */
	@Override
	public String toString() {
		String output = this.name;
		
		if(!this.active){
			output += " DOWN";
		}
		
		output += "\n";
		
		Collections.sort(this.adjacent);
		
		for(Edge e : this.adjacent){
			output += e.toString();
			output += "\n";
		}
		
		
		return output;
	}
	
}
