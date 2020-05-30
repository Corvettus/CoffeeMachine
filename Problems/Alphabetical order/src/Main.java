import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        String prev = null;
        boolean ascendOrder = true;
        int orderIndex;

        for (String word:
             line.split(" ")) {
            if (word.equals(" ") || word.equals("")) {
                continue;
            }
            if (prev != null) {
                orderIndex = word.compareTo(prev);
                if (orderIndex < 0){
                    ascendOrder = false;
                }
                if (!ascendOrder) {
                    System.out.println(false);
                    return;
                }
            }
            prev = word;
        }
        System.out.println(true);
    }
}