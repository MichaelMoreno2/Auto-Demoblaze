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
      | Usuario  | Contraseña | Categoria | Productos             | Name    | Country  | City   | CreditCard     | Month | Year |
      | test     | test       | Phones    | Sony xperia z5        | Michael | Colombia | Bogota | 42424242424242 | 12    | 2026 |
      | admin    | admin      | Phones    | Iphone 6 32gb         | Juan    | Mexico   | CDMX   | 42424242424242 | 12    | 2027 |
      | admin123 | 123        | Laptops   | MacBook Pro           | Juan    | Peru     | Lima   | 42424242424242 | 12    | 2027 |
      | admin    | admin      | Monitors  | Apple monitor 24      | Juan    | Peru     | Lima   | 42424242424242 | 12    | 2027 |
      | 324234   | 233432     | Monitors  | ASUS Full HD          | Juan    | Peru     | Lima   | 42424242424242 | 12    | 2028 |
      | 12345    | 12345      | Monitors  | PRODUCTO NO EXISTENTE | Alberto | Chile    | NA     | 42424242424242 | 12    | 2029 |

    #Se generan varios casos de prueba en donde se agrega los diferentes tipos de productos y categorias
    #Se agregan 4 casos exitosos y 2 con errores controlados
    #(producto no encontrado/categoria no encontrada) -Logueo