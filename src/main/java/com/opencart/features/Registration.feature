Feature: Register Flow Feature Test Suite

  @Regression @Smoke
  Scenario: Access the Account Page after successful registration
    Given : Home Page is accessed
    And RegisteredPage is accessed from HomePage menu
    When the registration form is completed with valid random data
    And the privacyToggle is enabled
    And continueButton is clicked
    Then the current url contains the following keyword: "account"

  Scenario: User remains on Register Page when continue button is not clicked during the register flow
    Given : Home Page is accessed
    And RegisteredPage is accessed from HomePage menu
    When the registration form is completed with valid random data
    And the privacyToggle is enabled
    Then the current url contains the following keyword: "account"

  @Regression
  Scenario: User remains on Register Page when privacy conditions are not accepted during the registration flow
    Given : Home Page is accessed
    And RegisteredPage is accessed from HomePage menu
    When the registration form is completed with valid random data
    And continueButton is clicked
    Then the current url contains the following keyword: "account"

  @run
  Scenario Outline: Error messages are displayed when trying to register with invalid <attribute> data
    Given : Home Page is accessed
    And RegisteredPage is accessed from HomePage menu
    And the registration form is completed with the following data:
      | firstName | <firstName> |
      | lastName  | <lastName>  |
      | email     | Random      |
      | password  | Random      |
    When continueButton is clicked
    Then the following error messages are displayed:
      | Warning: You must agree to the Privacy Policy!    |
      | <attribute>  must be between 1 and 32 characters! |
    Examples:
      | attribute  | firstName | lastName  |
      | First Name | afsdfuicx | random    |
      | Last Name  | random    | afsdfuicx |

