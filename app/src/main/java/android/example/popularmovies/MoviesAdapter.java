package android.example.popularmovies;

import android.content.Context;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder> {
    private List<String> mImageList;
    private Context context;
    private final String TAG = this.getClass().getSimpleName();

    public class MoviesViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public MoviesViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.movie_image);
        }
    }

    public MoviesAdapter(Context context, List<String> imageList) {
        this.context = context;
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
        Picasso.get()
                .load(path)
                .into(holder.imageView);

        holder.imageView.setOnClickListener(new View.OnClickListener() {
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
}
