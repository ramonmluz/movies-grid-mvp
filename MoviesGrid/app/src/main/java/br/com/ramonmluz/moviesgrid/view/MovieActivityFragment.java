package br.com.ramonmluz.moviesgrid.view;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.VolleyError;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.com.ramonmluz.moviesgrid.R;
import br.com.ramonmluz.moviesgrid.adapter.MovieRecyclerViewAdapter;
import br.com.ramonmluz.moviesgrid.model.vo.Movie;
import br.com.ramonmluz.moviesgrid.presenter.MoviePresenter;
import br.com.ramonmluz.moviesgrid.presenterImpl.MoviePresenterImpl;
import br.com.ramonmluz.moviesgrid.util.Constants;

public class MovieActivityFragment extends Fragment implements MovieView  {

    private RecyclerView moviesRcyclerView;
    private JSONObject jsonObject;
    private MoviePresenter moviePresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Instancia o presenter
        moviePresenter = new MoviePresenterImpl(this, getActivity());

        // Carrega o RecycleView
        moviesRcyclerView = (RecyclerView) inflater.inflate(R.layout.fragment_main, container, false);

        // Instancia o GridLayoutManeger informado que terá duas colunas
        moviesRcyclerView.setLayoutManager(new GridLayoutManager(moviesRcyclerView.getContext(), 2));

        // Neste primeiro momento, o adapter será  prereenchido com a lista vazia
        moviesRcyclerView.setAdapter(new MovieRecyclerViewAdapter(new ArrayList<Movie>()));

        return moviesRcyclerView;
    }

    @Override
    public void onStart() {
        super.onStart();
        moviePresenter.getMoviesVolley();
    }

    @Override
    public void fillRecyclerView(List<Movie> movies) {
        moviesRcyclerView.setAdapter(new MovieRecyclerViewAdapter(movies));
    }

    @Override
    public void notifyError(VolleyError error) {
         Log.e(Constants.TAG_MOVIE, error.toString());

        AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("Is necessary create a key in Site: https://api.themoviedb.org");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        alertDialog.show();
    }
}
