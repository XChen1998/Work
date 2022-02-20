public class BioBox extends StandardBox {
    public void unlock(SkeletonKey sk) {
        System.out.println("USK");
    }

    public void unlock(Fingerprint f) {
        System.out.println("UF");
    }
}