import IOInterfaces.ConsoleInterface;
import IOInterfaces.IInputInterface;
import IOInterfaces.InputInterface;
import IOInterfaces.NonInput;
import elevator.*;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
public class Main {
    public static void main(String[] args) {

        CommandLine commandLine;
        Option option_eN = Option.builder("eN").argName("elevNumb").hasArg().desc("Elevators number").build();
        Option option_fN = Option.builder("fN").argName("floorNumb").hasArg().desc("Floors number").build();
        Option option_i = Option.builder("i").argName("input").desc("Console input").build();
        Option option_n = Option.builder("n").argName("noInput").desc("NoInput").build();

        Options options = new Options();
        CommandLineParser parser = new DefaultParser();

        options.addOption(option_eN);
        options.addOption(option_fN);
        options.addOption(option_i);
        options.addOption(option_n);

        String header = "\nOptions, flags and arguments may be in any order\n";
        String footer = "\n\n";
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("elev", header, options, footer, true);


        int elevatorNumbers = 0;
        int floorNumbers = 0;
        IInputInterface inputInterface = new NonInput();
        try {
            commandLine = parser.parse(options, args);

            if (commandLine.hasOption("eN")) {
                elevatorNumbers = Integer.parseInt(commandLine.getOptionValue("eN"));
            }

            if (commandLine.hasOption("fN")) {
                floorNumbers = Integer.parseInt(commandLine.getOptionValue("fN"));
            }

            if (commandLine.hasOption("i") && !commandLine.hasOption("n")) {
                inputInterface = new InputInterface();
            }

            if (!commandLine.hasOption("i") && commandLine.hasOption("n")) {
                inputInterface = new NonInput();
            }

            if(elevatorNumbers > 0 && floorNumbers > 0) {
                ElevatorSystem es = new ElevatorSystem(elevatorNumbers, floorNumbers);
                es.run(new ConsoleInterface(), inputInterface);
            }
        }
        catch (ParseException exception) {
            System.out.print("Parse error: ");
            System.out.println(exception.getMessage());
        }
    }
}
