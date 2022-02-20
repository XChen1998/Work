public class AltList<X, Y> {
    private X item;
    private AltList<Y, X> next;

    AltList(X item, AltList<Y, X> next) {
        this.item = item;
        this.next = next;
    }


    public AltList pairsSwapped() {
        AltList newList = new AltList(next.item, new AltList<X, Y>(item, null));
        if(next.next != null){
            newList.next.next = next.next.pairsSwapped();
        }
        return newList;

    }
    public static void main(String args[]){
        AltList<Integer, String> list =
                new AltList<Integer, String>(5,
                        new AltList<String, Integer>("cat",
                                new AltList<Integer, String>(10,
                                        new AltList<String, Integer>("dog", null))));

        AltList<Integer, String> swapedList = list.pairsSwapped();
    }
}
