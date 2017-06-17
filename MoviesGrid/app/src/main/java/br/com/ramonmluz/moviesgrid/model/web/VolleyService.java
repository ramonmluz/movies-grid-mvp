package br.com.ramonmluz.moviesgrid.model.web;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

import br.com.ramonmluz.moviesgrid.model.vo.Movie;
import br.com.ramonmluz.moviesgrid.presenterImpl.MoviePresenterImpl;
import br.com.ramonmluz.moviesgrid.util.Constants;

/**
 * Created by ramon on 08/06/17.
 */

public class VolleyService {

    private Context context;
    private JSONObject movieJsonObject;
    private List<Movie> movies;
    private RequestMovieLister requestMovieLister;
    private MoviePresenterImpl.ResultListner mResultListner;


    public VolleyService(Context context, MoviePresenterImpl.ResultListner resultListner) {
        this.context = context;
        mResultListner = resultListner;
    }

    public void getMoviesVolley() {
        Uri uri = buildUri();

        requestMovieLister = new RequestMovieLister();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, uri.toString(), null,
                requestMovieLister, requestMovieLister) {
        };

        // add it to the RequestQueue
        VolleyRequest.getInstance(context).addToRequestQueue(request);
    }

    private Uri buildUri() {
        // Construindo uma URI
        return Uri.parse(Constants.POPULAR_MOVIE_URL).buildUpon()
                .appendPath(Constants.POPULAR)
                .appendQueryParameter(Constants.API_KEY, Constants.KEY_MOVIE_DB)
                .appendQueryParameter(Constants.LANGUAGE, Constants.LANGUAGE_VALUE)
                .appendQueryParameter(Constants.PAGE, Constants.PAGE_VALUE).build();
    }

    public List <Movie> obtainMoviesDataFromJson(JSONObject movieJsonObject) throws JSONException {
        Movie[] movies = null;
        if (movieJsonObject != null) {
            JSONArray movieArray = movieJsonObject.getJSONArray(Movie.ParametersEnum.RESULTS.getValue());

            movies = new Movie[movieArray.length()];

            for (int i = 0; i < movieArray.length(); i++) {
                JSONObject dataMovie = movieArray.getJSONObject(i);

                // Preenche através do construtor
                movies[i] = new Movie(dataMovie.getString(Movie.ParametersEnum.ID.getValue()),
                        dataMovie.getString(Movie.ParametersEnum.POSTER_PATH.getValue()),
                        dataMovie.getString(Movie.ParametersEnum.ORIGINAL_TITLE.getValue()),
                        dataMovie.getString(Movie.ParametersEnum.OVERVIEW.getValue()),
                        dataMovie.getString(Movie.ParametersEnum.VOTE_AVERAGE.getValue()),
                        dataMovie.getString(Movie.ParametersEnum.RELEASE_DATE.getValue()));
            }

           return Arrays.asList(movies);
        }
        return null;

    }

    class RequestMovieLister implements Response.Listener<JSONObject>, Response.ErrorListener {

        @Override
        public void onErrorResponse(VolleyError error) {
            Log.d("Error.Response", error.toString());
            mResultListner.notifyError(error);
        }

        @Override
        public void onResponse(JSONObject response) {
            Log.d("Response", response.toString());
            try {
                mResultListner.notifySuccess(obtainMoviesDataFromJson(response));
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }


}
