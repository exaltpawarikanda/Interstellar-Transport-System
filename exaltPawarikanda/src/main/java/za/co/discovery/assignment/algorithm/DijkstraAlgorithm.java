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

    private VertexDto SOURCE_VERTEX;
    public  VertexDto DESTINATION_VERTEX;
    private ArrayList<VertexDto> visitedNodes = new ArrayList<>();
    private Set<VertexDto> unvisitedNodes = new HashSet<>();
    private Set<VertexDto> leastPathNodes = new HashSet<>();
    private Double MIN_DISTANCE = 0.0;
    private int MAX_DISTANCE = Integer.MAX_VALUE;
    private  List<VertexDto> nodes;
    private  List<EdgeDto> edges;
    private  List<EdgeDto> edgeDtoList = new ArrayList<>();
    LinkedList<String> theFinalPath = new LinkedList<>();
    private Double calculatedDistance;
    private Double previousVertexWeight;
    private Map<String, Double> distance;
    LinkedList<String> theQuickestRoute = new LinkedList<>();
    private Map<String,VertexDto> pathMap = new HashMap<>();

    public DijkstraAlgorithm(Graph graph,VertexDto startVertex,VertexDto destinationVertex) {
        this.nodes = new ArrayList<>(graph.getVertexes());
        this.edges = new ArrayList<>(graph.getEdges());
        this.distance = new HashMap<>();
        this.SOURCE_VERTEX = startVertex;
        this.DESTINATION_VERTEX = destinationVertex;

        this.calculateShortestPathDistance(SOURCE_VERTEX,DESTINATION_VERTEX);
    }

    public double calculateShortestPathDistance(VertexDto startVertex,VertexDto destinationVertex){
        for(VertexDto vertex:nodes) {
            if(vertex.getNode() == startVertex.getNode()){
                vertex.setShortestDistance(MIN_DISTANCE);
                distance.put(vertex.getNode(),MIN_DISTANCE);
            }else{
                vertex.setShortestDistance((double) MAX_DISTANCE);
                distance.put(vertex.getNode(),(double) MAX_DISTANCE);
            }
            unvisitedNodes.add(vertex);
        }

        while(!unvisitedNodes.isEmpty()){
            VertexDto vertexWithShortestDistance = unvisitedNodes.stream().min(Comparator.comparing(VertexDto::getShortestDistance)).get();
            /*     for (Edge edge: edges) {
                if(edge.getSource().getNode()==vertexWithShortestDistance.getNode()){
                    vertexWithShortestDistance.addNeighbour(edge);
                }
            }*/
            Double vertexDistance = vertexWithShortestDistance.getShortestDistance();
            log.info("-------------------------TESTING---------------------------" + vertexWithShortestDistance.getShortestDistance());

            List<EdgeDto> updatedVertices =this.updateTheAdjacentVertices(vertexWithShortestDistance);
            log.info("----------------------------------------------------" + updatedVertices.size());
            VertexDto tempVertexDto = vertexWithShortestDistance;
            VertexDto tempPathDto = startVertex;
            for (EdgeDto edge: updatedVertices) {
                ///if(visitedNodes.contains(edge.getDestination())) continue;
               log.info("-------------------------THE NAME---------------------------" + edge.getSource().getName());
               log.info("-------------------------THE TOSTRING---------------------------" + edge.getDestination().getShortestDistance());
               if(edge.getSource().getNode().equals(startVertex.getNode())){
                   log.info("***************************************STARTING NODE********************************************************************");
                   if(distance.containsKey(edge.getSource().getNode())){
                       calculatedDistance = edge.getWeight() + distance.get(edge.getSource().getNode());
                       previousVertexWeight = distance.get(edge.getSource().getNode());
                       if(distance.containsKey(edge.getDestination().getNode())){
                           if(calculatedDistance < distance.get(edge.getDestination().getNode()).doubleValue()){
                               edge.getDestination().setShortestDistance(calculatedDistance);
                              unvisitedNodes.stream().forEach(node ->{
                                   if(node.getNode() == edge.getDestination().getNode()){
                                       node.setShortestDistance(calculatedDistance);
                                   }
                               });
                           }
                       }
                   }
                   tempVertexDto = edge.getSource();
                   if(visitedNodes.isEmpty()){
                       edge.getSource().setShortestDistance(vertexDistance);
                       visitedNodes.add(edge.getSource());
                       unvisitedNodes.removeIf(n -> edge.getSource().getNode().equals(n.getNode()));
                   }else{
                       visitedNodes.stream().forEach(t ->{
                           System.out.println("_______________________________________" + t.getNode());
                           System.out.println("_______________________________________" + edge.getSource().getNode());
                          // t.setShortestDistance(vertexDistance);
                           edge.getSource().setShortestDistance(vertexDistance);
                           if(edge.getSource().getNode() != t.getNode()){
                               t.setShortestDistance(vertexDistance);
                               //visitedNodes.add(t);
                           }
                       });
                       unvisitedNodes.stream().forEach(t ->{
                           if(edge.getSource().getNode() == t.getNode()){
                               unvisitedNodes.remove(t);
                           }
                       });
                   }
                  // continue;
               }else{
                   if(distance.containsKey(edge.getSource().getNode())){
                       unvisitedNodes.stream().forEach(node ->{
                           if(node.getNode() == edge.getSource().getNode()){
                               calculatedDistance = edge.getWeight() + node.getShortestDistance();
                           }
                       });
                       if(distance.containsKey(edge.getDestination().getNode())){
                           if(calculatedDistance < distance.get(edge.getDestination().getNode()).doubleValue()){
                               edge.getDestination().setShortestDistance(calculatedDistance);
                               unvisitedNodes.stream().forEach(node ->{
                                   if(node.getNode() == edge.getDestination().getNode()){
                                       node.setShortestDistance(calculatedDistance);
                                   }
                               });
                           }
                       }
                       edge.getSource().setShortestDistance(vertexDistance);
                   }
                   edge.getSource().setShortestDistance(vertexDistance);
      //           unvisitedNodes.remove(edge.getSource());
                   tempVertexDto = edge.getSource();
               }

            }
            System.out.println("*********************************************************" + tempVertexDto);
            tempVertexDto.setShortestDistance(vertexDistance);
            visitedNodes.add(tempVertexDto);
            VertexDto finalTempVertexDto = tempVertexDto;
            System.out.println("*********************************************************" + finalTempVertexDto);
            unvisitedNodes.removeIf(n -> finalTempVertexDto.getNode().equals(n.getNode()));
            calculatedDistance = 0.0;
        }

        createShortPaths();
        getShortestPath(startVertex,destinationVertex);
        theFinalPath = shortestRoute(startVertex,destinationVertex);
        System.out.println("Shortest Path from " + startVertex.getNode() + " to " + destinationVertex.getNode() + " is " + theFinalPath);
        return 0.0;

    }





/*  public LinkedList<String> getPathShortest(VertexDto startVertex,VertexDto destinationVertex){

      while(!visitedNodes.isEmpty()){
   //       VertexDto vertexWithShortestDistance = unvisitedNodes.stream().min(Comparator.comparing(VertexDto::getShortestDistance)).get();

          List<EdgeDto> updatedVertices =this.updateTheAdjacentVertices(startVertex);
          for (EdgeDto edge: updatedVertices) {

             *//* if(edge.getSource().getNode().equals(startVertex.getNode())){
                  if(distance.containsKey(edge.getSource().getNode())){
                      calculatedDistance = edge.getWeight() + distance.get(edge.getSource().getNode());
                      previousVertexWeight = distance.get(edge.getSource().getNode());
                      if(distance.containsKey(edge.getDestination().getNode())){
                          if(calculatedDistance < distance.get(edge.getDestination().getNode()).doubleValue()){
                              edge.getDestination().setShortestDistance(calculatedDistance);
                              unvisitedNodes.stream().forEach(node ->{
                                  if(node.getNode() == edge.getDestination().getNode()){
                                      node.setShortestDistance(calculatedDistance);
                                  }
                              });
                          }
                      }
                  }
                  tempVertexDto = edge.getSource();

          }*//*

          System.out.println("*********************************************************" + tempVertexDto);
          visitedNodes.add(tempVertexDto);
          VertexDto finalTempVertexDto = tempVertexDto;
          System.out.println("*********************************************************" + finalTempVertexDto);
          unvisitedNodes.removeIf(n -> finalTempVertexDto.getNode().equals(n.getNode()));
          calculatedDistance = 0.0;
      }
      }

    }*/

    public String getShortestPath(VertexDto startVertex,VertexDto destinationVertex){
        startVertex.setShortestDistance( Double.valueOf(MAX_DISTANCE));
        VertexDto startingVertex = startVertex;
        VertexDto shortestDistanceVertex = new VertexDto();
        Double weight = MIN_DISTANCE;
        theFinalPath.add(startVertex.getNode());
        VertexDto previous = startingVertex;
        outerloop:
        for( int iterator=0; iterator < nodes.size(); iterator++){
            List<EdgeDto> adjacentVertices = this.updateAdjacentVertices(destinationVertex);
            //  List<EdgeDto> adjacentVertices = this.updateTheAdjacentVertices(startingVertex);
            if(adjacentVertices.size() == 0){
                return String.valueOf(theFinalPath);
            }
            for (EdgeDto edge: adjacentVertices) {
                if(edge.getDestination().getNode() == destinationVertex.getNode()){
                    theFinalPath.add(destinationVertex.getNode());
                    shortestDistanceVertex = edge.getDestination();
                    break outerloop;
                }else{
                    if(edge.getDestination().getShortestDistance() < weight){
                        weight = edge.getDestination().getShortestDistance();
                        shortestDistanceVertex = edge.getDestination();
                    }
                }
            }
     /*
                for (EdgeDto edge: adjacentVertices) {
                    double shortest = 0.0;
                    while(edge.getSource().getPreviousVertex() != previous.getNode()){
                        weight += edge.getSource().getShortestDistance();
                        shortestDistanceVertex = edge.getDestination();
                    }

                }*/

            theFinalPath.add(shortestDistanceVertex.getNode());
            visitedNodes.remove(shortestDistanceVertex);
            startingVertex = shortestDistanceVertex;
            weight = MIN_DISTANCE;
        }
        Collections.reverse(theQuickestRoute);
        log.info("Shortest Path from " + startVertex.getNode() + " to " + destinationVertex.getNode() + " is "  );
        return "test";
    }

    public List<EdgeDto> updateTheAdjacentVertices(VertexDto startVertex){
        List<EdgeDto> adjacentVertexList = new ArrayList<>();
        for (EdgeDto edge: edges) {
            if(edge.getSource().getNode() == startVertex.getNode() || edge.getSource().getName().equals(startVertex.getName())){
                adjacentVertexList.add(edge);
            }
        }
        return adjacentVertexList;
    }

    public List<EdgeDto> updateAdjacentVertices(VertexDto startVertex){
        List<EdgeDto> adjacentVertexList = new ArrayList<>();
        for (EdgeDto edge: edgeDtoList) {
            if(edge.getSource().getPreviousVertex() == startVertex.getPreviousVertex()){
                adjacentVertexList.add(edge);
            }
        }
        return adjacentVertexList;
    }

    public List<EdgeDto> updatePreviousVertices(VertexDto startVertex){
        List<EdgeDto> sourceVertexList = new ArrayList<>();
        for (EdgeDto edge: edges) {
            if(edge.getDestination().getNode() == startVertex.getNode()){
                sourceVertexList.add(edge);
            }
        }
        return sourceVertexList;
    }

    public void printDetails(String startVertexNode,String destinationVertex){
        //System.out.println("Shortest Path from " + startVertexNode + " to " + destinationVertex + " is " + theFinalPath );
    }

    public VertexDto getMinVertex(){
        double distance = MAX_DISTANCE;
        VertexDto theDto = new VertexDto();
        for(VertexDto vertexDto: visitedNodes) {
            if(vertexDto.getShortestDistance() < distance){
                distance = vertexDto.getShortestDistance();
                theDto = vertexDto;
            }
        }
        return theDto;
    }

    public void createShortPaths(){
        List<VertexDto> vertexDtos = new ArrayList<>();
        Double minWeight = Double.valueOf(MAX_DISTANCE);
        VertexDto tempDto = new VertexDto();
        String previousVertex = "";

        while(!visitedNodes.isEmpty()){
            VertexDto startVertex = visitedNodes.stream().min(Comparator.comparing(VertexDto::getShortestDistance)).get();
            System.out.println("********************************************************* 1                      " + visitedNodes.size());
            System.out.println("********************************************************* indexed    " + visitedNodes.get(0));
          VertexDto tempPathDto = new VertexDto();
          List<EdgeDto> updatedVertices;
   /*       if(vertexDtos.isEmpty()){
              System.out.println("********************************************************* 2" + vertexDtos.size());
              updatedVertices = this.updateTheAdjacentVertices(this.getMinVertex());
              previousVertex = this.getMinVertex().getNode();
              *//*for (EdgeDto source: updatedVertices) {
                  edgeDtoList.add(source);
              }*//*
          }else{*/
             // VertexDto startVertex = vertexDtos.stream().min(Comparator.comparing(VertexDto::getShortestDistance)).get();
              System.out.println("********************************************************* culprit get node :" + startVertex.getNode());
              System.out.println("********************************************************* culprit  get name:" + startVertex.getName());
              System.out.println("********************************************************* culprit  get previous vertex" + startVertex.getPreviousVertex());
              previousVertex = startVertex.getNode();
              updatedVertices =this.updateTheAdjacentVertices(startVertex);
              vertexDtos.removeIf(n -> startVertex.getNode()==(n.getNode()));

              vertexDtos.removeIf(n-> startVertex.getNode().equals(startVertex.getPreviousVertex()));
          //}
            if(updatedVertices.size() == 0){
                visitedNodes
                        .removeIf(n -> this.
                                getMinVertex()
                                .getNode()
                                .equals(n.getNode()) || this.getMinVertex().getName().equals(n.getName()));
            }
            for (EdgeDto edge: updatedVertices) {
                List<EdgeDto> updatedSourceVertices = updatePreviousVertices(edge.getDestination());
                for (EdgeDto source: updatedSourceVertices) {
                    source.getDestination().setPreviousVertex(previousVertex);
                    edgeDtoList.add(source);
                    if(minWeight > source.getWeight() + edge.getSource().getShortestDistance()){
                        minWeight = source.getWeight() + edge.getSource().getShortestDistance();
                        tempDto.setNode(source.getSource().getNode());
                    }
                }
                vertexDtos.add(edge.getDestination());
                VertexDto finalTempDto = tempDto;
                minWeight = Double.valueOf(MAX_DISTANCE);
                tempDto = edge.getSource();
                String finalPreviousVertex = previousVertex;

              //  vertexDtos.removeIf(n-> startVertex.getNode().equals(startVertex.getPreviousVertex()));
             // boolean removed = visitedNodes.removeIf(n ->(edge.getSource().getName().equals(n.getName()))||edge.getSource().getNode().equals(n.getNode()));
             // if(removed)
             // break;
    //          else visitedNodes.removeIf(n -> edge.getSource().getNode().equals(n.getNode()));
      //            visitedNodes.forEach( t -> System.out.println(" Iterating the remaining nodes   :  " + t.getNode())); break;
              //  System.out.println(removed);
                //System.out.println(edge.getSource());
       //         System.out.println(edge.getSource().getNode());
             /*   visitedNodes.stream().forEach(t ->{
                    visitedNodes.removeIf(n -> edge.getSource().getNode().equals(n.getNode()));
                });*/
         //       System.out.println("********************************************************* culprit source  " +  edge.getSource().getNode()  + "  " + edge.getSource().getPreviousVertex());
               /* Iterator<VertexDto> iterator = visitedNodes.iterator();
                while(iterator.hasNext()){
                    if(iterator.next().getNode().equals(edge.getSource().getNode())){
                        iterator.remove();
                    }else if ( edge.getSource().getNode().equals(edge.getSource().getPreviousVertex())){
                        iterator.remove();
                    }*//*else if ( edge.getSource().getPreviousVertex() == null){
                        iterator.remove();
                    }*//*
                }*/

            }
            boolean removed = visitedNodes.removeIf(n ->(startVertex.getNode().equals(n.getNode())));
            previousVertex = "";

        }
    }


    public LinkedList<String> shortestRoute(VertexDto source,VertexDto destination){
        /*for (EdgeDto edge: edgeDtoList) {
            if(edge.getDestination().getNode() == source.getNode()){
                source.setPreviousVertex(edge.getDestination().getPreviousVertex());
            }
        }*/
        VertexDto start = source;
        VertexDto end = destination;
        while(end.getPreviousVertex() != start.getNode()){
            theQuickestRoute.add(end.getNode());
            List<EdgeDto> adjacentNodes = adjacentVerticesList(end);
            if(adjacentNodes.isEmpty())break;
            end = adjacentVerticesList(end).get(0).getSource();
        }
      //  System.out.println(theQuickestRoute);
        Collections.reverse(theQuickestRoute);
        return theQuickestRoute;
      //  return theQuickestRoute;
    }

    public List<EdgeDto> adjacentVerticesList(VertexDto startVertex){
        for (EdgeDto edge: edgeDtoList) {
            if(edge.getDestination().getNode() == startVertex.getNode()){
                startVertex.setPreviousVertex(edge.getDestination().getPreviousVertex());
            }
        }
        List<EdgeDto> adjacentVertexList = new ArrayList<>();
        for (EdgeDto edge: edgeDtoList) {
            if(edge.getSource().getNode() == startVertex.getPreviousVertex()){
                adjacentVertexList.add(edge);
            }
        }
        return adjacentVertexList;
    }


}
