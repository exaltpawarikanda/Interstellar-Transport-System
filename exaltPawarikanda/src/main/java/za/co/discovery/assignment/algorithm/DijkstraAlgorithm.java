package za.co.discovery.assignment.algorithm;

import lombok.extern.slf4j.Slf4j;
import za.co.discovery.assignment.dto.EdgeDto;
import za.co.discovery.assignment.dto.VertexDto;
import za.co.discovery.assignment.util.Graph;

import java.util.*;

/**
 * @author Exalt Pawarikanda
 */
@Slf4j
public class DijkstraAlgorithm {

    public VertexDto DESTINATION_VERTEX;
    private final ArrayList<VertexDto> visitedNodes = new ArrayList<>();
    private final Set<VertexDto> unvisitedNodes = new HashSet<>();
    private final int MAX_DISTANCE = Integer.MAX_VALUE;
    private final List<VertexDto> nodes;
    private final List<EdgeDto> edges;
    private final List<EdgeDto> edgeDtoList = new ArrayList<>();
    LinkedList<String> theFinalPath = new LinkedList<>();
    private Double calculatedDistance;
    private final Map<String, Double> distance;
    LinkedList<String> theQuickestRoute = new LinkedList<>();

    public DijkstraAlgorithm(Graph graph, VertexDto startVertex, VertexDto destinationVertex) {
        this.nodes = new ArrayList<>(graph.getVertexes());
        this.edges = new ArrayList<>(graph.getEdges());
        this.distance = new HashMap<>();
        this.DESTINATION_VERTEX = destinationVertex;
    }

    public LinkedList<String> calculateShortestPathDistance(VertexDto startVertex, VertexDto destinationVertex) {
        for (VertexDto vertex : nodes) {
            if (vertex.getNode() == startVertex.getNode()) {
                Double MIN_DISTANCE = 0.0;
                vertex.setShortestDistance(MIN_DISTANCE);
                distance.put(vertex.getNode(), MIN_DISTANCE);
            } else {
                vertex.setShortestDistance((double) MAX_DISTANCE);
                distance.put(vertex.getNode(), (double) MAX_DISTANCE);
            }
            unvisitedNodes.add(vertex);
        }

        while (!unvisitedNodes.isEmpty()) {
            VertexDto vertexWithShortestDistance = unvisitedNodes.stream().min(Comparator.comparing(VertexDto::getShortestDistance)).get();
            Double vertexDistance = vertexWithShortestDistance.getShortestDistance();
            List<EdgeDto> updatedVertices = this.updateTheAdjacentVertices(vertexWithShortestDistance);
            VertexDto tempVertexDto = vertexWithShortestDistance;
            for (EdgeDto edge : updatedVertices) {
                if (edge.getSource().getNode().equals(startVertex.getNode())) {
                    if (distance.containsKey(edge.getSource().getNode())) {
                        calculatedDistance = edge.getWeight() + distance.get(edge.getSource().getNode());
                        setShortestDistance(edge);
                    }
                    tempVertexDto = edge.getSource();
                    if (visitedNodes.isEmpty()) {
                        edge.getSource().setShortestDistance(vertexDistance);
                        visitedNodes.add(edge.getSource());
                        unvisitedNodes.removeIf(n -> edge.getSource().getNode().equals(n.getNode()));
                    } else {
                        visitedNodes.stream().forEach(t -> {
                            edge.getSource().setShortestDistance(vertexDistance);
                            if (edge.getSource().getNode() != t.getNode()) {
                                t.setShortestDistance(vertexDistance);
                            }
                        });
                        unvisitedNodes.stream().forEach(t -> {
                            if (edge.getSource().getNode() == t.getNode()) {
                                unvisitedNodes.remove(t);
                            }
                        });
                    }
                } else {
                    if (distance.containsKey(edge.getSource().getNode())) {
                        unvisitedNodes.stream().forEach(node -> {
                            if (node.getNode() == edge.getSource().getNode()) {
                                calculatedDistance = edge.getWeight() + node.getShortestDistance();
                            }
                        });
                        setShortestDistance(edge);
                        edge.getSource().setShortestDistance(vertexDistance);
                    }
                    edge.getSource().setShortestDistance(vertexDistance);
                    tempVertexDto = edge.getSource();
                }

            }
            tempVertexDto.setShortestDistance(vertexDistance);
            visitedNodes.add(tempVertexDto);
            VertexDto finalTempVertexDto = tempVertexDto;
            unvisitedNodes.removeIf(n -> finalTempVertexDto.getNode().equals(n.getNode()));
            calculatedDistance = 0.0;
        }

        createShortPaths();
        theFinalPath = shortestRoute(startVertex, destinationVertex);
        return theFinalPath;

    }

    public void setShortestDistance(EdgeDto edge) {
        if (distance.containsKey(edge.getDestination().getNode())) {
            if (calculatedDistance < distance.get(edge.getDestination().getNode()).doubleValue()) {
                edge.getDestination().setShortestDistance(calculatedDistance);
                unvisitedNodes.stream().forEach(node -> {
                    if (node.getNode() == edge.getDestination().getNode()) {
                        node.setShortestDistance(calculatedDistance);
                    }
                });
            }
        }
    }

    public List<EdgeDto> updateTheAdjacentVertices(VertexDto startVertex) {
        List<EdgeDto> adjacentVertexList = new ArrayList<>();
        for (EdgeDto edge : edges) {
            if (edge.getSource().getNode().equals(startVertex.getNode())) {
                adjacentVertexList.add(edge);
            }
        }
        return adjacentVertexList;
    }

    public List<EdgeDto> updatePreviousVertices(VertexDto startVertex) {
        List<EdgeDto> sourceVertexList = new ArrayList<>();
        for (EdgeDto edge : edges) {
            if (edge.getDestination().getNode() == startVertex.getNode()) {
                sourceVertexList.add(edge);
            }
        }
        return sourceVertexList;
    }

    public VertexDto getMinVertex() {
        double distance = MAX_DISTANCE;
        VertexDto theDto = new VertexDto();
        for (VertexDto vertexDto : visitedNodes) {
            if (vertexDto.getShortestDistance() < distance) {
                distance = vertexDto.getShortestDistance();
                theDto = vertexDto;
            }
        }
        return theDto;
    }

    public void createShortPaths() {
        List<VertexDto> vertexDtos = new ArrayList<>();
        Double minWeight = Double.valueOf(MAX_DISTANCE);
        VertexDto tempDto = new VertexDto();
        visitedNodes.remove(0);
        while (!visitedNodes.isEmpty()) {
            VertexDto startVertex = visitedNodes.stream().min(Comparator.comparing(VertexDto::getShortestDistance)).get();
            VertexDto tempPathDto = new VertexDto();
            List<EdgeDto> updatedVertices;
            updatedVertices = this.updateTheAdjacentVertices(startVertex);
            if (updatedVertices.size() == 0) {
                visitedNodes.removeIf(n -> this.getMinVertex().getNode().equals(n.getNode()) || this.getMinVertex().getName().equals(n.getName()));
            } else {
                for (EdgeDto edge : updatedVertices) {
                    List<EdgeDto> updatedSourceVertices = updatePreviousVertices(edge.getDestination());
                    for (EdgeDto source : updatedSourceVertices) {
                        source.getDestination().setPreviousVertex(edge.getSource().getNode());
                        edgeDtoList.add(source);
                        if (minWeight > source.getWeight() + edge.getSource().getShortestDistance()) {
                            minWeight = source.getWeight() + edge.getSource().getShortestDistance();
                        }
                    }
                    vertexDtos.add(edge.getDestination());
                    VertexDto finalTempDto = tempDto;
                    minWeight = Double.valueOf(MAX_DISTANCE);
                    tempDto = edge.getSource();
                }
                visitedNodes.removeIf(n -> (startVertex.getNode().equals(n.getNode())));
            }
        }
    }


    public LinkedList<String> shortestRoute(VertexDto source, VertexDto destination) {
        VertexDto start = source;
        VertexDto end = destination;
        while (end.getPreviousVertex() != "") {
            theQuickestRoute.add(end.getName());
            List<EdgeDto> adjacentNodes = adjacentVerticesList(end);
            if (adjacentNodes.isEmpty()) break;
            else {
                end = adjacentNodes.get(0).getSource();
                adjacentVerticesList(end);
            }
        }
        Collections.reverse(theQuickestRoute);
        return theQuickestRoute;
    }

    public List<EdgeDto> adjacentVerticesList(VertexDto startVertex) {
        for (EdgeDto edge : edgeDtoList) {
            if (edge.getDestination().getNode() == startVertex.getNode()) {
                startVertex.setPreviousVertex(edge.getDestination().getPreviousVertex());
            }
        }
        List<EdgeDto> adjacentVertexList = new ArrayList<>();
        for (EdgeDto edge : edgeDtoList) {
            if (edge.getSource().getNode() == startVertex.getPreviousVertex()) {
                adjacentVertexList.add(edge);
            }
        }
        return adjacentVertexList;
    }

}
