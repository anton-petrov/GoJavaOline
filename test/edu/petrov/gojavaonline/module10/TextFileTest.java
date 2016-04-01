package edu.petrov.gojavaonline.module10;

import org.apache.commons.io.FileUtils;
import org.junit.*;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;


public class TextFileTest {

    private final String writeFileName = "tempFile1_TextFileTest";
    private final String readFileName = "tempFile2_TextFileTest";
    private final String textToWrite = "This a test text for write, cheers!";

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @BeforeClass
    public static void setUpClass() throws Exception {

    }

    @AfterClass
    public static void tearDownClass() throws Exception {

    }

    @Before
    public void setUp() throws Exception {

    }

    @After
    @SuppressWarnings(value = "all")
    public void tearDown() throws Exception {
        try {
            Files.delete(Paths.get(writeFileName));
        }
        catch (NoSuchFileException e) {

        }
    }

//    @Ignore("Not ready")
    @Test
    public void testWriteRead() throws Exception {
        TextFile.Write(writeFileName, textToWrite, false);
        final String readText = FileUtils.readFileToString(new File(writeFileName));
        assertEquals(textToWrite, readText);
    }

    //    @Ignore("Not ready")
    @Test
    public void testRead() throws Exception {
        File tmpFile = folder.newFile(readFileName);
        FileUtils.writeStringToFile(tmpFile, textToWrite);
        assertEquals(textToWrite, TextFile.Read(tmpFile.getAbsolutePath()));
    }

    @Test(expected = IOException.class)
    public void readException() throws Exception {
        TextFile.Read("fileDoesNotExists");
    }

    @Test(expected = IOException.class)
    public void writeException() throws Exception {
        TextFile.Write(null, textToWrite, false);
    }
}