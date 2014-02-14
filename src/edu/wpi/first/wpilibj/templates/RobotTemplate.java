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
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Solenoid;

public class RobotTemplate extends SimpleRobot {

    //set the joystick
    Joystick driveStick = new Joystick(1);

    //Gyro Stuff
    private Gyro gyro;
    double Kp = 0.006;

    public RobotTemplate() {
        gyro = new Gyro(1);
        chassis.setExpiration(0.1);
    }

    //set whick motors are hooked up where
    //drive motors
    Victor frontRight = new Victor(3);
    Victor frontLeft = new Victor(1);
    Victor backRight = new Victor(4);
    Victor backLeft = new Victor(2);
    RobotDrive chassis = new RobotDrive(frontRight, backRight, frontRight, backRight);
    //Loader Motors
    Jaguar loadingArmMotor = new Jaguar(6);
    Jaguar loadingWheelMotor = new Jaguar(7);
    //Whinch Motor
    Talon armMotor = new Talon(5);

    Solenoid armSolenoid = new Solenoid(8);

    public void autonomous() 
    {
        gyro.reset();
	double speed;
	int x = 200; //SlowDown / Speed Up rate

        //---------begin autonomous---------//
        loadingArmMotor.set(-0.8); //bring the loading arm down
        //for speeding up and maintaining a straight line
	for(int i = 0; i <= x; i++){
		double gyroAngle = gyro.getAngle();
		speed = i / x;
		chassis.drive(speed,-gyroAngle * Kp);
		Timer.delay(0.001);
	}
	//get to the correct position
	Timer.delay(2.0);
	//for slowing down to prevet skiding
	for(int i = x; i >= 0; i--){
		double gyroAngle = gyro.getAngle();
		speed = i / x;
		chassis.drive(speed,-gyroAngle * Kp);
		Timer.delay(0.001); 
	}
        chassis.drive(0.0, 0.0); //full stop
        armSolenoid.set(true); //launch ball
        for(int i = 0; i <= x; i++){
		double gyroAngle = gyro.getAngle();
		speed = i / x;
		chassis.drive(speed,-gyroAngle * Kp);
		Timer.delay(0.001);
	}
        Timer.delay(2.0);
        chassis.drive(0.0, 0.0);

    }

    public void operatorControl() 
    {

        //------------------Start of the Joystick Mapping--------------------//   
        //this is probably really inifficent and there is a better way to
        //do it but this works so yaaa... if you have any other ideas, give me a shout.
        while (isOperatorControl() && isEnabled()) 
        {

            //Arcade Drive
            chassis.arcadeDrive(-driveStick.getY(), -driveStick.getX());

            //Get button 7 for fireing the robot, ,will not fire when retracting.
            if (driveStick.getRawButton(7) == true && driveStick.getRawButton(8) == false) 
            {
                //release the solioid!(AMY)
                armSolenoid.set(true);
            } 
            else 
            {
                armSolenoid.set(false);
            }

            //Get button 7 for retracting the arm
            if (driveStick.getRawButton(8) == true) 
            {
                //Retract the Arm, use Talon motor controler (AMY)
                armMotor.set(1.0);
            } 
            else 
            {
                armMotor.set(0.0);
            }

            //Get button 1 for the loading wheels.
            if (driveStick.getRawButton(1) == true) 
            {
                //Turn Loading Wheels!(ADAM)
                loadingWheelMotor.set(1.0);
            } 
            else 
            {
                loadingWheelMotor.set(0.0);
            }

            //loader arm has three states: 1-up(button5) 2-down(button3) 3-stop(no buttons pressed)
            //Only one button can be pressed at a time so the motor does not recieve mixed signals.
            if (driveStick.getRawButton(5) == true && driveStick.getRawButton(3) == false) 
            {
                //Raise the Loading Arm
                loadingArmMotor.set(1.0);
            }
            if (driveStick.getRawButton(3) == true && driveStick.getRawButton(5) == false) 
            {
                //Lower the Loading Arm
                loadingArmMotor.set(-1.0);
            }
            if (driveStick.getRawButton(5) == false && driveStick.getRawButton(3) == false) 
            {
                //Stop the loading Arm
                loadingArmMotor.set(0.0);
            }
        }
        //----------------------------END OF BUTTON MAPPING---------------------------//
    }

    public void test() {

    }
}
