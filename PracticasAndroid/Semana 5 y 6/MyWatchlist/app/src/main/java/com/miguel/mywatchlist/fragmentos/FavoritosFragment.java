package com.miguel.mywatchlist.fragmentos;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.miguel.mywatchlist.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoritosFragment extends Fragment {


    public static FavoritosFragment nuevaInstancia(){
        return new FavoritosFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favoritos, container, false);
    }

}
