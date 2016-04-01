package edu.petrov.gojavaonline.module10;

import org.junit.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.*;

/**
 * Created by anton on 01/04/16.
 */
public class TextFileTest {

    private final String fileName = "tempFile_TextFileTest";
    private final String writtenText = "This a test text for write, cheers!";

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
        try {
            Files.delete(Paths.get(fileName));
        }
        catch (NoSuchFileException e) {

        }
    }

    @BeforeClass
    public static void setUpClass() throws Exception {

    }

    @AfterClass
    public static void tearDownClass() throws Exception {

    }

//    @Ignore("Not ready")
    @Test
    public void testWriteRead() throws Exception {
        String readText = null;

        TextFile.Write(fileName, writtenText, false);

        assertEquals(writtenText, TextFile.Read(fileName));
    }

    @Ignore("Not ready")
    @Test
    public void __stub() throws Exception {

    }

    @Test(expected = IOException.class)
    public void readException() throws Exception {
        TextFile.Read("fileDoesNotExists");
    }

    @Test(expected = IOException.class)
    public void writeException() throws Exception {
        TextFile.Write(null, writtenText, false);
    }
}