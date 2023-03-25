package unitTests;

import com.example.graphproject.graphUtils.Graph;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import unitTests.graphTestingTemplates.GraphTemplates;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class GraphTextPrinterTest {
    private final PrintStream standardOut = System.out;

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outputStream));
    }

    @After
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    public void testingPrintingGraphToConsole() {
        //given
        Graph template = new GraphTemplates().templateGraphOne();

        //when
        new GraphTemplates().printToConsole(template);
        String expectedOutput = """
                3 3
                     1 :0,76  3 :0,66 \s
                     0 :0,09  2 :0,03  4 :0,01 \s
                     1 :0,58  5 :0,71 \s
                     4 :0,42  6 :0,50  0 :0,07 \s
                     3 :0,88  5 :0,24  7 :0,04  1 :0,37 \s
                     4 :0,69  8 :0,38  2 :0,40 \s
                     7 :0,55  3 :0,53 \s
                     6 :0,77  8 :0,97  4 :0,68 \s
                     7 :0,31  5 :0,99 \s
                """;

        //then
        String actualOutput = outputStream.toString();
        assertEquals(expectedOutput, actualOutput);

    }

}
