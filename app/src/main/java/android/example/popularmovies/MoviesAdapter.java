package android.example.popularmovies;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder> {
    private final String TAG = this.getClass().getSimpleName();
    private final List<String> mImageList;

    MoviesAdapter(List<String> imageList) {
        mImageList = imageList;
        if (mImageList != null) {
            Log.v(TAG, "In MoviesAdapter constructor mImageList.size = " + mImageList.size());
        } else {
            Log.v(TAG, "mImageList is null");
        }
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
        final String path = mImageList.get(position);
        Picasso.get().setLoggingEnabled(true);
        Picasso.get()
                .load(path)
                .into(holder.posterView);

        holder.posterView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO implement onClick behavior for moviePosters
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mImageList == null) {
            return 0;
        }
        return mImageList.size();
    }

    class MoviesViewHolder extends RecyclerView.ViewHolder {
        final ImageView posterView;

        MoviesViewHolder(View itemView) {
            super(itemView);
            posterView = itemView.findViewById(R.id.movie_image);
        }
    }
}
