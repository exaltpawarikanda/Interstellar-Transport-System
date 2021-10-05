package za.co.discovery.assignment.util;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import za.co.discovery.assignment.domain.Edge;
import za.co.discovery.assignment.domain.Traffic;
import za.co.discovery.assignment.domain.Vertex;
import za.co.discovery.assignment.repository.EdgeRepository;
import za.co.discovery.assignment.repository.TrafficRepository;
import za.co.discovery.assignment.repository.VertexRepository;

import javax.annotation.PostConstruct;
import java.io.File;

@Slf4j
@RequiredArgsConstructor
@Service
public class ExcelReaderService {

    private File file;
    private final VertexRepository vertexRepository;
    private final EdgeRepository edgeRepository;
    private final TrafficRepository trafficRepository;


    @PostConstruct
    public void loadInitialData() {
        try {
            Workbook workbook = WorkbookFactory.create(new ClassPathResource("interstellar.xlsx").getInputStream());
            DataFormatter dataFormatter = new DataFormatter();
            workbook.forEach(sheet -> {
                if (sheet.getSheetName().equalsIgnoreCase("Planet Names")) {
                    sheet.forEach(row -> {
                        if (row.getRowNum() != 0) {
                            Vertex planets = new Vertex();
                            row.forEach(cell -> {
                                String cellValue = dataFormatter.formatCellValue(cell);
                                if (planets.getNode() == null) {
                                    planets.setNode(cellValue);
                                } else if (planets.getName() == null) {
                                    planets.setName(cellValue);
                                }
                                vertexRepository.save(planets);
                            });
                        }
                    });

                } else if (sheet.getSheetName().equalsIgnoreCase("Routes")) {
                    sheet.forEach((row -> {
                        if (row.getRowNum() != 0) {
                            Edge routes = new Edge();
                            row.forEach(cell -> {
                                String cellValue = dataFormatter.formatCellValue(cell);
                                if (routes.getId() == 0) {
                                    routes.setId(Integer.parseInt(cellValue));
                                } else if (routes.getStartNode() == null) {
                                    routes.setStartNode(cellValue);
                                } else if (routes.getEndNode() == null) {
                                    routes.setEndNode(cellValue);
                                } else if (routes.getDistance() == null) {
                                    routes.setDistance(Double.parseDouble(cellValue));
                                }
                                edgeRepository.save(routes);
                            });
                        }
                    }));
                } else if (sheet.getSheetName().equalsIgnoreCase("Traffic")) {
                    sheet.forEach((row -> {
                        if (row.getRowNum() != 0) {
                            Traffic traffic = new Traffic();
                            row.forEach(cell -> {
                                String cellValue = dataFormatter.formatCellValue(cell);
                                if (traffic.getId() == 0) {
                                    traffic.setId(Integer.parseInt(cellValue));
                                } else if (traffic.getStartNode() == null) {
                                    traffic.setStartNode(cellValue);
                                } else if (traffic.getEndNode() == null) {
                                    traffic.setEndNode(cellValue);
                                } else if (traffic.getTrafficDelay() == null) {
                                    traffic.setTrafficDelay(Double.parseDouble(cellValue));
                                }
                                trafficRepository.save(traffic);
                            });
                        }
                    }));
                }
            });
            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
