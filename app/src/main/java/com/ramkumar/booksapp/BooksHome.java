package com.ramkumar.booksapp;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.ramkumar.booksapp.adapter.viewPagerAdapter;

public class BooksHome extends AppCompatActivity {

    private Toolbar toolbar;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books_home);
        toolbar = (Toolbar)findViewById(R.id.booksapp_toolbar);
        setSupportActionBar(toolbar);
        viewPager = (ViewPager)findViewById(R.id.booksapp_viewpager);
        setupViewPager(viewPager);
        tabLayout = (TabLayout)findViewById(R.id.booksapp_tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        viewPagerAdapter adapter = new viewPagerAdapter(getSupportFragmentManager());
        adapter.addBooksFragment(new Books_Fragment(),"Books");
        adapter.addBooksFragment(new EBooks_Fragments(),"eBooks");
        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }
}
