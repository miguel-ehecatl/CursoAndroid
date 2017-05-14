package com.miguel.mywatchlist.adaptadores;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.SparseArray;

import com.miguel.mywatchlist.fragmentos.FavoritosFragment;
import com.miguel.mywatchlist.fragmentos.TopPeliculasFragment;
import com.miguel.mywatchlist.fragmentos.TopSeriesFragment;

/**
 * Creado por Miguel Ángel Hernández Muñoz
 * para Servicios de Software Ehecatl con fecha 13/04/2017.
 */

public class HomeAdapter extends FragmentStatePagerAdapter {

    private int numeroTabs;
    private SparseArray<Fragment> registeredFragments;

    public HomeAdapter(FragmentManager fm, int numeroTabs) {
        super(fm);
        this.numeroTabs = numeroTabs;
        registeredFragments = new SparseArray<>();
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                FavoritosFragment favoritosFragment = FavoritosFragment.nuevaInstancia();
                registeredFragments.put(position,favoritosFragment);
                return favoritosFragment;
            case 1:
                TopPeliculasFragment topPeliculasFragment = TopPeliculasFragment.nuevaInstancia();
                registeredFragments.put(position,topPeliculasFragment);
                return TopPeliculasFragment.nuevaInstancia();
            case 2:
                TopSeriesFragment topSeriesFragment = new TopSeriesFragment();
                registeredFragments.put(position,topSeriesFragment);
                return topSeriesFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numeroTabs;
    }

    public Fragment getRegisteredFragment(int position) {
        return registeredFragments.get(position);
    }

}
