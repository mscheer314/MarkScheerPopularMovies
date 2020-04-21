package android.example.popularmovies;

import androidx.appcompat.app.AppCompatActivity;

import android.example.popularmovies.model.Movie;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MovieDetails extends AppCompatActivity {
    private TextView movieTitle;
    private TextView releaseDate;
    private ImageView moviePoster;
    private TextView voteAvg;
    private TextView overview;

    private Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        Bundle data = getIntent().getExtras();
        movie = data.getParcelable("movie");

        setViews();
    }

    private void setViews() {
        movieTitle = findViewById(R.id.movie_details_title);
        movieTitle.setText(movie.getTitle());
        releaseDate = findViewById(R.id.movie_details_release_date);
        String datePattern = "yyyy-MM-dd";
        DateFormat df = new SimpleDateFormat(datePattern, Locale.getDefault());
        String dateString = df.format(movie.getReleaseDate());
        releaseDate.setText(dateString);
        moviePoster = findViewById(R.id.movie_details_poster);
        Picasso.get().load(movie.getPosterPath()).into(moviePoster);
        voteAvg = findViewById(R.id.movie_details_vote_average);
        voteAvg.setText(String.format("%s%s", getString(
                R.string.vote_avg), String.valueOf(movie.getVoteAvg())));
        overview = findViewById(R.id.movie_details_overview);
        overview.setText(movie.getOverview());
    }
}
