Feature: Purchace process on the page Demoblaze

  Scenario Outline: Login, checkout and purchase flow.
    Given The user logs into the page
    When the user enter the user "<Usuario>" and password "<Contraseña>"
    And the user enter correctly and select the category "<Categoria>"
    And the user add the "<Productos>" products to the cart
    And the user proceed to checkout
    And the user complete the purchase with name "<Name>", country "<Country>", city "<City>", creditcard"<CreditCard>", month "<Month>", year "<Year>"
    Then the user should see a confirmation message

    Examples:
      | Usuario | Contraseña | Categoria | Productos      | Name    | Country  | City   | CreditCard     | Month | Year |
      | test    | test       | Phones    | Sony xperia z5 | Michael | Colombia | Bogota | 42424242424242 | 12    | 2026 |
      | admin   | admin      | Laptops   | MacBook Pro4   | Juan    | Peru     | Lima   | 42424242424242 | 12    | 2027 |
      | 324234  | 233432     | Laptops   | MacBook Pro4   | Juan    | Peru     | Lima   | 42424242424242 | 12    | 2028 |
      | 12345   | 12345      | Monitors  | ASUS Full HD   | Alberto | Chile    | NA     | 42424242424242 | 12    | 2029 |