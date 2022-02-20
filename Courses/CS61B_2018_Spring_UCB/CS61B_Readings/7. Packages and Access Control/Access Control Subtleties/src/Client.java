import universe.*;
import static universe.CreationUtils.hirsute;

class Client {
    void demoAccess() {
        BlackHole b = hirsute();
        b.add("horse");
        b.get(0);
        HasHair hb = (HasHair) b;
    }
}