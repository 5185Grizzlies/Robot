/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.RobotDrive;

public class Drivetrain 
{
    private RobotDrive chassis;
    private Motors driveMotors;
    
    private Gyro gyro; //creating the object gyro
    //proportional scaling constant, the angle is 
    //multiplied by 0.06 to correct its path
    private double Kp = 0.006;
    
    public void Init()
    { 
        gyro = new Gyro(1);
        driveMotors = new Motors();
        chassis = new RobotDrive(driveMotors.GetFrontRight(),
                driveMotors.GetFrontLeft(),driveMotors.GetBackRight(),
                driveMotors.GetBackLeft());
        chassis.setExpiration(0.1);
    }
    
    
  
}
