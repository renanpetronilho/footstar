package com.sensedia.footstar.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sensedia.footstar.R;
import com.sensedia.footstar.model.Team;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by renan on 19/03/16.
 */
public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.TeamViewHolder> {

    private Context context;
    private List<Team> teams;
    private LayoutInflater inflater;
    private TeamOnClickListener teamOnClickListener;


    public TeamAdapter(Context _context, List<Team> teams, TeamOnClickListener teamOnClickListener) {
        this.context = _context;
        this.teams = teams;
        this.teamOnClickListener = teamOnClickListener;
    }

    @Override
    public int getItemCount() {
        return this.teams != null ? this.teams.size() : 0;
    }


    @Override
    public TeamViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.team_line, parent, false);

        CardView cardView = (CardView) view.findViewById(R.id.team_card);

        TeamViewHolder holder = new TeamViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final TeamViewHolder holder, final int position) {

        populateView(holder, position);

        if (teamOnClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    teamOnClickListener.onClickTeam(holder.itemView, position);
                }
            });
        }
    }

    private void populateView(final TeamViewHolder holder, final int position) {
        holder.tvName.setText(teams.get(position).getName());
        holder.tvInitials.setText(teams.get(position).getInitials());
    }


    public interface TeamOnClickListener {
        public void onClickTeam(View view, int idx);
    }

    public static class TeamViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.tv_name)
        TextView tvName;
        @Bind(R.id.tv_initials)
        TextView tvInitials;

        public TeamViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
