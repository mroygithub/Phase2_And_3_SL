
Feature: IDFC First Bank Test Scenarios




  @Smoke
  Scenario : Validate IDFC Bank Footer Links
    Given user launch "https://www.idfcfirstbank.com/"
    Then user validate Personal option is selected by default
    Then Scroll to footer section popular products
    And validate "Home Loan" link is part od "Popular Products"
    And validate "Leadership" link is part of "About IDFC FIRST Bank"
    Then validate Copyright line contains current year 2022



  @logic2
  Scenario: Test2
    Given Two plus Two is Four


  @logic3
  Scenario: Test2
    Given Two plus Two is Four







