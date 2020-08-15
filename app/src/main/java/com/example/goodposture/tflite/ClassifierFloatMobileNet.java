/* Copyright 2019 The TensorFlow Authors. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
==============================================================================*/

package com.example.goodposture.tflite;

import android.app.Activity;

import org.tensorflow.lite.support.common.TensorOperator;
import org.tensorflow.lite.support.common.ops.NormalizeOp;

import java.io.IOException;

import static com.example.goodposture.Adapter.pageNum;

/** This TensorFlowLite classifier works with the float MobileNet model. */
public class ClassifierFloatMobileNet extends Classifier {

  /** Float MobileNet requires additional normalization of the used input. */
  private static final float IMAGE_MEAN = 127.5f;

  private static final float IMAGE_STD = 127.5f;

  /**
   * Float model does not need dequantization in the post-processing. Setting mean and std as 0.0f
   * and 1.0f, repectively, to bypass the normalization.
   */
  private static final float PROBABILITY_MEAN = 0.0f;

  private static final float PROBABILITY_STD = 1.0f;


  /**
   * Initializes a {@code ClassifierFloatMobileNet}.
   *
   * @param activity
   */
  public ClassifierFloatMobileNet(Activity activity, Device device, int numThreads)
      throws IOException {
    super(activity, device, numThreads);
  }

  @Override
  protected String getModelPath() {
    // you can download this file from
    // see build.gradle for where to obtain this file. It should be auto
    // downloaded into assets.

    String poseModel = null;

    if(pageNum == 0) {
      poseModel = "pose_1_model_unquant.tflite";
    } else if(pageNum == 1) {
      poseModel = "pose_2_model_unquant.tflite";
    } else if(pageNum == 2) {
      poseModel = "pose_3_model_unquant.tflite";
    } else if(pageNum == 3) {
      poseModel = "pose_4_model_unquant.tflite";
    }

    return poseModel;
  }

  @Override
  protected String getLabelPath() {

    String poseLables = null;

    if(pageNum == 0) {
      poseLables = "pose_1_labels.txt";
    } else if(pageNum == 1) {
      poseLables = "pose_2_labels.txt";
    } else if(pageNum == 2) {
      poseLables = "pose_3_labels.txt";
    } else if(pageNum == 3) {
      poseLables = "pose_4_labels.txt";
    }

    return poseLables;
  }

  @Override
  protected TensorOperator getPreprocessNormalizeOp() {
    return new NormalizeOp(IMAGE_MEAN, IMAGE_STD);
  }

  @Override
  protected TensorOperator getPostprocessNormalizeOp() {
    return new NormalizeOp(PROBABILITY_MEAN, PROBABILITY_STD);
  }
}
