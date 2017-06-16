package br.com.ramonmluz.moviesgrid.presenterImpl;

import android.content.Context;

import com.android.volley.VolleyError;

import java.util.List;

import br.com.ramonmluz.moviesgrid.model.vo.Movie;
import br.com.ramonmluz.moviesgrid.model.web.VolleyService;
import br.com.ramonmluz.moviesgrid.presenter.MoviePresenter;
import br.com.ramonmluz.moviesgrid.view.MovieView;


public class MoviePresenterImpl implements MoviePresenter {

    private MovieView mView;
    private Context context;
    private VolleyService movieWeb;

    public class ResultListner implements IResult {

        @Override
        public void notifySuccess(List<Movie> movies) {

            mView.fillRecyclerView(movies);
        }

        @Override
        public void notifyError(VolleyError error) {
            mView.notifyError(error);
        }
    }

    public MoviePresenterImpl(MovieView view, Context context) {
        this.mView = view;
        this.context = context;
        ResultListner mResultListner  = new ResultListner();

        this.movieWeb =  new VolleyService(this.context, mResultListner);
    }

    public void getMoviesVolley()
    {
        movieWeb.getMoviesVolley();
    }

}
