package fr.dok.chabadabada.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import fr.dok.chabadabada.R;

import static fr.dok.chabadabada.utils.Utils.TEAM_1_NAME;
import static fr.dok.chabadabada.utils.Utils.TEAM_2_NAME;

public class MainActivity extends ActionBarActivity {

	private Context context;
    private EditText team1;
    private EditText team2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        team1 = (EditText) findViewById(R.id.team1Name);
        team2 = (EditText) findViewById(R.id.team2Name);
		context = this;
		ActionBar ab = getSupportActionBar();
		ab.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.holoPurple)));
		Button newGame = (Button) findViewById(R.id.newGameButton);
		newGame.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
                Intent intent = new Intent(context,GameActivity.class);
                intent.putExtra(TEAM_1_NAME,team1.getText().toString());
                intent.putExtra(TEAM_2_NAME, team2.getText().toString());
				startActivity(intent);
			}
		});
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_help) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
