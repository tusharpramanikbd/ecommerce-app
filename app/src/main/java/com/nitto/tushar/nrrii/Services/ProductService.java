package com.nitto.tushar.nrrii.Services;



import com.nitto.tushar.nrrii.Entity.Dress;
import com.nitto.tushar.nrrii.Entity.ProductItem;
import com.nitto.tushar.nrrii.R;

import java.util.ArrayList;
import java.util.List;

public class ProductService {
    private ProductItem productItem;
    private ArrayList<Dress> dressItems;
    private Dress dress;

    private static ProductService productService = new ProductService();

    public static ProductService getInstance() {
        if(productService==null){
            Class clazz = ProductService.class;
            synchronized (clazz){
                productService = new ProductService();
            }
        }

        return productService;
    }

    // Constructor
    private ProductService(){
//        // all class related initialization goes here
//        this.orderItemsList = new ArrayList<>();
    }

    public void InitializeProductService() {
        this.productItem = null;
    }

    public void insertProductItem(ProductItem productItem) {
        this.productItem = productItem;
    }

    public ProductItem getInsertedProductItem(){
        return this.productItem;
    }

    public ArrayList<Dress> getAllDress(){
        this.dressItems = new ArrayList<>();
        this.dressItems.add(new Dress("1", R.mipmap.dress40, new int[]{R.mipmap.dress41,R.mipmap.dress42,R.mipmap.dress43,R.mipmap.dress44}, "2950"));
        this.dressItems.add(new Dress("2", R.mipmap.dress50, new int[]{R.mipmap.dress51,R.mipmap.dress52,R.mipmap.dress53,R.mipmap.dress54}, "3450"));
        this.dressItems.add(new Dress("3", R.mipmap.dress60, new int[]{R.mipmap.dress61,R.mipmap.dress62,R.mipmap.dress63,R.mipmap.dress64,R.mipmap.dress65},"2250"));
        this.dressItems.add(new Dress("4", R.mipmap.dress70, new int[]{R.mipmap.dress71,R.mipmap.dress72,R.mipmap.dress73,R.mipmap.dress74,R.mipmap.dress75,R.mipmap.dress76},"3550"));
        this.dressItems.add(new Dress("5", R.mipmap.dress80, new int[]{R.mipmap.dress81,R.mipmap.dress82},"2950"));

        return this.dressItems;
    }

    public void setDressForDetails(String dressId){
        this.dress = new Dress();
        for (int i=0; i<this.dressItems.size(); i++){
            if(this.dressItems.get(i).getDressId().equals(dressId)){
                this.dress.setDressId(this.dressItems.get(i).getDressId());
                this.dress.setActualPrice(this.dressItems.get(i).getActualPrice());
                this.dress.setPromotionalPrice(this.dressItems.get(i).getPromotionalPrice());
                this.dress.setDressCoverPhoto(this.dressItems.get(i).getDressCoverPhoto());
                this.dress.setDressImages(this.dressItems.get(i).getDressImages());

            }
        }
    }

    public Dress getDressDetails(){
        return this.dress;
    }

}
