/**
 * 
 * @author Vamshedhar Reddy Chintala
 */

/**
 * Edge class to represent an edge in a Graph
 * @author vamshedhar
 *
 */
public class Edge implements Comparable<Edge> {
	public Vertex startVertex;
	public Vertex endVertex;
	public double weight;
	public boolean active;
	
	/**
	 * Initializes edge from tail vertex to edge vertex with specified weight 
	 * @param startVertex
	 * @param endVertex
	 * @param weight
	 */
	public Edge(Vertex startVertex, Vertex endVertex, double weight) {
		this.startVertex = startVertex;
		this.endVertex = endVertex;
		this.weight = weight;
		this.active = true;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((endVertex == null) ? 0 : endVertex.hashCode());
		result = prime * result + ((startVertex == null) ? 0 : startVertex.hashCode());
		return result;
	}

	/**
	 * Two edges are equal when both their start and end vertices are same
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Edge other = (Edge) obj;
		if (endVertex == null) {
			if (other.endVertex != null)
				return false;
		} else if (!endVertex.equals(other.endVertex))
			return false;
		if (startVertex == null) {
			if (other.startVertex != null)
				return false;
		} else if (!startVertex.equals(other.startVertex))
			return false;
		return true;
	}
	
	/**
	 * Compares edges based on their end vertex names
	 */
	@Override
	public int compareTo(Edge E) {
		// TODO Auto-generated method stub
		return this.endVertex.name.compareTo(E.endVertex.name);
	}
	
	/**
	 * Prints edge in specified format
	 */
	@Override
	public String toString() {
		String output = "  " + this.endVertex.name + " " + this.weight;
		
		if(!this.active){
			output += " DOWN";
		}
		
		return output;
	}	
}
