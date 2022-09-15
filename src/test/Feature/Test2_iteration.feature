Feature: Scenarios to validate five applications and Title


  Scenario Outline: validate five application titles
    Given user launch application with given url with <loop>

      |URL|
      | https://www.google.com |
      | https://www.facebook.com |
      | https://www.linkedin.com |
      | https://www.microsoft.com |
      | https://manual2automation.com |

    Then get title value from application and its value should be as feature file <loop>


      |Title|
      | Google |
      | Facebook – log in or sign up |
      | LinkedIn |
      | Microsoft - Official Home Page |
      | Automation Testing tools : selenium WebDriver, API , Rest Assured , Appium |

    Then User close the application

    Examples:
      |loop|
      |0|
      |1|
      |2|
      |3|
      |4|