import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.lang.Math;
import java.util.Scanner;

public class v {
        public static void main(String[] args) throws IOException {
            Scanner input = new Scanner(System.in);
            String text = input.nextLine();

                String around = "";
                for (int i = 0; i< text.length(); i++) {
                    around += text.charAt(text.length()-(i+1));
                }

                System.out.println(around);

        }
    }

