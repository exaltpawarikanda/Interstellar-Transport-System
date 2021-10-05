package za.co.discovery.assignment.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * @author Exalt Pawarikanda
 */
@Data
@Builder
public class FinalPathModel implements Serializable {
    private String sourceVertex;
    private String destinationVertex;
    private LinkedList<String> thePath;
}
