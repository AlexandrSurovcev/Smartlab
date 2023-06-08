package com.example.smartlab;

import android.widget.ImageView;

public class imageNumber {

    Boolean number1 = false,number2= false, number3= false,number4= false;
    public void getImg(ImageView img1, ImageView img2, ImageView img3, ImageView img4){

        if(!number1){
            img1.setImageResource(R.drawable.ellipse_57);
            number1 = true;
        } else if(number1 && !number2){
            img2.setImageResource(R.drawable.ellipse_57);
            number2 = true;
        } else if (number2 && !number3) {
            img3.setImageResource(R.drawable.ellipse_57);
            number3 = true;
        } else if (number3 && !number4) {
                img4.setImageResource(R.drawable.ellipse_57);
                number4 = true;

        }
    }
    public void setImg(ImageView img1, ImageView img2, ImageView img3, ImageView img4){

        if(number4){
            img4.setImageResource(R.drawable.ellipse_58);
            number4 = false;
        } else if(!number4 && number3){
            img3.setImageResource(R.drawable.ellipse_58);
            number3 = false;
        } else if (!number3 && number2) {
            img2.setImageResource(R.drawable.ellipse_58);
            number2 = false;
        } else if (!number2 && number1) {
                img1.setImageResource(R.drawable.ellipse_58);
            number1 = false;
        }
    }
}
