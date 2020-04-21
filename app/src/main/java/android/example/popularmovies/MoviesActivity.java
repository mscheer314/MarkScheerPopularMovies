package android.example.popularmovies;

import android.example.popularmovies.utilities.MovieJsonUtility;
import android.example.popularmovies.utilities.NetworkUtils;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class MoviesActivity extends AppCompatActivity {
    private final String TAG = this.getClass().getSimpleName();

    private ArrayList<String> mPosters;
    // This int determines if the movies populated are "popular" or "top rated"
    // 1 = popular movies
    // 2 = top rated
    private int typeOfMovies = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        startAsyncTask(typeOfMovies);
    }

    private void startAsyncTask(int typeOfMovies) {
        URL url = NetworkUtils.buildUrl(typeOfMovies);
        new MovieApiPosterTask().execute(url);
    }

    private void setUpRecyclerview() {
        RecyclerView mRecyclerView = findViewById(R.id.recyclerview_movies);
        GridLayoutManager layoutManager =
                new GridLayoutManager(MoviesActivity.this, 2);
        mRecyclerView.setLayoutManager(layoutManager);

        MoviesAdapter mMovieAdapter = new MoviesAdapter(mPosters);
        mRecyclerView.setAdapter(mMovieAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_movies_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.sort_popular) {
            typeOfMovies = 1;
        } else if (item.getItemId() == R.id.sort_top_rated) {
            typeOfMovies = 2;
        }

        startAsyncTask(typeOfMovies);
        setUpRecyclerview();
        return true;
    }

    class MovieApiPosterTask extends AsyncTask<URL, ArrayList<String>, ArrayList<String>> {
        private final String TAG = this.getClass().getSimpleName();

        MovieApiPosterTask() {
        }

        @Override
        protected ArrayList<String> doInBackground(URL... urls) {
            URL movieUrl = urls[0];
            String movieResults = null;
            ArrayList<String> posterPaths = null;
            try {
                movieResults = NetworkUtils.getResponseFromHttpsUrl(movieUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                posterPaths = MovieJsonUtility.getPosterPathsFromJson(movieResults);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            mPosters = posterPaths;

            if (mPosters != null) {
                Log.v(TAG, "In doInBackground mPosters = " + mPosters.size());
            }
            return posterPaths;
        }

        @Override
        protected void onPostExecute(ArrayList<String> strings) {
            mPosters = strings;
            Log.v(TAG, "In onPostExecute mPosters = " + mPosters.size());
            setUpRecyclerview();
        }
    }
}
