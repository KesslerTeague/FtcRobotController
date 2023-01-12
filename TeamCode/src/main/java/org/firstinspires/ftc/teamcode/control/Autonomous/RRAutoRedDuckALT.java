package org.firstinspires.ftc.teamcode.control.Autonomous;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.roadrunner.SampleMecanumDrive;

@Disabled
@Autonomous(name = "RR Auto Red Duck ALT", preselectTeleOp = "TeleOp_Iterative")
public class RRAutoRedDuckALT extends AutoBase {

    //Variables
    public static double DISTANCE = 50;

    @Override
    public void runOpMode() {
        //Initialize the robot
        auto_init();
        SampleMecanumDrive rr_drive = new SampleMecanumDrive(hardwareMap);

        //Set positions
        Pose2d startPose = new Pose2d(-35.4331, -64.85827, Math.toRadians(90));
        Pose2d allyHubPose = new Pose2d(-34.5, -23.622, Math.toRadians(0));
        Pose2d carouselPose = new Pose2d(-60.50, -58.0, Math.toRadians(-90));
        Pose2d storagePose = new Pose2d(-59.0551, -35.4331, Math.toRadians(0));


        rr_drive.setPoseEstimate(startPose);

        //Build all trajectories


        Trajectory trajCarousel = rr_drive.trajectoryBuilder(startPose)
                .lineToLinearHeading(carouselPose)
                .build();

        Trajectory trajHub = rr_drive.trajectoryBuilder(trajCarousel.end())
                .lineToLinearHeading(allyHubPose)
                .build();

        Trajectory trajStorage = rr_drive.trajectoryBuilder(trajHub.end())
                .lineToLinearHeading(storagePose)
                .addDisplacementMarker(1.0, () -> {
                    robot.arm.arm_move(robot.arm.armPos0);
                })
                .build();

        /** Wait for the game to begin */
        telemetry.addData(">", "Press Play to start op mode");
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        //Code that finds which barcode the duck/shipping element is on
        detect_barcode_pos();

        //Code that makes the robot move
        // Code that goes to alliance shipping hub, drops off pre-loaded freight, and comes back
        rr_drive.followTrajectory(trajCarousel);
        spin_duck(-0.38,3500);
        rr_drive.turn(0);
        move_arm(targetLevel);
        rr_drive.followTrajectory(trajHub);
        open_grabber();
        sleep(750);
        rr_drive.followTrajectory(trajStorage);

        //Done with Autonomous
        sleep(2000);
    }
}