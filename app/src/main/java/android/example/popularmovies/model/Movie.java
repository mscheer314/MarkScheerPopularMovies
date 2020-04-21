package android.example.popularmovies.model;

import java.util.Date;

public class Movie {
    private int id;
    private String title;
    private int popularity;
    private int voteAvg;
    private String posterPath;
    private Date releaseDate;
    private String overview;



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
}
