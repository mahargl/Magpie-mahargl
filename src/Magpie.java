/**
 * A program to carry on conversations with a human user.
 * This is the initial version that:  
 * <ul><li>
 *       Uses indexOf to find strings
 * </li><li>
 * 		    Handles responding to simple words and phrases 
 * </li></ul>
 * This version uses a nested if to handle default responses.
 * @author Laurie White
 * @version April 2012
 */
public class Magpie
{
    //INSTANCE VARIABLES
    private boolean knowsAboutPets = false;
	/**
	 * Get a default greeting 	
	 * @return a greeting
	 */
	public String getGreeting()
	{
		return "Hello, let's talk.";
	}
	
	/**
	 * Gives a response to a user statement
	 * 
	 * @param statement
	 *            the user statement
	 * @return a response based on the rules given
	 */
	public String getResponse(String statement)
	{
            statement = statement.trim().toLowerCase();
            if(statement.length() == 0) return "Hey, gimme something to work with here";
            
		String response = "";
		if (statement.indexOf("no") >= 0)
		{
			response = "Why so negative?";
		}
		else if (statement.indexOf("mother") >= 0
				|| statement.indexOf("father") >= 0
				|| statement.indexOf("sister") >= 0
				|| statement.indexOf("brother") >= 0)
		{
			response = "Tell me more about your family.";
		}
                else if (statement.indexOf("dog") >= 0
                        || statement.indexOf("cat") >= 0){
                    if(!knowsAboutPets){
                        response = "Wow, you have pets? Please tell me more!";
                        knowsAboutPets = true;
                    }
                    else
                        response = "I never get tired of talking about pets";
                    //TODO: prevent repeat response with a toggle
                }
                else if (statement.indexOf("Mr. A") >= 0
                        || statement.indexOf("Mr. Adiletta") >= 0) {
                    response = "He sounds like a great teacher!";
                }
                else if (statement.indexOf("hey") >= 0
                        || statement.indexOf("hello") >= 0
                        || statement.indexOf("hi") >= 0) {
                    response = "Hello, nice to meet you! What's your name?";
                }
                else if (statement.indexOf("my name is") >= 0
                        || statement.indexOf("I'm Lily") >= 0) {
                    response = "What a lovely name";
                }
                if (statement.indexOf("What's your name?") >= 0
                        || statement.indexOf("What is your name?") >= 0
                        || statement.indexOf("What's your name") >= 0
                        || statement.indexOf("What is your name") >= 0) {
                    response = "that's for me to know, and you to never find out";
                }
                else
		{
			response = getRandomResponse();
		}
		return response;
	}
	
	/**
	 * Pick a default response to use if nothing else fits.
	 * @return a non-committal string
	 */
	private String getRandomResponse()
	{
		final int NUMBER_OF_RESPONSES = 6;
		double r = Math.random();
		int whichResponse = (int)(r * NUMBER_OF_RESPONSES);
		String response = "";
		
		if (whichResponse == 0)
		{
			response = "Interesting, tell me more.";
		}
		else if (whichResponse == 1)
		{
			response = "Hmmm.";
		}
		else if (whichResponse == 2)
		{
			response = "Do you really think so?";
		}
		else if (whichResponse == 3)
		{
			response = "You don't say.";
		}
                else if (whichResponse == 4)
                {
                        response = "That's Cool";
                }
                else if (whichResponse == 5)
                {
                        response = "We're having a great conversation eh?";
                }
		return response;
	}
}
