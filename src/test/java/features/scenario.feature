Feature: User Login, Hotel Search, Hotel Selection, Booking, and Logout
  Description: This feature covers user login, hotel search, hotel selection, booking, and logout functionality.

  Scenario: Successful User Login, Hotel Search, Hotel Selection, Booking, and Logout
    Given a user is on the Adactin Hotel App login page
    When the user enters a valid username and password
    #And clicks on the Login button
    #Then the user should be redirected to their account dashboard displaying a welcome message

    And the user is on the hotel search page
    When the user enters the following search criteria
    And clicks on the Search button
    And user should be directed to the select  hotel page
   # Then the user should see a list of available hotels matching the criteria

    And the user selects the hotel
    Then user clicks continue button
    Then the user should see details of the selected hotel
    And the user should see details to book a hotel

    And the user enters the following booking details

    And clicks on the Book Now button
    Then the booking should be successful, and the user receives a confirmation message

    And the user clicks on the Logout button
    Then the user should be logged out successfully


