package android.example.popularmovies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.example.popularmovies.utilities.MovieJsonUtility;
import android.example.popularmovies.utilities.NetworkUtils;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class MoviesActivity extends AppCompatActivity {
    private final String TAG = this.getClass().getSimpleName();
    private RecyclerView mRecyclerView;
    private MoviesAdapter mMovieAdapter;
    private GridLayoutManager layoutManager;

    private ArrayList<String> mPosters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);

        URL url = NetworkUtils.buildUrl();
        new MovieApiPosterTask().execute(url);
        setUpRecyclerview();
    }

    private void setUpRecyclerview() {
        mRecyclerView = findViewById(R.id.recyclerview_movies);
        GridLayoutManager layoutManager =
                new GridLayoutManager(MoviesActivity.this, 2);
        mRecyclerView.setLayoutManager(layoutManager);

        mMovieAdapter = new MoviesAdapter(MoviesActivity.this, mPosters);
        mRecyclerView.setAdapter(mMovieAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_movies_activity, menu);
        return true;
    }

    public class MovieApiPosterTask extends AsyncTask<URL, ArrayList<String>, ArrayList<String>> {
        private final String TAG = this.getClass().getSimpleName();

        public MovieApiPosterTask() {
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

            Log.v(TAG, "In doInBackground mPosters = " + mPosters.size());
            return posterPaths;
        }

        @Override
        protected void onPostExecute(ArrayList<String> strings) {
            mPosters = strings;
            Log.v(TAG, "In onPostExecute mPosters = " + mPosters.size());
        }
    }
}