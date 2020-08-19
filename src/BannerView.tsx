import React from 'react';
import { ImageStyle, requireNativeComponent, StyleProp } from 'react-native';

//RNBigImageViewer
const UI = requireNativeComponent('RNBigImageViewer');

type resizeMode =
  | 'center'
  | 'centerCrop'
  | 'centerInside'
  | 'fitCenter'
  | 'fitEnd'
  | 'fitStart'
  | 'fitXY'
  | 'start';

interface props {
  source: string;
  loading?: boolean;
  resizeMode?: resizeMode;
  style?: StyleProp<ImageStyle>;
  onCacheHit?: Function;
  onCacheMiss?: Function;
  onStart?: Function;
  onProgress?: Function;
  onFinish?: Function;
  onSuccess?: Function;
  onFail?: Function;
}

const BannerView = React.forwardRef((props: props, ref: any) => (
  <UI ref={ref} {...props} />
));

export default BannerView;
