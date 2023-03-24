package unitTests;

import com.example.graphproject.functions.DijkstraConsolePrinter;
import com.example.graphproject.graphUtils.Graph;
import com.example.graphproject.graphUtils.Node;

import org.junit.jupiter.api.*;
import unitTests.graphTestingTemplates.GraphTemplates;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


public class DijkstraSolverTest {
    private final PrintStream standardOut = System.out;

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    public void cleanUp() {
        System.setOut(standardOut);
    }

    @Test
    public void distanceFromZeroToElevenShouldEqualThirteen() {
        //given
        Graph graph = new GraphTemplates().templateGraphForDijkstraOne();
        Node sourceNode = graph.getNodes().get(0);
        DijkstraConsolePrinter dijkstraConsolePrinter = new DijkstraConsolePrinter(graph, sourceNode);

        //when
        double expectedDistance = 13;
        String expectedPath = "11 <- 7 <- 3 <- 2 <- 1 <- 0";
        //then
        double actualDistance = dijkstraConsolePrinter.getDistances()[11].getDistance();
        dijkstraConsolePrinter.print(graph.getNodes().get(11));
        String actualPath = outputStream.toString();

        Assertions.assertEquals(expectedDistance, actualDistance);
        Assertions.assertEquals(expectedPath, actualPath);

    }

    @Test
    public void distanceFromZeroToTenShouldEqualNine() {
        //given
        Graph graph = new GraphTemplates().templateGraphForDijkstraOne();
        Node sourceNode = graph.getNodes().get(0);
        DijkstraConsolePrinter dijkstraConsolePrinter = new DijkstraConsolePrinter(graph, sourceNode);

        //when
        double expectedDistance = 9;
        String expectedPath = "10 <- 9 <- 8 <- 4 <- 0";

        //then
        double actualDistance = dijkstraConsolePrinter.getDistances()[10].getDistance();
        dijkstraConsolePrinter.print(graph.getNodes().get(10));
        String actualPath = outputStream.toString();

        Assertions.assertEquals(expectedDistance, actualDistance);
        Assertions.assertEquals(expectedPath, actualPath);


    }

    @Test
    public void distanceFromZeroToThreeShouldEqualEight() {
        //given
        Graph graph = new GraphTemplates().templateGraphForDijkstraTwo();
        Node sourceNode = graph.getNodes().get(0);
        DijkstraConsolePrinter dijkstraConsolePrinter = new DijkstraConsolePrinter(graph, sourceNode);

        //when
        double expectedDistance = 8;
        String expectedPath = "3 <- 4 <- 1 <- 0";

        //then
        double actualDistance = dijkstraConsolePrinter.getDistances()[3].getDistance();
        dijkstraConsolePrinter.print(graph.getNodes().get(3));
        String actualPath = outputStream.toString();

        Assertions.assertEquals(expectedDistance, actualDistance);
        Assertions.assertEquals(expectedPath, actualPath);

    }

    @Test
    public void distanceFromFourToEightShouldEqualThree() {
        //given
        Graph graph = new GraphTemplates().templateGraphForDijkstraTwo();
        Node sourceNode = graph.getNodes().get(4);
        DijkstraConsolePrinter dijkstraConsolePrinter = new DijkstraConsolePrinter(graph, sourceNode);

        //when
        double expectedDistance = 3;
        String expectedPath = "8 <- 7 <- 4";

        //then
        double actualDistance = dijkstraConsolePrinter.getDistances()[8].getDistance();
        dijkstraConsolePrinter.print(graph.getNodes().get(8));
        String actualPath = outputStream.toString();

        Assertions.assertEquals(expectedDistance, actualDistance);
        Assertions.assertEquals(expectedPath, actualPath);
    }

    @Test
    public void distanceFromFourToZeroShouldEqualEight() {
        //given
        Graph graph = new GraphTemplates().templateGraphForDijkstraTwo();
        Node sourceNode = graph.getNodes().get(4);
        DijkstraConsolePrinter dijkstraConsolePrinter = new DijkstraConsolePrinter(graph, sourceNode);

        //when
        double expectedDistance = 8;
        String expectedPath = "0 <- 3 <- 4";

        //then
        double actualDistance = dijkstraConsolePrinter.getDistances()[0].getDistance();
        dijkstraConsolePrinter.print(graph.getNodes().get(0));
        String actualPath = outputStream.toString();

        Assertions.assertEquals(expectedDistance, actualDistance);
        Assertions.assertEquals(expectedPath, actualPath);
    }

    @Test
    public void distanceFromThreeToFiveShouldEqualEight() {
        //given
        Graph graph = new GraphTemplates().templateGraphForDijkstraTwo();
        Node sourceNode = graph.getNodes().get(3);
        DijkstraConsolePrinter dijkstraConsolePrinter = new DijkstraConsolePrinter(graph, sourceNode);

        //when
        double expectedDistance = 8;
        String expectedPath = "5 <- 2 <- 1 <- 0 <- 3";

        //then
        double actualDistance = dijkstraConsolePrinter.getDistances()[5].getDistance();
        dijkstraConsolePrinter.print(graph.getNodes().get(5));
        String actualPath = outputStream.toString();

        Assertions.assertEquals(expectedDistance, actualDistance);
        Assertions.assertEquals(expectedPath, actualPath);
    }
    @Test
    public void distanceFromZeroToSixShouldEqualTwelve() {
        //given
        Graph graph = new GraphTemplates().templateGraphForDijkstraTwo();
        Node sourceNode = graph.getNodes().get(0);
        DijkstraConsolePrinter dijkstraConsolePrinter = new DijkstraConsolePrinter(graph, sourceNode);

        //when
        double expectedDistance = 12;
        String expectedPath = "6 <- 7 <- 4 <- 1 <- 0";

        //then
        double actualDistance = dijkstraConsolePrinter.getDistances()[6].getDistance();
        dijkstraConsolePrinter.print(graph.getNodes().get(6));
        String actualPath = outputStream.toString();

        Assertions.assertEquals(expectedDistance, actualDistance);
        Assertions.assertEquals(expectedPath, actualPath);
    }
}
