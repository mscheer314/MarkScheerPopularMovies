package android.example.popularmovies.utilities;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MovieJsonUtility {
    private final static String MOVIE_DB_POSTER_BASE_URL = "https://image.tmdb.org/t/p/";
    private final static String SIZE_PARAM = "w500";

    public static ArrayList<String> getPosterPathsFromJson(String movieJsonString) throws JSONException {
        final String OWM_POSTER_PATH = "poster_path";

        ArrayList<String> moviePosterPaths = new ArrayList<>();

        JSONObject movieJson = new JSONObject(movieJsonString);
        JSONArray results = movieJson.getJSONArray("results");
        for (int i = 0; i < results.length(); i++) {
            JSONObject j = results.getJSONObject(i);
            String posterPath = j.getString(OWM_POSTER_PATH);
            moviePosterPaths.add(MOVIE_DB_POSTER_BASE_URL + SIZE_PARAM + posterPath);
        }
        return moviePosterPaths;
    }
}
