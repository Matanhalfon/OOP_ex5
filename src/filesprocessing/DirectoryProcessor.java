package filesprocessing;


import filesprocessing.fileExceptions.TypeTwoException;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

/**
 * the main class that get the args from the cmd and preform the processing
 */

public class DirectoryProcessor {

    private static final int NUM_OF_ARGS = 2;
    private static final int ARG_SOURCE_DIR = 0;
    private static final int ARG_COMMAND_FILE = 1;
    private static final String ERROR="ERROR: ";
    private static final String UNREADABLE_DIR ="DIR file unreadable";

    /**
     * parse the args and run the program
     *
     * @param argv the args dir path and command file path
     */
    public static void main(String[] argv) {
        try {
            //check input
            if (argv.length != NUM_OF_ARGS) {
                throw new IllegalArgumentException(String.format("Bad number of " +
                                "arguments (receive %d, expected %d)", argv.length,
                        NUM_OF_ARGS));
            }
            //set basic args
            ArrayList<File> Dir = null;
            try {
                Dir = ParseDir.ParseFile(argv[ARG_SOURCE_DIR]);
            } catch (Exception e) {
                throw new TypeTwoException(UNREADABLE_DIR);
            }
            File commandsFile = new File(argv[ARG_COMMAND_FILE]);
            ParseCommand parser = new ParseCommand(commandsFile);
            Collection<Section> Sections = parser.parse();
            //try to parse command file
            //set the dir files to each section and then print warnings and output
            for (Section section : Sections) {
                section.SetInput(Dir);
                section.PrintWarnings();
                section.PrintGoodFiles();
            }
            // catch Errors and print the needed message
        } catch (TypeTwoException e) {
            System.err.print(ERROR + e.getMessage() + "\n");
        } catch (IllegalArgumentException e) {
            System.err.printf("ERROR: %s\n", e.getMessage());
        } catch (Exception e) {
            System.err.print("ERROR: %s\n");
        }

    }
}
