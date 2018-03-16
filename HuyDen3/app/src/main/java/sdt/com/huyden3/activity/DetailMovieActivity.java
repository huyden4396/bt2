package sdt.com.huyden3.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import sdt.com.huyden3.R;
import sdt.com.huyden3.pojo.Result;

import static sdt.com.huyden3.adapter.MovieAdapter.HEADER_URL_IMAGE;

public class DetailMovieActivity extends AppCompatActivity {
    ImageView poster;
    TextView title;
    TextView releaseDate;
    TextView overview;
    RatingBar voteAverage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        poster = findViewById(R.id.poster);
        title = findViewById(R.id.title);
        releaseDate = findViewById(R.id.release_date);
        overview = findViewById(R.id.movie_overview);
        voteAverage = findViewById(R.id.rating_bar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        Intent intent = getIntent();
        if (intent != null) {
            Result result = (Result) intent.getSerializableExtra("detail");
            if (result != null) {
                Picasso.with(this).load(HEADER_URL_IMAGE + result.getBackdropPath())
                        .fit().centerInside().into(poster);
                title.setText(result.getTitle());
                overview.setText(result.getOverview());
                voteAverage.setRating(result.getVoteAverage() / 2);
                releaseDate.setText("Release Date: " + result.getReleaseDate());
                getSupportActionBar().setTitle(result.getTitle());
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
