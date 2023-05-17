package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "MecanumHardware: HIGHAutoSignalCode", group = "Mecanum Hardware")
public class HIGHAutoSignalCode extends LinearOpMode {

    ColorSensor colorsensor;

    /* Declare OpMode members. */
    MecanumHardware robot = new MecanumHardware();
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() {

        // Initialize the drive system variables.
        // The init() method of the hardware class does all the work her.
        robot.init(hardwareMap);

        telemetry.addData("Status", "Ready to run");
        telemetry.update();

        robot.arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.arm.setTargetPosition(0);

        // Wait for the game to start (driver presses PLAY).
        waitForStart();

        // Make the servo move up so it is vertical.
        robot.leftClaw.setPosition(0.5);
        robot.rightClaw.setPosition(0.45);
        sleep(200);

        // Open claw to lift cone.
        robot.leftClaw.setPosition(0.25);
        robot.rightClaw.setPosition(0.60);
        sleep(200);

        // Pause.
        robot.leftFrontDrive.setPower(0);
        robot.rightFrontDrive.setPower(0);
        robot.leftBackDrive.setPower(0);
        robot.rightBackDrive.setPower(0);
        sleep(400);

        // Lift arm to read signal cone.
        robot.arm.setTargetPosition(8565);
        robot.arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.arm.setPower(1);
        sleep(1000);

        // Pause.
        robot.leftFrontDrive.setPower(0);
        robot.rightFrontDrive.setPower(0);
        robot.leftBackDrive.setPower(0);
        robot.rightBackDrive.setPower(0);
        robot.arm.setPower(0);
        sleep(100);

        // Drive forward to read sensor.
        robot.leftFrontDrive.setPower(0.4);
        robot.rightFrontDrive.setPower(0.4);
        robot.leftBackDrive.setPower(0.4);
        robot.rightBackDrive.setPower(0.4);
        sleep(1100);

        // Pause.
        robot.leftFrontDrive.setPower(0);
        robot.rightFrontDrive.setPower(0);
        robot.leftBackDrive.setPower(0);
        robot.rightBackDrive.setPower(0);
        sleep(300);

        double green = 0;
        double blue = 0;
        double red = 0;

        // Define and initialize color sensor.
        colorsensor = hardwareMap.get(ColorSensor.class, "color2");
        colorsensor.enableLed(true);  // Turn the LED on.

        // Pause.
        robot.leftFrontDrive.setPower(0);
        robot.rightFrontDrive.setPower(0);
        robot.leftBackDrive.setPower(0);
        robot.rightBackDrive.setPower(0);
        sleep(300);

        // If green.
        if ((colorsensor.green() > colorsensor.red()) && (colorsensor.green() > colorsensor.blue())) {
            green = 1;
        }
        // If blue.
        if ((colorsensor.blue() > colorsensor.red()) && (colorsensor.blue() > colorsensor.green())) {
            blue = 1;
        }
        // If red.
        if ((colorsensor.red() * 1.3 > colorsensor.green()) && (colorsensor.red() * 1.3 > colorsensor.blue())) {
            red = 1;
        }

        // Pause.
        robot.leftFrontDrive.setPower(0);
        robot.rightFrontDrive.setPower(0);
        robot.leftBackDrive.setPower(0);
        robot.rightBackDrive.setPower(0);
        sleep(100);

        // Drive backwards to read sensor.
        robot.leftFrontDrive.setPower(-0.4);
        robot.rightFrontDrive.setPower(-0.4);
        robot.leftBackDrive.setPower(-0.4);
        robot.rightBackDrive.setPower(-0.4);
        sleep(180);

        // Pause.
        robot.leftFrontDrive.setPower(0);
        robot.rightFrontDrive.setPower(0);
        robot.leftBackDrive.setPower(0);
        robot.rightBackDrive.setPower(0);
        sleep(100);

        colorsensor.enableLed(false);  // Turn the LED off.

        if (blue == 1) {
            telemetry.addData("MOVE TO", "BLUE PARKING!");

            // Moves right to go into the middle of field #3.
            robot.leftFrontDrive.setPower(0.4);
            robot.rightBackDrive.setPower(0.4);
            robot.rightFrontDrive.setPower(-0.4);
            robot.leftBackDrive.setPower(-0.4);
            sleep(1400);

            // Pause.
            robot.leftFrontDrive.setPower(0);
            robot.rightFrontDrive.setPower(0);
            robot.leftBackDrive.setPower(0);
            robot.rightBackDrive.setPower(0);
            sleep(100);

        }
        if (green == 1) {
            telemetry.addData("MOVE TO", "GREEN PARKING!");

            // Pause.
            robot.leftFrontDrive.setPower(0);
            robot.rightFrontDrive.setPower(0);
            robot.leftBackDrive.setPower(0);
            robot.rightBackDrive.setPower(0);
            robot.arm.setPower(0);
            sleep(100);

        }
        if (red == 1) {
            telemetry.addData("MOVE TO", "RED PARKING!");

            // Moves left to go into the middle of field #3.
            robot.leftFrontDrive.setPower(-0.4);
            robot.rightBackDrive.setPower(-0.4);
            robot.rightFrontDrive.setPower(0.4);
            robot.leftBackDrive.setPower(0.4);
            sleep(1400);

            // Pause.
            robot.leftFrontDrive.setPower(0);
            robot.rightFrontDrive.setPower(0);
            robot.leftBackDrive.setPower(0);
            robot.rightBackDrive.setPower(0);
            sleep(100);

        }

        // Place arm down.
        robot.arm.setTargetPosition(-90);
        robot.arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.arm.setPower(-1);
        sleep(1000);

        while (opModeIsActive()) {
            telemetry.addData("Red", red);
            telemetry.addData("Green", green);
            telemetry.addData("Blue", blue);

            telemetry.update();
        }

        telemetry.addData("Path", "Complete");
        telemetry.update();
    }
}