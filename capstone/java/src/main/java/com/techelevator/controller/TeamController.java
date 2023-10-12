package com.techelevator.controller;


import com.techelevator.dao.ProfileDao;
import com.techelevator.dao.TeamDao;
import com.techelevator.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;

@RequestMapping(path="/teams")
@RestController
@CrossOrigin
public class TeamController {

    @Autowired
    TeamDao teamDao;

    @Autowired
    ProfileDao profileDao;

    @GetMapping(path="/{teamId}")
    public Team getSpecificTeam(@PathVariable int teamId){
        return teamDao.getTeam(teamId);
    }

        @GetMapping(path = "/{teamId}/members")
        public List<String> getTeamMembersForSpecificTeam(@PathVariable int teamId){
            return teamDao.getDisplayNamesFromTeamId(teamId);
        }

        @GetMapping()
        public List<Team> getTeamsForMember(Principal principal){
            int profileId= profileDao.getProfileIdByUsername(principal.getName());
            String displayName=profileDao.getDisplayNameFromProfileId(profileId);

            return teamDao.getUsersTeams(displayName);
        }
        @ResponseStatus(HttpStatus.CREATED)
        @PostMapping()
        public void createATeam(@RequestBody Team team){
            teamDao.createTeam(team);
        }

        @ResponseStatus(HttpStatus.CREATED)
        @PostMapping(path = "/addMember")
        public void addUsertoTeam(Principal principal, @RequestBody Team team){
            int profileId=profileDao.getProfileIdByUsername(principal.getName());
            String displayName=profileDao.getDisplayNameFromProfileId(profileId);
            teamDao.addUserToTeam(team.getTeamId(),displayName);
        }

        @GetMapping(path="/allTeams")
    public List<Team> getAllTeamsAvailable(){
            return teamDao.getAllTeams();
        }

        @PutMapping()
    public void updateTeamName(@RequestBody Team team){
            boolean updated=teamDao.updateTeam(team);
            if (!updated){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Team not updated");
            }
        }

        @DeleteMapping(path="/{id}")
        public void deleteUserFromTeam(Principal principal, @PathVariable int id){
            int profileId=profileDao.getProfileIdByUsername(principal.getName());
            String displayName=profileDao.getDisplayNameFromProfileId(profileId);
            teamDao.deleteUserFromTeam(id,displayName);
        }

        @PreAuthorize("hasRole('ADMIN')")
        @DeleteMapping(path="/remove/{id}")
        public void deleteTeam(@PathVariable int id){
            teamDao.deleteTeam(id);
        }



}
