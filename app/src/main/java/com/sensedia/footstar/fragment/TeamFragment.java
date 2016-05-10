package com.sensedia.footstar.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.sensedia.footstar.R;
import com.sensedia.footstar.api.TeamAPI;
import com.sensedia.footstar.client.ServiceGenerator;
import com.sensedia.footstar.model.Team;
import com.sensedia.footstar.util.BaseFragment;
import com.sensedia.footstar.util.Operation;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Response;

/**
 * Created by renan on 19/03/16.
 */
public class TeamFragment extends BaseFragment {

    private Team team;
    private Long id;

    @Bind(R.id.edName)
    protected EditText edName;

    @Bind(R.id.edInitials)
    protected EditText edInitials;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.team_fragment, container, false);
        setHasOptionsMenu(true);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.menu_crud, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_save:
                save();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void save() {
        if (this.team.getId() == null)
            createTeam();
        else
            updateTeam();

    }

    public void setTeam(Team team) {
        if (team != null) {
            this.team = team;
            this.id = team.getId();
            populateView();
        } else {
            this.team = new Team();
        }
    }

    private void populateView() {
        edName.setText(team.getName());
        edInitials.setText(team.getInitials());
    }

    private void populateBean(Operation op) {

        team.setName(edName.getText().toString());
        team.setInitials(edInitials.getText().toString());

    }

    private void createTeam() {
        populateBean(Operation.CREATE);
        TeamAPI api = ServiceGenerator.createService(TeamAPI.class);
        try {
            Response response = api.createTeam(team).execute();
            if (response.isSuccessful()) {
                toast("Time criado com sucesso!");
                getActivity().finish();
            } else {
                toast(response.errorBody().string());
            }
        } catch (IOException e) {
            toast(e.getMessage());
        }


    }

    private void updateTeam() {
        populateBean(Operation.UPDATE);
        TeamAPI api = ServiceGenerator.createService(TeamAPI.class);
        try {
            Response response = api.updateTeam(team, team.getId()).execute();
            if (response.isSuccessful()) {
                toast("Time Atualizado com sucesso");
                getActivity().finish();
            } else {
                toast(response.errorBody().string());
            }
        } catch (IOException e) {
            toast(e.getMessage());
        }
    }

}
