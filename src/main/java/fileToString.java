import java.io.IOException;
import java.util.Scanner;
import java.io.File;

public class fileToString {

    public String convert(String textFile){
        String result="";
        try {
            File myObj = new File(textFile);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                result = result + "\n"+ myReader.nextLine();
            }
            myReader.close();
        }catch(Exception e){
            ;
        }
        return result;
    }
}