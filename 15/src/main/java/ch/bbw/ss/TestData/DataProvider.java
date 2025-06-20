package ch.bbw.ss.TestData;

import ch.bbw.ss.Model.Person;
import ch.bbw.ss.adapter.GsonAdapter;
import com.google.gson.Gson;
import org.instancio.Instancio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataProvider {
    private static final String filePath = ".\\src\\main\\java\\ch\\bbw\\ss\\TestData\\data.json";

    public static List<Person> GetData(){
        EnsureData();

        var dataFile = new File(filePath);
        var dataString = "";

        if(dataFile.exists()){

            try{
                Scanner myReader = new Scanner(dataFile);
                while (myReader.hasNextLine()) {
                    dataString += myReader.nextLine();
                }
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            }

            var gson = GsonAdapter.createGson();
            var idk = gson.fromJson(dataString, Person[].class);
            return List.of(idk);
        }
        else {
            System.out.println("Something went wrong, file does not exist...");
            return null;
        }
    }

    private static void EnsureData(){
        File file;

        try {
            file = new File(filePath);
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            System.out.println(e.getMessage());
            return;
        }

        FileWriter writer = null;
        try{
            var data = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                var person = Instancio.create(Person.class);
                data.add(person);
            }

            Gson gson = GsonAdapter.createGson();
            String jsonData = gson.toJson(data);


            writer = new FileWriter(file);
            writer.write(jsonData);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        finally {
            try{
                if(writer != null){
                    writer.close();
                }
            }
            catch (IOException e){
                System.out.println(e.getMessage());
            }
        }
    }
}
