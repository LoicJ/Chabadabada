package fr.dok.chabadabada.activities;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

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
    private List <ChabadaCard> gamingCards;
    private String team1,team2;
    private Bundle currentBundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            team1 = bundle.getString(Utils.TEAM_1_NAME,getString(R.string.team1));
            team2 = bundle.getString(Utils.TEAM_2_NAME,getString(R.string.team2));
        }
        ActionBar ab = getSupportActionBar();
        ab.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.holoPurple)));
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mAdapter = new ChabadAdapter(getSupportFragmentManager());
        final ChabadaFragment fragment = new ChabadaFragment();
         currentBundle = new Bundle();
        currentBundle.putString(Utils.TEAM_NAME,team1);
        fragment.setArguments(currentBundle);
        mAdapter.addItem(fragment);
        mAdapter.addItem(new ChabadaFragment());
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
                        mAdapter.remove();
                        mSelectedPageIndex--;
                        mViewPager.setCurrentItem(mSelectedPageIndex,false);
                        Fragment newFragment = new ChabadaFragment();
                        newFragment.setArguments(currentBundle);
                        mAdapter.addItem(new ChabadaFragment());

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
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_pause) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
