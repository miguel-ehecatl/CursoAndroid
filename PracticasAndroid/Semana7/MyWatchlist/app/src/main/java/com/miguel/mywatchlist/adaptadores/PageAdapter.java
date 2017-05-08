package com.miguel.mywatchlist.adaptadores;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.miguel.mywatchlist.fragmentos.FavoritosFragment;
import com.miguel.mywatchlist.fragmentos.TopPeliculasFragment;
import com.miguel.mywatchlist.fragmentos.TopSeriesFragment;

/**
 * Created by miguelangel on 4/29/17.
 */

public class PageAdapter extends FragmentStatePagerAdapter{

    private int numeroTabs;

    public PageAdapter(FragmentManager fm,int numeroTabs) {
        super(fm);
        this.numeroTabs=numeroTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return FavoritosFragment.nuevaInstancia();
            case 1:
                return TopPeliculasFragment.nuevaInstancia();
            case 2:
                return TopSeriesFragment.nuevaInstancia();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numeroTabs;
    }
}
