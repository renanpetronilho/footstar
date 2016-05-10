package com.sensedia.footstar.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;

import com.sensedia.footstar.R;
import com.sensedia.footstar.fragment.TeamFragment;
import com.sensedia.footstar.model.Team;
import com.sensedia.footstar.util.BaseActivity;

/**
 * Created by renan on 19/03/16.
 */
public class TeamActivity extends BaseActivity {


    protected Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        intent = getIntent();
        setContentView(R.layout.team_activity);

        setUpToolbar();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TeamFragment teamFragment = (TeamFragment) getSupportFragmentManager().findFragmentById(R.id.TeamFragment);

        Team team = (Team) getIntent().getSerializableExtra("team");

        teamFragment.setTeam(team);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                NavUtils.navigateUpTo(this, intent);
                return true;
            case 888:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
