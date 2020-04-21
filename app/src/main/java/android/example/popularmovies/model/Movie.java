package android.example.popularmovies.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.Date;

public class Movie implements Parcelable {

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    private int id;
    private String title;
    private int popularity;
    private int voteAvg;
    private String posterPath;
    private Date releaseDate;
    private String overview;

    public Movie(int id, String title, int popularity, int voteAvg, String posterPath,
                 Date releaseDate, String overview) {
        this.id = id;
        this.title = title;
        this.popularity = popularity;
        this.voteAvg = voteAvg;
        this.posterPath = posterPath;
        this.releaseDate = releaseDate;
        this.overview = overview;
    }

    public Movie() { }

    public Movie(Parcel in) {
        id = in.readInt();
        title = in.readString();
        popularity = in.readInt();
        voteAvg = in.readInt();
        posterPath = in.readString();
        releaseDate = new Date(in.readLong());
        overview = in.readString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public int getVoteAvg() {
        return voteAvg;
    }

    public void setVoteAvg(int voteAvg) {
        this.voteAvg = voteAvg;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeInt(this.popularity);
        dest.writeInt(this.voteAvg);
        dest.writeString(this.posterPath);
        dest.writeLong(this.releaseDate.getTime());
        dest.writeString(this.overview);
    }

    @NonNull
    @Override
    public String toString() {
        return "Movie{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ",popularity='" + popularity + '\'' +
                ",voteAvg='" + voteAvg + '\'' +
                ",posterPath'" + posterPath + '\'' +
                ",releaseDate='" + releaseDate.toString() + '\'' +
                ",overview='" + overview + '\'';
    }
}
