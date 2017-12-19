package com.example.xhh.glideprojecttwo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

public class FristActivity extends AppCompatActivity {
    private Button button;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frist);
        imageView = (ImageView) findViewById(R.id.image_view);
//        默认的fit_center
        Toast.makeText(this, "默认的拉伸方式" + imageView.getScaleType(), Toast.LENGTH_SHORT).show();

    }

    public void loadImage(View view) {
        String url = "https://www.baidu.com/img/bd_logo1.png";
        Glide.with(FristActivity.this)
                .load(url)
//                .dontTransform()//取消Glide的自动对图片的拉伸等操作 使用这个方法会使加载的图片都不能使用Glide的拉伸效果
//                需要拉伸的时候可以指定  override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
//                .fitCenter()//是图片在水平方向平铺整个屏幕
//                .centerCrop()//对图片中心进行裁剪显示
                .transform(new CircleCrop(FristActivity.this))
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        Toast.makeText(FristActivity.this, "图片加载失败" + e, Toast.LENGTH_SHORT).show();
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        Toast.makeText(FristActivity.this, "图片加载成功", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                })
                .into(imageView);
    }
}
