package unitTests;

import com.example.graphproject.functions.BfsSolver;
import com.example.graphproject.graphUtils.Graph;
import com.example.graphproject.graphUtils.GraphTextReader;
import org.junit.Test;
import unitTests.graphTestingTemplates.GraphTemplates;

import java.io.IOException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BfsSolverTest {
    @Test
    public void testingBfsSolverInCoherentGraph() {
        //given
        Graph graph = new GraphTemplates().coherentGraph();

        //when
        BfsSolver bfsSolver = new BfsSolver(graph);

        //then
        boolean isCoherent = bfsSolver.solve();
        assertTrue(isCoherent);
    }

    @Test
    public void testingBfsSolverInIncoherentGraph() {
        //given
        Graph graph = new GraphTemplates().incoherentGraph();

        //when
        BfsSolver bfsSolver = new BfsSolver(graph);

        //then
        boolean isCoherent = bfsSolver.solve();
        assertFalse(isCoherent);
    }

    @Test
    public void testingBfsSolverFromFileCoherentGraph() throws IOException {
        //given
        GraphTextReader graphTextReader = new GraphTextReader("src/main/resources/com/example/graphproject/GraphsDirectory/coherent.txt");
        Graph graph = graphTextReader.read();

        //when
        BfsSolver bfsSolver = new BfsSolver(graph);

        //then
        boolean isCoherent = bfsSolver.solve();
        assertTrue(isCoherent);

    }

    @Test
    public void testingBfsSolverFromFileIncoherentGraph() throws IOException {
        //given
        GraphTextReader graphTextReader = new GraphTextReader("src/main/resources/com/example/graphproject/GraphsDirectory/incoherent.txt");
        Graph graph = graphTextReader.read();

        //when
        BfsSolver bfsSolver = new BfsSolver(graph);

        //then
        boolean isCoherent = bfsSolver.solve();
        assertFalse(isCoherent);

    }

}
