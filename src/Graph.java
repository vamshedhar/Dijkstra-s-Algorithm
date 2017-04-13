/**
 * 
 * @author Vamshedhar Reddy Chintala
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * 
 * Simple Graph class. Takes no parameters. Contains list of vertices and all related functions
 * 
 * @author vamshedhar
 */
public class Graph {

	// Use HashMap to store list of all the vertices
	public HashMap<String, Vertex> vertices = new HashMap<String, Vertex>();
	
	
	/**
	 * Check if a vertex exists with the given name
     * If yes returns the vertex
     * If no creates a new vertex with that name and returns the new vertex
     * 
	 * @param name String VertexName
	 * @return Vertex with given name
	 */
	public Vertex getVertex(String name){
		Vertex V = this.vertices.get(name);
		
		if(V == null){
			V = new Vertex(name);
			this.vertices.put(name, V);
		}
		
		return V;
	}
	
	/**
	 * Checks if the edge already exists or not
     * If yes returns updates the edge's transmission time
     * If no creates a new edge between the edges with the given weight
     * 
	 * @param tailVertexName String tail vertex name
	 * @param headVertexName String head vertex name
	 * @param weight Double transmission time
	 */
	public void addEdge(String tailVertexName, String headVertexName, double weight){
		Vertex tailVertex = this.getVertex(tailVertexName);
		Vertex headVertex = this.getVertex(headVertexName);
		
		Edge E = new Edge(tailVertex, headVertex, weight);
		
		int index = tailVertex.adjacent.indexOf(E);
		if(index == -1){
			tailVertex.adjacent.add(E);
		} else{
			tailVertex.adjacent.get(index).weight = E.weight;
		}
	}
	
	
	/**
	 * Removes edge from the adjacency list of TailVertex
	 * 
	 * @param tailVertexName String tail vertex name
	 * @param headVertexName String head vertex name
	 */
	public void deleteEdge(String tailVertexName, String headVertexName){
		Vertex tailVertex = this.vertices.get(tailVertexName);
		Vertex headVertex = this.vertices.get(headVertexName);
		
		if(tailVertex == null){
			System.out.println("Invalid Edge. Source vertex not found!");
		} else if(headVertex == null){
			System.out.println("Invalid Edge. Destination vertex not found!");
		} else{
			Edge E = new Edge(tailVertex, headVertex, 0.0);
			tailVertex.adjacent.remove(E);
		}
		
		
	}
	
	
	/**
	 * 
	 * @param tailVertexName String tail vertex name
	 * @param headVertexName String head vertex name
	 * @param status Boolean Status of edge
	 */
	public void updateEdgeStatus(String tailVertexName, String headVertexName, boolean status){
		Vertex tailVertex = this.vertices.get(tailVertexName);
		Vertex headVertex = this.vertices.get(headVertexName);
		
		if(tailVertex == null){
			System.out.println("Invalid Edge. Source vertex not found!");
		} else if(headVertex == null){
			System.out.println("Invalid Edge. Destination vertex not found!");
		} else{
			Edge E = new Edge(tailVertex, headVertex, 0.0);
			
			int index = tailVertex.adjacent.indexOf(E);
			if(index == -1){
				System.out.println("Edge not found!");
			} else{
				tailVertex.adjacent.get(index).active = status;
			}
		}
	}
	
	
	/**
	 * Marks the Edge DOWN
	 * 
	 * @param tailVertexName String tail vertex name
	 * @param headVertexName String head vertex name
	 */
	public void edgeDown(String tailVertexName, String headVertexName){
		this.updateEdgeStatus(tailVertexName, headVertexName, false);
	}
	
	/**
	 * Marks the Edge UP
	 * 
	 * @param tailVertexName String tail vertex name
	 * @param headVertexName String head vertex name
	 */
	public void edgeUp(String tailVertexName, String headVertexName){
		this.updateEdgeStatus(tailVertexName, headVertexName, true);
	}

	
	/**
	 * Updates the status of the Vertex to specified status
	 * 
	 * @param vertexName String vertex name
	 * @param status Status of vertex
	 */
	public void updateVertexStatus(String vertexName, boolean status){
		Vertex V = this.vertices.get(vertexName);
		
		if(V == null){
			System.out.println("Vertex not found!");
			return;
		}
		
		V.active = status;
	}
	
	
	/**
	 * Marks the Vertex DOWN
	 * 
	 * @param vertexName String vertex name
	 */
	public void vertexDown(String vertexName){
		this.updateVertexStatus(vertexName, false);
	}
	
	/**
	 * Marks the Vertex UP
	 * 
	 * @param vertexName String vertex name
	 */
	public void vertexUp(String vertexName){
		this.updateVertexStatus(vertexName, true);
	}
	
	/**
	 * 
     * Lists all the reachable vertices for all the UP vertices
     * It uses Reachable class to find all the reachable vertices from a specified start vertex
     */
	public void printReachable(){
		ArrayList<String> vertexNames = new ArrayList<String>(this.vertices.keySet());
		
		Collections.sort(vertexNames);
		
		for(String vertexName : vertexNames){
			Vertex vertex = this.vertices.get(vertexName);
			if(vertex.active){
				Reachable reachable = new Reachable(this, vertex);
				System.out.println(reachable);
			}	
		}
	}
	
	/**
	 * 
     * Overrides toString function to print Graph in required format
     */
	@Override
	public String toString() {
		
		ArrayList<String> vertexNames = new ArrayList<String>(this.vertices.keySet());
		
		Collections.sort(vertexNames);
		
		String output = "";
		
		for(String vertex : vertexNames){
			output += this.vertices.get(vertex);
		}
		
		return output;
	}
	
	public static void main(String[] args){
		
		// Validate arguments 
		if (args.length != 1) {
            System.err.println("Invalid Format!");
            System.err.println("Expected Format: java Graph <filename or filepath>");
            return;
        }

        String filename = args[0];
		
		Graph G = new Graph();
		
		try{
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			
			String line;
			while((line = reader.readLine()) != null) {
				
				StringTokenizer st = new StringTokenizer(line);
				
				if(st.countTokens() != 3){
					System.err.println("Invalid data!");
					reader.close();
					return;
				}
				
				String tailVertexName = st.nextToken();
				String headVertexName = st.nextToken();
				Double weight = Double.parseDouble(st.nextToken());
				
				G.addEdge(tailVertexName, headVertexName, weight);
				G.addEdge(headVertexName, tailVertexName, weight);
			}
			
			reader.close();
		} catch(FileNotFoundException e){
			System.err.println("File Not Found!");
			return;
		} catch(NumberFormatException e){
			System.err.println("Invalid Data!");
			return;
		} catch(IOException e){
			System.err.println("Invalid Data!");
			return;
		}
		
		Scanner s = new Scanner(System.in);
		
		/*
		 * 
		 * Wait for commands. 
		 * Reads commands and executes them. 
		 * Raises valid errors if any
		 */
		while(true){
			
			/*
			 * Valid Commands
			 * 		print		quit	reachable	addedge		deleteedge
			 * 		edgedown	edgeup	vertexdown	vertexup	path
			 */
			String command = s.nextLine();
			
			StringTokenizer st = new StringTokenizer(command);
			
			int tokenCount = st.countTokens();
			
			if(tokenCount == 0){
				continue;
			}
			
			String query = st.nextToken();
			
			if(query.equals("quit") && tokenCount == 1){
				s.close();
				return;
			} else if(query.equals("print") && tokenCount == 1){
				System.out.println(G);
			} else if(query.equals("reachable") && tokenCount == 1){
				G.printReachable();
			} else if(query.equals("addedge") && tokenCount == 4){

				String tailVertex = st.nextToken();
				String headVertex = st.nextToken();
				
				try{
					Double weight = Double.parseDouble(st.nextToken());
					G.addEdge(tailVertex, headVertex, weight);
				} catch(NumberFormatException e){
					System.out.println("Invalid Transmission Time!");
				}
				

			} else if(query.equals("deleteedge") && tokenCount == 3){

				String tailVertex = st.nextToken();
				String headVertex = st.nextToken();
				G.deleteEdge(tailVertex, headVertex);

			} else if(query.equals("edgedown") && tokenCount == 3){

				String tailVertex = st.nextToken();
				String headVertex = st.nextToken();
				G.edgeDown(tailVertex, headVertex);

			} else if(query.equals("edgeup") && tokenCount == 3){

				String tailVertex = st.nextToken();
				String headVertex = st.nextToken();
				G.edgeUp(tailVertex, headVertex);

			} else if(query.equals("vertexdown") && tokenCount == 2){

				String vertex = st.nextToken();
				G.vertexDown(vertex);

			} else if(query.equals("vertexup") && tokenCount == 2){

				String vertex = st.nextToken();
				G.vertexUp(vertex);

			} else if(query.equals("path") && tokenCount == 3){

				Dijkstra dj = new Dijkstra(G);
				String startVertex = st.nextToken();
				String endVertex = st.nextToken();
				dj.findShortestPath(startVertex, endVertex);
				
			} else{
				System.out.println("Invalid Command!");
			}
		}
	
	}
}
