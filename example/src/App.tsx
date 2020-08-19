import * as React from 'react';
import { StyleSheet, View } from 'react-native';
import BannerView from 'react-native-bigimageviewer';

export default function App() {
  return (
    <View style={styles.container}>
      <BannerView
        style={styles.image}
        source={
          'https://4youscan.xyz/wp-content/uploads/2020/08/005-2537-900x1280.jpg'
        }
        resizeMode={'fitCenter'}
        loading={true}
      />
    </View>
  );
}

const styles = StyleSheet.create({
  image: {
    height: '100%',
    width: '100%',
  },
  container: {
    flex: 1,
  },
});
