package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "PresetArmProgram: Teleop", group = "Linear Opmode")
public class PresetArmProgram extends LinearOpMode {

    /* Declare OpMode members. */
    MecanumHardware robot = new MecanumHardware(); // Use a Mecanum's hardware.
    double leftClawPosition = MecanumHardware.LEFT_CLAW_HOME; // Servo's starting position.
    double rightClawPosition = MecanumHardware.RIGHT_CLAW_HOME;
    // final double leftClawSpeed = 0.1; // Sets rate to move servo.
    // final double rightClawSpeed = 0.1;

    @Override
    public void runOpMode() {
        double x1 = 0; // The initial value, which will change when the joystick is used.
        double y1 = 0;

        double fortyFiveInRads = -Math.PI / 4;
        double cosine45 = Math.cos(fortyFiveInRads);
        double sine45 = Math.sin(fortyFiveInRads);

        double x2 = 0;
        double y2 = 0;

        robot.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Hi", "I'm Rover!");
        telemetry.update();

        robot.arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.arm.setTargetPosition(0);

        // Wait for the game to start (driver presses PLAY).
        waitForStart();

        // Make the servo move up so it is vertical.
        robot.leftClaw.setPosition(0.5);
        sleep(1000);

        robot.rightClaw.setPosition(0.45);
        sleep(1000);

        // Run until the end of the match (driver presses STOP).
        while (opModeIsActive()) {
            double spin = gamepad1.left_stick_x; // For controlling spin.

            double spinPower1 = spin * 0.5;
            double spinPower2 = spin * 0.3;

            if (Math.abs(spin) > 0.1) {
                if (gamepad1.left_bumper) {
                    // If someone is moving the right joystick and want to move slowly, then spin.
                    robot.rightFrontDrive.setPower(-spinPower2);
                    robot.rightBackDrive.setPower(-spinPower2);

                    robot.leftFrontDrive.setPower(spinPower2);
                    robot.leftBackDrive.setPower(spinPower2);
                } else {
                    // If someone is moving the right joystick and want to move quickly, then spin.
                    robot.rightFrontDrive.setPower(-spinPower1);
                    robot.rightBackDrive.setPower(-spinPower1);

                    robot.leftFrontDrive.setPower(spinPower1);
                    robot.leftBackDrive.setPower(spinPower1);
                }
            } else {
                // If someone is moving the left joystick, the drive normally.
                y1 = -gamepad1.right_stick_y; // Getting value from y-direction of right joystick.
                x1 = gamepad1.right_stick_x; // Getting value from x-direction of right joystick.

                // Rotate (x1, y1) 45 degrees.
                y2 = y1 * cosine45 + x1 * sine45;
                x2 = x1 * cosine45 - y1 * sine45;

                double drivePowerX1 = x2 * 0.7;
                double drivePowerY1 = y2 * 0.7;
                double drivePowerX2 = x2 * 0.25;
                double drivePowerY2 = y2 * 0.25;

                if (gamepad1.left_bumper) {
                    // Drive slowly.
                    robot.leftFrontDrive.setPower(drivePowerX2);
                    robot.rightBackDrive.setPower(drivePowerX2);

                    robot.rightFrontDrive.setPower(drivePowerY2);
                    robot.leftBackDrive.setPower(drivePowerY2);
                } else {
                    // Drive quickly.
                    robot.leftFrontDrive.setPower(drivePowerX1);
                    robot.rightBackDrive.setPower(drivePowerX1);

                    robot.rightFrontDrive.setPower(drivePowerY1);
                    robot.leftBackDrive.setPower(drivePowerY1);
                }
            }

            // Use gamepad X open claw lift cone
            if (gamepad2.x || gamepad1.x) {
                robot.leftClaw.setPosition(0.3);
                // sleep(700);
                robot.rightClaw.setPosition(0.6);
                sleep(700);
            }
            // Use gamepad B to close claw drop cone
            else if (gamepad2.b || gamepad1.b) {
                robot.leftClaw.setPosition(0.5);
                // sleep(700);
                robot.rightClaw.setPosition(0.45);
                sleep(700);
            }

            // Use dpad to raise and lower arm to preset levels.
            // Low.
            if (gamepad2.dpad_left)  {
                robot.arm.setTargetPosition(5214);
                robot.arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.arm.setPower(1);
            }
            // Medium.
            else if (gamepad2.dpad_up) {
                robot.arm.setTargetPosition(8565);
                robot.arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.arm.setPower(1);
            }
            // Tallest.
            else if (gamepad2.dpad_right) {
                robot.arm.setTargetPosition(11722);
                robot.arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.arm.setPower(1);
            }
            // Ground.
            else if (gamepad2.dpad_down) {
                robot.arm.setTargetPosition(1000);
                robot.arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.arm.setPower(-1);
            }

            double power1 = 1;
            double power2 = 0.5;

            // Manual up and down for arm.
            // If gamepad2.y or gamepad1.y is true, set ypower to 0.5. If false, set to 0. set motor power to ypower. Do the same with a.
            if (gamepad1.left_bumper || gamepad2.left_bumper) {
                if (gamepad2.y || gamepad1.y) {
                    robot.arm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                    robot.arm.setPower(power2);
                } else if (gamepad2.a || gamepad1.a) {
                    robot.arm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                    robot.arm.setPower(-power2);
                } else if (gamepad2.right_bumper || gamepad1.right_bumper) {
                    robot.arm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                    robot.arm.setPower(0.0);
                }
            } else {
                if (gamepad2.y || gamepad1.y) {
                    robot.arm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                    robot.arm.setPower(power1);
                } else if (gamepad2.a || gamepad1.a) {
                    robot.arm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                    robot.arm.setPower(-power1);
                } else if (gamepad2.right_bumper || gamepad1.right_bumper) {
                    robot.arm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                    robot.arm.setPower(0.0);
                }
            }

                double telemetryArmPosition = robot.arm.getCurrentPosition();

                // Send telemetry message to signify robot is running.
                telemetry.addData("left_hand", "%.2f", leftClawPosition); // Shows the values on the phone of the servo.
                telemetry.addData("right_hand", "%.2f", rightClawPosition);
                telemetry.addData("x1", "%.2f", x1);
                telemetry.addData("y1", "%.2f", y1);
                telemetry.addData("x2", "%.2f", x2);
                telemetry.addData("y2", "%.2f", y2);
                telemetry.addData("arm_position", "%.2f", telemetryArmPosition);
                telemetry.update();

                // Pace this loop so jaw action is reasonable speed.
                sleep(50);
            }
        }
    }