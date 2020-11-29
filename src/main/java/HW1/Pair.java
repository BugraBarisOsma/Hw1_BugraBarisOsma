package HW1;
import java.util.Objects;

public class Pair implements Comparable<Pair> {
    private String t1;
    private String t2;
    private double factor;

    public Pair(String t1, String t2, double factor) {
        this.t1 = t1;
        this.t2 = t2;
        this.factor = factor;
    }

    public String toString() {
        return "HW1.Pair{" + "t1='" + t1 + '\'' + ", t2='" + t2 + '\'' + ", factor=" + factor + '}';
    }

    public int compareTo(Pair o) {
        return Double.compare(o.factor, factor);
    }


    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair pair = (Pair) o;
        return Double.compare(pair.factor, factor) == 0 &&
                Objects.equals(t1, pair.t1) &&
                Objects.equals(t2, pair.t2);
    }


    public int hashCode() {
        return Objects.hash(t1, t2, factor);
    }
}
