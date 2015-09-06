package jp.wasabeef.picasso.transformations.gpu;

/**
 * Copyright (C) 2015 Wasabeef
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PointF;
import com.squareup.picasso.Transformation;
import jp.co.cyberagent.android.gpuimage.GPUImage;
import jp.co.cyberagent.android.gpuimage.GPUImageSwirlFilter;

public class SwirlFilterTransformation implements Transformation {

  private Context mContext;

  private GPUImageSwirlFilter mFilter = new GPUImageSwirlFilter();
  private float mRadius;
  private float mAngle;
  private PointF mCenter;

  public SwirlFilterTransformation(Context context) {
    mContext = context;
    mCenter = new PointF();
  }

  /**
   * @param radius from 0.0 to 1.0, default 0.5
   * @param angle minimum 0.0, default 1.0
   * @param center default (0.5, 0.5)
   */
  public SwirlFilterTransformation(Context context, float radius, float angle, PointF center) {
    mContext = context;
    mRadius = radius;
    mAngle = angle;
    mCenter = center;
    mFilter.setRadius(mRadius);
    mFilter.setAngle(mAngle);
    mFilter.setCenter(mCenter);
  }

  @Override public Bitmap transform(Bitmap source) {

    GPUImage gpuImage = new GPUImage(mContext);
    gpuImage.setImage(source);
    gpuImage.setFilter(mFilter);
    Bitmap bitmap = gpuImage.getBitmapWithFilterApplied();

    source.recycle();

    return bitmap;
  }

  @Override public String key() {
    return "SwirlFilterTransformation(radius=" + mRadius +
        ",angle=" + mAngle + ",center=" + mCenter.toString() + ")";
  }
}
