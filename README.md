# react-native-bigimageviewer

react native Big image viewer supporting pan and zoom, with very little memory usage and full
             featured image loading choices. Powered by
             [Glide](https://github.com/bumptech/glide)

## Installation

```sh
yarn add react-native-bigimageviewer
```

```sh
allprojects {
    repositories {
       maven {
         url  "http://dl.bintray.com/piasy/maven"
       }
       ...
    }
}
```
Note: please put this download url at the first of your repositories part, otherwise, gradle may search in wrong place.

## Demo

| | | |
|:-------------------------:|:-------------------------:|:-------------------------:|
|<img width="1604" alt="1" src="https://i.ibb.co/bd8zMpq/1.png">  |  <img width="1604" alt="2" src="https://i.ibb.co/Sv8Qkbv/2.png">|<img width="1604" alt="3" src="https://i.ibb.co/mJJgYB2/3.png">|

## Usage

```js
import BannerView from 'react-native-bigimageviewer';
// ...

<BannerView
        {...imageProps}
        style={styles.image}
        source={
          'https://4youscan.xyz/wp-content/uploads/2020/08/005-2537-900x1280.jpg'
        }
        resizeMode={'fitCenter'}
        loading={true}

/>
```

## props

```typescript

type resizeMode =
  | 'center'
  | 'centerCrop'
  | 'centerInside'
  | 'fitCenter'
  | 'fitEnd'
  | 'fitStart'
  | 'fitXY'
  | 'start';

interface imageProps {
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

```

## Contributing

See the [contributing guide](CONTRIBUTING.md) to learn how to contribute to the repository and the development workflow.

## License
MIT
