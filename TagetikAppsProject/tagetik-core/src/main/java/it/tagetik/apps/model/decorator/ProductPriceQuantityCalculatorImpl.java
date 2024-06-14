package it.tagetik.apps.model.decorator;

 class ProductPriceQuantityCalculatorImpl implements ProductPriceQuantityCalculator {
     @Override
     public double compute(Integer quantity, Double price) {

         if(quantity == null){
             throw new IllegalStateException("Quantity could not be null");
         }

         if(price == null){
             throw new IllegalStateException("Price could not be null");

         }

         return quantity * price;
     }
 }
