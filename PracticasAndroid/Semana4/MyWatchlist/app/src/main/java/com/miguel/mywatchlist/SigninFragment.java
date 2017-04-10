package com.miguel.mywatchlist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by miguelangel on 4/8/17.
 */

public class SigninFragment extends Fragment{

    public static SigninFragment nuevaInstancia(){
        return new SigninFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.signin_fragment,container,false);

        return vista;
    }
}
