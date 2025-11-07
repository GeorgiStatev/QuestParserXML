package Logic;

public interface RewardParser {
    static int parseTheRewardInGold(String stringToParse) {
        String[] parts = stringToParse.split(", ");

        if (parts.length == 0) {return 0;}
        if (parts.length >= 1) {
            String[] elements = parts[0].split(" ");
            if (elements[1].equals("gold")) {
                int gold = Integer.parseInt(elements[0]);
                return gold;
            }
        }
        return 0;
    }

    static int parseTheRewardInSilver(String stringToParse) {
        String[] parts = stringToParse.split(", ");

        if (parts.length == 0) {return 0;}
        if (parts.length >= 0) {
            String[] elements = parts[1].split(" ");
            if (elements[1].equals("silver")) {
                int silver = Integer.parseInt(elements[0]);
                return silver;
            }
        }
        return 0;
    }

    static int parseTheRewardInCopper(String stringToParse) {
        String[] parts = stringToParse.split(", ");

        if (parts.length == 0) {return 0;}
        if (parts.length >= 1) {
            String[] elements = parts[2].split(" ");
            if (elements[1].equals("copper")) {
                int copper = Integer.parseInt(elements[0]);
                return copper;
            }
        }
        return 0;
    }

}
