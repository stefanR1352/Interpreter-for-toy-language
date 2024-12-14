package repo;

import exceptions.RepoException;
import model.state.PrgState;

import java.util.List;

public interface IRepo {
//    public PrgState getCurrentPrg();
    void add(PrgState state);
    void logPrg(PrgState state) throws RepoException;
    List<PrgState> getPrgList();
    void setPrglist(List<PrgState> prgList);
}
