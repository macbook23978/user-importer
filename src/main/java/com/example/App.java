package com.example;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.exceptions.CsvValidationException;
import com.google.gson.Gson;
import org.apache.hc.client5.http.fluent.Request;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.client5.http.fluent.Content;

import java.io.FileReader;
import java.io.IOException;

public class App {
    public static void main(String[] args) {
        // Uso argomenti: <file.csv> <url-server>
        String csvFile = args.length > 0 ? args[0] : "users.csv";
        String apiUrl = args.length > 1 ? args[1] : "http://192.168.1.10:8080/users";
        Gson gson = new Gson();

     try (CSVReader reader = new CSVReaderBuilder(new FileReader(csvFile))
        .withCSVParser(new CSVParserBuilder().withSeparator(';').build())
        .build()) {

          String[] line;
          reader.readNext(); // Salta intestazione
          while ((line = reader.readNext()) != null) {
          if (line.length < 4) {
              System.out.println("Riga CSV incompleta: " + String.join(";", line));
            continue;
          }

                // CSV: firstName;lastName;email;address
                String firstName = line[0];
                String lastName = line[1];
                String email = line[2];
                String address = line[3];

                // Trasforma in JSON
                String json = gson.toJson(new User(firstName, lastName, email, address));

                // Invia POST e gestisce server spento
                try {
                    Content response = Request.post(apiUrl)
                            .bodyString(json, ContentType.APPLICATION_JSON)
                            .execute()
                            .returnContent();
                    System.out.println("POST Response: " + response.asString());
                } catch (IOException e) {
                    System.out.println("Errore: server non disponibile all'URL " + apiUrl);
                }
            }
        } catch (IOException | CsvValidationException e) {
            System.out.println("Errore nella lettura del CSV: " + e.getMessage());
        }
    }

    // Classe interna per JSON
    static class User {
        String firstName;
        String lastName;
        String email;
        String address;

        User(String firstName, String lastName, String email, String address) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.address = address;
        }
    }
}
