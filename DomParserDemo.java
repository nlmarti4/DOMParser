import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.lang.Integer;
import java.util.Scanner;


public class DomParserDemo 
{
	public static boolean mediumMotorDistanceRotationsDownward(Element e) // Returns true if moving downward, returns false if moving upward
	{
		boolean speedPos = true;
		boolean rotationsPos = true;
		
		NodeList cmt = e.getElementsByTagName("ConfigurableMethodTerminal"); 
		
		for (int i = 0; i < cmt.getLength(); i++)
		{
			Node cmtNode = cmt.item(i);
			Element cmtElement = (Element) cmtNode;
			
			String configuredValueString = cmtElement.getAttribute("ConfiguredValue");
			
			NodeList terminalList = cmtElement.getElementsByTagName("Terminal");
			
			Node terminalNode = terminalList.item(0);
			Element terminalElement = (Element) terminalNode;
			
			if (terminalElement.getAttribute("Id").compareTo("Speed") == 0)
			{
				double configuredValue = Double.parseDouble(configuredValueString);
				if (configuredValue < 0)
				{
					speedPos = false;
				}
			}
			
			if (terminalElement.getAttribute("Id").compareTo("Rotations") == 0)
			{
				double configuredValue = Double.parseDouble(configuredValueString);
				if (configuredValue < 0)
				{
					rotationsPos = false;
				}
			}
			
		}
		
		if ((speedPos && rotationsPos) || (!speedPos && !rotationsPos))
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}
	
	public static int mediumMotorDistanceRotationsRotationsRange(Element e) // Returns 0 for failure, 1 for satisfactory, 2 for sub-optimal, 3 for optimal
	{
		double configuredValue = 0;
		
		NodeList cmt = e.getElementsByTagName("ConfigurableMethodTerminal"); 
		
		for (int i = 0; i < cmt.getLength(); i++)
		{
			Node cmtNode = cmt.item(i);
			Element cmtElement = (Element) cmtNode;
			
			String configuredValueString = cmtElement.getAttribute("ConfiguredValue");
			
			NodeList terminalList = cmtElement.getElementsByTagName("Terminal");
			
			Node terminalNode = terminalList.item(0);
			Element terminalElement = (Element) terminalNode;
			
			if (terminalElement.getAttribute("Id").compareTo("Rotations") == 0)
			{
				configuredValue = Double.parseDouble(configuredValueString);
				break;
			}
		}
		
		if (configuredValue < .25 && configuredValue > .15 || configuredValue > -.25 && configuredValue < -.15)
		{
			return 3;
		}
		else if (configuredValue < .3 && configuredValue > .1 || configuredValue > -.3 && configuredValue < -.1)
		{
			return 2;
		}
		else if (configuredValue < .4 && configuredValue > 0 || configuredValue > -.4 && configuredValue < 0)
		{
			return 1;
		}
		else
		{
			return 0;
		}
	}
	
	public static boolean moveTankDistanceRotationsMovingForward(Element e) // Returns true if moving forward, returns false if moving backward
	{
		boolean speedLeftPos = true;
		boolean speedRightPos = true;
		boolean rotationsPos = true;
		
		NodeList cmt = e.getElementsByTagName("ConfigurableMethodTerminal"); 
		
		for (int i = 0; i < cmt.getLength(); i++)
		{
			Node cmtNode = cmt.item(i);
			Element cmtElement = (Element) cmtNode;
			
			String configuredValueString = cmtElement.getAttribute("ConfiguredValue");
			
			NodeList terminalList = cmtElement.getElementsByTagName("Terminal");
			
			Node terminalNode = terminalList.item(0);
			Element terminalElement = (Element) terminalNode;
			
			if (terminalElement.getAttribute("Id").compareTo("Speed\\ Left") == 0)
			{
				double configuredValue = Double.parseDouble(configuredValueString);
				if (configuredValue < 0)
				{
					speedLeftPos = false;
				}
			}
			
			if (terminalElement.getAttribute("Id").compareTo("Speed\\ Right") == 0)
			{
				double configuredValue = Double.parseDouble(configuredValueString);
				if (configuredValue < 0)
				{
					speedRightPos = false;
				}
			}
			
			if (terminalElement.getAttribute("Id").compareTo("Rotations") == 0)
			{
				double configuredValue = Double.parseDouble(configuredValueString);
				if (configuredValue < 0)
				{
					rotationsPos = false;
				}
			}
		}
		
		if ((speedLeftPos && speedRightPos && rotationsPos) || (!speedLeftPos && !speedRightPos && !rotationsPos))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public static int moveTankDistanceRotationsRange(Element e) // Returns 0 for failure, 1 for satisfactory, 2 for sub-optimal, 3 for optimal
	{
		double configuredValue = 0;
		
		NodeList cmt = e.getElementsByTagName("ConfigurableMethodTerminal"); 
		
		for (int i = 0; i < cmt.getLength(); i++)
		{
			Node cmtNode = cmt.item(i);
			Element cmtElement = (Element) cmtNode;
			
			String configuredValueString = cmtElement.getAttribute("ConfiguredValue");
			
			NodeList terminalList = cmtElement.getElementsByTagName("Terminal");
			
			Node terminalNode = terminalList.item(0);
			Element terminalElement = (Element) terminalNode;
			
			if (terminalElement.getAttribute("Id").compareTo("Rotations") == 0)
			{
				configuredValue = Double.parseDouble(configuredValueString);
				break;
			}
		}
		
		if (configuredValue > 3.9 && configuredValue < 4.1 || configuredValue < -3.9 && configuredValue > -4.1 )
		{
			return 3;
		}
		else if (configuredValue > 3.7 && configuredValue < 4.3 || configuredValue < -3.7 && configuredValue > -4.3)
		{
			return 2;
		}
		else if (configuredValue > 3 && configuredValue < 5 || configuredValue < -3 && configuredValue > -5)
		{
			return 1;
		}
		else
		{
			return 0;
		}
		
	}
	
	public static boolean moveTankDistanceRotationsOnlyRightWheelMoving(Element e) // Returns true if only right wheel moving forward, returns false if otherwise
	{
		boolean speedLeftEqualsZero = false;
		boolean speedRightPos = false;
		
		NodeList cmt = e.getElementsByTagName("ConfigurableMethodTerminal"); 
		
		for (int i = 0; i < cmt.getLength(); i++)
		{
			Node cmtNode = cmt.item(i);
			
			Element cmtElement = (Element) cmtNode;
			
			String configuredValueString = cmtElement.getAttribute("ConfiguredValue");
			
			NodeList terminalList = cmtElement.getElementsByTagName("Terminal");
			
			Node terminalNode = terminalList.item(0);
			
			Element terminalElement = (Element) terminalNode;
			
			if (terminalElement.getAttribute("Id").compareTo("Speed\\ Left") == 0)
			{
				double configuredValue = Double.parseDouble(configuredValueString);
				if (configuredValue == 0)
				{
					speedLeftEqualsZero = true;
				}
			}
			
			if (terminalElement.getAttribute("Id").compareTo("Speed\\ Right") == 0)
			{
				double configuredValue = Double.parseDouble(configuredValueString);
				if (configuredValue > 0)
				{
					speedRightPos = true;
				}
			}
		}
		
		boolean onlyRightWheelMoving = false;
		
		if (speedRightPos && speedLeftEqualsZero)
		{
			onlyRightWheelMoving = true;
		}
		
		return onlyRightWheelMoving;
	}
	
	public static int moveTankDistanceRotationsRangeForTurn(Element e) // Returns 0 for failure, 1 for satisfactory, 2 for sub-optimal, 3 for optimal
	{
		double configuredValue = 0;
		
		NodeList cmt = e.getElementsByTagName("ConfigurableMethodTerminal"); 
		
		for (int i = 0; i < cmt.getLength(); i++)
		{
			Node cmtNode = cmt.item(i);
			
			Element cmtElement = (Element) cmtNode;
			
			String configuredValueString = cmtElement.getAttribute("ConfiguredValue");
			
			NodeList terminalList = cmtElement.getElementsByTagName("Terminal");
			
			Node terminalNode = terminalList.item(0);
			Element terminalElement = (Element) terminalNode;
			
			if (terminalElement.getAttribute("Id").compareTo("Rotations") == 0)
			{
				configuredValue = Double.parseDouble(configuredValueString);
				break;
			}
		}
		
		if (configuredValue > .9 && configuredValue < 1.1)
		{
			return 3;
		}
		else if (configuredValue > .7 && configuredValue < 1.3)
		{
			return 2;
		}
		else if (configuredValue > 0 && configuredValue < 2)
		{
			return 1;
		}
		else
		{
			return 0;
		}
	}
	
	public static void main(String[] args)
	{
		int moduleNum = 0;
		
		System.out.println("Please input the module number: "); // Prompt for module number
		
		Scanner scan = new Scanner(System.in); // Instantiate Scanner object
		
		moduleNum = scan.nextInt(); // Read in the module number
		
		try 
		{	
			File inputFile = new File("/Users/nickmartinez/Desktop/Program.ev3p"); // Initialize new File object from local path
         
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance(); // Initialize new DocumentBuilderFactory object
         
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder(); // Initialize new DocumentBuilder object using DocumentBuilderFactory object
         
			Document doc = dBuilder.parse(inputFile); // Initialize new Document object using the File object
         
			doc.getDocumentElement().normalize(); // Normalize (?) the Document object
         
			System.out.println("Root element: " + doc.getDocumentElement().getNodeName()); // Get the name of the root element of the XML file
        
			NodeList wList = doc.getElementsByTagName("Wire"); // Initialize new NodeList object of all "Wire" tags
         
			System.out.println("Number of wire tags: " + wList.getLength()); // Display number of "Wire" tags
				
			int numWires = wList.getLength(); // Initialize numWires
				
			int[][] holder = new int[2][numWires]; // Initialize 2-d array where each column will hold a SequenceOut/SequenceIn pair
				
			for (int i = 0; i < numWires; i++) // Iterate through the all "Wire" tags
			{
				Node wireNode = wList.item(i); // Instantiate Node object using the ith "Wire"
				
				Element wireElement = (Element) wireNode; // Instantiate Element object using the Node object above 
				
				String joints = wireElement.getAttribute("Joints"); // Store the SequenceOut/SequenceIn information in a string
				
				System.out.print(joints + " "); // Print out the SequenceOut/SequenceIn string
				
				System.out.print("Bookend indices are: " + joints.indexOf("n") + " " + joints.indexOf(":SequenceOut") + " "); // Print out the indices which immediately precede and immediately follow the SequenceOut number
					
				int firstNumStartIndex = joints.indexOf("n"); // Store the firstNumStartIndex
				
				int firstNumEndIndex = joints.indexOf(":SequenceOut"); // Store the firstNumEndIndex
					
				String firstNumString = joints.substring(firstNumStartIndex + 1, firstNumEndIndex); // Store the SequenceOut number 
					
				int firstNum = Integer.parseInt(firstNumString);  // Parse the SequenceOut number to an int
				
				System.out.println("So what we get is: " + firstNum); // Display the number of interest
					
				holder[0][i] = firstNum; // Since these are the "firsts" in the pairs, we store in the first row of the 2-d array
					
			}
				
			for (int i = 0; i < numWires; i++) // Iterate through the all "Wire" tags
			{
				Node wire = wList.item(i); // Instantiate Node object using the ith "Wire"
				
				Element wireElement = (Element) wire; // Instantiate Element object using the Node object above 
				
				String joints = wireElement.getAttribute("Joints"); // Store the SequenceOut/SequenceIn information in a string
				
				System.out.print(joints + " "); // Print out the SequenceOut/SequenceIn string
				
				System.out.print("Bookend indices are: " + joints.lastIndexOf("(n") + " " + joints.indexOf(":SequenceIn") + " "); // Print out the indices which immediately precede (by 2) and immediately follow the SequenceIn number
					
				int secondNumStartIndex = joints.lastIndexOf("(n"); // Store the secondNumStartIndex
				
				int secondNumEndIndex = joints.indexOf(":SequenceIn"); // Store the secondNumEndIndex
					
				String secondNumString = joints.substring(secondNumStartIndex + 2, secondNumEndIndex); // Store the SequenceIn number 
					
				int secondNum = Integer.parseInt(secondNumString); // Parse the SequenceIn number to an int
				
				System.out.println("So what we get is: " + secondNum); // Display the number of interest
					
				holder[1][i] = secondNum; // Since these are the "seconds" in the pairs, we store in the second row of the 2-d array			
			}
				
			for (int i = 0; i < 2; i++) // Display the filled out 2-d array
			{
				for (int j = 0; j < numWires; j++)
				{
					System.out.print(holder[i][j] + " ");
				}
				
				System.out.println();
			}
           
			int indexOfStart = 0;
			
			for (int i = 0; i < numWires; i++) // Algorithm for finding the index (column) of the starting "Wire" pair
			{
				int topNumber = holder[0][i];
					
				boolean foundBelow = false;
					
				for (int j = 0; j < numWires; j++)
				{
					if (topNumber == holder[1][j])
					{
						foundBelow = true;
					}
				}
					
				if (!foundBelow)
				{
					indexOfStart = i;
					break;
				}
			}
				
			int indexOfEnd = 0;
			
			for (int i = 0; i < numWires; i++) // Algorithm for finding the index (column) of the ending "Wire" pair
			{
				int bottomNumber = holder[1][i];
					
				boolean foundAbove = false;
					
				for (int j = 0; j < numWires; j++)
				{
					if (bottomNumber == holder[0][j])
					{
						foundAbove = true;
					}
				}
					
				if (!foundAbove)
				{
					indexOfEnd = i;
					break;
				}
			}
				
			System.out.println("indexOfStart: " + indexOfStart); // Display the index (column) of the starting "Wire" pair
			System.out.println("indexOfEnd: " + indexOfEnd); // Display the index (column) of the ending "Wire" pair
				
			int[][] startHolder = new int[2][1]; // Swap in order to place the starting "Wire" pair in the first column
				
			startHolder[0][0] = holder[0][indexOfStart];
			startHolder[1][0] = holder[1][indexOfStart];
				
			holder[0][indexOfStart] = holder[0][0];
			holder[1][indexOfStart] = holder[1][0];
				
			holder[0][0] = startHolder[0][0];
			holder[1][0] = startHolder[1][0];
				
			int[][] endHolder = new int[2][1]; // Swap in order to place the ending "Wire" pair in the first column
				
			endHolder[0][0] = holder[0][indexOfEnd];
			endHolder[1][0] = holder[1][indexOfEnd];
				
			holder[0][indexOfEnd] = holder[0][numWires - 1];
			holder[1][indexOfEnd] = holder[1][numWires - 1];
				
			holder[0][numWires - 1] = endHolder[0][0];
			holder[1][numWires - 1] = endHolder[1][0];
				
			for (int i = 0; i < 2; i++) // Display the 2-d array after placing the starting/ending "Wire" pair in the correct location
			{
				for (int j = 0; j < numWires; j++)
				{
					System.out.print(holder[i][j] + " ");
				}
				
				System.out.println();
			}
				
			for (int i = 0; i < numWires - 3; i++) // Algorithm for placing the remaining pairs in the correct location
			{
				for (int j = i + 1; j < numWires - 1; j++)
				{
					if (holder[1][i] == holder[0][j]) // If the secondNum matches the firstNum of a pair, then we have pairs that should be contiguous
					{
						int[][] temp = new int[2][1]; // Swap to make pairs contiguous
							
						temp[0][0] = holder[0][j];
						temp[1][0] = holder[1][j];
							
						holder[0][j] = holder[0][i + 1];
						holder[1][j] = holder[1][i + 1];
							
						holder[0][i + 1] = temp[0][0];
						holder[1][i + 1] = temp[1][0];
					}
				}
			}
				
			System.out.println("After sorting: "); // Display 2-d array after sorting algorithm
			for (int i = 0; i < 2; i++)
			{
				for (int j = 0; j < numWires; j++)
				{
					System.out.print(holder[i][j] + " ");
				}
					
				System.out.println();
			}
				
			String stringHolder[][] = new String[1][numWires + 1]; // Instantiate new String array which will hold the sequence of CMCs by "Id" attribute
			
			for (int i = 0; i < numWires; i++)
			{
				stringHolder[0][i] = "n" + holder[0][i];
			}
			
			stringHolder[0][numWires] = "n" + holder[1][numWires - 1];
			
			System.out.println("So sequence is: "); // Display the sequence according to "Id" attribute
			
			for (int i = 0; i < numWires + 1; i++)
			{
				System.out.print(stringHolder[0][i] + " ");
			}
			
			System.out.println();
				
			Element e = null; // Instantiate generic Element object to test methods
			
			NodeList nList = doc.getElementsByTagName("ConfigurableMethodCall");
			
			for (int i = 0; i < nList.getLength(); i++) // Iterate through the CMCs to find a particular CMC by their "Id"
			{
				Node node = nList.item(i);
				
				Element element = (Element) node;
				
				if (element.getAttribute("Id").compareTo("n8") == 0)
				{
					e = element; 
				}
			}
								
			switch (moduleNum) 
			{
				case 1: 
				{
					boolean secondBlockCorrect = false;
					boolean thirdBlockCorrect = false;
					
					for (int i = 1; i < numWires + 1; i++) // Iterate through each of the instructions, not including the StartBlock
					{
						String IdValue = stringHolder[0][i]; // Get the Id of the current instruction
												
						Element eToCheck = null; // Element object which will be initialized to the CMC of the current instruction
						
						NodeList nListToCheck = doc.getElementsByTagName("ConfigurableMethodCall"); // NodeList object of all CMCs
						
						for (int j = 0; j < nListToCheck.getLength(); j++) // Iterate through the CMCs to find a particular CMC by their "Id"
						{
							Node node = nListToCheck.item(j);
							
							Element element = (Element) node;
														
							if (element.getAttribute("Id").compareTo(IdValue) == 0)
							{
								eToCheck = element; 
							}
						}
						
						if (i == 1) // First non-trial instruction (StartBlock is trivial)
						{
							String blockInstruction = eToCheck.getAttribute("Target");
																
							if (blockInstruction.compareTo("MoveTankDistanceRotations\\.vix") == 0)
							{
								secondBlockCorrect = true;
							}
						}
						else if (i == 2) // Second non-trial instruction
						{
							String blockInstruction = eToCheck.getAttribute("Target");
														
							if (blockInstruction.compareTo("StopBlock\\.vix") == 0)
							{
								thirdBlockCorrect = true;
							}
						}
					}
					
					boolean correctOrder = false;
					
					if (secondBlockCorrect && thirdBlockCorrect) // If all blocks correct, we have correct order
					{
						correctOrder = true;
					}
					
					
					
					System.out.println("Correct Order: " + correctOrder);
					System.out.println();
					
					if (correctOrder)
					{
						boolean firstBlockCorrectDirection = false;
						int firstBlockCorrectRotations = 0;
						
						for (int i = 1; i < numWires + 1; i++) // Iterate through each of the instructions, not including the StartBlock
						{
							String IdValue = stringHolder[0][i]; // Get the Id of the current instruction
														
							Element eToCheck = null; // Element object which will be initialized to the CMC of the current instruction
							
							NodeList nListToCheck = doc.getElementsByTagName("ConfigurableMethodCall"); // NodeList object of all CMCs
							
							for (int j = 0; j < nListToCheck.getLength(); j++) // Iterate through the CMCs to find a particular CMC by their "Id"
							{
								Node node = nListToCheck.item(j);
								
								Element element = (Element) node;
																
								if (element.getAttribute("Id").compareTo(IdValue) == 0)
								{
									eToCheck = element; 
								}
							}
							
							if (i == 1) // First non-trial instruction
							{
								if (moveTankDistanceRotationsMovingForward(eToCheck) == false)
								{
									firstBlockCorrectDirection = true;
								}
								
								firstBlockCorrectRotations = moveTankDistanceRotationsRange(eToCheck);
								
								System.out.println("Moving forward 80 cm");
								System.out.println("Correct direction: " + firstBlockCorrectDirection);
								System.out.println("Correct rotations (On a scale from 0-3): " + firstBlockCorrectRotations);
								System.out.println();
							}
						}
					} // End of iterating through each of the instructions
				break;
				} // End of case 1
				case 2:
				{
					// first block is always correct because it is under a StartBlock tag instead of a ConfigurableMethodCallTag
					boolean secondBlockCorrect = false;
					boolean thirdBlockCorrect = false;
					boolean fourthBlockCorrect = false;
					boolean fifthBlockCorrect = false;
					boolean sixthBlockCorrect = false;
					
					for (int i = 1; i < numWires + 1; i++) // Iterate through each of the instructions, not including the StartBlock
					{
						String IdValue = stringHolder[0][i]; // Get the Id of the current instruction
												
						Element eToCheck = null; // Element object which will be initialized to the CMC of the current instruction
						
						NodeList nListToCheck = doc.getElementsByTagName("ConfigurableMethodCall"); // NodeList object of all CMCs
						
						for (int j = 0; j < nListToCheck.getLength(); j++) // Iterate through the CMCs to find a particular CMC by their "Id"
						{
							Node node = nListToCheck.item(j);
							
							Element element = (Element) node;
														
							if (element.getAttribute("Id").compareTo(IdValue) == 0)
							{
								eToCheck = element; 
							}
						}
						
						if (i == 1) // First non-trial instruction (StartBlock is trivial)
						{
							String blockInstruction = eToCheck.getAttribute("Target");
																
							if (blockInstruction.compareTo("MediumMotorDistanceRotations\\.vix") == 0)
							{
								secondBlockCorrect = true;
							}
						}
						else if (i == 2) // Second non-trial instruction
						{
							String blockInstruction = eToCheck.getAttribute("Target");
														
							if (blockInstruction.compareTo("MoveTankDistanceRotations\\.vix") == 0)
							{
								thirdBlockCorrect = true;
							}
						}
						else if (i == 3) // Third non-trial instruction
						{
							String blockInstruction = eToCheck.getAttribute("Target");
														
							if (blockInstruction.compareTo("MediumMotorDistanceRotations\\.vix") == 0)
							{
								fourthBlockCorrect = true;
							}
						}
						else if (i == 4) // Fourth non-trial instruction
						{
							String blockInstruction = eToCheck.getAttribute("Target");
														
							if (blockInstruction.compareTo("MoveTankDistanceRotations\\.vix") == 0)
							{
								fifthBlockCorrect = true;
							}
						}
						else if (i == 5) // Fifth non-trial instruction
						{
							String blockInstruction = eToCheck.getAttribute("Target");
														
							if (blockInstruction.compareTo("StopBlock\\.vix") == 0)
							{
								sixthBlockCorrect = true;
							}
						}
				
					} // End of correct order error-checking
					
					boolean correctOrder = false;
					
					if (secondBlockCorrect && thirdBlockCorrect && fourthBlockCorrect && fifthBlockCorrect && sixthBlockCorrect) // If all blocks correct, we have correct order
					{
						correctOrder = true;
					}
					
					System.out.println("Correct Order: " + correctOrder);
					System.out.println();
					
					if (correctOrder)
					{
						boolean firstBlockCorrectDirection = false;
						int firstBlockCorrectRotations = 0;
						
						boolean secondBlockCorrectDirection = false;
						int secondBlockCorrectRotations = 0;
						
						boolean thirdBlockCorrectDirection = false;
						int thirdBlockCorrectRotations = 0;
						
						boolean fourthBlockCorrectDirection = false;
						int fourthBlockCorrectRotations = 0;
						
						for (int i = 1; i < numWires + 1; i++) // Iterate through each of the instructions, not including the StartBlock
						{
							String IdValue = stringHolder[0][i]; // Get the Id of the current instruction
														
							Element eToCheck = null; // Element object which will be initialized to the CMC of the current instruction
							
							NodeList nListToCheck = doc.getElementsByTagName("ConfigurableMethodCall"); // NodeList object of all CMCs
							
							for (int j = 0; j < nListToCheck.getLength(); j++) // Iterate through the CMCs to find a particular CMC by their "Id"
							{
								Node node = nListToCheck.item(j);
								
								Element element = (Element) node;
																
								if (element.getAttribute("Id").compareTo(IdValue) == 0)
								{
									eToCheck = element; 
								}
							}
							
							if (i == 1) // First non-trial instruction
							{
								if (mediumMotorDistanceRotationsDownward(eToCheck) == false)
								{
									firstBlockCorrectDirection = true;
								}
								
								firstBlockCorrectRotations = mediumMotorDistanceRotationsRotationsRange(eToCheck);
								
								System.out.println("Moving arm up");
								System.out.println("Correct direction: " + firstBlockCorrectDirection);
								System.out.println("Correct rotations (On a scale from 0-3): " + firstBlockCorrectRotations);
								System.out.println();
							}
							
							if (i == 2) // Second non-trial instruction
							{
								if (moveTankDistanceRotationsMovingForward(eToCheck) == true)
								{
									secondBlockCorrectDirection = true;
								}
								
								secondBlockCorrectRotations = moveTankDistanceRotationsRange(eToCheck);
								
								System.out.println("Moving forward 80 cm");
								System.out.println("Correct direction: " + secondBlockCorrectDirection);
								System.out.println("Correct rotations (On a scale from 0-3): " + secondBlockCorrectRotations);
								System.out.println();

							}
							
							if (i == 3) // Third non-trial instruction
							{
								if (mediumMotorDistanceRotationsDownward(eToCheck) == true)
								{
									thirdBlockCorrectDirection = true;
								}
								
								thirdBlockCorrectRotations = mediumMotorDistanceRotationsRotationsRange(eToCheck);
								
								System.out.println("Moving arm down");
								System.out.println("Correct direction: " + thirdBlockCorrectDirection);
								System.out.println("Correct rotations (On a scale from 0-3): " + thirdBlockCorrectRotations);
								System.out.println();

							}
							
							if (i == 4) // Fourth non-trial instruction
							{
								if (moveTankDistanceRotationsMovingForward(eToCheck) == false)
								{
									fourthBlockCorrectDirection = true;
								}
								
								fourthBlockCorrectRotations = moveTankDistanceRotationsRange(eToCheck);
								
								System.out.println("Moving backwards 80 cm");
								System.out.println("Correct direction: " + fourthBlockCorrectDirection);
								System.out.println("Correct rotations (On a scale from 0-3): " + fourthBlockCorrectRotations);
								System.out.println();

							}
							
						}
						
					} // End of correct order error-checking
				break;
				} // End Case 2
				case 3:
				{
					boolean secondBlockCorrect = false;
					boolean thirdBlockCorrect = false;
					
					for (int i = 1; i < numWires + 1; i++) // Iterate through each of the instructions, not including the StartBlock
					{
						String IdValue = stringHolder[0][i]; // Get the Id of the current instruction
												
						Element eToCheck = null; // Element object which will be initialized to the CMC of the current instruction
						
						NodeList nListToCheck = doc.getElementsByTagName("ConfigurableMethodCall"); // NodeList object of all CMCs
						
						for (int j = 0; j < nListToCheck.getLength(); j++) // Iterate through the CMCs to find a particular CMC by their "Id"
						{
							Node node = nListToCheck.item(j);
							
							Element element = (Element) node;
														
							if (element.getAttribute("Id").compareTo(IdValue) == 0)
							{
								eToCheck = element; 
							}
						}
						
						if (i == 1) // First non-trial instruction (StartBlock is trivial)
						{
							String blockInstruction = eToCheck.getAttribute("Target");
																
							if (blockInstruction.compareTo("MoveTankDistanceRotations\\.vix") == 0)
							{
								secondBlockCorrect = true;
							}
						}
						else if (i == 2) // Second non-trial instruction
						{
							String blockInstruction = eToCheck.getAttribute("Target");
														
							if (blockInstruction.compareTo("StopBlock\\.vix") == 0)
							{
								thirdBlockCorrect = true;
							}
						}
					}
					
					boolean correctOrder = false;
					
					if (secondBlockCorrect && thirdBlockCorrect) // If all blocks correct, we have correct order
					{
						correctOrder = true;
					}
					
					System.out.println("Correct Order: " + correctOrder);
					System.out.println();
					
					if (correctOrder)
					{
						boolean firstBlockCorrectSpeeds = false;
						int firstBlockCorrectRotations = 0;
						
						for (int i = 1; i < numWires + 1; i++) // Iterate through each of the instructions, not including the StartBlock
						{
							String IdValue = stringHolder[0][i]; // Get the Id of the current instruction
														
							Element eToCheck = null; // Element object which will be initialized to the CMC of the current instruction
							
							NodeList nListToCheck = doc.getElementsByTagName("ConfigurableMethodCall"); // NodeList object of all CMCs
							
							for (int j = 0; j < nListToCheck.getLength(); j++) // Iterate through the CMCs to find a particular CMC by their "Id"
							{
								Node node = nListToCheck.item(j);
								
								Element element = (Element) node;
																
								if (element.getAttribute("Id").compareTo(IdValue) == 0)
								{
									eToCheck = element; 
								}
							}
							
							if (i == 1) // First non-trial instruction
							{
								if (moveTankDistanceRotationsOnlyRightWheelMoving(eToCheck) == true)
								{
									firstBlockCorrectSpeeds = true;
								}
								
								firstBlockCorrectRotations = moveTankDistanceRotationsRangeForTurn(eToCheck);
								
								System.out.println("Turn 90 degrees moving right wheel only");
								System.out.println("Correct wheels moving: " + firstBlockCorrectSpeeds);
								System.out.println("Correct rotations (On a scale from 0-3): " + firstBlockCorrectRotations);
								System.out.println();
							}
						}
					} // End of iterating through each of the instructions
					break;
				}
				case 4:
				{
					
				}
				case 5:
				{
					
				}			
			}
			
        }
		catch(Exception e) 
		{
			e.printStackTrace();
		}

		
   } // End main method
} // End class