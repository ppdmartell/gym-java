import java.util.ArrayList;


class Example1 {

    record GroceryItem(String name, String type, int count) {
        public GroceryItem(String name) {
            this(name, "DAIRY", 1);
        }
    }

    public static void main(String[] args) {
        System.out.println("Example about LinkedLists.");

        GroceryItem[] groceryArray = new GroceryItem[3];                // simple array
        groceryArray[0] = new GroceryItem("milk");
        groceryArray[1] = new GroceryItem("apples", "PRODUCE", 6);
        groceryArray[2] = new GroceryItem("oranges", "PRODUCE", 5);

        ArrayList objectList = new ArrayList();   // No <T> type is specified, so raw/default type will be Object
                                                  // for example you could objectList.add(3000);


        ArrayList<GroceryItem> groceryList = new ArrayList<>(); // Type is <GroceryItem>, so only records of that type can be added
        groceryList.add(new GroceryItem("Butter"));
    }

}
