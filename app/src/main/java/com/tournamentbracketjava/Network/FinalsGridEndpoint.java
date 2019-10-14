package com.tournamentbracketjava.Network;

import com.tournamentbracketjava.Models.GridDataClass;
import retrofit2.http.GET;
import retrofit2.Call;

public interface FinalsGridEndpoint {

    @GET("/")
    Call<GridDataClass> getFinalGrid();
}
