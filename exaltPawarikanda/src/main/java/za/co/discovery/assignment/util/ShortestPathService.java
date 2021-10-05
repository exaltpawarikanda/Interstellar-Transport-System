package za.co.discovery.assignment.util;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import za.co.discovery.assignment.algorithm.DijkstraAlgorithm;
import za.co.discovery.assignment.domain.Edge;
import za.co.discovery.assignment.domain.Traffic;
import za.co.discovery.assignment.domain.Vertex;
import za.co.discovery.assignment.dto.EdgeDto;
import za.co.discovery.assignment.dto.VertexDto;
import za.co.discovery.assignment.mappers.EdgeMapper;
import za.co.discovery.assignment.mappers.VertexMapper;
import za.co.discovery.assignment.repository.EdgeRepository;
import za.co.discovery.assignment.repository.TrafficRepository;
import za.co.discovery.assignment.repository.VertexRepository;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Slf4j
@Data
@RequiredArgsConstructor
@Service
public class ShortestPathService {
    private final VertexRepository vertexRepository;
    private final EdgeRepository edgeRepository;
    private final TrafficRepository trafficRepository;
   private final VertexMapper vertexMapper;
    private final EdgeMapper edgeMapper;
    private VertexDto SOURCE_VERTEX;
    public  VertexDto DESTINATION_VERTEX;
    public List<VertexDto> vertexDtos = new ArrayList<>();
    public List<EdgeDto> edgeDtos = new ArrayList<>();
    public List<Traffic> trafficDtos = new ArrayList<>();


   public LinkedList<String> initializeGraph(String startVertex, String destinationVertex,Boolean traffic){
       System.out.println("**********************************Data Submitted**********************  :" + startVertex + "    :" + destinationVertex + " traffic : " + traffic);
       this.SOURCE_VERTEX = vertexMapper.vertexToVertexDto(vertexRepository.findByName(startVertex));
       this.DESTINATION_VERTEX = vertexMapper.vertexToVertexDto(vertexRepository.findByName(destinationVertex));
       List<Vertex> vertexList = vertexRepository.findAll();
       List<Edge> edgeList = edgeRepository.findAll();
       List<Traffic> trafficList = trafficRepository.findAll();
       vertexList.forEach(vertex -> vertexDtos.add(vertexMapper.vertexToVertexDto(vertex)));

       if(traffic){
           System.out.println("********************************** Adding Traffic Distance **********************  :" );
           for (Edge edge: edgeList) {
               for (Traffic traffic1: trafficList) {
                   if(edge.getStartNode().equals(traffic1.getStartNode()) && edge.getEndNode().equals(traffic1.getEndNode())){
                     edge.setDistance(edge.getDistance() + traffic1.getTrafficDelay());
                     edgeDtos.add(edgeMapper.edgeToEdgeDto(edge));
                   }
               }
           }
       }
       else {
           edgeList.forEach(route -> edgeDtos.add(edgeMapper.edgeToEdgeDto(route)));
       }
       Graph graph = new Graph(vertexDtos,edgeDtos);
       DijkstraAlgorithm dijkstraAlgorithm = new DijkstraAlgorithm(graph,SOURCE_VERTEX,DESTINATION_VERTEX);
       return dijkstraAlgorithm.calculateShortestPathDistance(SOURCE_VERTEX,DESTINATION_VERTEX);
   }


}
