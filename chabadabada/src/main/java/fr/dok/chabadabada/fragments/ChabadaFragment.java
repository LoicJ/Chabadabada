package fr.dok.chabadabada.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import de.passsy.holocircularprogressbar.HoloCircularProgressBar;
import fr.dok.chabadabada.R;
import fr.dok.chabadabada.activities.GameActivity;
import fr.dok.chabadabada.utils.Utils;

/**
 * Created by Doky on 03/09/2014.
 */
public class ChabadaFragment extends Fragment implements GameActivity.ChabadaListener {

	private TextView team;
	private TextView frenchWord;
	private TextView englishWord;
	private HoloCircularProgressBar progressBar;
	private GameActivity mActivity;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.game_content, null);
		team = (TextView) view.findViewById(R.id.teamName);
		frenchWord = (TextView) view.findViewById(R.id.frenchWord);
		englishWord = (TextView) view.findViewById(R.id.englishWord);
		progressBar = (HoloCircularProgressBar) view.findViewById(R.id.holoCircularProgressBar1);
		progressBar.setMarkerEnabled(false);
		Bundle bundle = getArguments();
		if (bundle != null) {
			team.setText(bundle.getString(Utils.TEAM_NAME));
		}
		return view;

	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		mActivity = (GameActivity) activity;
	}

	@Override
	public void update(long elapsedTime) {
		if (team != null) {
//			team.setText(String.valueOf(Math.round(elapsedTime / 1000)));
			if (elapsedTime <= GameActivity.GAME_TIME) {
				float progress = elapsedTime / GameActivity.GAME_TIME;
				progressBar.setProgress(progress);

			}
		}
	}
}
