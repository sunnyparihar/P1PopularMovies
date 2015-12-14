package udacity.popularmoviesstage1.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import udacity.popularmoviesstage1.R;
import udacity.popularmoviesstage1.bean.MoviesData;

/**
 * A placeholder fragment containing a simple view.
 */
public class MoviesDetailFragment extends Fragment {


    View rootView;
    private TextView total_user, rating, description, release_date;
    private ImageView imgThumbnail;
    private String IMG_BASE_URL = "http://image.tmdb.org/t/p/w185/";
    public static final String MOVIES_DATA = "MOVIES_DATA";
    private MoviesData moviesData;


    public MoviesDetailFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_movies_detail, container, false);

        total_user = (TextView) rootView.findViewById(R.id.total_user);
        rating = (TextView) rootView.findViewById(R.id.rating);
        description = (TextView) rootView.findViewById(R.id.description);
        release_date = (TextView) rootView.findViewById(R.id.release_date);

        imgThumbnail = (ImageView) rootView.findViewById(R.id.thumb);

        Bundle arguments = getArguments();

        if (arguments != null) {
            moviesData = arguments.getParcelable(MoviesDetailFragment.MOVIES_DATA);
        }

        if (moviesData.getReleaseDate() != null)
            release_date.setText(getString(R.string.release_date_string) + " " + moviesData.getReleaseDate());

        rating.setText(getString(R.string.user_rating_string) + " " + String.valueOf(moviesData.getRating()));

        if (moviesData.getDesc() != null)
            description.setText(String.valueOf(moviesData.getDesc()));

        total_user.setText(getString(R.string.total_user_string) + " " + String.valueOf(moviesData.getVote_count()));

        Picasso.with(getActivity()).load(IMG_BASE_URL + moviesData.getImg_path()).noFade().into(imgThumbnail);

        return rootView;
    }


}
