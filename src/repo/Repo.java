package repo;

import exceptions.RepoException;
import model.state.PrgState;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Repo implements IRepo {
    private List<PrgState> states;
    private int index;//index la al catelea prg din lista executam

    private String filePath;

    public Repo(String filePath) {
        this.filePath = filePath;
        this.index = 0;
        this.states = new ArrayList<PrgState>();
    }

    public void incrementIndex() {
        this.index++;
    }

    @Override
    public void add(PrgState state) {
        states.add(state);
    }

    @Override
    public void logPrg(PrgState state) throws RepoException {
        try {
            PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(filePath, true)));
            logFile.println(state.toString());
            logFile.close();
        } catch (IOException e) {
            throw new RepoException(e.getMessage());
        }
    }

    @Override
    public List<PrgState> getPrgList() {
        return states;
    }

    @Override
    public void setPrglist(List<PrgState> prgList) {
        this.states = prgList;
    }
}
