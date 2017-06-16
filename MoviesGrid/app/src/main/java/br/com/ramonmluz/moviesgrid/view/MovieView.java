package br.com.ramonmluz.moviesgrid.view;


import com.android.volley.VolleyError;

import java.util.List;

import br.com.ramonmluz.moviesgrid.model.vo.Movie;

public interface MovieView {

      void fillRecyclerView(List<Movie> movies);
      void notifyError(VolleyError error);
}
