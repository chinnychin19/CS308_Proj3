SLogo Team 4
Chinmay Patwardhan, Lalita Maraj, Ken McAndrews, Susan Zhang

Date started: Sept 30
Date ended: Oct 23
Estimate time to finish: 50 hours
Team repo link: https://github.com/duke-compsci308-fall2013/slogo_team04
Team roles: backend - Chinmay and Ken, frontend - Lalita and Susan
File to start project: Main.java in default package
Files to test project: All classes in test and test.french packages. ParserTests class in model package.
Files required by project: Properties files in model.resources package, images in view.display package, and JGame
Files of interest: Model, Interpreter, Parser, and Turtle in model package. Instruction and InstructionFactory in model.instruction package.
Caveats with program: Undo and redo only undoes and redoes turtle commands. Onmove seems to register too fast for the program to keep up, so it overloads the program at times. Changing workspaces works completely only if all turtles on screen are active. Workspace preferences saving is very rudimentary.