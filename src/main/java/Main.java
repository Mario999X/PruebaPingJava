import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {
        ProcessBuilder pB = new ProcessBuilder();

        // -- Windows --
        pB.command("cmd.exe", "/c", "ping google.es");

        // -- Linux --
        //pb.command("bash", "-c", "ping google.es");

        try {
            Process process = pB.start();
            StringBuilder output = new StringBuilder();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));

            String line;

            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }
            int exit = process.waitFor();
            if (exit == 0) {
                System.out.println("Proceso correcto");
                System.out.println(output);
                System.exit(0);
            } else {
                System.out.println("Error");
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
