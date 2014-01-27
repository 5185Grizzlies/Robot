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
/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SimpleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class RobotClass extends SimpleRobot {
    //This function is called once each time the robot enters autonomous mode.
    private Gyro gyro; //creating the object gyro
    double Kp = 0.03; //proportional scaling constant, the angle is multiplied by 0.03 to correct its path
   
    public RobotClass()
    { 
        chassis.setExpiration(0.1);
    }
  
    Victor frontRight = new Victor(3); //set which motor is hooked up to which port.
    Victor frontLeft = new Victor(1);
    Victor backRight = new Victor(4);
    Victor backLeft = new Victor(2);
    
    RobotDrive chassis = new RobotDrive(frontLeft, backLeft, frontRight, backRight);
     Joystick driveStick = new Joystick(1);
    
    
    public void autonomous() {
        gyro.reset(); //resets gyro to forward
        chassis.setSafetyEnabled(false); // it is a safety mechanism that will stop the motor if the speed isn't updated
        while (isAutonomous()) { //creates while loop
            double angle = gyro.getAngle(); //retrieves angle that the gyro is reading 
            chassis.drive(0.3, -angle*Kp);//adjusts angle of drive to correct inefficiencies in the motors
            Timer.delay(0.004); 
        }
        chassis.drive(0.0, 0.0);//stops robot
        
        
      //  chassis.drive(-0.2, 0.0); // driving in a negative directon at a speed of 0.2
      //  Timer.delay(2.0); // waiting for 2.0 seconds
       // chassis.drive(0.0, 0.0); // stopped driving    
    }

    /**
     * This function is called once each time the robot enters operator control.
     */
    public void operatorControl() {
        chassis.setSafetyEnabled(true); //enables safety
        //chassis.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true); //inverts motors so that they don't fight each other.
        //chassis.setInvertedMotor(RobotDrive.MotorType.etX()); //get the inputs from left & rigt stick
            Timer.delay(0.01); //update every 0.01 secondskFrontLeft, true); 
        while (isOperatorControl() && isEnabled()) { //while loop for re-updating speed and such
            chassis.arcadeDrive(-driveStick.getY(),-driveStick.getX()); //get the inputs from left & rigt stick
            Timer.delay(0.01); //update every 0.01 seconds
        } 
 
    }
    
    /**
     * This function is called once each time the robot enters test mode.
     */
    public void test() {
    
    }
}
