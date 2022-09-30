Here are the descriptions of the different classes of our Online Shopping System:

Account: There are two types of registered accounts in the system: one will be an Admin, 
who is responsible for adding new product categories and blocking/unblocking members; the other, a Member, 
who can buy/sell products.

Guest: Guests can search for and view products, and add them in the shopping cart. To place an order 
they have to become a registered member.

Catalog: Users of our system can search for products by their name or category. 
This class will keep an index of all products for faster search.

ProductCategory: This will encapsulate the different categories of products, such as books, electronics, etc.

Product: This class will encapsulate the entity that the users of our system will be buying and selling.
Each Product will belong to a ProductCategory.

ProductReview: Any registered member can add a review about a product.

ShoppingCart: Users will add product items that they intend to buy to the shopping cart.

Item: This class will encapsulate a product item that the users will be buying or placing in the shopping cart.
For example, a pen could be a product and if there are 10 pens in the inventory, each of these 10 
pens will be considered a product item.

Order: This will encapsulate a buying order to buy everything in the shopping cart.

OrderLog: Will keep a track of the status of orders, such as unshipped, pending, complete, canceled, etc.

ShipmentLog: Will keep a track of the status of shipments, such as pending, shipped, delivered, etc.

Notification: This class will take care of sending notifications to customers.

Payment: This class will encapsulate the payment for an order. Members can pay through credit card or
electronic bank transfer. 