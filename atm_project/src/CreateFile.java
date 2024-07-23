import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CreateFile {

    protected static void createFile() throws IOException {
        File f = new File("data.txt"); //default data file with default users
        BufferedWriter writer =  new BufferedWriter(new FileWriter(f));
        writer.write("Abc\n");
        writer.write("1234\n");
        writer.write("5000000\n");
        writer.write("Xyz\n");
        writer.write("5678\n");
        writer.write("200000\n");
        writer.flush();
    }

    public static void main(String[] args) throws IOException {
        createFile();
    }
}
