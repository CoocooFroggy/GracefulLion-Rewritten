import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        //Inputs
        String inputFilePath = "input.txt";
        String outputFilePath = "output.txt";

        //Input file
        File inputFile = new File(inputFilePath);

        //Get all dialogues
        ArrayList<String> dialogueArray = dialogues(inputFile);

        //Write
        try {
            writeFile(outputFilePath, dialogueArray);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<String> dialogues(File inputFile) {
        //Vars
        ArrayList<String> dialogueArray = new ArrayList<>();
        //Define our regex pattern
        Pattern pattern = Pattern.compile("\"(.*?)[\"\\n]");

        //File not found exception
        try {
            //Scanner variable
            Scanner scanner = new Scanner(inputFile);

            //While there are more lines in the input file
            while (scanner.hasNextLine()) {
                //Set string currentLine to scanner's next line
                String currentLine = scanner.nextLine();

                //Match our regex pattern
                Matcher matcher = pattern.matcher(currentLine);

                //If we have a match
                while (matcher.find()) {
                    //Add the current line's regex group one (only the text) to dialogueArray
                    dialogueArray.add(matcher.group(1));
                }
            }

            //Close scanner
            scanner.close();
            //Catch exception
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            e.printStackTrace();
        }

        return dialogueArray;
    }

    public static void writeFile(String outputFilePath, ArrayList<String> dialogueArray) throws IOException {

        FileWriter fileWriter = new FileWriter(outputFilePath, false);

        for (String dialogue : dialogueArray) {
            fileWriter.write(dialogue + "\n");
        }

        fileWriter.flush();
        fileWriter.close();
    }
}
