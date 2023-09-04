@SearchBookAndGetTheInformation
Feature: Input CustNo and Validate It

  @Valid
  Scenario Outline: Login with Invalid credential
    Given Users go into web <web>
		When Users login input username <username> and password <password>
		Then User login succesfully <url>

    Examples: 
      | web   																									  | username   | password |
      | http://fgc-cms-middle-fgc-test.apps.ocpnp.fifgroup.co.id/ | admin-fgc  | password |
  
	@InValid
  Scenario Outline: Login with Invalid credential
    Given Users go into web <web>
		When Users login input invalid username <username> and password <password>
		Then User should not be able to login succesfully

    Examples: 
      | web   																									  | username  | password |
      | http://fgc-cms-middle-fgc-test.apps.ocpnp.fifgroup.co.id/ | test-fgc  | password |
      
	@Valid
  Scenario Outline: Search CustNo and Validate It
    Given Users go into web <web>
		When Users login input username <username> and password <password>
		And Users go to menu 
		And Input CustNo <CustNo>
		And User validate CustNo <CustNo> and TextCustNo 
		Then User Logout

    Examples: 
      | web   																									  | username  | password | CustNo 		  |
      | http://fgc-cms-middle-fgc-test.apps.ocpnp.fifgroup.co.id/ | admin-fgc | password | 101000000388 |
      