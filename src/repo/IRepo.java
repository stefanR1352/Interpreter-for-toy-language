package repo;

import exceptions.RepoException;
import model.state.PrgState;

public interface IRepo {
    public PrgState getCurrentPrg();
    void add(PrgState state);
    void logPrg() throws RepoException;
}
