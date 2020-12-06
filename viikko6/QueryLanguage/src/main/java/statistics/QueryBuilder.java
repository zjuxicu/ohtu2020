package statistics;

import statistics.matcher.*;
import statistics.*;

public class QueryBuilder {
    Matcher matcher;

    public Matcher build() {
        return matcher;
    }

    public QueryBuilder playsIn(String string) {
        this.matcher = new And(this.matcher, new PlaysIn(this.matcher, string));
        return this;
    }

    public QueryBuilder hasAtLeast(int i, String string) {
        this.matcher = new And(this.matcher, new HasAtLeast(this.matcher, i, string));
        return this;
    }

    public QueryBuilder hasFewerThan(int i, String string) {
        this.matcher = new And(this.matcher, new HasFewerThan(this.matcher, i, string));
        return this;
    }

    public QueryBuilder oneOf(Matcher m1, Matcher m2) {
        this.matcher = new Or(m1, m2);
        return this;
    }

}
