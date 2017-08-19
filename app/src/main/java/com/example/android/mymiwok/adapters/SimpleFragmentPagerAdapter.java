package com.example.android.mymiwok.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.android.mymiwok.R;
import com.example.android.mymiwok.fragments.ColorsFragment;
import com.example.android.mymiwok.fragments.FamilyFragment;
import com.example.android.mymiwok.fragments.NumbersFragment;
import com.example.android.mymiwok.fragments.PhrasesFragment;

/**
 * Created by joselobm on 19/08/17.
 */

public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 4;
    private int tabTitles[] = new int[] {R.string.str_colors, R.string.str_family, R.string.str_numbers, R.string.str_phrases };

    private Context context;

    public SimpleFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new ColorsFragment();
        } else if (position == 1){
            return new FamilyFragment();
        } else if (position == 2) {
            return new NumbersFragment();
        } else {
            return new PhrasesFragment();
        }
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return context.getString(tabTitles[position]);
    }
}
