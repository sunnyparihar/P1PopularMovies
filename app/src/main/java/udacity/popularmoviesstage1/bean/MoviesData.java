package udacity.popularmoviesstage1.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by pawankumar on 21/08/15.
 */
public class MoviesData implements Parcelable {

    private String title;
    private String desc;
    private String releaseDate;
    private String img_path;
    private double popularity,rating;
    private int vote_count,movie_id;
    private int mData;

    public MoviesData(){

    }

    private MoviesData(Parcel in) {
        readFromParcel(in);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getImg_path() {
        return img_path;
    }

    public void setImg_path(String img_path) {
        this.img_path = img_path;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

    public int getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(int movie_id) {
        this.movie_id = movie_id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(desc);
        parcel.writeString(releaseDate);
        parcel.writeString(img_path);
        parcel.writeDouble(popularity);
        parcel.writeDouble(rating);
        parcel.writeInt(vote_count);
        parcel.writeInt(movie_id);
    }

    private void readFromParcel(Parcel in) {
        // We just need to read back each
        // field in the order that it was
        // written to the parcel
        title = in.readString();
        desc = in.readString();
        releaseDate = in.readString();
        img_path = in.readString();
        popularity = in.readDouble();
        rating = in.readDouble();
        vote_count = in.readInt();
        movie_id = in.readInt();
    }


    public static final Creator CREATOR =
            new Creator() {
                public MoviesData createFromParcel(Parcel in) {
                    return new MoviesData(in);
                }

                public MoviesData[] newArray(int size) {
                    return new MoviesData[size];
                }
            };
}
