package udacity.popularmoviesstage1.main;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import udacity.popularmoviesstage1.R;
import udacity.popularmoviesstage1.bean.MoviesData;


public class MoviesDetail extends AppCompatActivity {


    private MoviesData moviesData;
    private String IMG_BASE_URL = "http://image.tmdb.org/t/p/w185/";
    private ImageView imgLargeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_detail);

        moviesData = getIntent().getParcelableExtra(MoviesDetailFragment.MOVIES_DATA);

        Bundle arguments = new Bundle();
        arguments.putParcelable(MoviesDetailFragment.MOVIES_DATA, moviesData);
        imgLargeView = (ImageView) findViewById(R.id.movies_img);

        MoviesDetailFragment fragment = new MoviesDetailFragment();
        fragment.setArguments(arguments);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.movie_detail_container, fragment)
                .commit();

        Picasso.with(this).load(IMG_BASE_URL + moviesData.getImg_path()).noFade().into(imgLargeView);

        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.details_collasping_toopbar);
        collapsingToolbar.setTitle(moviesData.getTitle());
        Toolbar toolbar = (Toolbar) findViewById(R.id.details_toopbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

}
