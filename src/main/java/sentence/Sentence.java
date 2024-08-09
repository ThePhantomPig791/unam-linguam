package sentence;

public class Sentence {
    public final IndependentClause independentClause;

    public Sentence() {
        // TODO dependent clauses
        independentClause = new IndependentClause();
    }

    public String assembleLatin() {
        return independentClause.assembleLatin();
    }
    public String assembleEnglish() {
        return independentClause.assembleEnglish();
    }
}
