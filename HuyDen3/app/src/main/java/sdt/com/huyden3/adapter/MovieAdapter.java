package sdt.com.huyden3.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import sdt.com.huyden3.R;
import sdt.com.huyden3.pojo.Result;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    public static final String HEADER_URL_IMAGE = "https://image.tmdb.org/t/p/w500/";

    private Context context;
    private List<Result> listMovies;
    private MovieItemClickListener listener;

    public interface MovieItemClickListener {
        void onMovieClick(Result result);
    }

    public MovieAdapter(Context context, List<Result> listMovies, MovieItemClickListener listener) {
        this.context = context;
        this.listMovies = listMovies;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.movie_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Result result = listMovies.get(position);
        Picasso.with(context).load(HEADER_URL_IMAGE + result.getPosterPath())
                .fit().centerInside()
                .into(holder.poster);
        holder.title.setText(result.getTitle());
        holder.overview.setText(result.getOverview());
        holder.itemView.setOnClickListener(v -> listener.onMovieClick(result));
        holder.playMovie.setVisibility(result.getVideo() ? View.VISIBLE : View.GONE);
    }

    @Override
    public int getItemCount() {
        return listMovies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView poster;
        TextView title;
        TextView overview;
        ImageButton playMovie;

        public ViewHolder(View itemView) {
            super(itemView);
            poster = itemView.findViewById(R.id.poster);
            title = itemView.findViewById(R.id.movie_title);
            overview = itemView.findViewById(R.id.movie_overview);
            playMovie = itemView.findViewById(R.id.play_movie);
        }
    }
}
