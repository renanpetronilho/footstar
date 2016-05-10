package com.sensedia.footstar.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sensedia.footstar.R;
import com.sensedia.footstar.activity.TeamActivity;
import com.sensedia.footstar.adapter.TeamAdapter;
import com.sensedia.footstar.api.TeamAPI;
import com.sensedia.footstar.client.ServiceGenerator;
import com.sensedia.footstar.model.Team;
import com.sensedia.footstar.util.BaseFragment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

/**
 * Created by renan on 19/03/16.
 */
public class TeamsFragment extends BaseFragment {

    protected RecyclerView recyclerView;
    private List<Team> teams = new ArrayList<Team>();
    private LinearLayoutManager mLayoutManager;
    FloatingActionButton btAdd;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.teams_fragment, container, false);
        setHasOptionsMenu(true);
        recyclerView = (RecyclerView) view.findViewById(R.id.rcTeams);
        mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        getActionBar().setTitle(getString(R.string.teams));
        btAdd = (FloatingActionButton) view.findViewById(R.id.btAdd);
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), TeamActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        taskTeams();
    }

    private void taskTeams() {
        teams.clear();
        TeamAPI api = ServiceGenerator.createService(TeamAPI.class);
        try {
            Response<List<Team>> response = api.getTeams().execute();
            if (response.isSuccessful()) {
                teams = response.body();
                recyclerView.setAdapter(new TeamAdapter(getContext(), teams, onClickCustomer()));
            }
        } catch (IOException e) {
            toast("Failed to fetch teams");
        }
    }

    private TeamAdapter.TeamOnClickListener onClickCustomer() {
        return new TeamAdapter.TeamOnClickListener() {
            @Override
            public void onClickTeam(View view, int idx) {
                Team  team = teams.get(idx);
                Intent intent = new Intent(getContext(), TeamActivity.class);
                intent.putExtra("team", team);
                startActivity(intent);
            }
        };
    }

    @Override
    public void onResume() {
        super.onResume();
        taskTeams();
    }


}
