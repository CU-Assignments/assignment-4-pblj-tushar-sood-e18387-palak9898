import java.util.*;

class exp4medium {
    private static HashMap<String, List<String>> cardMap = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nCard Collection System");
            System.out.println("1. Add Card");
            System.out.println("2. Search Cards by Symbol");
            System.out.println("3. Display All Cards");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    addCard();
                    break;
                case 2:
                    searchCards();
                    break;
                case 3:
                    displayCards();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    private static void addCard() {
        System.out.print("Enter Card Symbol (e.g., Hearts, Spades): ");
        String symbol = scanner.nextLine();
        
        System.out.print("Enter Card Name (e.g., Ace, King, Queen): ");
        String name = scanner.nextLine();
        
        cardMap.putIfAbsent(symbol, new ArrayList<>());
        cardMap.get(symbol).add(name);
        System.out.println("Card added successfully!");
    }

    private static void searchCards() {
        System.out.print("Enter Symbol to search: ");
        String symbol = scanner.nextLine();
        
        if (cardMap.containsKey(symbol)) {
            System.out.println("Cards under " + symbol + ": " + cardMap.get(symbol));
        } else {
            System.out.println("No cards found for this symbol.");
        }
    }

    private static void displayCards() {
        if (cardMap.isEmpty()) {
            System.out.println("No cards in the collection.");
            return;
        }
        for (Map.Entry<String, List<String>> entry : cardMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}

