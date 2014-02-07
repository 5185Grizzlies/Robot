/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.Gyro;


public class RobotTemplate extends SimpleRobot 
{
    
    private Gyro gyro; //creating the object gyro
    //proportional scaling constant, the angle is 
    //multiplied by 0.06 to correct its path
    double Kp = 0.006;
    
    
    public RobotTemplate()
    { 
        gyro = new Gyro(1);
        chassis.setExpiration(0.1);
    }
    
    //set which motor is hooked up to which port.
    Victor frontRight = new Victor(3); 
    Victor frontLeft = new Victor(1);
    Victor backRight = new Victor(4);
    Victor backLeft = new Victor(2);
    
    RobotDrive chassis = new RobotDrive(frontLeft, backLeft, frontRight, backRight);
     Joystick driveStick = new Joystick(1);
    
    
    public void autonomous() 
    {
       int i = 0;
        gyro.reset(); //resets gyro to forward
        
        //safety mechanism that will stop the motor if the speed isn't updated
        chassis.setSafetyEnabled(false);
        
        while (isAutonomous() && isEnabled()) 
        { 
            //retrieves angle that the gyro is reading
            double angle = gyro.getAngle();
            //adjusts angle of drive to correct inefficiencies in the motors
            chassis.drive(0.5, -angle*Kp);

            System.out.println("ticks = " + i++);
           // System.out.println("Kp Angle = " + -angle*Kp);
            Timer.delay(0.004); 
        }
        chassis.drive(0.0, 0.0);//stops robot
    }

    /**
     * This function is called once each time the robot enters operator control.
     */
    public void operatorControl()
    {
        chassis.setSafetyEnabled(true); //enables safety
        
        //while loop for re-updating speed and such
        while (isOperatorControl() && isEnabled()) 
        {
            //get the inputs from left & rigt stick
            //chassis.arcadeDrive(-driveStick.getY(),-driveStick.getX()); 
            chassis.drive(0,0);
            System.out.println("Magnatude " + driveStick.getMagnitude());
            System.out.println("Get X " + driveStick.getX());
            System.out.println("Get Y"+ driveStick.getY());
            
            Timer.delay(2.001); //update every 0.001 seconds
            
            //Joystick buttons corrispond to the labels thay have on the joystick. I checked.
            //Joystick button 1 is also mapped as .getTrigger().
            //Joystick getTwist() value range: left -1.0, neutral 0.0, right 1.0
        } 
 
    }
    
    /**
     * This function is called once each time the robot enters test mode.
     */
    public void test() 
    {
    
    }
}
