package utility;
import org.junit.Assert;
import java.io.*;
import java.util.Properties;

public class FileReaderManager {
    private static FileInputStream fileInputStream;
    private static Properties properties;
    public static void setUpProperty() {
        File file = new File("C:\\Users\\thouf\\IdeaProjects\\QA_TESTING\\src\\main\\resources\\testdata.Properties");
        try {
            fileInputStream=new FileInputStream(file);
            properties = new Properties();
            properties.load(fileInputStream);
        } catch (FileNotFoundException e) {
            Assert.fail("ERROR OCCUR DURING FILE LOAD");
        } catch (IOException e) {
            Assert.fail("ERROR OCCUR DURING FILE READ");
        }
    }
    public static String getProperty(String value){
        setUpProperty();
        String property=properties.getProperty(value);
        return property;
    }

    public static void main(String[] args) {
        System.out.println(getProperty("Username"));
    }
}
