package auxiliar;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;
import model.Question;
import model.User;

public class FileOperations {

    private String FILENAME;

    public static ArrayList<Question> readQuestions(String FILENAME) throws IOException {

        ArrayList<Question> questionsRead = new ArrayList<>();

        Random rn = new Random();

        int random1 = rn.nextInt(2 - 1 + 1) + 1;
        int random2 = rn.nextInt(4 - 3 + 1) + 3;
        int random3 = rn.nextInt(6 - 5 + 1) + 5;
        int random4 = rn.nextInt(8 - 7 + 1) + 7;
        int random5 = rn.nextInt(10 - 9 + 1) + 9;

        Path path = Paths.get(FILENAME + ".txt");
        BufferedReader br = Files.newBufferedReader(path, java.nio.charset.StandardCharsets.UTF_8);
        Question question;
        String linea;
        String[] data;
        int i = 1;
        while ((linea = br.readLine()) != null) {
            data = linea.split(";");
            String[] answers = {data[1], data[2], data[3]};
            question = new Question(data[0], answers, Integer.parseInt(data[4]), Integer.parseInt(data[5]));
            if (i == random1) {
                questionsRead.add(question);
            }
            if (i == random2) {
                questionsRead.add(question);
            }
            if (i == random3) {
                questionsRead.add(question);
            }
            if (i == random4) {
                questionsRead.add(question);
            }
            if (i == random5) {
                questionsRead.add(question);
            }
            i++;
        }

        return questionsRead;
    }
    public static void writeScore(User[] users){
        Path path = Paths.get("Puntuaciones.txt");
        BufferedWriter bw= null;
        try {
            bw = Files.newBufferedWriter(path,java.nio.charset.StandardCharsets.UTF_8,java.nio.file.StandardOpenOption.CREATE);
            for (int i=0; i < 2;i++){
                bw.write("Player: "+users[i].getName()+";Score: "+users[i].getScore());
                bw.newLine();
            }
        } catch (IOException ex) {
            System.out.println("Error FileOperations writeFile: "+ex.getMessage());
        } finally {
               if (bw!=null) try{
                   bw.close();
            } catch (IOException ex) {
                System.out.println("Error FileOperations writeFile: "+ex.getMessage());
            }
        }
    }

}
