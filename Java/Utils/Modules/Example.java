/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.home.example;

import java.awt.image.BufferedImage;

/**
This is not going to work in a standalone .java file. It was created with Apache NetBeans.
in order for having the BufferedImage import accessible , it is necessary to include the
* "requires java.desktop" module in the module-info.java file.
* 
* The videos used for this part are found in linkedin learning course named "Advanced Java Programming"
* by Bethan Palmer, the section "Modular Programming in Java"[1].
* 
* Resources:
* [1] https://www.linkedin.com/learning/advanced-java-programming-2/understanding-modules-in-java?resume=false
*/
public class Example {
    
    //This BufferedImage class is in the desktop module which is no longer
    //part of the core JDK
    BufferedImage image;

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
}
