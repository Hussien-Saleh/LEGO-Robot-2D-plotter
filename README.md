# LEGO-Robot-2D-plotter

Application for a LEGO robot to implement a 2D-plotter. The plotter is able to draw two different shapes (a rectangle and a sailing ship) in different sizes selectable via a user interface. 
The software runs on a LEGO NXT Intelligent Brick using the LeJOS firmware version 0.9.1 beta.

The general procedure is as follows: 
- The user chooses one of the two predefined shapes and selects the size of the shape. 
- The robot is placed on a provided coordinate grid, and after a calibration phase, the robot starts drawing at a predefined position and with the size selected by the user.

The robot:
The robot has three motors: 
- one motor drives the robot forward and backward.
- Another one moves the swivel arm, on which the pen is mounted, left and right. 
- The third motor moves the pen up and down (by coiling a rope on a reel). 

Three sensors are installed for calibration purposes: 
- a touch sensor allows to determine when the motor driving the swivel arm is in its rightmost position. 
- Another touch sensor detects if the pen reaches its maximum upper position. 
- A light sensor enables the robot to find a black bar, which marks the start of the drawing area.


The program passes through the following phases:
- Userinteraction(chooseshapeandsize) 
- Calibration
- Drawingthechosenshape

User interaction:
For interaction with the user of the robot, the LCD and the buttons of the NXT intelligent brick are used.
A user menu is implemented which allows the user to select the shape to be drawn and to input the size of this selected shape (in mm). 
After entering these inputs, the parameters are passed to the drawing routines.

Calibration:
The initial position of the robot is with its driving direction perpendicular to the black bar and the swivel arm facing straight in driving direction. 
Before starting any drawing task, the robot needs to bring its motors in a predefined starting position. 
For calibration, the robot drives backwards until the light sensor detects the black bar marking the initial y = 0 position. The grid on the drawing plate is not used for orientation while drawing.

The touch sensor of the swivel arm is used during calibration to obtain the maximum angle of the arm with the assumption that the swivel arm of the robot is in a central position when the program starts. 
The position of the pen is calibrated by the touch sensor above the pen. The robot finds out about the maximum angle the swivel arm can travel to make sure it is never driven beyond this point.

Coordinate System:
The software uses a Cartesian coordinate system to define locations in two dimensions x and y.
The y-direction is defined as the direction in which the robot moves forward and backward. The position y = 0 is located at the upper edge of the black bar (shown in gray).
The x-direction is orthogonal to y and has its x = 0 value in the middle of the robot. 
The x-position cannot be calibrated and is freely chosen thus results from the initial position of the robot.


Shapes:
The main task of the robot is to draw the two shapes. One shape is a rectangle, the other one is a sailing ship. Both shapes are drawn with the upper edge located at y = 230 mm. The rectangle is determined by height and width and the ship by parameter width.
For the ship, the height is freely chosen as long as width ≥ height holds.

Programming constants:
For calculations, the mechanical constants of the robot are provided.

- Distance from arm’s rotation center to pen: 80mm
- Distance from arm’s rotation center to light sensor: 105mm
- Wheel diameter: 56mm
- Gear ratio of arm motor: 84
- Gear ratio of wheel motor: 5
- Light sensor threshold for detecting the black bar: ~500

Conceptual design:
Coordinates:
Before drawing a shape, the motor angle of the real robot is abstracted into an xy-coordinate system that is used by the program.

Lines:
After the real-world robot motor movements are abstracted to an xy-coordinate-system, a class is implemented that is able to draw arbitrary straight lines.

Shapes:
After the drawing of lines are implemented, they are used then to draw complex shapes. 
The rectangle is defined by four points which are connected by the created lines. The rectangle is defined by providing a coordinate of the lower left corner as well as upper right corner.
The Ship is composed of different shapes; lines and rectangles.
