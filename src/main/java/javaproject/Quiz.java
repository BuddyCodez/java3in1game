package javaproject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class Quiz {
    private void GameInterFace() {
        String url = "https://opentdb.com/api.php?amount=1&type=multiple";
        try {
            Scanner scanner = new Scanner(System.in);
            // Create a URL object from the specified URL string
            URL dataUrl = new URL(url);

            // Open a connection to the URL
            HttpURLConnection connection = (HttpURLConnection) dataUrl.openConnection();

            // Set the request method
            connection.setRequestMethod("GET");

            // Get the response code
            int ResponseCode = connection.getResponseCode();

            // If the response code is HTTP_OK (200), read the data and print it
            if (ResponseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuilder response = new StringBuilder();

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                // Print the fetched data
                String jsonResponse = response.toString();
                /*
                 * {"response_code":0,"results":[{"category":"Sports","type":"multiple",
                 * "difficulty":"medium",
                 * "question":"Which car manufacturer won the 2017 24 Hours of Le Mans?"
                 * ,"correct_answer":"Porsche","incorrect_answers":["Toyota","Audi","Chevrolet"]
                 * }]}
                 */
                // showing the question extract using Gson package
                Gson gson = new Gson();
                JsonObject json = gson.fromJson(jsonResponse, JsonObject.class);
                JsonArray results = json.getAsJsonArray("results");
                if (results.size() > 0) {
                    JsonObject quizQuestion = results.get(0).getAsJsonObject();

                    String question = quizQuestion.get("question").getAsString();
                    String correctAnswer = quizQuestion.get("correct_answer").getAsString();
                    JsonArray incorrectAnswersArray = quizQuestion.getAsJsonArray("incorrect_answers");
                    String[] incorrectAnswers = new String[incorrectAnswersArray.size()];
                    for (int i = 0; i < incorrectAnswersArray.size(); i++) {
                        incorrectAnswers[i] = incorrectAnswersArray.get(i).getAsString();
                    }

                    System.out.println("Question: " + question);
                    String[] options = { correctAnswer, incorrectAnswers[0], incorrectAnswers[1], incorrectAnswers[2] };

                    // Randomize the order of options
                    List<String> optionsList = Arrays.asList(options);
                    Collections.shuffle(optionsList);
                    options = optionsList.toArray(new String[0]);
                
                    for (String opt : options) {
                        System.out.print(opt + "\t");
                    }
                    System.out.println("Enter Your Answer: ");
                    String user_option = scanner.nextLine();
                    if (user_option.toLowerCase().equals(correctAnswer.toLowerCase())) {
                        System.out.println("Correct Answer");
                    } else {
                        System.out.println("Wrong Answer! The correct answer is " + correctAnswer);
                    }
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void play() {
        GameInterFace();
    }
}
