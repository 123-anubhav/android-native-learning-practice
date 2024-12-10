package online.fakedevelopers.retrofitexample.rest;

import online.fakedevelopers.retrofitexample.entity.Repo;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("products/1")
    Call<Repo> listRepos();
}
