package com.yuan.androidwechatphoto.imgloader;

import java.util.LinkedList;
import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.yuan.androidwechatphoto.R;
import com.yuan.androidwechatphoto.utils.CommonAdapter;
import com.yuan.androidwechatphoto.utils.ViewHolder;


public class PhotoesAdapter extends CommonAdapter<String> {

    /**
     * 用户选择的图片，存储为图片的完整路径
     */
    public static List<String> mSelectedImage = new LinkedList<>();

    /**
     * 文件夹路径
     */
    private String mDirPath;

    public PhotoesAdapter(Context context, List<String> mDatas, int itemLayoutId,
                          String dirPath) {
        super(context, mDatas, itemLayoutId);
        this.mDirPath = dirPath;
    }

    @Override
    public void convert(final ViewHolder holder, final String item) {
        //设置no_pic
        holder.setImageResource(R.id.id_item_image, R.mipmap.pictures_no);
        //设置no_selected
        holder.setImageResource(R.id.id_item_select,
                R.mipmap.picture_unselected);
        //设置图片
        holder.setImageByUrl(R.id.id_item_image, mDirPath + "/" + item);

        final ImageView mImageView = holder.getView(R.id.id_item_image);
        final ImageView mSelect = holder.getView(R.id.id_item_select);

        mImageView.setColorFilter(null);
        //设置ImageView的点击事件
        mImageView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

            }
        });

        mSelect.setOnClickListener(new OnClickListener() {
            //选择，则将图片变暗，反之则反之
            @Override
            public void onClick(View v) {

                // 已经选择过该图片
                if (mSelectedImage.contains(mDirPath + "/" + item)) {
                    mSelectedImage.remove(mDirPath + "/" + item);
                    mSelect.setImageResource(R.mipmap.picture_unselected);
                    mImageView.setColorFilter(null);
                } else
                // 未选择该图片
                {
                    mSelectedImage.add(mDirPath + "/" + item);
                    mSelect.setImageResource(R.mipmap.pictures_selected);
                    mImageView.setColorFilter(Color.parseColor("#77000000"));
                }

            }
        });

        /**
         * 已经选择过的图片，显示出选择过的效果
         */
        if (mSelectedImage.contains(mDirPath + "/" + item)) {
            mSelect.setImageResource(R.mipmap.pictures_selected);
            mImageView.setColorFilter(Color.parseColor("#77000000"));
        }

    }
}
