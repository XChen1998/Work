import java.util.NoSuchElementException;

public class SLListVista<T> extends SLList<T>{
    @Override
    public int indexOf(T x) {
        if (super.indexOf(x) != -1) {
            return super.indexOf(x);
        }
        else {
            throw new NoSuchElementException();
        }
    }
}