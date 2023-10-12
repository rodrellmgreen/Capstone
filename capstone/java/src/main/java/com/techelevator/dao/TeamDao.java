package com.techelevator.dao;



import com.techelevator.model.Team;

import java.util.List;

public interface TeamDao {

    void createTeam(Team team);

    Team getTeam(int id);

    List<Team> getAllTeams();

    boolean updateTeam(Team team);

    boolean deleteTeam(int id);

    int getTeamIdByTeamName(String name);

    boolean updateUsersOnTeam(String teamName,String displayName);

    int getUserIdFromDisplayName(String displayName);

    void addUserToTeam(int teamId,String displayName);

    List<Team> getUsersTeams(String displayName);

    boolean deleteUserFromTeam(int teamId,String displayName);

    List<String> getDisplayNamesFromTeamId(int teamId);
}
