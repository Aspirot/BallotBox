package edu.colval.javase.ballotbox.electorservice.bll.control;


import edu.colval.javase.ballotbox.electorservice.bll.model.Elector;
import edu.colval.javase.ballotbox.electorservice.dal.IElectorDAO;

public class ElectorController {
    /*
    private IElectorDAO electorDAO;

    public ElectorController(IElectorDAO electorDAO){
        this.electorDAO = electorDAO;
    }

    public void createElector(String login, String password, int weight, String email){
        Elector newElector = new Elector(login,password,weight,email);
        this.electorDAO.addElector(newElector);
    }

    public void addElectorToElection(int electorId, int pollId){
        BallotController.getBallotDAO().fetchBallotById(pollId).get().addElector(this.electorDAO.fetchElectorById(electorId).get());
    }

    public IElectorDAO getElectorDAO() {
        return electorDAO;
    }*/
}
