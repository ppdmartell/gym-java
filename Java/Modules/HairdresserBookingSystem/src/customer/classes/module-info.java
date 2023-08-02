/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2SEModule/module-info.java to edit this template
 */

module customer {
    
    requires bookingsystem;  //Even when it might looks like the entire module is being required (and used)
                             //the access will only be granted to scheduling, since it's the only one being
                             //exported by bookingsystem module.
    
}
