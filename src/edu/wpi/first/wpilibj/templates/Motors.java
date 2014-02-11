/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;

/**
 *
 * @author Developer
 */
public class Motors{
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
        private Victor frontRight; 
        private Victor frontLeft;
        private Victor backRight;
        private Victor backLeft;
    
    
    public void Init() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    
        frontRight = new Victor(3);
        frontLeft = new Victor(1);
        backRight = new Victor(4);
        backLeft = new Victor(2);
     
    }
    
    public Victor GetFrontRight()
    {
        return frontRight;
    }
    
    public Victor GetFrontLeft()
    {
        return frontLeft;
    }
    
    public Victor GetBackLeft()
    {
        return backLeft;   
    }
    public Victor GetBackRight()
    {
        return backRight;
    }
}
