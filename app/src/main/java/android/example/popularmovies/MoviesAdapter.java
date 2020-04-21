package android.example.popularmovies;

import android.content.Context;
import android.content.Intent;
import android.example.popularmovies.model.Movie;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder> {
    private final String TAG = this.getClass().getSimpleName();
    private final ArrayList<Movie> mMovies;
    private final Context context;

    MoviesAdapter(Context context, ArrayList<Movie> movies) {
        this.context = context;
        mMovies = movies;
    }

    @NonNull
    @Override
    public MoviesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_list_item, parent, false);

        return new MoviesViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesViewHolder holder, int position) {
        final String posterPath = mMovies.get(position).getPosterPath();
        Picasso.get().setLoggingEnabled(true);
        Picasso.get()
                .load(posterPath)
                .into(holder.posterView);

        holder.posterView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MovieDetails.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mMovies == null) {
            return 0;
        }
        return mMovies.size();
    }

    class MoviesViewHolder extends RecyclerView.ViewHolder {
        final ImageView posterView;

        MoviesViewHolder(View itemView) {
            super(itemView);
            posterView = itemView.findViewById(R.id.movie_image);
        }
    }
}
