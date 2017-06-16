package br.com.ramonmluz.moviesgrid.presenter;

import com.android.volley.VolleyError;

import java.util.List;

import br.com.ramonmluz.moviesgrid.model.vo.Movie;

/**
 * Created by ramon on 08/06/17.
 */

public interface MoviePresenter {
    void getMoviesVolley();

    public interface IResult {
        public void notifySuccess(List<Movie> movies);
        public void notifyError(VolleyError error);
    }


}
