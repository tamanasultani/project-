import java.io.*;
import java.util.*;

class Word {
    String word;
    float[] vector;

    public Word(String word, float[] vector) {
        this.word = word;
        this.vector = vector;
    }

    @Override
    public String toString() {
        return word + " " + Arrays.toString(vector);
    }
}

public class KdTreeLoader {
    public static void main(String[] args) {
        String filename = "dat.1"; 
        List<Word> wordList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line = br.readLine(); // Read the first line
            if (line != null) {
                // The first line contains the count of words and the vector dimensions
                String[] firstLine = line.split(" ");
                int wordCount = Integer.parseInt(firstLine[0]);
                int vectorDimension = Integer.parseInt(firstLine[1]);

                System.out.println("Words: " + wordCount + ", Dimensions: " + vectorDimension);

                // Read the remaining lines
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(" ");
                    String word = parts[0];
                    float[] vector = new float[vectorDimension];

                    for (int i = 0; i < vectorDimension; i++) {
                        vector[i] = Float.parseFloat(parts[i + 1]);
                    }

                    wordList.add(new Word(word, vector));
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        // Print loaded words for verification
        for (Word w : wordList) {
            System.out.println(w);
        }
    }
}
