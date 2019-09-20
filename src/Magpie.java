
/**
 * A program to carry on conversations with a human user.
 * This is the initial version that:
 * <ul><li>
 * Uses indexOf to find strings
 * </li><li>
 * Handles responding to simple words and phrases
 * </li></ul>
 * This version uses a nested if to handle default responses.
 *
 * @author Laurie White
 * @version April 2012
 */
public class Magpie {

    //INSTANCE VARIABLES
    private boolean knowsAboutPets = false;

    /**
     * Get a default greeting
     *
     * @return a greeting
     */
    public String getGreeting() {
        return "Hello, let's talk.";
    }

    /**
     * Gives a response to a user statement
     *
     * @param statement the user statement
     * @return a response based on the rules given
     */
    public String getResponse(String statement) {
        statement = statement.trim().toLowerCase();
        if (statement.length() == 0) {
            return "Hey, gimme something to work with here";
        }
        // blank response
        String response = "";

        // String Analysis
        if (findKeyword(statement, "no") >= 0) {
            response = "Why so negative?";
        } else if (findKeyword(statement, "sister") >= 0
                || findKeyword(statement, "mother") >= 0
                || findKeyword(statement, "father") >= 0
                || findKeyword(statement, "brother") >= 0) {
            response = "Tell me more about your family.";
        } else if (findKeyword(statement, "dog") >= 0
                || findKeyword(statement, "cat") >= 0) {
            if (!knowsAboutPets) {
                response = "Wow, you have pets? Please tell me more!";
                knowsAboutPets = true;
            } else {
                response = "I never get tired of talking about pets";
            }
            //TODO: prevent repeat response with a toggle
        } else if (findKeyword(statement, "Mr. A") >= 0
                || findKeyword(statement, "Mr. Adiletta") >= 0) {
            response = "He sounds like a great teacher!";
        } else if (findKeyword(statement, "hey") >= 0
                || findKeyword(statement, "hello") >= 0
                || findKeyword(statement, "hi") >= 0) {
            response = "Hello, nice to meet you! What's your name?";
        } else if (findKeyword(statement, "my name is") >= 0
                || findKeyword(statement, "I'm Lily") >= 0) {
            response = "What a lovely name";
        }
        if (findKeyword(statement, "What's your name?") >= 0
                || findKeyword(statement, "What is your name?") >= 0
                || findKeyword(statement, "What's your name") >= 0
                || findKeyword(statement, "What is your name") >= 0) {
            response = "that's for me to know, and you to never find out";
        } // Responses which require transformations
        else if (findKeyword(statement, "I want to", 0) >= 0) {
            response = transformIWantToStatement(statement);
        } // Activity 4
        else if (findKeyword(statement, "I want", 0) >= 0) {
            response = transformIWantStatement(statement);
        }
        // check for "I like you"
        else if(findKeyword(statement, "I", 0) >= 0){
            int psnYou = findKeyword(statement, "you", 0);
            int psnI = findKeyword(statement, "I", 0);
            
            if (psnYou >= 0
                    && findKeyword(statement, "you", psnI) >= 0) {
                response = transformIYouStatement(statement);
            } 
        }
        else {
            // Look for a two word (you <something> me)
            // pattern
            int psn = findKeyword(statement, "you", 0);

            if (psn >= 0
                    && findKeyword(statement, "me", psn) >= 0) {
                response = transformYouMeStatement(statement);
            } else {
                response = getRandomResponse();
            }
        }
        return response;
    }

    /**
     * Pick a default response to use if nothing else fits.
     *
     * @return a non-committal string
     */
    private String getRandomResponse() {
        final int NUMBER_OF_RESPONSES = 6;
        double r = Math.random();
        int whichResponse = (int) (r * NUMBER_OF_RESPONSES);
        String response = "";

        if (whichResponse == 0) {
            response = "Interesting, tell me more.";
        } else if (whichResponse == 1) {
            response = "Hmmm.";
        } else if (whichResponse == 2) {
            response = "Do you really think so?";
        } else if (whichResponse == 3) {
            response = "You don't say.";
        } else if (whichResponse == 4) {
            response = "That's Cool";
        } else if (whichResponse == 5) {
            response = "We're having a great conversation eh?";
        }
        return response;
    }

    /**
     * Take a statement with "I want to <something>." and transform it into
     * "What would it mean to <something>?"
     *
     * @param statement the user statement, assumed to contain "I want to"
     * @return the transformed statement
     */
    private String transformIWantToStatement(String statement) {
        //  Remove the final period, if there is one
        statement = statement.trim();
        String lastChar = statement.substring(statement
                .length() - 1);
        if (lastChar.equals(".")) {
            statement = statement.substring(0, statement
                    .length() - 1);
        }
        int psn = findKeyword(statement, "I want to", 0);
        String restOfStatement = statement.substring(psn + 9).trim();
        return "What would it mean to " + restOfStatement + "?";
    }

    private String transformIWantStatement(String statement) {
        //  Remove the final period, if there is one
        statement = statement.trim();
        String lastChar = statement.substring(statement
                .length() - 1);
        if (lastChar.equals(".")) {
            statement = statement.substring(0, statement
                    .length() - 1);
        }
        int psn = findKeyword(statement, "I want", 0);
        String restOfStatement = statement.substring(psn + 6).trim();
        return "Would you really be happy if you had " + restOfStatement + "?";
        // Responses which require tranformations
    }

    /**
     * Take a statement with "you <something> me" and transform it into "What
     * makes you think that I <something> you?"
     *
     * @param statement the user statement, assumed to contain "you" followed by
     * "me"
     * @return the transformed statement
     */
    private String transformYouMeStatement(String statement) {
        //  Remove the final period, if there is one
        statement = statement.trim();
        String lastChar = statement.substring(statement
                .length() - 1);
        if (lastChar.equals(".")) {
            statement = statement.substring(0, statement
                    .length() - 1);
        }

        int psnOfYou = findKeyword(statement, "you", 0);
        int psnOfMe = findKeyword(statement, "me", psnOfYou + 3);

        String restOfStatement = statement.substring(psnOfYou + 3, psnOfMe).trim();
        return "What makes you think that I " + restOfStatement + " you?";
    }

    private String transformIYouStatement(String statement) {
        //  Remove the final period, if there is one
        statement = statement.trim();
        String lastChar = statement.substring(statement
                .length() - 1);
        if (lastChar.equals(".")) {
            statement = statement.substring(0, statement
                    .length() - 1);
        }

        int psnOfI = findKeyword(statement, "I", 0);
        int psnOfYou = findKeyword(statement, "you", 0);

        String restOfStatement;
        restOfStatement = statement.substring(psnOfI + 1, psnOfYou).trim();
        return "Why do you " + restOfStatement + " me?";
    }

    /**
     * Search for one word in phrase. The search is not case sensitive. This
     * method will check that the given goal is not a substring of a longer
     * string (so, for example, "I know" does not contain "no").
     *
     * @param statement the string to search
     * @param goal the string to search for
     * @param startPos the character of the string to begin the search at
     * @return the index of the first occurrence of goal in statement or -1 if
     * it's not found
     */
    private int findKeyword(String statement, String goal,
            int startPos) {
        String phrase = statement.trim().toLowerCase();
        goal = goal.toLowerCase();

        // The only change to incorporate the startPos is in
        // the line below
        int psn = phrase.indexOf(goal, startPos);

        // Refinement--make sure the goal isn't part of a
        // word
        while (psn >= 0) {
            // Find the string of length 1 before and after
            // the word
            String before = " ", after = " ";
            if (psn > 0) {
                before = phrase.substring(psn - 1, psn);
            }
            if (psn + goal.length() < phrase.length()) {
                after = phrase.substring(
                        psn + goal.length(),
                        psn + goal.length() + 1);
            }

            // If before and after aren't letters, we've
            // found the word
            if (((before.compareTo("a") < 0) || (before
                    .compareTo("z") > 0)) // before is not a
                    // letter
                    && ((after.compareTo("a") < 0) || (after
                    .compareTo("z") > 0))) {
                return psn;
            }

            // The last position didn't work, so let's find
            // the next, if there is one.
            psn = phrase.indexOf(goal, psn + 1);

        }

        return -1;
    }

    /**
     * Search for one word in phrase. The search is not case sensitive. This
     * method will check that the given goal is not a substring of a longer
     * string (so, for example, "I know" does not contain "no"). The search
     * begins at the beginning of the string.
     *
     * @param statement the string to search
     * @param goal the string to search for
     * @return the index of the first occurrence of goal in statement or -1 if
     * it's not found
     */
    private int findKeyword(String statement, String goal) {
        return findKeyword(statement, goal, 0);
    }

} // closes Magpire class
