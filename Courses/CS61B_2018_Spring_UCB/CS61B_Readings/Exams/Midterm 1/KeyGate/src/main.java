public class main {

    public static void doStuff(Key k, SkeletonKey sk, Fingerprint f) {
        StandardBox sb = new StandardBox();
        StandardBox sbbb = new BioBox();
        BioBox bb = new BioBox();
        sb.unlock(k);
        sbbb.unlock(k);     
        bb.unlock(k);
        sb.unlock(sk);     
        sbbb.unlock(sk);     
        bb.unlock(sk);
        sb.unlock(f);
        sbbb.unlock(f);
        bb.unlock(f);
        bb = (BioBox) sbbb; 
        ((StandardBox) bb).unlock(sk);     
        ((StandardBox) sbbb).unlock(sk);     
        ((BioBox) sb).unlock(sk);
    }

    public static void main(String[] args) {
        Key k = new Key();
        SkeletonKey sk = new SkeletonKey();
        Fingerprint f = new Fingerprint();
        doStuff(k, sk, f);
    }
}
