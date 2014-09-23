package fr.dok.chabadabada.activities;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Chronometer;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import fr.dok.chabadabada.R;
import fr.dok.chabadabada.adapter.ChabadAdapter;
import fr.dok.chabadabada.fragments.ChabadaFragment;
import fr.dok.chabadabada.model.ChabadaCard;
import fr.dok.chabadabada.utils.Utils;

public class GameActivity extends ActionBarActivity {

	private ViewPager mViewPager;
	private ChabadAdapter mAdapter;
	private int mSelectedPageIndex = 0;
	private int mPosition = 0;
	private List<ChabadaCard> gamingCards;
	private String team1, team2;
	private Bundle currentBundle;
	private ChabadaFragment currentFragment;
	private Chronometer chrono;
	public static float GAME_TIME = 30000.0f; //in s
	private long baseTime;
	private Timer timer;
	private static String TIMER_NAME = "CHABADATIMER";
	public boolean isRunning = false;
	private long savedElapsedTime = 0;
	private Menu abMenu;
	private MenuItem pauseButton;

	private List<ChabadaListener> listeners;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		Bundle bundle = getIntent().getExtras();
		if (bundle != null) {
			team1 = bundle.getString(Utils.TEAM_1_NAME, getString(R.string.team1));
			team2 = bundle.getString(Utils.TEAM_2_NAME, getString(R.string.team2));
		}
		ActionBar ab = getSupportActionBar();
		ab.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.holoPurple)));
		mViewPager = (ViewPager) findViewById(R.id.viewpager);
		mAdapter = new ChabadAdapter(getSupportFragmentManager());
		listeners = new ArrayList<ChabadaListener>();
		ChabadaFragment fragment = new ChabadaFragment();
		currentBundle = new Bundle();
		currentBundle.putString(Utils.TEAM_NAME, team1);
		fragment.setArguments(currentBundle);

		mAdapter.addItem(fragment);
		listeners.add(fragment);
		fragment = new ChabadaFragment();
		fragment.setArguments(currentBundle);
		mAdapter.addItem(fragment);
		listeners.add(fragment);
		runTimer();
		mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

			}

			@Override
			public void onPageSelected(int position) {
				mPosition = position;
			}

			@Override
			public void onPageScrollStateChanged(int state) {
				if (state == ViewPager.SCROLL_STATE_IDLE) {
					if (mPosition != mSelectedPageIndex) {
						mSelectedPageIndex = mPosition;
						ChabadaFragment newFragment = new ChabadaFragment();
						newFragment.setArguments(currentBundle);
						mAdapter.addItem(newFragment);
						listeners.add(newFragment);
						mAdapter.remove();
						listeners.remove(0);
						mSelectedPageIndex--;
						mViewPager.setCurrentItem(mSelectedPageIndex, false);
						if (!isRunning) {
							runTimer();
						}

					}
				}
			}
		});
		mViewPager.setAdapter(mAdapter);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game, menu);
		abMenu = menu;
		pauseButton = abMenu.findItem(R.id.action_pause);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_pause) {
			pauseOrResumeTimer();
		}
		return super.onOptionsItemSelected(item);
	}

	public void pauseOrResumeTimer() {

		if (isRunning) {
			timer.cancel();
			timer.purge();
			isRunning = false;
			savedElapsedTime = SystemClock.elapsedRealtime() - baseTime + savedElapsedTime;
			pauseButton.setIcon(getResources().getDrawable(R.drawable.ic_action_play));

		} else {
			runTimer();
		}
		//Todo save state
	}

	public void stopTimer() {
		timer.cancel();
		timer.purge();
		isRunning = false;
		savedElapsedTime = 0;
	}

	public void resumeTimer() {

	}

	public void runTimer() {
		timer = new Timer(TIMER_NAME);
		timer.scheduleAtFixedRate(new ChabadaTask(), 0, 100);
		isRunning = true;
		baseTime = SystemClock.elapsedRealtime();
		if (pauseButton != null) {
			pauseButton.setIcon(getResources().getDrawable(R.drawable.ic_action_pause));
		}
	}

	public void updateListeners(long elapsedTime) {
		for (ChabadaListener listener : listeners)
			listener.update(elapsedTime);
	}

	private class ChabadaTask extends TimerTask {

		@Override
		public void run() {
			final long elapsedTime = SystemClock.elapsedRealtime() - baseTime + savedElapsedTime;

			if (elapsedTime <= GAME_TIME) {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						updateListeners(elapsedTime);
					}
				});
			} else {
				stopTimer();
			}

		}
	}

	public interface ChabadaListener {

		public void update(long elapsedTime);
	}

	@Override
	protected void onPause() {
		super.onPause();
		pauseOrResumeTimer();
	}

	@Override
	protected void onStop() {
		super.onStop();
		pauseOrResumeTimer();
	}
}
