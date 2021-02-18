import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
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
        } catch (IOException e) {
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

    public static ArrayList<String> dialogues(String inputFilePath) throws IOException {
        //Vars
        ArrayList<String> dialogueArray = new ArrayList<>();
        String script = new String(Files.readAllBytes(Paths.get(inputFilePath)), StandardCharsets.UTF_8);
        //Define our regex pattern
        Pattern pattern = Pattern.compile("\"(?!\\s)(.*?)(\\n(?=\\n*\")|\")", Pattern.DOTALL);

        //Match our regex pattern
        Matcher matcher = pattern.matcher(script);

        //If we have a match
        while (matcher.find()) {
            //Add the current line's regex group one (only the text) to dialogueArray
            dialogueArray.add(matcher.group(1));
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
