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

        //Get all dialogues
        ArrayList<String> dialogueArray;
        try {
            dialogueArray = dialogues(inputFilePath);
        } catch (FileNotFoundException e) {
            System.out.println("File " + inputFilePath + " not found.");
            e.printStackTrace();
            //Return so that it doesn't try to write to file afterwards
            return;
        }

        //Write
        try {
            writeFile(outputFilePath, dialogueArray);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<String> dialogues(String inputFilePath) throws FileNotFoundException {
        //Vars
        File inputFile = new File(inputFilePath);
        ArrayList<String> dialogueArray = new ArrayList<>();
        //Define our regex pattern
        Pattern pattern = Pattern.compile("\"(.*?)[\"\\n]");

        //Scanner variable, file not found exception
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
