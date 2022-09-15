Feature: Test StarHealth



  @Smoke
  Scenario Outline: Validate StarHealth Buy Now flow
    Given User launch StarHealth application with "<URL>"
    When User wait for Welcome to Star Health pop up
    And User close the pop up
    Then User validate Star Health home page title using Junit assertion
    Then User clicks on Buy Now Button
    And User Type Name as "<FullName>"
    And User Type Phome as "<PhNo>"
    And User Type PIN as "<PIN>"
    When User click on I need health insurance for drop down
    Then User select option as "<optionPlan>"
    And User see Plan for My Family page
    Then User validate mobile number same as previous page given number using Junit assertion
    When User clicks on Star Health logo
    Then Application should redirect to home page
    Then User close the child TAB
    And User navigate back to Parent TAB


    Examples:


    |URL|FullName|PhNo|PIN|optionPlan|
    |https://www.starhealth.in/|MITHUN ROY|6295653745|713213|My Family|


