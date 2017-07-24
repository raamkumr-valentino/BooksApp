package com.ramkumar.booksapp;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ramkumar.booksapp.adapter.Recycler_Adapter;
import com.ramkumar.booksapp.client.Books_Client;
import com.ramkumar.booksapp.model.pojo.Book;
import com.ramkumar.booksapp.model.pojo.Books;
import com.ramkumar.booksapp.model.pojo.ImageLinks;
import com.ramkumar.booksapp.model.pojo.Item;
import com.ramkumar.booksapp.model.pojo.RetailPrice;
import com.ramkumar.booksapp.model.pojo.SaleInfo;
import com.ramkumar.booksapp.model.pojo.VolumeInfo;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Books_Fragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Books_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Books_Fragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private RecyclerView recyclerView;
    private static final String TAG = "RecyclerViewFragment";
    private List<Book> bookList = new ArrayList<>();
    private Recycler_Adapter adapter;
    private View rootView;


    public Books_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Books_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Books_Fragment newInstance(String param1, String param2) {
        Books_Fragment fragment = new Books_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_books_,container,false);
        rootView.setTag(TAG);
        setRecycler();
        return rootView;
    }

    private void setRecycler() {
        recyclerView = (RecyclerView)rootView.findViewById(R.id.booksapp_recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        networkcall();
    }

    private void networkcall() {

        String API_BASE_URL = "https://www.googleapis.com/books/v1/";

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.client(httpClient.build())
                .build();

        Books_Client booksInterface = retrofit.create(Books_Client.class);

        Call<Books> call = booksInterface.getBooks();
        call.enqueue(new Callback<Books>() {
            @Override
            public void onResponse(@NonNull Call<Books> call, @NonNull retrofit2.Response<Books> response) {
                if(response.isSuccessful()){
                    List<Item> itemList = response.body().getItems();
                    for (Item item : itemList) {
                        VolumeInfo volumeInfo = item.getVolumeInfo();
                        SaleInfo salesinfo = item.getSaleInfo();
                        RetailPrice retail_price = salesinfo.getRetailPrice();
                        String title = volumeInfo.getTitle();
                        List<String> authList = volumeInfo.getAuthors();
                        String authors= "";Integer pages = 0;
                        if(authList.size() == 0)
                            authors = "by Unknown";
                        else
                            authors = "by "+authList.get(0);
                        ImageLinks links = volumeInfo.getImageLinks();
                        String imageUrl = links.getSmallThumbnail();
                        double rating = 0,price= 0;
                        if(volumeInfo.getAverageRating() != null){
                            rating = volumeInfo.getAverageRating();   
                        }else {
                            rating = 1;
                        }

                        if(retail_price.getAmount() != null){
                            price = retail_price.getAmount();
                        }else {
                            price = 0.00;
                        }
                        if(volumeInfo.getPageCount() != 0){
                            pages = volumeInfo.getPageCount();
                        }else {
                            pages = 0;
                        }
                        bookList.add(new Book(title, authors, imageUrl, rating, price, pages));
                    }
                    adapter = new Recycler_Adapter(getActivity(),bookList);
                    recyclerView.setAdapter(adapter);

                }else {
                    Toast.makeText(getContext(), response.body()+"", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Books> call, @NonNull Throwable t) {

            }
        });

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

 /*   @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }
*/
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
