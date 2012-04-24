package com.nullprogram.dp40d;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import org.junit.Test;
import static org.junit.Assert.*;

public class PointTest {

    /**
     * Test point list serialization.
     */
    @Test
    public void fileTest() throws IOException {
        File temp = File.createTempFile("points", ".txt");
        temp.deleteOnExit();
        PointFactory factory = new PointFactory(new Random(100), 4);
        List<Point> created = factory.generate(10000);
        PointFactory.toFile(temp, created);
        List<Point> read = PointFactory.fromFile(temp);
        assertEquals(created, read);
    }
}
