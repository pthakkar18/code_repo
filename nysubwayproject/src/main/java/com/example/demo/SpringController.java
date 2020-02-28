package com.example.demo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SpringController {

	@Value("${welcome.message:test}")
	private String message = "Hello World";


	@RequestMapping("/")
	public String welcome(Map<String, Object> model) {
		model.put("message", this.message);
		return "welcome";
	}

	@RequestMapping("/loadData")
	public String loadData(@RequestParam("destinationName") String destinationName, Map<String, Object> model) {

		Node nodeA = new Node("34Street");
		Node nodeB = new Node("42streetportauthority");
		Node nodeC = new Node("rockfellercenter");
		Node nodeD = new Node("worldtradecenter");
		Node nodeE = new Node("brooklyn");
		Node nodeF = new Node("coneyIsland");

		nodeA.addDestination(nodeB, 10);
		nodeA.setAvgWaitingTime(1);
		nodeA.addDestination(nodeC, 15);

		nodeB.addDestination(nodeD, 12);
		nodeB.setAvgWaitingTime(1);
		nodeB.addDestination(nodeF, 15);
		nodeB.setRouteName("A->B");
		
		nodeC.addDestination(nodeE, 10);
		nodeC.setAvgWaitingTime(2);
		nodeC.setRouteName("A->C");
		
		nodeD.addDestination(nodeE, 2);
		nodeD.setAvgWaitingTime(2);
		nodeD.addDestination(nodeF, 1);
		nodeD.setRouteName("A->B->D");

		nodeE.setRouteName("A->B->D->E");
		nodeF.setAvgWaitingTime(1);
		nodeF.addDestination(nodeE, 5);
		nodeF.setRouteName("A->B->D->F");
		Graph graph = new Graph();

		graph.addNode(nodeA);
		graph.addNode(nodeB);
		graph.addNode(nodeC);
		graph.addNode(nodeD);
		graph.addNode(nodeE);
		graph.addNode(nodeF);

		graph = calculateShortestPathFromSource(graph, nodeA);
		List<Node> nodeLists = new ArrayList<Node>();
		for (Node node : graph.getNodes()) {
			if (destinationName.equalsIgnoreCase(node.getName())) {
//			System.out.println("Travel time from Node A to Node " + node.getName() + " is: " + node.getDistance());
				nodeLists.add(node);
			}
		}

		model.put("nodeList", nodeLists);
		return "welcome";
	}

	public static Graph calculateShortestPathFromSource(Graph graph, Node source) {
		source.setDistance(0);

		Set<Node> settledNodes = new HashSet<Node>();
		Set<Node> unsettledNodes = new HashSet<Node>();

		unsettledNodes.add(source);

		while (unsettledNodes.size() != 0) {
			Node currentNode = getLowestDistanceNode(unsettledNodes);
			unsettledNodes.remove(currentNode);
			for (Entry<Node, Integer> adjacencyPair : currentNode.getAdjacentNodes().entrySet()) {
				Node adjacentNode = adjacencyPair.getKey();
				Integer edgeWeight = adjacencyPair.getValue();
				if (!settledNodes.contains(adjacentNode)) {
					CalculateMinimumDistance(adjacentNode, edgeWeight, currentNode);
					unsettledNodes.add(adjacentNode);
					
				}
			}
			settledNodes.add(currentNode);
		}
		return graph;
	}

	private static Node getLowestDistanceNode(Set<Node> unsettledNodes) {
		Node lowestDistanceNode = null;
		int lowestDistance = Integer.MAX_VALUE;
		for (Node node : unsettledNodes) {
			int nodeDistance = node.getDistance();
			if (nodeDistance < lowestDistance) {
				lowestDistance = nodeDistance;
				lowestDistanceNode = node;
			}
		}
		return lowestDistanceNode;
	}

	private static void CalculateMinimumDistance(Node evaluationNode, Integer edgeWeigh, Node sourceNode) {
		Integer sourceDistance = sourceNode.getDistance();
		if (sourceDistance + edgeWeigh < evaluationNode.getDistance()) {
			evaluationNode.setDistance(sourceDistance + edgeWeigh);
			LinkedList<Node> shortestPath = new LinkedList<Node>(sourceNode.getShortestPath());
			shortestPath.add(sourceNode);
			evaluationNode.setShortestPath(shortestPath);
		}
	}
}