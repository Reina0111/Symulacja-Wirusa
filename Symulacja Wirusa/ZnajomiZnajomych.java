import java.util.ArrayList;

class ZnajomiZnajomych {
    public Agent agent;
    public ArrayList<Agent> znajomi;
    public int liczbaZnajomych;

    public ZnajomiZnajomych(Agent agent, Agent obecny) {
        this.agent = agent;
        this.znajomi = new ArrayList<>(agent.znajomi);
        while (this.znajomi.remove(obecny)) ;
        this.liczbaZnajomych = znajomi.size();
    }

}
