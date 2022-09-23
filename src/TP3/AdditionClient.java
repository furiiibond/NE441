package TP3;

import java.io.IOException;
import java.util.List;

/**
 * The objective of this exercise is to be able to write a program capable of reading and interpreting questions in a TCP stream.
 * A game server is installed on the teacher's machine, the server has the IP address 192.168.130.150 and listens on port 7500 in TCP.
 */
public class AdditionClient {

    private ClientTCP client;

    public static void main(String[] args) {
        AdditionClient additionClient = new AdditionClient("127.0.0.1", 7500);
        additionClient.execute();
    }

    public AdditionClient(String ip, int port) {
        this.client = new ClientTCP(ip, port);
    }

    public void execute() {
        try {
            while (true) {
                StringBuilder responce = new StringBuilder();
                String questionsString = this.client.receive();
                List<String> questions = List.of(questionsString.split("\\?"));
                try {
                    for (String question : questions) {
                        String[] questionParts = question.split("\\+");
                        int opp1 = Integer.parseInt(questionParts[0]);
                        int opp2 = Integer.parseInt(questionParts[1].split("=")[0]);
                        int result = opp1 + opp2;
                        responce.append(result).append(";");
                    }

                } catch (Exception e) {
                    responce.append("0;");
                }
                this.client.send(responce.toString());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}

