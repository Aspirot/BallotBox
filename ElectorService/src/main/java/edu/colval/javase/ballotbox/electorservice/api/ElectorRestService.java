package edu.colval.javase.ballotbox.electorservice.api;

import edu.colval.javase.ballotbox.electorservice.bll.model.Elector;
import edu.colval.javase.ballotbox.electorservice.dal.IElectorDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/electorService")
public class ElectorRestService {
    @Autowired
    private IElectorDAO electorDAO;

    @PostMapping("/elector/create")
    public Elector createElector(@RequestBody Elector newElector){
        this.electorDAO.addElector(newElector);
        return this.electorDAO.fetchElectorById(newElector.getId());
    }

    @GetMapping("/elector/findAllElectors")
    public List<Elector> findAllElectors(){
        return this.electorDAO.getAllElectors();
    }

    @GetMapping("/elector/findElector/{electorId}")
    public Elector findElectorById(@PathVariable("electorId") int electorId) throws SQLException{
        Elector foundElector = this.electorDAO.fetchElectorById(electorId);
        return foundElector;
    }



    @DeleteMapping("/elector/delete/{electorId}")
    public Elector deleteBallotById(@PathVariable("electorId") int electorId) throws SQLException {
        Elector deletedElector = this.electorDAO.fetchElectorById(electorId);
        this.electorDAO.deleteElector(electorId);
        return deletedElector;
    }
}
