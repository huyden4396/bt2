package sdt.com.huyden3.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.gson.Gson;

import sdt.com.huyden3.adapter.MovieAdapter;
import sdt.com.huyden3.MyApp;
import sdt.com.huyden3.R;
import sdt.com.huyden3.pojo.Movie;
import sdt.com.huyden3.pojo.Result;


public class MainActivity extends AppCompatActivity implements MovieAdapter.MovieItemClickListener {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_view);

        getSupportActionBar().setTitle("Movies");

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,
                false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        init();
    }

    private void init() {
        Gson gson = new Gson();
        String moviesJSON = MyApp.getMsgMovie();
        Movie movie = gson.fromJson(moviesJSON, Movie.class);
        if (movie != null) {
            MovieAdapter adapter = new MovieAdapter(this, movie.getResults(), this);
            recyclerView.setAdapter(adapter);
        } else {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onMovieClick(Result result) {
        Intent intent = new Intent(this, DetailMovieActivity.class);
        intent.putExtra("detail", result);
        startActivity(intent);
    }
}
