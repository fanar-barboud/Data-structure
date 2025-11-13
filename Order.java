import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;


public class Order {
    
    /*============ ATTRIBUTES ==============*/
    private int orderId;
    private int customerId;
    private LinkedList <Integer> products = new LinkedList <Integer> ();  
    private double totalPrice;
    private LocalDate date;
    private String status; // (pending, shipped, delivered, cancelled)

    /*============= CONSTRUCTORS =============*/
    public Order() {
        this.orderId = 0;
        this.customerId = 0;
        this.totalPrice = 0;
        this.status = "";
    }

    public Order(int orderId, int customerId, Integer [] productIds, double totalPrice, String date, String status) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.totalPrice = totalPrice;
        this.date = LocalDate.parse(date);
        this.status = status;
        
        for (int i = 0 ; i < productIds.length ; i++)
          products.insert(productIds[i]);
    }
    
   /*================================ SEARCH for a specific ORDER by its ID =====================================*/
    public Order searchOrder(int id) {
    if (orders.empty()) {
        System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("️  No orders are currently available in the system.");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
        return null;
    }

     orders.findFirst();
    while (true) {
        Order current = orders.retrieve();
        if (current.getOrderId() == id) {
            return current; // found
        }
        if (orders.last()) break; 
        orders.findNext();
    }
    
    /*orders.findFirst();
    while (! orders.last()) {

        if (orders.retrieve().getOrderId()== id) {
            return orders.retrieve(); 
        }
        else{
        orders.findNext(); 
        }
    }
     Check for last order 
    if (orders.retrieve().getOrderId()== id) {
            return orders.retrieve(); 
        }*/

    System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    System.out.println(" No order found with ID: " + id);
    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
    return null;
}
    
 /*================================ UPDATE an OREDER STATUS- =============================*/
    public boolean updateOrderStatus(int id, String newStatus) {
    if (orders.empty()) {
        System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("️  No orders are currently available in the system.");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
        return false;
    }

    orders.findFirst();
    while (true) {
        Order current = orders.retrieve();

        if (current.getOrderId() == id) {
            if ("cancelled".equalsIgnoreCase(current.getStatus())) {
                System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println("️  Cannot update: order " + id + " is already Cancelled.");
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
                return false;
            }
            current.setStatus(newStatus);
            System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println(" Order " + id + " status updated successfully.");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

            return true;
        }

        if (orders.last()) break;
        orders.findNext();
    }
    System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    System.out.println(" No order found with ID: " + id);
    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
    return false;
}
    /*================================ CANCEL an ORDER by its ID =============================*/
    public boolean cancelOrder(int id) {
    if (orders.empty()) {
        System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("️  No orders are currently available in the system.");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");       
        return false;
    }

    orders.findFirst();
    while (true) {
        Order current = orders.retrieve();

        if (current.getOrderId() == id) {

            if ("cancelled".equalsIgnoreCase(current.getStatus())) {
                System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println("️  Order " + id + " was already cancelled.");
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
                return false; 
            }

            current.setStatus("cancelled");
            System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println(" Order " + id + " has been Cancelled successfully.");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
            return true;
        }

        if (orders.last()) break;
        orders.findNext();
    }
 
    System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    System.out.println(" No order found with ID: " + id);
    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");   
    return false;
    }
    
    /*====================== GET ORDERS BETTWEN TWO DATES =======================*/
    public LinkedList<Order> allOrdersBetweenDates(String sDate, String eDate) {
        
    LinkedList<Order> ordersD = new LinkedList<>();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    LocalDate start = LocalDate.parse(sDate, formatter);
    LocalDate end = LocalDate.parse(eDate, formatter);

    if (orders.empty()) {
        System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("️  No orders are currently available in the system.");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
        return ordersD;
    }

    orders.findFirst();
    while (true) {
        Order current = orders.retrieve();
        LocalDate orderDate = current.getDate();

        if (!orderDate.isBefore(start) && !orderDate.isAfter(end)) {
            ordersD.insert(current);
            System.out.println(current);
        }

        if (orders.last()) break;
        orders.findNext();
    }

    if (ordersD.empty()) {
        System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println(" No orders between " + sDate + " and " + eDate + ".");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
    } else {
        System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println(" Orders between " + sDate + " and " + eDate + " displayed successfully.");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
    }

    return ordersD;
}
    
    // ===================================================================================================
    
    
    /*public boolean removeProduct( Integer P)
    {
        if ( ! products.empty())
        {
            products.findFirst();
            while(! products.last())
            {
                if (products.retrieve() == P)
                {
                    products.remove();
                    return true;
                }
                else
                    products.findNext();
            }
            if (products.retrieve() == P)
            {
                products.remove();
                return true;
            }
        }
        return false;
    }*/
    
    
    
    /*============================ SETTERS & GETTERS ===========================*/
    public int getOrderId() {
        return orderId;
    }

    public int getcustomerId() {
        return customerId;
    }

    public LinkedList<Integer> getProducts() {
        return products;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setcustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setProducts(int pid) {
        this.products.insert(pid);
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void addProduct (Integer p )
    {
        products.insert(p);
    }

   /*============ To Display Order Informaition ============*/
    @Override
    public String toString() {
       String orderInfo = "\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n";
    orderInfo += "Order Details\n";
    orderInfo += "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n";
    orderInfo += "Order ID        : " + orderId + "\n";
    orderInfo += "Customer ID     : " + customerId + "\n";
    orderInfo += "Total Price     : " + totalPrice + "\n";
    orderInfo += "Date            : " + date + "\n";
    orderInfo += "Status          : " + status + "\n";
                   
    if (!products.empty()) {
        orderInfo += "Product IDs     : ";
        products.findFirst();
        while (true) {
            orderInfo += products.retrieve();
            if (products.last()) break;
            orderInfo += ", ";
            products.findNext();
        }
        orderInfo += "\n";
    } else {
        orderInfo += "Products List   : (none)\n";
    }

    orderInfo += "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n";
    return orderInfo;
    }

}
