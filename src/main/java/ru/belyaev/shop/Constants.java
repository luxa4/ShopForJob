package ru.belyaev.shop;

public final class Constants {
    public static final String CURRENT_SHOPPING_CART = "CURRENT_SHOPPING_CART";
    public static final int MAX_PRODUCTS_COUNT_IN_CART = 20 ;
    public static final int MAX_ONE_PRODUCT_COUNT_IN_CART = 10 ;

    public static final String ACCOUNT_ACTIONS_HISTORY = "ACCOUNT_ACTIONS_HISTORY";
    public static final int MAX_PRODUCTS_PER_HTML_PAGE = 12 ;

    public static final String CURRENT_ACCOUNT = "CURRENT_ACCOUNT";
    public static final String SUCCESS_REDIRECT_URL_AFTER_SIGNIN = "SUCCESS_REDIRECT_URL_AFTER_SIGNIN";
    public static final String CURRENT_REQUEST_URL = "CURRENT_REQUEST_URL";
    public static final int ORDERS_PER_PAGE = 12;

    public enum Cookie {


        SHOPPING_CART ("ICSC", 60*60*24*365) ; // 1 year in seconds

        private String name;
        private int ttl;

        Cookie(String name,int ttl){
            this.name = name;
            this.ttl = ttl;
        }

        public String getName() {
            return name;
        }

        public int getTtl() {
            return ttl;
        }
    }


}
