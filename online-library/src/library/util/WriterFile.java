package library.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class WriterFile {

    public final static String FILE_PATH = "E://book";


    public static void writeUsingFiles(String data,String file) {
        try {
            Files.write(Paths.get(FILE_PATH +file), data.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String readUsingBufferedReaderText(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader( new FileReader(fileName));
        String line = null;
        StringBuilder stringBuilder = new StringBuilder();
        while( ( line = reader.readLine() ) != null ) {
            stringBuilder.append( line );
        }
        return stringBuilder.toString();
    }


    public static String readUsingBufferedReader(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader( new FileReader(fileName));
        String line = null;
        StringBuilder stringBuilder = new StringBuilder();
        String p = "<p style=\"text-indent:1.5em\">";
        String pCl = "</p>";
        while( ( line = reader.readLine() ) != null ) {
            stringBuilder.append( p);
            stringBuilder.append( line );
            stringBuilder.append( pCl );
        }

        stringBuilder.deleteCharAt(stringBuilder.length()-1);
        return stringBuilder.toString();
    }
}
