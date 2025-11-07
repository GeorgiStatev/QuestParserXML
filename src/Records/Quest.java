package Records;

public record Quest(
        String title,
        String text,
        int gold,
        int silver,
        int copper,
        String rewardItem,
        int experience) {

    public String getTitle() {return title;}
    public String getText() {return text;}
    public int getGold() {return gold;}
    public int getSilver() {return silver;}
    public int getCopper() {return copper;}
    public String rewardItem() {return rewardItem;}
    public int getExperience() {return experience;}

    @Override
    public String toString() {
        return String.format("Quest Title: %s\nQuest Text: %s\nCoins: %d gold, %d silver," +
                " %d copper\nItem: %s\nExperience: %d\n",title,text,gold,silver,copper,rewardItem,experience);
    }
}

