package unitTests;

import com.example.graphproject.graphUtils.Graph;
import com.example.graphproject.graphUtils.GraphTextReader;
import static junit.framework.Assert.assertEquals;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import unitTests.graphTestingTemplates.GraphTemplates;

import java.io.IOException;


public class GraphTextReaderTest {

    private GraphTextReader graphTextReader;

    @Test
    public void testingNumberOfRows() throws IOException {
        //given
        graphTextReader = new GraphTextReader("src/main/resources/com/example/graphproject/GraphsDirectory/mygraph.txt");

        //when
        Graph graphFromFile = graphTextReader.read();
        int actualNumberOfRows = graphFromFile.getRows();

        //then
        int expectedNumberOfRows = 7;
        assertEquals(expectedNumberOfRows, actualNumberOfRows);
    }

    @Test
    public void testingNumberOfCollumns() throws IOException {
        //given
        graphTextReader = new GraphTextReader("src/main/resources/com/example/graphproject/GraphsDirectory/mygraph.txt");


        //when
        Graph graphFromFile = graphTextReader.read();
        int actualNumberOfCollumns = graphFromFile.getCollumns();

        //then
        int expectedNumberOfCollumns = 4;
        assertEquals(expectedNumberOfCollumns, actualNumberOfCollumns);
    }


    @Test
    public void testingGraphFromGraphThreeByThree() throws IOException{
        //given
        graphTextReader = new GraphTextReader("src/main/resources/com/example/graphproject/GraphsDirectory/graph3x3.txt");

        //when
        Graph graphFromFile = graphTextReader.read();
        Graph templateGraph = new GraphTemplates().templateGraphOne();
        //then
        Assertions.assertEquals(graphFromFile, templateGraph);
    }


}
