package udacity.popularmoviesstage1.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import udacity.popularmoviesstage1.R;
import udacity.popularmoviesstage1.bean.MoviesData;

public class MainActivity extends AppCompatActivity implements MoviesFragment.Callback {

    private boolean mDualPane;
    private static final String TAG = "DetailsTag";
    static final String FILTER_POPULAR = "popularity.desc";
    static final String MOVIES_DETAIL_FRAGMENT_TAG = "TAG";
    static final String FILTER_HIGEST_RATED = "vote_average.desc";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.movies_detail_container) != null) {
            mDualPane = true;
            if (savedInstanceState == null) {
                getSupportFragmentManager().beginTransaction().replace(R.id.movies_detail_container, new MoviesDetailFragment(), TAG);
            }
        } else {
            mDualPane = false;

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_pouplar) {
            getSupportActionBar().setTitle(getString(R.string.most_popular_string));
            MoviesFragment moviesFragment = (MoviesFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_movies);
            if (null != moviesFragment) {
                moviesFragment.setFilter(FILTER_POPULAR);
            }

            return true;
        }
        if (id == R.id.action_highest_rated) {
            getSupportActionBar().setTitle(getString(R.string.highest_rating_string));
            MoviesFragment moviesFragment = (MoviesFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_movies);
            if (null != moviesFragment) {
                moviesFragment.setFilter(FILTER_HIGEST_RATED);
            }

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onGridItemSelected(ArrayList<MoviesData> moviesData, int pos) {

        MoviesData moviesPojo = new MoviesData();
        moviesPojo.setTitle(moviesData.get(pos).getTitle());
        moviesPojo.setRating(moviesData.get(pos).getRating());
        moviesPojo.setImg_path(moviesData.get(pos).getImg_path());
        moviesPojo.setReleaseDate(moviesData.get(pos).getReleaseDate());
        moviesPojo.setVote_count(moviesData.get(pos).getVote_count());
        moviesPojo.setDesc(moviesData.get(pos).getDesc());

        if (mDualPane) {
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.
            Bundle args = new Bundle();
            Bundle args1 = new Bundle();

            args.putParcelable(MoviesDetailFragment.MOVIES_DATA, moviesPojo);

            MoviesDetailFragment fragment = new MoviesDetailFragment();
            fragment.setArguments(args);

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.movies_detail_container, fragment, MOVIES_DETAIL_FRAGMENT_TAG)
                    .commit();
        } else {
            Intent intent = new Intent(this, MoviesDetail.class);
            intent.putExtra(MoviesDetailFragment.MOVIES_DATA, moviesPojo);
            startActivity(intent);
        }
    }
}
