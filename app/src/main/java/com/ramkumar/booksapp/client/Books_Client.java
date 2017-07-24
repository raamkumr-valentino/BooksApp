package com.ramkumar.booksapp.client;


import com.ramkumar.booksapp.model.pojo.Books;

import retrofit2.Call;
import retrofit2.http.GET;


/**
 * Created by Ramkumar on 7/17/2017.
 */

public interface Books_Client {

    @GET("volumes?q=maxResults=10&key=AIzaSyDNpo36bF-hhoGzBDEyLMqIllgfuLmg00I")
    Call<Books> getBooks();

    @GET("volumes?q=maxResults=10&filter=free-ebooks&key=AIzaSyDNpo36bF-hhoGzBDEyLMqIllgfuLmg00I")
    Call<Books> geteBooks();

}
