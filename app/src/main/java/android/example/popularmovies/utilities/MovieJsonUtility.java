package android.example.popularmovies.utilities;

import android.example.popularmovies.model.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MovieJsonUtility {
    private final static String MOVIE_DB_POSTER_BASE_URL = "https://image.tmdb.org/t/p/";
    private final static String SIZE_PARAM = "w500";

    public static ArrayList<Movie> getMoviesFromJson(String movieJsonString) throws JSONException {
        final String OWM_ID = "id";
        final String OWM_TITLE = "title";
        final String OWM_POPULARITY = "popularity";
        final String OWM_VOTE_AVG = "vote_average";
        final String OWM_POSTER_PATH = "poster_path";
        final String OWM_RELEASE_DATE = "release_date";
        final String OWM_OVERVIEW = "overview";

        ArrayList<Movie> moviesList = new ArrayList<Movie>() {
        };

        JSONObject movieJson = new JSONObject(movieJsonString);
        JSONArray results = movieJson.getJSONArray("results");
        for (int i = 0; i < results.length(); i++) {
            Movie m = new Movie();
            JSONObject j = results.getJSONObject(i);
            m.setId(j.getInt(OWM_ID));
            m.setTitle(j.getString(OWM_TITLE));
            m.setPopularity(j.getInt(OWM_POPULARITY));
            m.setVoteAvg(j.getInt(OWM_VOTE_AVG));
            m.setPosterPath(MOVIE_DB_POSTER_BASE_URL + SIZE_PARAM + j.getString(OWM_POSTER_PATH));
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            try {
            m.setReleaseDate(formatter.parse(j.getString(OWM_RELEASE_DATE)));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            m.setOverview(j.getString(OWM_OVERVIEW));

            moviesList.add(m);
        }
        return moviesList;
    }
}
