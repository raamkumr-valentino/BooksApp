package com.ramkumar.booksapp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ramkumar on 7/20/2017.
 */

public class viewPagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> mBooksFragments = new ArrayList<>();
    private final List<String> mBooksFragmentTitle = new ArrayList<>();

    public viewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void addBooksFragment(Fragment fragment,String title) {
        mBooksFragments.add(fragment);
        mBooksFragmentTitle.add(title);
    }

    @Override
    public Fragment getItem(int position) {
        return mBooksFragments.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mBooksFragmentTitle.get(position);
    }

    @Override
    public int getCount() {
        return mBooksFragments.size();
    }
}
