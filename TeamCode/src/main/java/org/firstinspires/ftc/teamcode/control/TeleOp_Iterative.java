/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode.control;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="TeleOp_Iterative", group="Iterative Opmode")

public class TeleOp_Iterative extends OpMode {

    //Declare OpMode members
    Provider robot = new Provider();

    //Code to run once when the driver hits INIT
    @Override
    public void init() {
        //Initialize the robot
        robot.init(hardwareMap);

        //Tell the driver that initialization is complete
        telemetry.addData("Status", "Initialized");
    }

    //Code to run repeatedly after the driver hits INIT, but before they hit PLAY
    @Override
    public void init_loop() {
    }

    //Code to run once when the driver hits PLAY
    @Override
    public void start() {
        //Resets the runtime value to 0
        robot.runtime.reset();
    }

    //Code to run repeatedly after the driver hits PLAY but before they hit STOP
    @Override
    public void loop() {
        //This uses basic math to combine motions and is easier to drive straight
        double drive  = +gamepad1.left_stick_y;
        double strafe = +gamepad1.left_stick_x ;
        double turn   = -gamepad1.right_stick_x;

        //Send calculated power to wheels
        robot.driveLF.setPower( - drive + strafe - turn );
        robot.driveRF.setPower( - drive - strafe + turn );
        robot.driveLB.setPower( - drive - strafe - turn );
        robot.driveRB.setPower( - drive + strafe + turn );

        //Show the elapsed game time and wheel power
        telemetry.addData("Status", "Run Time: " + robot.runtime.toString());
    }

    //Code to run once after the driver hits STOP
    @Override
    public void stop() {
        //Just cause
        telemetry.addData("BOT Status", "I AM A BOT!");
    }
}