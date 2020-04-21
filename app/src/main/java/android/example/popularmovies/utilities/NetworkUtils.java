package android.example.popularmovies.utilities;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;


public class NetworkUtils {

    private final static String MOVIE_DB_API_KEY = "";
    private final static String POPULAR_MOVIE_BASE_URL = "https://api.themoviedb.org/3/movie/popular";
    private final static String TOP_RATED_MOVIE_BASE_URL = "https://api.themoviedb.org/3/movie/top_rated";

    private final static String PARAM_API_KEY = "api_key";

    public static URL buildUrl(int typeOfMovies) {
        Uri builtUri = null;
        if (typeOfMovies == 1) {
            builtUri = Uri.parse(POPULAR_MOVIE_BASE_URL).buildUpon()
                    .appendQueryParameter(PARAM_API_KEY, MOVIE_DB_API_KEY)
                    .build();
        } else if (typeOfMovies == 2) {
            builtUri = Uri.parse(TOP_RATED_MOVIE_BASE_URL).buildUpon()
                    .appendQueryParameter(PARAM_API_KEY, MOVIE_DB_API_KEY)
                    .build();
        }

        URL url = null;
        try {
            if (builtUri != null)
                url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    public static String getResponseFromHttpsUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}
