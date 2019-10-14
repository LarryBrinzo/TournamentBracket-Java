package com.tournamentbracketjava.Network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FinalsGridApi {

    private static Retrofit retrofit;
    private static final String Api_Url = "";

    public static Retrofit getFinalsGrid() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(Api_Url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
