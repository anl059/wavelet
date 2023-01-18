import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

class Handler implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.
    int num = 0;

    public String handleRequest(URI url) {
        ArrayList<String> bank = new ArrayList<>();
        String output = "";
        if (url.getPath().contains("/add")) {
            String[] parameters = url.getQuery().split("?s=");
            bank.add(parameters[1]);
        }
        if (url.getPath().contains("/search")) {
            String[] parameters = url.getQuery().split("?s=");
            String search = parameters[1];
            for(int i = 0; i < bank.size(); i++){
                if(bank.get(i).contains(search)){
                    output += bank.get(i) + " ";
                }
            }
        }
        return output;
    }
}

class SearchEngine {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}
