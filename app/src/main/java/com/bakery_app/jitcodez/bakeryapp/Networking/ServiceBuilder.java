package com.bakery_app.jitcodez.bakeryapp.Networking;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jitu on 18/2/18.
 */

public class ServiceBuilder {

    private static final String URL="https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/";
    private  static Retrofit.Builder builder=new Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit =builder.build();

    public static<S> S buildService(Class<S> serviceType)
    {
        return retrofit.create(serviceType);
    }
}
