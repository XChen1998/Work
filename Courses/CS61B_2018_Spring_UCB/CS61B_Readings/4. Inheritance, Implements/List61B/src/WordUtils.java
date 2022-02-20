public class WordUtils {
    public static String longest(List61B<String> list) {
        int maxDex = 0;
        for (int i = 0; i < list.size(); i += 1) {
            String longestString = list.get(maxDex);
            String thisString = list.get(i);
            if (thisString.length() > longestString.length()) {
                maxDex = i;
            }
        }
        return list.get(maxDex);
    }

    public static void peek(List61B<String> list) {
        System.out.println(list.getLast());
    }
    public static void peek(SLList<String> list) {
        System.out.println(list.getFirst());
    }

    public static void main(String[] args) {
        AList<String> someList = new AList();
        for (int i = 0; i < 6; i++) {
            someList.addFirst("elk");
            someList.addFirst("are");
            someList.addFirst("watching");
        }

        List61B<String> someList61B = new SLList<String>();
        someList61B.addFirst("elk");
        someList61B.addFirst("elk");
        someList61B.addFirst("elk");
        someList61B.addFirst("elk");
        someList.print();
        someList61B.print();


        SLList<String> SP = new SLList<String>();
        List61B<String> LP = SP;
        SP.addLast("elk");
        SP.addLast("are");
        SP.addLast("cool");
        peek(SP);
        peek(LP);


    }

}