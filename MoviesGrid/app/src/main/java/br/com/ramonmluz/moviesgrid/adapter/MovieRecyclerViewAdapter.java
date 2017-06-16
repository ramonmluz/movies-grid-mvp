package br.com.ramonmluz.moviesgrid.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.ramonmluz.moviesgrid.R;
import br.com.ramonmluz.moviesgrid.model.vo.Movie;


public class MovieRecyclerViewAdapter extends RecyclerView.Adapter<MovieRecyclerViewAdapter.ViewHolder>{

    private static String LOG_TAG = MovieRecyclerViewAdapter.class.getSimpleName();
    private List<Movie> movies;


    public class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView movieImageView;

        public ViewHolder(View itemView) {

            super(itemView);

            // Carrega a imagem do item da RecycleView layout
            movieImageView = (ImageView) itemView.findViewById(R.id.movie_image_grid);
        }
    }

    public MovieRecyclerViewAdapter(List<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public MovieRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Carrega o layout dos itens da Receycle view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MovieRecyclerViewAdapter.ViewHolder holder, int position) {
        final Movie movie = movies.get(position);

        holder.movieImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Método executado quando você clica na imagem.
            }
        });

        // Obtem a imagem do filme e a insere no componente de imagem instaciada
        Picasso.with(holder.movieImageView.getContext()).load(movie.getPosterPath()).into(holder.movieImageView);
    }

    public int getItemCount() {
        return movies.size();
    }
}
