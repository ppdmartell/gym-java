/* Rules for declaring multiple variables in one statement
 * - You cannot declare variables with different data types in a single statement.
 * - If you declare multiple variables of the same data type in a single statement, you
 *   must specify the data type only once before any variable names.
 * */

class Example1 {
    public static void main(String[] args) {
        byte myMinByteValue = Byte.MIN_VALUE, myMaxByteValue = Byte.MAX_VALUE;
        System.out.println(myMinByteValue + " <-> " + myMaxByteValue);
    }
}
