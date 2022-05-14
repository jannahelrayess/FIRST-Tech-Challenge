package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name="HardwareBot: Teleop", group="HardwareBot")
public class HardwareBotTeleop extends LinearOpMode {

    /* Declare OpMode members. */
    HardwareBot robot = new HardwareBot();

    @Override
    public void runOpMode() {
        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Hi", "My name is Hal!");    //
        telemetry.update();

        robot.intakePush.setPosition(0.18);
        robot.carousel.setPower(0.0);
        robot.intake.setPower(0.0);

        robot.frontLeft.setPower(0.0);
        robot.frontRight.setPower(0.0);
        robot.backLeft.setPower(0.0);
        robot.backRight.setPower(0.0);

        robot.arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        robot.arm.setTargetPosition(0);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            double spin = gamepad1.left_stick_x/1.5;
            double straight = -gamepad1.right_stick_y/2;
   
            if (Math.abs(spin) > 0.1) {
                robot.frontLeft.setPower(spin);
                robot.frontRight.setPower(-spin);
                robot.backLeft.setPower(spin);
                robot.backRight.setPower(-spin);
            }
            else {
                robot.frontLeft.setPower(straight);
                robot.frontRight.setPower(straight);
                robot.backLeft.setPower(straight);
                robot.backRight.setPower(straight);
            }

            if (gamepad1.dpad_left) {
                robot.arm.setTargetPosition(110);
                robot.arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.arm.setPower(0.3);
            }
            else if (gamepad1.dpad_up) {
                robot.arm.setTargetPosition(200);
                robot.arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.arm.setPower(0.4);
            }
            else if (gamepad1.dpad_right) {
                robot.arm.setTargetPosition(280);
                robot.arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.arm.setPower(0.5);
            }
            else if (gamepad1.dpad_down) {
                robot.arm.setTargetPosition(0);
                robot.arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.arm.setPower(-0.1);
            }

            if (gamepad1.b) {
                robot.intakePush.setPosition(0.32);
                sleep(700);
                robot.intakePush.setPosition(0.18);
            }
            if (gamepad1.y)
                robot.intake.setPower(-0.6);
            else if (gamepad1.a) {
                robot.intake.setPower(0.6);
            }
            else if (gamepad1.x) {
                robot.intake.setPower(0.0);
            }
            if (gamepad1.left_bumper)
                robot.carousel.setPower(1.0);
            else if (gamepad1.right_bumper) {
                robot.carousel.setPower(-1.0);
            }
            else if (gamepad1.left_trigger > 0.1) {
                robot.carousel.setPower(0.0);
            }

            // Send telemetry message to signify robot running;
            telemetry.addData("hi lol", "dw im working");
            telemetry.update();

            // Pace this loop so jaw action is reasonable speed.
            sleep(10);
        }
    }
}
