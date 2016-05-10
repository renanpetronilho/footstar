package com.sensedia.footstar.api;

import com.sensedia.footstar.model.Team;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by renanpetronilho on 10/05/16.
 */
public interface TeamAPI {

    @GET("teams")
    Call<List<Team>> getTeams();

    @GET("teams/{id}")
    Call<Team> getTeamById(@Path("id") Long id);

    @POST("teams")
    Call<Team> createTeam(@Body Team team);

    @PUT("teams/{id}")
    Call<Team> updateTeam(@Body Team team, @Path("id") Long id);
}
