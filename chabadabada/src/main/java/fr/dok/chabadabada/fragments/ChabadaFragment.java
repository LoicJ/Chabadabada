package fr.dok.chabadabada.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import fr.dok.chabadabada.R;
import fr.dok.chabadabada.utils.Utils;

/**
 * Created by Doky on 03/09/2014.
 */
public class ChabadaFragment extends Fragment {

    private TextView team;
    private TextView frenchWord;
    private TextView englishWord;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.game_content,null);
        team  = (TextView) view.findViewById(R.id.teamName);
        frenchWord = (TextView) view.findViewById(R.id.frenchWord);
        englishWord = (TextView) view.findViewById(R.id.englishWord);
        Bundle bundle = getArguments();
        if (bundle != null) {
            team.setText(bundle.getString(Utils.TEAM_NAME));
        }
        return view;

    }
}
