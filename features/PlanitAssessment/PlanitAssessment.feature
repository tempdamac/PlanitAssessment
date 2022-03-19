@PlanitAssessment
Feature: Planit Technical Assessment - Automation

@TC01
Scenario Outline: TC01 - Submit Contact Information
	Given I navigate to Jupiter Cloud site
	When I go to tab <tabName>
	And populate all mandatory fields <foreName>, <surName>, <email>, <telephone>, <message>
	Then successful submission message is displayed
	
Examples:	
	|tabName	|foreName	|surName	|email	|telephone	|message	|
	|Contact	|John	|Doe	|@yopmail.com	|02 5132 8881	|Test Message	|
	|Contact	|John	|Doe	|@yopmail.com	|02 5132 8881	|Test Message	|
	|Contact	|John	|Doe	|@yopmail.com	|02 5132 8881	|Test Message	|
	|Contact	|John	|Doe	|@yopmail.com	|02 5132 8881	|Test Message	|
	|Contact	|John	|Doe	|@yopmail.com	|02 5132 8881	|Test Message	|		
	
@TC02
Scenario Outline: TC02 - Add to Cart
	Given I navigate to Jupiter Cloud site
	When I go to tab <tabName>
	And add to cart the following products <products>
	Then I can view the added products <products> in my cart
	
Examples:	
	|tabName	|products	|
	|Shop			|Funny Cow>2;Fluffy Bunny>1|
	
@TC03
Scenario Outline: TC03 - Verify Cart Subtotal
	Given I navigate to Jupiter Cloud site
	When I go to tab <tabName>
	And add to cart the following products <products>
	Then I can verify the subtotal of each added products <products> in my cart
	
Examples:	
	|tabName	|products	|
	|Shop			|Stuffed Frog>2;Fluffy Bunny>5;Valentine Bear>3|

	
	
	
	