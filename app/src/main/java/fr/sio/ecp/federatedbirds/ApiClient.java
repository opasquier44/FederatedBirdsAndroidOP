package fr.sio.ecp.federatedbirds;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import fr.sio.ecp.federatedbirds.model.Message;
import fr.sio.ecp.federatedbirds.model.User;

/**
 * Created by Michaël on 24/11/2015.
 */
public class ApiClient {

    private static final String API_BASE = "http://localhost:8080/";

    private static ApiClient mInstance;

    public static synchronized ApiClient getInstance() {
        if (mInstance == null) {
            mInstance = new ApiClient();
        }
        return mInstance;
    }

    private ApiClient() {

    }

    private String getUserToken() {
        return "azetyiu";
    }

    private <T> T get(String path, Type type) throws IOException {
        String url = API_BASE + path;
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        String token = getUserToken();
        if (token != null) {
            connection.addRequestProperty("Authorization", "Bearer " + token);
        }
        Reader reader = new InputStreamReader(connection.getInputStream());
        try {
            return new Gson().fromJson(reader, type);
        } finally {
            reader.close();
        }
    }

    public List<Message> getMessages() throws IOException {
        TypeToken<List<Message>> type = new TypeToken<List<Message>>() {};
        return get("/messages", type.getType());
    }

    public User getUser(long id) throws IOException {
        return get("/users/" + id, User.class);
    }

}
