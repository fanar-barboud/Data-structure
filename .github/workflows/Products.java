package Project;

public class Products {
	 private int productId;
	    private String name;
	    private double price;
	    private int stock;
	    private LinkedList<Integer> reviews = new LinkedList<Integer>();

	    public Products(int productId, String name, double price, int stock) {
	        this.productId = productId;
	        this.name = name;
	        this.price = price;
	        this.stock = stock;
	    }
	    
	    public Products() { //unparameterized constructor
	    	this.productId = 0;
	        this.name = "";
	        this.price = 0;
	        this.stock = 0;
	    }
	    
	    
	    public void addStock(int s) {
	    	stock += s;
	    }
	    
	    
	    public void removeStock(int s) {
	    	stock -= s;
	    }
	    
	    
	    public void addReview(Integer reviewId) {
	    	reviews.insert(reviewId);
	    }

	    
	    public boolean removeReview(Integer reviewId)//************
	    {
	        if ( !reviews.empty())
	        {
	            reviews.findFirst();
	            while(!reviews.last())
	            {
	                if (reviews.retrieve() == reviewId)
	                {
	                    reviews.remove();
	                    return true;
	                }
	                else
	                    reviews.findNext();
	            }
	            if (reviews.retrieve() == reviewId)
	            {
	                reviews.remove();
	                return true;
	            }
	        }
	        return false;
	    }

	    
	    public LinkedList<Integer> getReviews (){
	    
	        return reviews;
	    }
	    
	    

		

	    // ----------- Minimal getters/setters (as needed later) -----------

	    public int getProductId() { return productId; }
	    public String getName()    { return name; }
	    public double getPrice()   { return price; }
	    public int getStock()      { return stock; }
	    
	    public void setProductId(int productId) { this.productId = productId; }
	    public void setName(String name) { this.name = name; }
	    public void setPrice(double price) { this.price = price; }
	    public void setStock(int stock) { this.stock = stock; }
	    
	    
	    // ----------- ToString method -----------
	    @Override
	    public String toString() {
	        String str =  "\nproductId=" + productId + ", name=" + name + ", price=" + price + ", stock=" + stock ;
	        if ( ! reviews.empty())
	        {
	            str += "(reviews List" ;
	            reviews.findFirst();
	            while(! reviews.last())
	            {
	                str += reviews.retrieve() + " ";
	                reviews.findNext();
	            }
	            str += reviews.retrieve() + " )";
	        }
	        return str;        
	    }


	}

