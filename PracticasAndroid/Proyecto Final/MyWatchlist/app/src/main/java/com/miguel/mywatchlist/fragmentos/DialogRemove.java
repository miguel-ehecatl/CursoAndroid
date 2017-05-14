package com.miguel.mywatchlist.fragmentos;

import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.miguel.mywatchlist.R;

/**
 * Created by miguelangel on 5/12/17.
 */

public class DialogRemove  extends android.support.v4.app.DialogFragment implements View.OnClickListener{


    FavoriteDialog favoriteDialog;
    private int movieID;

    public static DialogRemove nuevaInstancia(int movieID){
        DialogRemove dialogRemove = new DialogRemove();
        Bundle args = new Bundle();
        args.putInt("movie_id",movieID);
        dialogRemove.setArguments(args);
        return dialogRemove;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        movieID = getArguments().getInt("movie_id");
        setStyle(android.support.v4.app.DialogFragment.STYLE_NO_TITLE,0);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        favoriteDialog = (FavoriteDialog) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.remove_dialog,container,false);
        Button yes = (Button) vista.findViewById(R.id.yes);
        Button no = (Button) vista.findViewById(R.id.no);
        yes.setOnClickListener(this);
        no.setOnClickListener(this);
        return vista;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.yes:
                favoriteDialog.favoriteRemoved(movieID);
                getDialog().dismiss();
                break;
            case R.id.no:
                getDialog().dismiss();
                break;
        }
    }

    public interface FavoriteDialog{
        void favoriteRemoved(int movieID);
    }
}
