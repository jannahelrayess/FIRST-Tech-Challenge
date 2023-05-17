package org.firstinspires.ftc.teamcode;


// import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
// import com.qualcomm.robotcore.hardware.ColorSensor;

public class MecanumHardware
{
    // Declare OpMode members.
    public DcMotor leftFrontDrive = null;
    public DcMotor leftBackDrive = null;
    public DcMotor rightFrontDrive = null;
    public DcMotor rightBackDrive = null;
    public DcMotorEx arm = null;
    public Servo leftClaw = null;
    public Servo rightClaw = null;

    // ColorSensor colorsensor;

    // Create imu class object.
    // BNO055IMU imu;

    public final static double LEFT_CLAW_HOME = 0.2; // Starting position for Servo Arm.
    public final static double LEFT_CLAW_MIN_RANGE = 0.0; // Smallest number value allowed for servo position.
    public final static double LEFT_CLAW_MAX_RANGE = 1.0; // Largest number allowed for servo position.

    public final static double RIGHT_CLAW_HOME = 0.5; // Starting position for Servo Arm.
    public final static double RIGHT_CLAW_MIN_RANGE = 0.0; // Smallest number value allowed for servo position.
    public final static double RIGHT_CLAW_MAX_RANGE = 1.0; // Largest number allowed for servo position.

    // HardwareMap, which connects motors in program to real ones on the robot.
    HardwareMap hwMap = null;
    private ElapsedTime period = new ElapsedTime();

    /* Constructor */
    public MecanumHardware() {

    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;

        // Define and initialize color sensor.
        // colorsensor = hwMap.colorSensor.get("color2");

        // Define and initialize motors.
        leftFrontDrive = hwMap.get(DcMotor.class, "left_front_drive");
        leftBackDrive = hwMap.get(DcMotor.class, "left_back_drive");
        rightFrontDrive = hwMap.get(DcMotor.class, "right_front_drive");
        rightBackDrive = hwMap.get(DcMotor.class, "right_back_drive");
        arm = hwMap.get(DcMotorEx.class, "arm");

        // Set direction of the motor. Set direction to REVERSE if using AndyMark motors.
        leftFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        leftBackDrive.setDirection(DcMotor.Direction.REVERSE);
        rightFrontDrive.setDirection(DcMotor.Direction.REVERSE);
        rightBackDrive.setDirection(DcMotor.Direction.REVERSE);
        arm.setDirection(DcMotor.Direction.REVERSE);

        // Set all motors to zero power so they start turned off.
        leftFrontDrive.setPower(0);
        leftBackDrive.setPower(0);
        rightFrontDrive.setPower(0);
        rightBackDrive.setPower(0);
        arm.setPower(0);

        // Turn off encoders.
        leftFrontDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftBackDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightFrontDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightBackDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        // Define and initialize all installed servos.
        leftClaw = hwMap.servo.get("left_hand");
        rightClaw = hwMap.servo.get("right_hand");

        // BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        // parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        // parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        // parameters.calibrationDataFile = "BNO055IMUCalibration.json";
        // Keeps track of values.
        // parameters.loggingEnabled = true;
        // parameters.loggingTag = "IMU";
        // Determines the algorithm used for tracking the position of the robot.
        // parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();

        // Initialize IMU.
        // imu = hwMap.get(BNO055IMU.class, "imu");
        // Initialize IMU with above parameters.
        // imu.initialize(parameters);
    }
 }