package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class HardwareBot
{
    /* Public OpMode members. */
    public Servo intakePush = null;
    public DcMotor carousel = null;
    public DcMotor intake = null;
    public DcMotorEx arm = null;

    public DcMotor frontLeft = null;
    public DcMotor frontRight = null;
    public DcMotor backLeft = null;
    public DcMotor backRight = null;

    /* local OpMode members. */
    HardwareMap hwMap =  null;
    private ElapsedTime period = new ElapsedTime();

    /* Constructor */
    public HardwareBot(){

    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;

        intakePush = hwMap.get(Servo.class, "in");

        intake = hwMap.get(DcMotor.class, "IN");
        carousel = hwMap.get(DcMotor.class, "C");

        arm = hwMap.get(DcMotorEx.class, "ARM");
        arm.setDirection(DcMotor.Direction.REVERSE);

        frontLeft = hwMap.get(DcMotor.class, "FL");
        frontLeft.setDirection(DcMotor.Direction.REVERSE);

        frontRight = hwMap.get(DcMotor.class, "FR");

        backLeft = hwMap.get(DcMotor.class, "BL");
        backLeft.setDirection(DcMotor.Direction.REVERSE);

        backRight = hwMap.get(DcMotor.class, "BR");

    }
 }

