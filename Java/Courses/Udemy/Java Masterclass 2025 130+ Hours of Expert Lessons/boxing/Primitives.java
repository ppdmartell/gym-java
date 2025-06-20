class Primitives {

    public static void main(String[] args) {
        Integer boxed = Integer.valueOf(5);    // preferred but not necessary
        Integer deprecatedBoxing = new Integer(15);   // deprecated since JDK 9
        int unboxedInt = boxed.intValue();    // not necessary

        // Automatic
        Integer autoBoxing = 15;        // Autoboxing
        int autoUnboxed = autoBoxing;   // Autounboxing
        System.out.print("Class is -----> " + autoBoxing.getClass().getName());
        // System.out.print(autoUnboxed.getClass().getName())   // Compile-time Error
    }

}
