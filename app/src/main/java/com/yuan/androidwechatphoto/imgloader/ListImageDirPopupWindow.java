package com.yuan.androidwechatphoto.imgloader;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.yuan.androidwechatphoto.R;
import com.yuan.androidwechatphoto.entity.ImageFloder;
import com.yuan.androidwechatphoto.utils.BasePopupWindowForListView;
import com.yuan.androidwechatphoto.utils.CommonAdapter;
import com.yuan.androidwechatphoto.utils.ViewHolder;


public class ListImageDirPopupWindow extends BasePopupWindowForListView<ImageFloder> {
    private ListView mListDir;
    private FolderAdapter adapter;
    private int position_folder_current = 0;

    public ListImageDirPopupWindow(int width, int height,
                                   List<ImageFloder> datas, View convertView) {
        super(convertView, width, height, true, datas);
    }

    @Override
    public void initViews() {
        mListDir = (ListView) findViewById(R.id.id_list_dir);
        mListDir.setAdapter(adapter = new FolderAdapter(context, mDatas,
                R.layout.list_dir_item));
    }

    public interface OnImageDirSelected {
        void selected(ImageFloder floder);
    }

    private OnImageDirSelected mImageDirSelected;

    public void setOnImageDirSelected(OnImageDirSelected mImageDirSelected) {
        this.mImageDirSelected = mImageDirSelected;
    }

    @Override
    public void initEvents() {
        mListDir.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                if (mImageDirSelected != null) {
                    mImageDirSelected.selected(mDatas.get(position));
                }

                adapter.updateView(position);
                position_folder_current = position;
            }
        });
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }

    @Override
    public void init() {

    }

    @Override
    protected void beforeInitWeNeedSomeParams(Object... params) {

    }

    @Override
    public void showAsDropDown(View anchor, int xoff, int yoff) {
        super.showAsDropDown(anchor, xoff, yoff);
        adapter.notifyDataSetChanged();
    }

    private class FolderAdapter extends CommonAdapter<ImageFloder> {


        public FolderAdapter(Context context, List<ImageFloder> mDatas, int itemLayoutId) {
            super(context, mDatas, itemLayoutId);
        }

        @Override
        public void convert(ViewHolder holder, ImageFloder item) {
            holder.setText(R.id.id_dir_item_name, item.getName());
            holder.setImageByUrl(R.id.id_dir_item_image,
                    item.getFirstImagePath());
            holder.setText(R.id.id_dir_item_count, item.getCount() + " 张");
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final ViewHolder viewHolder = getViewHolder(position, convertView,
                    parent);
            convert(viewHolder, getItem(position));
            if (position_folder_current == position) {
                viewHolder.getView(R.id.dir_item_selector).setVisibility(View.VISIBLE);
            } else {
                viewHolder.getView(R.id.dir_item_selector).setVisibility(View.INVISIBLE);
            }

            return viewHolder.getConvertView();
        }

        public void updateView(int itemIndex) {
            if(position_folder_current == itemIndex){
                return;
            }
            int visiblePosition = mListDir.getFirstVisiblePosition();
            if (itemIndex - visiblePosition >= 0) {

                View view = mListDir.getChildAt(itemIndex - visiblePosition);
                ViewHolder holder = (ViewHolder) view.getTag();
                holder.getView(R.id.dir_item_selector).setVisibility(View.VISIBLE);

            }
            if(position_folder_current - visiblePosition >= 0) {
                View view = mListDir.getChildAt(position_folder_current - visiblePosition);
                if(view != null){
                    ViewHolder holder = (ViewHolder) view.getTag();
                    holder.getView(R.id.dir_item_selector).setVisibility(View.INVISIBLE);
                }
            }
        }
    }
}
