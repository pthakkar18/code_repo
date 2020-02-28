package com.example.demo;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Node {
    
    private String name;
     
    private List<Node> shortestPath = new LinkedList<Node>();
     
    private Integer distance = Integer.MAX_VALUE;
    
    private Integer avgWaitingTime = 0;
    
    Map<Node, Integer> adjacentNodes = new HashMap<Node, Integer>();
 
    private String routeName;
    
    public String getRouteName() {
		return routeName;
	}


	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}


	public void addDestination(Node destination, int distance) {
        adjacentNodes.put(destination, distance);
    }
  
    
    public Integer getAvgWaitingTime() {
		return avgWaitingTime;
	}


	public void setAvgWaitingTime(Integer avgWaitingTime) {
		this.avgWaitingTime = avgWaitingTime;
	}


	public Node(String name) {
        this.name = name;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Node> getShortestPath() {
		return shortestPath;
	}

	public void setShortestPath(List<Node> shortestPath) {
		this.shortestPath = shortestPath;
	}

	public Integer getDistance() {
		return distance;
	}

	public void setDistance(Integer distance) {
		this.distance = distance;
	}

	public Map<Node, Integer> getAdjacentNodes() {
		return adjacentNodes;
	}

	public void setAdjacentNodes(Map<Node, Integer> adjacentNodes) {
		this.adjacentNodes = adjacentNodes;
	}
     
}
