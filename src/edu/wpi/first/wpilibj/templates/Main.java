/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.SimpleRobot;


public class Main extends SimpleRobot 
{
    Drivetrain PLOB;
    
    
    
    public Main()
    { 
        
    }
    
    public void autonomous() 
    {   
        while (isAutonomous() && isEnabled()) 
        {
            Timer.delay(0.004); 
        }
    }

    
    public void operatorControl()
    {   
        //while loop for re-updating speed and such
        while (isOperatorControl() && isEnabled()) 
        {
            
        }
 
    }
  
    public void test() 
    {
    
    }
}
